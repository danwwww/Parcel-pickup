package mycontroller;

import java.util.ArrayList;

import utilities.Coordinate;
//this class is a strategy when car is going to pick a parcel
//only used when there is at least one valid path from current position to a known parcel
public class PickParcelStrategy implements Strategy{
	
	private ExploredMap exploredMap;
	private ArrayList<Coordinate> path;
	
	public PickParcelStrategy(ExploredMap map) {
		path=new ArrayList<Coordinate>();
		this.exploredMap = map;
		buildpath();
	}
//generate the path	from start point to parcel
    public void buildpath() {
    	Node node = null;
    	for (PathFinder pf :this.exploredMap.getParcelPathFinder()) {
			if (pf.PathSearch() != null) {
				node = pf.PathSearch();
				break;
			}
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
			this.exploredMap.parcelFound(nextstep);
			
		}
		path.remove(len-1);
		
		return nextstep;
	}

}
