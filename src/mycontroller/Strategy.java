package mycontroller;
import utilities.Coordinate;

public interface Strategy {
	//this function will give next step for the car to move
	public Coordinate nextStep(Pace p);

}
