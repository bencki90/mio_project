package pso_1;

public class Dimension {
	private double maxBoundary;
	private double minBoundary;
	private double maxVelocity;
	
	public Dimension(final double minBoundary, final double maxBoundary){
		this.minBoundary = minBoundary;
		this.maxBoundary = maxBoundary;
		this.maxVelocity = Math.abs(maxBoundary - minBoundary); 
	}
	
	public double getMaxBoundary() {
		return maxBoundary;
	}

	public double getMinBoundary() {
		return minBoundary;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}
}
