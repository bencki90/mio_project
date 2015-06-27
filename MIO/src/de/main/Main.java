package de.main;

import java.util.HashMap;
import java.util.Random;

import pso_1.PSODimension;
import pso_1.Swarm;
import de.algorithm.DEAlgorithm;
import functionParsing.ShuntingYard;
import functionParsing.RPNEvaluator;

public class Main {

	public static void main(String[] args) {
		
		RPNEvaluator.initDecimalformat();
		String bird = "sin ( x1 ) * e ^ ( 1 - cos ( x2 ) ) ^ 2 + cos ( x2 ) * e ^ ( 1 - sin ( x1 ) ) ^ 2 + ( x1 - x2 ) ^ 2";
		String birdRPN = ShuntingYard.infixToPostfix(bird);
				

		HashMap<String, Dimension> birdDimensions = new HashMap<String, Dimension>();
		birdDimensions.put("x1", new Dimension(-2 * Math.PI, 2 * Math.PI));
		birdDimensions.put("x2", new Dimension(-2 * Math.PI, 2 * Math.PI));
		
		try{
			DEAlgorithm de = new DEAlgorithm(birdRPN, 50, birdDimensions, Operation.Minimize, 0.5, 0.1);
			
			for(int i = 0; i < 100; i++){
				de.makeEvolution();
			}
			
			de.printResults();
			
			HashMap<String, Double> b = de.bestPosition();
			System.out.println("Bird: Best value: " + de.bestValue());
			for(String key : b.keySet()){
				System.out.print(b.get(key) + " ");
			}
		}
		catch(Exception ex) { }
		
		
		/*try {
			PSO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	public static void PSO() throws Exception{
		//McCormick function
		/*HashMap<String, Dimension> mcCormickDimensions = new HashMap<String, Dimension>();
		mcCormickDimensions.put("x", new Dimension(-1.5, 4));
		mcCormickDimensions.put("y", new Dimension(-3, 4));
		Swarm mcCormickSwarm = new McCormickSwarm(15, mcCormickDimensions, Swarm.Operation.Minimize, 2.0, 2.0);

		//Modified  Schaffer  function  #3
		HashMap<String, Dimension> schafferDimensions = new HashMap<String, Dimension>();
		schafferDimensions.put("x1", new Dimension(-100, 100));
		schafferDimensions.put("x2", new Dimension(-100, 100));
		Swarm schafferSwarm = new SchafferSwarm(1000, schafferDimensions, Swarm.Operation.Minimize, 2.0, 2.0);

		//Bird function:
		HashMap<String, Dimension> birdDimensions = new HashMap<String, Dimension>();
		birdDimensions.put("x1", new Dimension(-2 * Math.PI, 2 * Math.PI));
		birdDimensions.put("x2", new Dimension(-2 * Math.PI, 2 * Math.PI));
		Swarm birdSwarm = new BirdSwarm(30, birdDimensions, Swarm.Operation.Minimize, 2.0, 2.0);


		for(int i = 0; i < 30; ++i){
			mcCormickSwarm.makeIteration();
		}
		for(int i = 0; i < 500; ++i){
			schafferSwarm.makeIteration();
		}
		for(int i = 0; i < 50; ++i){
			birdSwarm.makeIteration();
		}
		

		HashMap<String, Double> bestPoints;
		System.out.println("McCormick: Best value: " + mcCormickSwarm.getBestKnownValue());
		bestPoints = mcCormickSwarm.getBestKnownPosition();
		for(String key : bestPoints.keySet()){
			System.out.format(key + ": %f.6\n", bestPoints.get(key));
		}
		System.out.println("Schaffer: Best value: " + schafferSwarm.getBestKnownValue());
		bestPoints = schafferSwarm.getBestKnownPosition();
		for(String key : bestPoints.keySet()){
			System.out.format(key + ": %f.6\n", bestPoints.get(key));
		}
		System.out.println("Bird: Best value: " + birdSwarm.getBestKnownValue());
		bestPoints = birdSwarm.getBestKnownPosition();
		for(String key : bestPoints.keySet()){
			System.out.format(key + ": %f.6\n", bestPoints.get(key));
		}*/

		RPNEvaluator.initDecimalformat();
		String bird = "sin ( x1 ) * e ^ ( 1 - cos ( x2 ) ) ^ 2 + cos ( x2 ) * e ^ ( 1 - sin ( x1 ) ) ^ 2 + ( x1 - x2 ) ^ 2";
		String birdRPN = ShuntingYard.infixToPostfix(bird);
		
		
		//Bird function:
		HashMap<String, PSODimension> birdDimensions = new HashMap<String, PSODimension>();
		birdDimensions.put("x1", new PSODimension(-2 * Math.PI, 2 * Math.PI));
		birdDimensions.put("x2", new PSODimension(-2 * Math.PI, 2 * Math.PI));
		Swarm birdSwarm = new Swarm(birdRPN, 50, birdDimensions, Operation.Minimize, 2.0, 2.0);
		
		for(int i = 0; i < 50; ++i){
			birdSwarm.makeIteration();
			
		}
		
		System.out.println("Bird: Best value: " + birdSwarm.getBestKnownValue());
		HashMap<String, Double> bestPoints = birdSwarm.getBestKnownPosition();
		for(String key : bestPoints.keySet()){
			System.out.format(key + ": %f.6\n", bestPoints.get(key));
		}
		
		//Bird function: (old way)
		/*HashMap<String, Dimension> birdDimensions = new HashMap<String, Dimension>();
		birdDimensions.put("x1", new Dimension(-2 * Math.PI, 2 * Math.PI));
		birdDimensions.put("x2", new Dimension(-2 * Math.PI, 2 * Math.PI));*/
		/*Swarm birdSwarmOld = new BirdSwarm(30, birdDimensions, Swarm.Operation.Minimize, 2.0, 2.0);


		for(int i = 0; i < 50; ++i){
			birdSwarmOld.makeIteration();
		}

		System.out.println("Bird: Best value: " + birdSwarmOld.getBestKnownValue());
		HashMap<String, Double> bestPoints = birdSwarmOld.getBestKnownPosition();
		for(String key : bestPoints.keySet()){
			System.out.format(key + ": %f.6\n", bestPoints.get(key));
		}*/
	}

}
