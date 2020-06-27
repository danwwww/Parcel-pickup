package mycontroller;
import java.util.ArrayList;
import utilities.Coordinate;
//this is a stack class of paces the car has moved through 
public class Pace {
	public ArrayList<Coordinate> coordinates;
	public Pace(ArrayList<Coordinate> coordinates)
	{
		this.coordinates=coordinates;
	}
	public Pace()
	{
		coordinates= new ArrayList<Coordinate>();
	}
	public void push(Coordinate coordinate)
	{
		coordinates.add(coordinate);
	}
	public Coordinate pop()
	{
			int length=coordinates.size();
			coordinates.remove(length-1);
			length=coordinates.size();
			return coordinates.get(length-1);
	}
	public boolean isempty()
	{
		return coordinates.isEmpty();
	}
	//get last but one position
	public Coordinate getLastPosition()
	{
		if(coordinates.get(coordinates.size()-2) != null)
		{
		return coordinates.get(coordinates.size()-2);
		}
		else 
			return null;
	}
	//update the pace stack after moving
	public void update(String position)
	{
		Coordinate coordinate = new Coordinate (position);
		
		if (coordinate.equals(coordinates.get(coordinates.size()-1)))
		{
			;
		}
		else if(coordinates.size()>1&&coordinate.equals(coordinates.get(coordinates.size()-2)))
		{
			pop();
		}
		else
		{
			push(coordinate);
		}
			
	}
}
