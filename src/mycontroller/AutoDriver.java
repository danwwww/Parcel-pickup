package mycontroller;

import utilities.Coordinate;
import world.WorldSpatial;
import world.WorldSpatial.Direction;



//this class is used to move car to a specific coordinate.
public class AutoDriver {
	
	
	private MyAutoController controller;
	private Direction goDirection;
	
	public AutoDriver(MyAutoController controller) {
		this.controller = controller;
		this.goDirection = controller.getOrientation();
	}
	
	
	// move to a nearby coordinate
	public void moveTo(Coordinate destination) {
		
		Coordinate currentpos = new Coordinate(controller.getPosition());
		
		
		int xDelta = destination.x - currentpos.x;
		int yDelta = destination.y - currentpos.y;
		
        if (xDelta == 1) {
        	setGoDirection(Direction.EAST);
        }
        else if (xDelta == -1) {
        	setGoDirection(Direction.WEST);
        }
        if (yDelta == 1) {
        	setGoDirection(Direction.NORTH);
        }
        else if (yDelta == -1) {
        	setGoDirection(Direction.SOUTH);
        } 
        else if (xDelta == 0 && yDelta == 0) {
        	
        	controller.applyBrake();
        	return;
        }
        
        
        
        float carDirectionDegree = WorldSpatial.rotation(controller.getOrientation());
        float carGoDirectionDegree = WorldSpatial.rotation(getGoDirection());
        float turnDegree = carGoDirectionDegree - carDirectionDegree;
        
        if (Math.abs(turnDegree) == 180f) {
        	controller.applyReverseAcceleration();
        }
        else if (turnDegree == 90f || turnDegree == -270f) {
        	controller.turnLeft();
        	if(this.controller.getSpeed()==0)
        	{
        	controller.applyForwardAcceleration(); 
        	}
        }
        else if (turnDegree == -90f || turnDegree == 270f) {
        	controller.turnRight();
        	if(this.controller.getSpeed()==0)
        	{
        	controller.applyForwardAcceleration(); 
        	}
        }
        else {
        	controller.applyForwardAcceleration();
        }
		       
		
	}

	
	public Direction getGoDirection() {
		return goDirection;
	}

	public void setGoDirection(Direction goDirection) {
		this.goDirection = goDirection;
	}
	
	
	
	
}
