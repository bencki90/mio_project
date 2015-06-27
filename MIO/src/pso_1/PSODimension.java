package pso_1;

import de.main.Dimension;

public class PSODimension extends Dimension{
	private final double maxVelocity;
	
	public PSODimension(final double minBoundary, final double maxBoundary){
		super(minBoundary, maxBoundary);
		this.maxVelocity = Math.abs(maxBoundary - minBoundary); 
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}
}
