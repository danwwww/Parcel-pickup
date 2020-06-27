package mycontroller;

import controller.CarController;
import world.Car;

import utilities.Coordinate;
import mycontroller.Pace;
import mycontroller.AutoDriver;
import mycontroller.ExploredMap;

public class MyAutoController extends CarController{		
		
		private Pace paces;
		private AutoDriver driver;
		private ExploredMap exploredMap=new ExploredMap(this);				
		public MyAutoController(Car car) {
			super(car);
			exploredMap.addSubExploredMap();
			this.paces = new Pace();
			paces.push(new Coordinate(this.getPosition()));
			this.driver =  new AutoDriver(this);
		}
		
		public void update() {
			//use strategyFactory to create a strategy to find next step, update relevant classes.
			Strategy strategy=StrategyFactory.getInstance().getStrategy(exploredMap);
			paces.update(this.getPosition());
			Coordinate nextPosition = strategy.nextStep(paces);
			this.driver.moveTo(nextPosition);
			exploredMap.addSubExploredMap();
		}
		
		
	}
