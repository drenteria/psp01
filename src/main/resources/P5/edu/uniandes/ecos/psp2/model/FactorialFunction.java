package edu.uniandes.ecos.psp2.model;

/**
 * This class implements the Factorial function (n!), which evaluates according to
 * following rules:<br>
 * 1. Factorial(0)  = 1<br>
 * 2. Factorial(1) = 1<br>
 * 3. Factorial(n) = n x (n-1) x ... x 2 x 1<br>
 * Factorial only needs one parameter: the integer number to calculate factorial
 * @author drenteria
 *
 */
public class FactorialFunction implements MathFunction {

	/**
	 * The current number to calc Factorial function
	 */
	private int number;
	
	@Override
	public double evaluateFunction(String... parameters) {
		
		// Parameter Validations
		if(null == parameters || parameters.length > 1){
			throw new IllegalArgumentException("Factorial: Function parameter is null or is more than one number");
		}
		
		try{
			number = Integer.parseInt(parameters[0]);
		} catch (NumberFormatException ex){
			throw new IllegalArgumentException("Factorial: Non-numeric value provided to evaluate this function", ex);
		}
		
		//Function evaluation definition
		if(number == 1 || number == 0){
			return 1;
		}
		else {
			return number * evaluateFunction(Integer.toString(number - 1));
		}
	}

}
