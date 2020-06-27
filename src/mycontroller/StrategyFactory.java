package mycontroller;

//this class is a factory to generate strategy
public class StrategyFactory {
	private static StrategyFactory instance;
	public static synchronized StrategyFactory getInstance() {
		if (instance == null) {
			return new StrategyFactory();
		} else {
			return instance;
		}
	}
	//select a strategy according to some conditions
	public Strategy getStrategy(ExploredMap exploredMap)
	{
		if((exploredMap.getController().numParcelsFound()>=exploredMap.getController().numParcels())
				&&exploredMap.getExitPathFinder()!=null&&
				exploredMap.getExitPathFinder().PathSearch()!=null)
		{
			return new ExitStrategy(exploredMap);
		}
		else if(!exploredMap.getParcelPathFinder().isEmpty()&&exploredMap.isParcelReachable())
		{

			return new PickParcelStrategy(exploredMap);
		}
		else
		{
			return new ExploreStrategy(exploredMap);
		}
	}
}
