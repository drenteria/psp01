package edu.uniandes.ecos.psp2.model;

/**
 * This class evaluates the Gamma function, which is defined as follows:
 * 1. Gamma(x) where x is integer = (x-1)*Gamma(x-1) or Factorial(x-1)<br>
 * 2. Gamma(x/y) = (x-y/y)*Gamma(x-y/y)<br>
 * Gamma function accepts one or two parameters:<br>
 * 1. One parameter for integer evaluation<br>
 * 2. Two parameters for fraction evaluation<br>
 * Gamma function basic conditions:<br>
 * 1. Gamma(1) = 1<br>
 * 2. Gamma(1/2) = Sqrt(Pi)<br>
 * Assumption: Parameter 2 for fractions must be 2
 * @author drenteria
 *
 */
public class GammaFunction implements MathFunction {

	private int number1 = 0;
	
	private int number2 = 0;
	
	private boolean isInteger = false;
	
	private double gammaEval = 0D;
	
	@Override
	public double evaluateFunction(String... parameters) throws Exception {
		
		//Parameter validation
		if(null == parameters || (parameters.length != 1 && parameters.length != 2)){
			throw new IllegalArgumentException("Gamma: Function parameters are null or has more than 1-2 parameters");
		}
		
		//Parameter setting
		if(parameters.length == 1){
			try{
				number1 = Integer.parseInt(parameters[0]);
				isInteger = true;
			} catch (NumberFormatException e){
				throw new IllegalArgumentException("Gamma: Parameter 1 could not be set as an integer");
			}
		}
		else{
			try{
				number1 = Integer.parseInt(parameters[0]);
				number2 = Integer.parseInt(parameters[1]);
				isInteger = false;
			} catch (NumberFormatException e){
				throw new IllegalArgumentException("Gamma: Any of the given parameters could not be set as an integer");
			}
			
			/*
			 * This exceptions are only to simplify calculations of gamma functions
			 * and should be removed for further development 
			 */
			if(number1 < 1){
				throw new IllegalArgumentException("Gamma: Parameter 1 must be a positive integer - Dummy exception");
			}
			if(number2 != 2){
				throw new IllegalArgumentException("Gamma: Parameter 2 must be valued as 2 - Dummy exception");
			}
		}
		
		//Function evaluation
		if(isInteger){
			FactorialFunction factorial = new FactorialFunction();
			gammaEval = factorial.evaluateFunction(String.valueOf(number1 - 1));
		}
		else{
			//Assumption: Number 2 is 2
			if(number1 == number2){
				gammaEval = 1.0D;
			}
			else if(number1 == 1){
				gammaEval = Math.sqrt(Math.PI);
			}
			else{
				gammaEval = Double.valueOf(String.valueOf((number1 - number2)));
				gammaEval = Double.valueOf(String.valueOf((gammaEval / number2)));
				gammaEval = gammaEval * evaluateFunction(
						String.valueOf(number1 - number2), 
						String.valueOf(number2));
			}
		}
		return gammaEval;
	}

}
