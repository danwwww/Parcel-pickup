package mycontroller;

import tiles.MapTile;
import utilities.Coordinate;
//This class is a strategy that the car uses when exploring map 
public class ExploreStrategy implements Strategy {
	
	private ExploredMap exploredMap;
	public ExploreStrategy(ExploredMap map) {
		this.exploredMap = map;
	}
	//get next step
	public Coordinate nextStep(Pace p)
	{
		int CurrentX = this.exploredMap.getCarPosition().x;
		int CurrentY = this.exploredMap.getCarPosition().y;
		int maxview=0;
		Coordinate nextStep=null;
		int x=CurrentX;
		int y=CurrentY-1;
		//choose a nearby coordinate which can reveal most numbers of unknown tiles
		if(!this.exploredMap.outofbound(x,y)&&!this.exploredMap.getExploredMap().get(new Coordinate(x,y)).isType(MapTile.Type.WALL))
		{
			if(maxview < this.exploredMap.checkInvisibility(x,y))
			{
			maxview = this.exploredMap.checkInvisibility(x,y);
			nextStep = new Coordinate(x,y);
			}
		}
		y=y+2;
		if(!this.exploredMap.outofbound(x,y)&&!this.exploredMap.getExploredMap().get(new Coordinate(x,y)).isType(MapTile.Type.WALL))
		{
			if(maxview < this.exploredMap.checkInvisibility(x,y))
			{
			maxview = this.exploredMap.checkInvisibility(x,y);
			nextStep = new Coordinate(x,y);
			}
		}
		x--;
		y=CurrentY;
		if(!this.exploredMap.outofbound(x,y)&&!this.exploredMap.getExploredMap().get(new Coordinate(x,y)).isType(MapTile.Type.WALL))
		{
			if(maxview < this.exploredMap.checkInvisibility(x,y))
			{
			maxview = this.exploredMap.checkInvisibility(x,y);
			nextStep = new Coordinate(x,y);
			}
		}
		x=x+2;
		if(!this.exploredMap.outofbound(x,y)&&!this.exploredMap.getExploredMap().get(new Coordinate(x,y)).isType(MapTile.Type.WALL))
		{
			if(maxview < this.exploredMap.checkInvisibility(x,y))
			{
			maxview = this.exploredMap.checkInvisibility(x,y);
			nextStep = new Coordinate(x,y);
			}
		}
		if(nextStep!=null)
		{
		return nextStep;
		}
		//when moving to all nearby coordinate can not produce more known tiles, go back to last step.
		else
		{
			return p.getLastPosition();
		}
	}

}
