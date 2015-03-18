package edu.uniandes.ecos.psp1.model;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.uniandes.ecos.psp0.StatisticCalculator;

/**
 * This class calculates the linear Regression parameters (B0 and B1)
 * and the correlation (Rxy and squareR) between two sets of numeric data
 * @author drenteria
 *
 */
public class RegressionCorrelationCalculator {
	
	/**
	 * Related to the inputs from first set of data
	 */
	private ArrayList<Double> inputSetX;
	
	/**
	 * Related to the inputs from second set of data
	 */
	private ArrayList<Double> inputSetY;
	
	private double b0;
	
	private double b1;
	
	private double rxy;
	
	private int n;
	
	/**
	 * Public constructor for class
	 */
	public RegressionCorrelationCalculator(){
		this.inputSetX = new ArrayList<Double>();
		this.inputSetY = new ArrayList<Double>();
		this.b0 = 0D;
		this.b1 = 0D;
		this.rxy = 0D;
		this.n = 0;
	}
	
	/**
	 * Setter method for input set X
	 * @param xData The numeric set X
	 */
	public void setInputSetX(ArrayList<Double> xData){
		this.inputSetX = xData;
	}
	
	/**
	 * Setter method for input set Y
	 * @param yData The numeric set Y
	 */
	public void setInputSetY(ArrayList<Double> yData){
		this.inputSetY = yData;
	}
	
	/**
	 * This method defines if the two sets of data are
	 * valid to calculate Regression and Correlation
	 * @return <code>true</code> if X and Y input sets are valid
	 * to calculate. <code>false</code> otherwise.
	 */
	private boolean prepareData(){
		if(inputSetX.isEmpty() || inputSetY.isEmpty()){
			System.out.println("Input Sets X and Y could not be empty");
			return false;
		}
		else if(inputSetX.size() != inputSetY.size()){
			System.out.println("Input Sets size must be the same");
			return false;
		}
		//Sets the value of the set size for both X and Y sets
		n = inputSetX.size();
		return true;
	}
	
	/**
	 * Starts the process to calculate Regression Parameters
	 * B1 and B0 according to the X and Y input sets
	 */
	private void calculateRegressionParameters(){
		
		if(!prepareData()){
			System.out.println("Regression Parameters can't be calculated");
			return;
		}
		
		//X and Y sets average
		double xAvg = 0D;
		double yAvg = 0D;
		
		//StatisticCalculator to calculate mean of both sets
		StatisticCalculator xMeanCalc = new StatisticCalculator();
		StatisticCalculator yMeanCalc = new StatisticCalculator();
		xMeanCalc.setInputData(new LinkedList<Double>(inputSetX));
		yMeanCalc.setInputData(new LinkedList<Double>(inputSetY));
		
		xAvg = xMeanCalc.calculateMean();
		yAvg = yMeanCalc.calculateMean();
		
		calculateB1Parameter(xAvg, yAvg);
		calculateB0Parameter(xAvg, yAvg);
	}
	
	/**
	 * Calculates the B1 Regression Parameter
	 * @param xAvg Average value for X input set 
	 * @param yAvg Average value for Y input set
	 */
	private void calculateB1Parameter(double xAvg, double yAvg){
		double sumXiYi = 0D;
		double sumSqXi = 0D;
		
		/*
		 * Calculates the sum for all Xi*Yi entries
		 * and the Xi2 sum
		 */
		for(int i = 0; i < n; i++){
			double xi = inputSetX.get(i);
			double yi = inputSetY.get(i);
			sumXiYi += (xi * yi);
			sumSqXi += Math.pow(xi, 2.0);
		}
		
		this.b1 = (sumXiYi - (this.n * xAvg * yAvg)) / 
				(sumSqXi - (this.n * Math.pow(xAvg, 2.0)));
	}
	
	/**
	 * Calculates the B0 Regression Parameter
	 * @param xAvg Average value for X input set 
	 * @param yAvg Average value for Y input set
	 */
	private void calculateB0Parameter(double xAvg, double yAvg){
		this.b0 = yAvg - (this.b1 * xAvg); 
	}
	
	/**
	 * Calculates the R(x,y) correlation coefficient
	 */
	private void calculateCorrelationCoefficients(){
		
		if(!prepareData()){
			System.out.println("Correlation Coefficient can't be calculated");
			return;
		}
		
		double sumXiYi = 0D;
		double sumXi = 0D;
		double sumYi = 0D;
		double sumXiSq = 0D;
		double sumYiSq = 0D;
		
		/*
		 * Calculates all required data
		 */
		for(int i = 0; i < n; i++){
			double xi = inputSetX.get(i);
			double yi = inputSetY.get(i);
			sumXiYi += (xi * yi);
			sumXi += xi;
			sumYi += yi;
			sumXiSq += Math.pow(xi, 2.0);
			sumYiSq += Math.pow(yi, 2.0);
		}
		
		this.rxy = ((this.n * sumXiYi) - (sumXi * sumYi)) /
				(Math.sqrt(
						(this.n * sumXiSq - Math.pow(sumXi, 2.0)) * 
						(this.n * sumYiSq - Math.pow(sumYi, 2.0))
					)
				);
	}
	
	public double calculateImprovedPredictionYk(double xk){
		calculateRegressionParameters();
		calculateCorrelationCoefficients();
		return (b0 + b1*xk);
	}
	
	public double getB1RegressionParameter(){
		return this.b1;
	}
	
	public double getB0RegressionParameter(){
		return this.b0;
	}
	
	public double getRXYCoeficcient(){
		return this.rxy;
	}
	
	public double getRSquaredCoefficient(){
		return Math.pow(this.rxy, 2.0);
	}

}
