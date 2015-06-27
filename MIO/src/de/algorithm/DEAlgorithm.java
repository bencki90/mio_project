package de.algorithm;

import java.util.HashMap;
import java.util.Random;

import pso_1.PSODimension;
import de.main.Dimension;
import de.main.Operation;
import functionParsing.RPNEvaluator;

public class DEAlgorithm {
	private final HashMap<String, Dimension> dimensions;
	private final double F;
	private final double CR;
	private Individual[] population;
	
	private final String functionRPN;
	private final Operation operation;
	
	private Random random;
	
	public DEAlgorithm(String functionRPN, final int populationSize, final HashMap<String, Dimension> dimensions, final Operation operation, final double F, final double CR) {
		this.functionRPN = functionRPN;
		this.dimensions = dimensions;
		this.operation = operation;
		this.F = F;
		this.CR = CR;

		this.random = new Random();
		this.InitializePopulation(populationSize);
	}


	public void InitializePopulation(final int populationSize) {
		
		this.population = new Individual[populationSize];
		
		for(int i = 0; i < populationSize; ++i){
			
			HashMap<String, Double> position = new HashMap<String, Double>();
			
			for (String key : this.dimensions.keySet()) {
        		Dimension dimension = dimensions.get(key);
        		
	        	position.put(key, (random.nextDouble() * (dimension.getMaxBoundary() - dimension.getMinBoundary()) + dimension.getMinBoundary()));
			}
			
			this.population[i] = new Individual(position);
		}
	}
	
	public void makeEvolution() throws Exception {
		for(int i = 0; i < this.population.length; ++i) {
			
			int x = (int) (random.nextDouble() * (double) (this.population.length - 1));
			int a, b, c;
			
			do{
				a = (int) (random.nextDouble() * (double) (this.population.length - 1));
			}while(a == x);
			
			do{
				b = (int) (random.nextDouble() * (double) (this.population.length - 1));
			}while(b == x || b == a);
			
			do{
				c = (int) (random.nextDouble() * (double) (this.population.length - 1));
			}while(c == x || c == a || c == b);
			
			int R = random.nextInt(this.dimensions.size() + 1);
			
			Individual individualCandidate = new Individual(this.population[x]);
			
			
			
			for(String key : this.dimensions.keySet()){
				if(random.nextInt(this.dimensions.size() + 1) == R || random.nextDouble() * 1 < this.CR) {
					
					individualCandidate.setPosition(key, this.population[a].getPosition(key) + this.F * (this.population[b].getPosition(key) - this.population[c].getPosition(key)));
				}
			}

			if(this.operation == Operation.Minimize){
				if(this.fitnessFunction(this.population[x].getPosition()) > this.fitnessFunction(individualCandidate.getPosition())){
					this.population[x] = individualCandidate;
				}
			}
			else{
				if(this.fitnessFunction(this.population[x].getPosition()) < this.fitnessFunction(individualCandidate.getPosition())){
					this.population[x] = individualCandidate;
				}
			}
			
		}
	}
	
	public double fitnessFunction(HashMap<String, Double> vars) throws Exception{
		String expr = this.functionRPN;
		for(String key : vars.keySet()){
			expr = expr.replaceAll(key, RPNEvaluator.df.format(vars.get(key)));
		}
		
		return RPNEvaluator.evalRPN(expr);
	}
	
	public HashMap<String, Double> bestPosition() throws Exception{
		HashMap<String, Double> best = this.population[0].getPosition();
		for(int i = 1; i < this.population.length; ++i){
			if(this.operation == Operation.Minimize){
				if(this.fitnessFunction(this.population[i].getPosition()) < this.fitnessFunction(best)){
					best = this.population[i].getPosition();
				}
			}
			else{
				if(this.fitnessFunction(this.population[i].getPosition()) > this.fitnessFunction(best)){
					best = this.population[i].getPosition();
				}
			}
		}
		return best;
	}
	
	public double bestValue() throws Exception{
		return this.fitnessFunction(this.bestPosition());
	}
	
	public void printResults() throws Exception{
		System.out.println("############ RESULT ############");
		for(int i = 0; i < this.population.length; ++i){
			System.out.print("Individual " + (i + 1) + ": ");

			for(String key : this.population[i].getPosition().keySet()){
				System.out.format(key + ": %f.6\t", this.population[i].getPosition(key));
			}
			System.out.println();
		}
	}
	
}
