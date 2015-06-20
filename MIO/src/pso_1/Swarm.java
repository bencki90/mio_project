package pso_1;

import java.util.HashMap;
import java.util.Random;


public abstract class Swarm {
	private Particle[] particles;
    private HashMap<String, Double> bestKnownPosition;
    private HashMap<String, Dimension> dimensions;
    private double bestKnownValue;
    public Operation operation;
    private double C1;
    private double C2;
    private Random rand;
    
    public enum Operation{
    	Minimize,
    	Maximize
    }

    
	public Swarm(int particlesNumber, HashMap<String, Dimension> dimensions, Operation operation, double C1, double C2){
        this.dimensions = dimensions;
        this.initBestKnownPositionAndValue();
        this.initParticles(particlesNumber);
        this.operation = operation;
        this.C1 = C1;
        this.C2 = C2;
        rand = new Random();
    }
	
	public abstract double fitnessFunction(HashMap<String, Double> vars);
	
    public Particle[] getParticles() {
		return particles;
	}
	public HashMap<String, Double> getBestKnownPosition() {
		return bestKnownPosition;
	}
	public double getBestKnownPosition(String key) {
		return bestKnownPosition.get(key);
	}
	public double getBestKnownValue() {
		return bestKnownValue;
	}
	public Operation getOperation(){
		return operation;
	}
	
	@SuppressWarnings("unchecked")
	public void updateBestKnownPositionAndValue(Particle particle){
		if(this.operation == Operation.Maximize)
		{
			if(particle.getBestKnownValue() > this.bestKnownValue){
				this.bestKnownPosition = (HashMap<String, Double>)particle.getBestKnownPosition().clone();
				this.bestKnownValue = particle.getBestKnownValue();
			}
		}
		else{
			if(particle.getBestKnownValue() < this.bestKnownValue){
				this.bestKnownPosition = (HashMap<String, Double>)particle.getBestKnownPosition().clone();
				this.bestKnownValue = particle.getBestKnownValue();
			}
		}
	}
	
	private Particle newPartcle(HashMap<String, Double> position, HashMap<String, Double> velocity){
		Particle particle = new Particle(position, velocity, this.fitnessFunction(position), this.operation);
		this.updateBestKnownPositionAndValue(particle);
		return particle;
	}
	
	private void initBestKnownPositionAndValue(){
		HashMap<String, Double> position = new HashMap<String, Double>();
		for (String key : this.dimensions.keySet()) {
			
			Dimension dimension = dimensions.get(key);
			position.put(key, ((dimension.getMinBoundary() + dimension.getMaxBoundary())/2.0));
		}
		
        this.bestKnownPosition = position;
        this.bestKnownValue = fitnessFunction(position);
	}
	private void initParticles(int particlesNumber){
        Random rand = new Random();
        this.particles = new Particle[particlesNumber];
        
        for (int i = 0; i < particlesNumber; ++i) {
        	
            HashMap<String, Double> position = new HashMap<String, Double>();
            HashMap<String, Double> velocity = new HashMap<String, Double>();
        	
        	for (String key : this.dimensions.keySet()) {
	        	Dimension dimension = dimensions.get(key);
	        	position.put(key, (rand.nextDouble() * (dimension.getMaxBoundary() - dimension.getMinBoundary()) + dimension.getMinBoundary()));
				
				velocity.put(key, 0.0);
			}
        	
        	particles[i] = this.newPartcle(position, velocity);
        }
	}
	
	public void makeIteration(){

		for(int i = 0; i < this.getParticles().length; ++i){
			
			Particle particle = this.getParticles()[i];
			
			for (String key : particle.getVelocity().keySet()) {
				
				double maxVelocity = dimensions.get(key).getMaxVelocity();
				double newVelocity = particle.getVelocity(key) + 
						C1 * rand.nextDouble() * (particle.getBestKnownPosition(key) - particle.getCurrentPosition(key)) + 
						C2 * rand.nextDouble() * (this.getBestKnownPosition(key) - particle.getCurrentPosition(key));
				
				if(newVelocity > maxVelocity) newVelocity = maxVelocity;
				else if(newVelocity < (maxVelocity * -1)) newVelocity = -maxVelocity;
				
				particle.setVelocity(key, newVelocity); 
			}
			
			HashMap<String, Double> newPosition = new HashMap<String, Double>();
			for(String key : particle.getBestKnownPosition().keySet()){
				double newPositionInDimension = particle.getCurrentPosition(key) + particle.getVelocity(key);
				
				if(newPositionInDimension > dimensions.get(key).getMaxBoundary()) newPositionInDimension = dimensions.get(key).getMaxBoundary();
				if(newPositionInDimension < dimensions.get(key).getMinBoundary()) newPositionInDimension = dimensions.get(key).getMinBoundary();
				
				newPosition.put(key, newPositionInDimension);
			}
			particle.setCurrentPosition(newPosition, this.fitnessFunction(newPosition));
			this.updateBestKnownPositionAndValue(particle);
		}
	}
}
