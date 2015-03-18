package edu.uniandes.ecos.psp1.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.uniandes.ecos.psp1.model.RegressionCorrelationCalculator;

public class ConsoleApp {

	public static void main(String[] args) {
		System.out.println("--- Regression and Correlation Calculator ---");
		System.out.println("Please, write the number of elements for each numeric set:");
		System.out.println("NOTE: Please be sure all numbers you write are decimals or at least numbers. No letters!");
		
		Scanner scanner = new Scanner(System.in);
		Integer number = 0;
		Double xk = 0D;
		Double yk = 0D;
		ArrayList<Double> setX = new ArrayList<Double>();
		ArrayList<Double> setY = new ArrayList<Double>();
		
		try{
			number = Integer.valueOf(scanner.nextLine());
			for(int i = 0; i < number; i++){
				System.out.print("Enter value X[" + i + "]: ");
				setX.add(Double.valueOf(scanner.nextLine()));
				System.out.print("Enter value Y[" + i + "]: ");
				setY.add(Double.valueOf(scanner.nextLine()));
			}
			
			System.out.println("Please, enter value for Xk to retrieve improved prediction:");
			xk = Double.valueOf(scanner.nextLine());
			
			System.out.println("Calculating Regression and Correlation...");
			
			RegressionCorrelationCalculator theCalc = new RegressionCorrelationCalculator();
			theCalc.setInputSetX(setX);
			theCalc.setInputSetY(setY);
			yk = theCalc.calculateImprovedPredictionYk(xk);
			
			System.out.println("B0: " + theCalc.getB0RegressionParameter());
			System.out.println("B1: " + theCalc.getB1RegressionParameter());
			System.out.println("Rxy: " + theCalc.getRXYCoeficcient());
			System.out.println("R2: " + theCalc.getRSquaredCoefficient());
			System.out.println("Xk: " + xk);
			System.out.println("Yk: " + yk);
			
			System.out.println("Finished. Have a nice day!");
		}
		catch (NumberFormatException ex){
			System.out.println("Typo Error: You wrote a number wrong. Start again!");
			ex.printStackTrace();
		}
		scanner.close();
	}

}
