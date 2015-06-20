package de.algorithm;

import java.util.Random;

public class DEAlgorithm {
	//liczba osobników w populacji (P * 5 lub 10)
	private final int NP;
	//liczba parametrów wektora
	private final int P;
	//wspó³czynnik mutacji <0, 1.2> optymalnie 0.5 - 1.0
	private final double F;
	//wspó³czynnik krzy¿owania <0, 1.0> 0.1, 0.9 i 1.0
	private final double CR;
	//bie¿¹ca populacja 
	private double population[][];
	
	private double candidateVector[];
	
	//liczby losowe
	private Random random = new Random();
	private long seed = (long) System.currentTimeMillis();
	
	public DEAlgorithm() {
		this.P = 2;
		this.NP = 50;
		this.F = 0.5;
		this.CR = 0.1;
		this.population = new double[this.NP][this.P];
		this.candidateVector = new double[this.P];
	}
	
	public DEAlgorithm(int P, int NP, double F, double CR) {
		this.P = P;
		this.NP = NP;
		this.F = F;
		this.CR = CR;
		this.population = new double[this.NP][this.P];
		this.candidateVector = new double[this.P];
	}
	
	public void generatePopulation() {
		this.random.setSeed(this.seed);
		
		for(int i = 0; i < this.NP; i++){
			System.out.print("Vector " + (i + 1) + ": ");
			for(int j = 0; j < this.P; j++){
				this.population[i][j] = (random.nextDouble() * (4*Math.PI)) - (2*Math.PI);
				System.out.print(this.population[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void makeEvolution() {
		for(int i = 0; i < this.NP; i++) {
			int x = (int) (random.nextDouble() * (double) (this.NP - 1));
			int a, b, c;
			
			do{
				a = (int) (random.nextDouble() * (double) (this.NP - 1));
			}while(a == x);
			
			do{
				b = (int) (random.nextDouble() * (double) (this.NP - 1));
			}while(b == x || b == a);
			
			do{
				c = (int) (random.nextDouble() * (double) (this.NP - 1));
			}while(c == x || c == a || c == b);
			
			int R = random.nextInt(this.P + 1);
			
			this.candidateVector = this.population[x];

			if(random.nextInt(this.P + 1) == R || random.nextDouble() * 1 < this.CR) {
				this.candidateVector[0] = this.population[a][0] + this.F * (this.population[b][0] - this.population[c][0]); 
			}
			
			if(random.nextInt(this.P + 1) == R || random.nextDouble() * 1 < this.CR) {
				this.candidateVector[1] = this.population[a][1] + this.F * (this.population[b][1] - this.population[c][1]); 
			}
			
//			if(((random.nextInt() * this.P) - 1) == R || random.nextDouble() * 1 < this.CR) {
//				this.candidateVector[2] = this.population[a][2] + this.F * (this.population[b][2] - this.population[c][2]); 
//			}
			
			if(this.fitnessFunction(this.population[x]) > this.fitnessFunction(candidateVector)){
				this.population[x] = this.candidateVector;
			}
		}
	}
	
	public double fitnessFunction(double vector[]) {
		//funkcja do sprawdzenia 
		double f = Math.sin(vector[0])*Math.exp(Math.pow(1-Math.cos(vector[1]), 2)) + Math.cos(vector[1])*Math.exp(Math.pow(1-Math.sin(vector[0]), 2)) + Math.pow(vector[0] - vector[1], 2);
		return f;
	}
	
	public double[] bestFitness(){
		double[] bestFitness = new double[this.P];
		bestFitness = this.population[0];
		for(int i = 1; i < this.NP; i++){
			if(this.fitnessFunction(bestFitness) < this.fitnessFunction(this.population[i])){
				bestFitness = this.population[i];
			}
		}
		return bestFitness;
	}
	
	public void printResults(){
		System.out.println("########### RESULT ############");
		for(int i = 0; i < this.NP; i++){
			System.out.print("Vector " + (i + 1) + ": ");
			for(int j = 0; j < this.P; j++){
				System.out.print(this.population[i][j] + " ");	
			}
			System.out.print(" f(x*) = " + this.fitnessFunction(this.population[i]));
			System.out.println();
		}
	}
	
}
