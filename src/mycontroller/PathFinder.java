package mycontroller;


import utilities.Coordinate;
//this is a class which is used to find a path from current coordinate to a known parcel
public class PathFinder {
	private ExploredMap exploredmap;
	private Coordinate parcelPosition;
	
	public PathFinder (ExploredMap exploredmap,  Coordinate parcelPosition) {
		this.exploredmap = exploredmap;
		this.parcelPosition = parcelPosition;
		
	}
//return the parcel Node if there is valid path from start point to parcel
//the path found is only based on tiles that have been explored
	public Node PathSearch() {
		Node root = new Node (this.exploredmap.getCarPosition());
		Tree tree = new Tree (root, this.exploredmap);
		return tree.searchTree(this.parcelPosition);
	}
	
	public Coordinate getParcelPosition() {
		return this.parcelPosition;
	}
	public boolean equals(Object c)
	{
		if(c instanceof PathFinder)
		{
			PathFinder pf=(PathFinder) c;
			return parcelPosition.equals(pf.getParcelPosition());
		}
		else {
			return false;
		}
	}
}
