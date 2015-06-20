package pso_1;

import java.util.HashMap;

public class BirdSwarm extends Swarm{

	public BirdSwarm(int particlesNumber,
			HashMap<String, Dimension> dimensions, Operation operation,
			double C1, double C2) throws Exception {
		super(particlesNumber, dimensions, operation, C1, C2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double fitnessFunction(HashMap<String, Double> vars) {

		double x1 = vars.get("x1");
		double x2 = vars.get("x2");
		
		double sinx1 = Math.sin(x1);
		double cosx2 = Math.cos(x2);
		double esinx1 = Math.exp(Math.pow(1 - sinx1, 2));
		double ecosx2 = Math.exp(Math.pow(1 - cosx2, 2));
		
		return sinx1 * ecosx2 + cosx2 * esinx1 + Math.pow(x1-x2, 2);
	}

}
