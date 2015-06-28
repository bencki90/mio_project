package algorithms.common;

import java.util.HashMap;


public interface IEvolutionaryAlgorithm {
	public void makeIteration();
	public Individual[] getPopulation();
	public double getBestValue();
	public HashMap<String, Double> getBestPosition();
}
