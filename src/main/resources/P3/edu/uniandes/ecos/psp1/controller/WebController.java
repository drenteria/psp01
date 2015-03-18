package edu.uniandes.ecos.psp1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uniandes.ecos.psp1.model.RegressionCorrelationCalculator;

/**
 * This class acts as the Controller for the Web view
 * @author drenteria
 *
 */
public class WebController {
	
	/**
	 * Show main input form for the X and Y lists for regression
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void showInputForm(HttpServletRequest request, HttpServletResponse response) 
	throws IOException{
		String formString = "<html>" +
				"<body>" +
				"<h1>PSP1 Regression and Correlation Program!</h1>" +
				"<p>Please, write numbers to calculate in semicolon (;) separated values.</p>" +
				"<p>Be sure both inputs have the same amount of numbers.</p>" +
				"<form action=\"predict\" method=\"post\"><br/>" +
				"List X: <input type=\"text\" name=\"listX\"><br/>" +
				"List Y: <input type=\"text\" name=\"listY\"><br/>" +
				"Xk: <input type=\"text\" name=\"xk\"><br/>" +
				"<input type=\"submit\" value=\"Show Improved Prediction (Yk)\">" +
				"</body>" +
				"</html>";
		PrintWriter writer = response.getWriter();
		writer.write(formString);
	}
	
	/**
	 * Show results 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void showRegressionCorrelationResponse(HttpServletRequest request, HttpServletResponse response, 
			List<Double> listX, List<Double> listY, Double xk,
			RegressionCorrelationCalculator theCalc)
	throws IOException{
		Double yk = theCalc.calculateImprovedPredictionYk(xk); 
		String resultString = "<html>" +
				"<body>" +
				"<h1>PSP1 Regression and Correlation Program!</h1>" +
				"<p>Results:</p>" +
				"List X:" + listX.toString() + "<br/>" +
				"List Y:" + listY.toString() + "<br/>" +
				"B0: " + theCalc.getB0RegressionParameter() + "<br/>" + 
				"B1: " + theCalc.getB1RegressionParameter() + "<br/>" +
				"Rxy: " + theCalc.getRXYCoeficcient() + "<br/>" +
				"R2: " + theCalc.getRSquaredCoefficient() + "<br/>" +
				"Xk:" + xk.toString() + "<br/>" +
				"Yk:" + yk.toString() +
				"</body>" +
				"</html>";
		PrintWriter writer = response.getWriter();
		writer.write(resultString);
	}

}
