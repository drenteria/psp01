package edu.uniandes.ecos.psp0;

import java.util.LinkedList;

/**
 * 
 * @author Daniel Felipe
 * This class performs the statistic calculations
 * (Mean and Standard Deviation)
 *
 */
public class StatisticCalculator {

	/**
	 * The data provided to calculate values
	 */
	private LinkedList<Double> inputData;
	
	/**
	 * Public constructor
	 */
	public StatisticCalculator()
	{
		inputData = new LinkedList<Double>();
	}
	
	/**
	 * Sets the data (numbers list) to be used to calculate
	 * @param input LinkedList with the input data (numbers)
	 */
	public void setInputData(LinkedList<Double> input)
	{
		this.inputData = input;
	}
	
	/**
	 * This method calculates the mean of a list of numbers
	 * @return The mean of a set of numbers or 0 if the list is empty
	 */
	public double calculateMean(){
		double mean = 0;
		double elementsSum = 0;
		for(Double singleData : inputData){
			elementsSum += singleData;
		}
		mean = elementsSum / inputData.size();
		return mean;
	}
	
	/**
	 * This method calculates the standard deviation of a list
	 * of numbers 
	 * @return The standard deviation of the list of numbers or 0 if the
	 * list is empty
	 */
	public double calculateStdDev(){
		double stdDev = 0;
		double mean = calculateMean();
		double variationSum = 0;
		for(Double singleData : inputData){
			variationSum += Math.pow((singleData - mean), 2);
		}
		stdDev = Math.sqrt(variationSum / (inputData.size() - 1));
		return stdDev;
	}
	
}
