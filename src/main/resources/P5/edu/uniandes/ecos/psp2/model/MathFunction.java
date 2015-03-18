package edu.uniandes.ecos.psp2.model;

/**
 * This interface acsts as a reference for mathematic functions
 * that will be used further
 * @author drenteria
 *
 */
public interface MathFunction {
	
	/**
	 * This method evaluates the function according a given number of parameters
	 * @param parameters
	 * 		A variable argument to set the parameters needed to evaluate the function 
	 * @return
	 * 		The numeric result of the function evaluation as a <code>double</code> number.
	 * @throws
	 * 		Exception If any error happens evaluating the function 
	 */
	public double evaluateFunction(String... parameters) throws Exception;

}
