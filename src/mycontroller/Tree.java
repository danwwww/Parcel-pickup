package mycontroller;

import java.util.ArrayList;

import utilities.Coordinate;
//this class is a tree used to find a path from a start point to destination
//it will generate all paths with out repeated coordinates from a start point
//each node in this tree is reachable in revealed map.
public class Tree {
 
	private ExploredMap map;
	private ArrayList<Node> Nodes = new ArrayList<Node>();
	private ArrayList<Node> leaves= new ArrayList<Node>();
 
	public Tree( Node root, ExploredMap Map) {
		this.map = Map;
		this.Nodes.add(root);
		this.leaves.add(root);
		while(leaves.size() != 0 ) {
			ArrayList<Node> nextlevel= new ArrayList<Node>();
			for (Node node : leaves) {
				for (Node nextnode: map.nextPace(node.getCoordinate())) {
					if (isinTree(nextnode)) {
      
					}else {
						node.addchild(nextnode);
						nextlevel.add(nextnode);
						Nodes.add(nextnode);
					}
				}
			}
			leaves = nextlevel;
		}
	}
	public boolean isinTree(Node node)
	{
		for(Node leave:Nodes)
		{
			if (leave.equals(node))
			{
				return true;
			}

		}
		return false;
	}
	
	public Node searchTree(Coordinate coordinate){
		Node des = new Node(coordinate);
		for(Node node:Nodes)
		{
			if(node.equals(des))
			{
				return node;
			}
		}
		return null;
		
	}
	public String toString()
	{
		String s="0";
		for(Node node:Nodes)
		{
			s+=" "+node.toString();
		}
		return s;
	}
}