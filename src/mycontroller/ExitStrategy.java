package mycontroller;

import java.util.ArrayList;

import utilities.Coordinate;
//this strategy is used when the car finds enough parcels and can find a path to exit.
public class ExitStrategy implements Strategy{
	
	private ExploredMap exploredMap;
	private ArrayList<Coordinate> path;
	
	public ExitStrategy(ExploredMap map) {
		this.exploredMap = map;
		this.path = new ArrayList<Coordinate>();
		buildpath();
	}
	
	public void buildpath() {
    	Node node = null;
		if (this.exploredMap.
				getExitPathFinder()
				.PathSearch() != null) {
			node = this.exploredMap.getExitPathFinder().PathSearch();
		}
		
    	while(node != null) {
    		path.add(node.getCoordinate());
    		node = node.getParents();
    	}
    	path.remove(path.size()-1);
	}
	
	public Coordinate nextStep(Pace p) {
		int len = this.path.size();
		Coordinate nextstep = path.get(len-1);
		if(len==1)
		{
			this.exploredMap.exitFound();
		}
		path.remove(len-1);
		return nextstep;
	}

}
