package edu.uniandes.ecos.psp2.model;

/**
 * This class evaluates the T-Distrubution statistic function<br>
 * The parameters received to evaluate this class are:<br>
 * 1. x The value to calculate the t-distribution function<br>
 * 2. dof The value of the degrees of freedom to evaluate the t-distribution<br>
 * @author drenteria
 *
 */
public class TDistributionFunction implements MathFunction {

	private double x;
	
	private int dof;
	
	@Override
	public double evaluateFunction(String... parameters) throws Exception {
		
		//Parameter validation
		if(null == parameters || parameters.length != 2){
			throw new IllegalArgumentException("TDistribution: Evaluation parameters are null or there are not 2 parameters");
		}
		
		//Parameter set
		try{
			x = Double.parseDouble(parameters[0]);
			dof = Integer.parseInt(parameters[1]);
		} catch (NumberFormatException e){
			throw new IllegalArgumentException("TDistribution: Non-numeric values not accepted for parameters");
		}
		
		return evaluateFirstPart() * evaluateSecondPart();
	}
	
	/**
	 * Evaluates left half of the t-distribution 
	 * @return
	 * @throws Exception
	 */
	private double evaluateFirstPart() throws Exception{
		double firstPart = 0D;
		GammaFunction upperPart = new GammaFunction();
		GammaFunction lowerPart = new GammaFunction();
		firstPart = upperPart.evaluateFunction(String.valueOf(dof + 1), "2");
		firstPart = firstPart / (Math.sqrt(Math.PI * dof) * lowerPart.evaluateFunction(String.valueOf(dof), "2"));
		return firstPart;
	}
	
	/**
	 * Evaluates the right part of the function 
	 * @return
	 */
	private double evaluateSecondPart(){
		double secondPart = Math.sqrt(1.0 + (Math.pow(x, 2.0) / (double)dof));
		secondPart = Math.pow(secondPart, (double)(dof + 1));
		secondPart = 1 / secondPart;
		return secondPart;
	}

}
