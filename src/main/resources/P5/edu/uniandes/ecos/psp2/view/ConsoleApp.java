package edu.uniandes.ecos.psp2.view;

import java.util.Scanner;

import edu.uniandes.ecos.psp2.model.SimpsonRuleIntegration;

/**
 * This class evaluates the Simpson's Rule Integration Feature.
 */
public class ConsoleApp 
{
    public static void main( String[] args )
    {
    	System.out.println("--- Simpson Rule Integration Calculator for T-Distribution ---");
		System.out.println("NOTE: Please be sure all numbers you write are decimals or at least numbers. No letters!");
		
		Scanner scanner = new Scanner(System.in);
		Integer degreesOfFreedom = 0;
		Double xi = 0D;
		Integer initialSegments = 10;
		Double allowedError = 0.0001;
		SimpsonRuleIntegration integrator = null;
		Double pValue = 0D;
		
		
		try{
			System.out.println("Please write the degrees of freedom of your calculation:");
			degreesOfFreedom = Integer.valueOf(scanner.nextLine());
			
			System.out.println("Please write the maximum Xi value:");
			xi = Double.valueOf(scanner.nextLine());
			
			integrator = new SimpsonRuleIntegration(initialSegments, xi, allowedError, degreesOfFreedom);
					
			System.out.println("Evaluating T-Distribution integration with Simpson Rule Integration...");
			
			pValue = integrator.getIntegralApproximation();
			
			System.out.println("From 0 to Xi: " + xi);
			System.out.println("Initial Segments: " + initialSegments);
			System.out.println("Allowed Error (E): " + allowedError);
			System.out.println("Degrees of Freedom : " + degreesOfFreedom);
			System.out.println("Area under the function (p) :" + pValue);
		}
		catch (NumberFormatException ex){
			System.out.println("Typo Error: You wrote a number in a wrong way. Start again!");
			ex.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Error calculating the integral approximation");
			e.printStackTrace();
		}
		scanner.close();
    }
}
