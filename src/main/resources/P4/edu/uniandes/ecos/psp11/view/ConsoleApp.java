package edu.uniandes.ecos.psp11.view;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

import edu.uniandes.ecos.psp11.model.RelativeSizeCalculator;

/**
 * Presents the console View
 * @author drenteria
 *
 */
public class ConsoleApp {

	public static void main(String[] args) {
		
		System.out.println("--- Relative Size Estimations Calculator ---");
		System.out.println("NOTE: Please be sure all numbers you write are decimals or at least numbers. No letters!");
		System.out.println("Please, write the number of elements for the numeric list to calculate:");
		
		Scanner scanner = new Scanner(System.in);
		Integer number = 0;
		LinkedList<Double> values = new LinkedList<Double>();
		Hashtable<String, Double> relativeSizes = new Hashtable<String, Double>();
		
		try{
			number = Integer.valueOf(scanner.nextLine());
			for(int i = 0; i < number; i++){
				System.out.print("Enter value X[" + i + "]: ");
				values.add(Double.valueOf(scanner.nextLine()));
			}
			
			System.out.println("Calculating Relative Size Estimations...");
			
			RelativeSizeCalculator theCalc = new RelativeSizeCalculator();
			relativeSizes = theCalc.calculateRelativeSizeRanges(values);
			
			System.out.println("Input Data: " + values.toString());
			System.out.println("Very Small: " + relativeSizes.get(RelativeSizeCalculator.VS));
			System.out.println("Small: " + relativeSizes.get(RelativeSizeCalculator.S));
			System.out.println("Medium: " + relativeSizes.get(RelativeSizeCalculator.M));
			System.out.println("Large: " + relativeSizes.get(RelativeSizeCalculator.L));
			System.out.println("Very Large: " + relativeSizes.get(RelativeSizeCalculator.VL));
			
		}
		catch (NumberFormatException ex){
			System.out.println("Typo Error: You wrote a number wrong. Start again!");
			ex.printStackTrace();
		}
		scanner.close();

	}

}
