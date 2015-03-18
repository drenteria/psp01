package edu.uniandes.ecos.psp2.model;

/**
 * This class evaluates the integration of a specific function
 * using the Simpson's Rule discrete integration.<br>
 * Parameters:<br>
 * 1. A concrete instance of <code>MathFunction</code> to be integrated<br>
 * 2. num_segs The number of segments to divide the integration<br>
 * 3. x The maximum Xi value to evaluate the integration<br>
 * 4. E The maximum allowed error accepted
 * 5. dof The degrees of freedom to be used statistically
 * @author drenteria
 *
 */
public class SimpsonRuleIntegration {
	
	private MathFunction functionToIntegrate;
	
	private int segments = 0;
	
	private double xi = 0D;
	
	private double maxError = 0D;
	
	private int degreesOfFreedom = 0;
	
	private double segmentWidth = 0D;
	
	/**
	 * Constructor
	 * @param initial_segments
	 * @param xi
	 * @param allowedError
	 * @param dof
	 */
	public SimpsonRuleIntegration(int initial_segments, double xi, 
			double allowedError, int dof){
		/*
		 * Assumption: function to integrate will be t-distribution and
		 * it won't be provided by client classes dynamically.
		 * This behavior will be changed in future
		 */
		functionToIntegrate = new TDistributionFunction();
		this.segments = initial_segments;
		this.xi = xi;
		this.maxError = allowedError;
		this.degreesOfFreedom = dof;
	}
	
	/**
	 * Evaluates
	 * @param current_segments
	 * @return
	 */
	private double evaluatePValue(int current_segments) throws Exception{
		//Parameter validation
		if(current_segments < 1 && (current_segments % 2) != 0){
			throw new IllegalArgumentException("SimpsonRuleIntegration: Segments must be a positive even number");
		}
		
		double pValue = 0D;
		double multiplier = 0D;
		
		segmentWidth = (xi / current_segments); 
		multiplier = (segmentWidth / 3);
		pValue = (functionToIntegrate.evaluateFunction("0.0", String.valueOf(degreesOfFreedom)) + 
				oddValuesSumatory(current_segments) + evenValuesSumatory(current_segments) + 
				functionToIntegrate.evaluateFunction(String.valueOf(xi), String.valueOf(degreesOfFreedom)));
		return (multiplier * pValue);
	}
	
	/**
	 * Executes the sumatory of the odds values for the integration
	 * function 4*F(iW)
	 * @return
	 * @throws Exception 
	 */
	private double oddValuesSumatory(int current_segments) throws Exception{
		double sum = 0D;
		for(int i = 1; i <= (current_segments - 1); i+=2){
			sum += 4.0 * functionToIntegrate.evaluateFunction(
					String.valueOf(Integer.valueOf(i).doubleValue() * segmentWidth), 
					String.valueOf(degreesOfFreedom));
		}
		return sum;
	}
	
	/**
	 * 
	 * @param current_segments
	 * @return
	 * @throws Exception
	 */
	private double evenValuesSumatory(int current_segments) throws Exception{
		double sum = 0D;
		for(int i = 2; i <= (current_segments - 2); i+=2){
			sum += 2.0 * functionToIntegrate.evaluateFunction(
					String.valueOf(Integer.valueOf(i).doubleValue() * segmentWidth), 
					String.valueOf(degreesOfFreedom));
		}
		return sum;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public double getIntegralApproximation() throws Exception{
		double currentError = 0D;
		double p1 = 0D;
		double p2 = 0D;
		do{
			p1 = evaluatePValue(segments);
			p2 = evaluatePValue(segments * 2);
			currentError = Math.abs(p1 - p2);
			segments = (segments * 2);
		} while(currentError > maxError);
		return p2;
	}
	

}
