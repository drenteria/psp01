package edu.uniandes.ecos.psp11.model;

import java.util.Hashtable;
import java.util.LinkedList;

import edu.uniandes.ecos.psp0.StatisticCalculator;

/**
 * This class is intended to calculate the relative size ranges
 * of a list of input numbers (recommended at least 6 to 8) to evaluate
 * the possible ranges VS - S - M - L - VL using standard deviation
 * @author drenteria
 *
 */
public class RelativeSizeCalculator {
	
	/**
	 * Receives the input data to be analyzed
	 */
	private LinkedList<Double> lnData;
	
	/**
	 * Stores the logarithmic calculated ranges
	 */
	private Hashtable<String, Double> logRanges;
	
	/**
	 * Stores the definitive numeric ranges
	 */
	private Hashtable<String, Double> numericRanges;
	
	/*
	 * Constants for the ranges
	 */
	public static final String VS = "VS";
	public static final String S = "S";
	public static final String M = "M";
	public static final String L = "L";
	public static final String VL = "VL";
	
	/**
	 * Public constructor
	 */
	public RelativeSizeCalculator(){
		lnData = new LinkedList<Double>();
		logRanges = new Hashtable<String, Double>();
		numericRanges = new Hashtable<String, Double>();
	}
	
	/**
	 * This method calculates the Relative Size Ranges
	 * @param theInput Input list with numeric entries
	 * @return A Hashtable with the values of the numeric relative size ranges
	 */
	public Hashtable<String, Double> calculateRelativeSizeRanges(LinkedList<Double> theInput){
		
		double avg = 0D;
		double stdDev = 0D;
		
		if(theInput == null || theInput.size() == 0){
			System.out.println("Null or Empty input data. Error");
			return null;
		}
		
		//Converts each value to Ln
		for(Double currentValue : theInput){
			lnData.add(Math.log(currentValue));
		}
		
		//Calculates Standard Deviation
		StatisticCalculator theStatCalc = new StatisticCalculator();
		theStatCalc.setInputData(lnData);
		avg = theStatCalc.calculateMean();
		stdDev = theStatCalc.calculateStdDev();
		
		/*
		 * Sets the Logarithmic ranges
		 */
		logRanges.put(VS, (avg - 2*stdDev));
		logRanges.put(S, (avg - stdDev));
		logRanges.put(M, avg);
		logRanges.put(L, (avg + stdDev));
		logRanges.put(VL, (avg + 2*stdDev));
		
		/*
		 * Sets the Numeric Ranges
		 */
		numericRanges.put(VS, Math.pow(Math.E, logRanges.get(VS)));
		numericRanges.put(S, Math.pow(Math.E, logRanges.get(S)));
		numericRanges.put(M, Math.pow(Math.E, logRanges.get(M)));
		numericRanges.put(L, Math.pow(Math.E, logRanges.get(L)));
		numericRanges.put(VL, Math.pow(Math.E, logRanges.get(VL)));
		
		return numericRanges;
	}

}
