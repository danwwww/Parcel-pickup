package mycontroller;

import java.util.ArrayList;

import utilities.Coordinate;
//Node structure, whose attributes are coordinate and relevant nodes
public class Node {
	Node parents;
	ArrayList<Node> children;
	Coordinate coordinate;
	
	Node(Coordinate coordinate) {
		this.coordinate=coordinate;
		this.children = new ArrayList<Node> ();
	}
	//set this as parent of a node
	public void addchild(Node node) {
		node.setParents(this);
		children.add(node);		
	}
	
	public int numOfChildren() {
		return children.size();
	}
	//set a parent for this node 
	public void setParents(Node parents) {
		this.parents = parents;
	}
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	public Node getParents() {
		return parents;
	}
	
	public boolean equals(Object c)
	{
		if(c instanceof Node)
		{
			Node node=(Node) c;
			return coordinate.equals(node.getCoordinate());
		}
		else {
			return false;
		}
	}
	public String toString()
	{
		return coordinate.toString();
	}
}
