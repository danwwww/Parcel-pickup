package mycontroller;

import java.util.ArrayList;
import java.util.HashMap;
import tiles.MapTile;
import controller.CarController;
import utilities.Coordinate;
//this class is used to record Tiles which have been found.
public class ExploredMap {
	HashMap<Coordinate, MapTile> exploredMap;
	MyAutoController controller;
	//generate one position data and pathFinder for each parcel,and similar to exit.
	private ArrayList<Coordinate> parcelPosition;
	private ArrayList<PathFinder> parcelPathFinder;
	private Coordinate exit;
	private PathFinder exitPathFinder;
	public ExploredMap(MyAutoController controller)
	{
		this.controller=controller;
		this.exploredMap=new HashMap<Coordinate, MapTile> ();
		parcelPosition=new ArrayList<Coordinate>();
		parcelPathFinder=new ArrayList<PathFinder>();
		exit=null;
		exitPathFinder=null;
		intializeMap();
	}
	public void intializeMap()
	{
		for (int x=-3;x<controller.mapWidth()+3;x++){
			for(int y=-3;y<controller.mapHeight()+3;y++)
			{
				exploredMap.put(new Coordinate(x,y), null);
			}
		}
	}
	// get current car position
	public Coordinate getCarPosition()
	{
		return new Coordinate(this.controller.getPosition());
	}
	
	//remove data of a parcel when it is reached
	public void parcelFound(Coordinate coordinate) {
		this.parcelPosition.remove(coordinate);
		for (int i=0;i<=parcelPathFinder.size()-1;i++) {
			PathFinder pf=parcelPathFinder.get(i);
			if (pf.getParcelPosition().equals(coordinate)) {
				this.parcelPathFinder.remove(pf);
				break;
			}
		}
	}
	//remove data of an exit when it is reached
	public void exitFound()
	{
		exit=null;
		exitPathFinder=null;
	}
	public HashMap<Coordinate, MapTile> getExploredMap()
	{
		return exploredMap;
	}
	public ArrayList<PathFinder> getParcelPathFinder()
	{
		return parcelPathFinder;
	}
	public Coordinate getExit() {
		  return this.exit;
	}
	public PathFinder getExitPathFinder() {
		  return this.exitPathFinder;
	}
	public boolean isParcelReachable() {
		for (PathFinder pf : this.parcelPathFinder) {
			if (pf.PathSearch() != null) {
				return true;
			}
		}
		return false;
	}
	//check a coordinate has been explored and can be moved through 
	public boolean checkPassable(Coordinate coordinate)
	{
		if(exploredMap.get(coordinate)!=null&&(!exploredMap.get(coordinate).isType(MapTile.Type.WALL)))
		{
				return true;
		}
		else
		{
			return false;
		}
	}
	//return all passable coordinates near a coordinate
	public ArrayList<Node> nextPace(Coordinate position)
	{
		ArrayList<Node> nextPaces=new ArrayList<Node>();
		Coordinate next = new Coordinate(position.x-1,position.y);
		if(checkPassable(next))
		{
			nextPaces.add(new Node(next));
		}
		next=new Coordinate(position.x+1,position.y);
		if(checkPassable(next))
		{
			nextPaces.add(new Node(next));
		}
		next=new Coordinate(position.x,position.y+1);
		if(checkPassable(next))
		{
			nextPaces.add(new Node(next));
		}
		next=new Coordinate(position.x,position.y-1);
		if(checkPassable(next))
		{
			nextPaces.add(new Node(next));
		}
		return nextPaces;
	}

	public CarController getController() {
		return this.controller;
	}

	//update the exploredMap data with new known tiles
	public void addSubExploredMap()
	{
		HashMap<Coordinate, MapTile> subMap=this.controller.getView();
		//generate data for parcels and exit if they are found first time
		for (Coordinate p : subMap.keySet()) {
			if (this.exploredMap.get(p) == null && subMap.get(p).isType(MapTile.Type.TRAP ) && !this.parcelPosition.contains(p)) {
				this.parcelPosition.add(p);
				this.parcelPathFinder.add(new PathFinder(this, p));
			}
			if (this.exploredMap.get(p) == null && subMap.get(p).isType(MapTile.Type.FINISH )) {
			    this.exit = p;
			    this.exitPathFinder = new PathFinder(this, this.getExit());
			   }
		}

		exploredMap.putAll(subMap);
	}
	//check whether a coordinate is in the range of the Map
	public boolean outofbound(int x,int y)
	{
		if(x<0||y<0||x>controller.mapWidth()||y>controller.mapHeight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//return number of unknown tiles in 9x9 square whose center is Coordinate(currentX,currentY)
	public int checkInvisibility(int currentX,int currentY)
	{
		int unknownArea=0;
		final int VIEW_SQUARE=4;
		for(int x = currentX - VIEW_SQUARE; x <= currentX+VIEW_SQUARE; x++){
			for(int y = currentY - VIEW_SQUARE; y <= currentY+VIEW_SQUARE; y++){
					if(exploredMap.get(new Coordinate(x,y))==null)
					{
						unknownArea++;
					}
			}
		}
		return unknownArea;
	}
}
