package edu.uniandes.ecos.psp11.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uniandes.ecos.psp11.model.RelativeSizeCalculator;

/**
 * This class is the controller for the web view
 * @author drenteria
 *
 */
public class WebController {
	
	/**
	 * Show main input form for the input list for Relative Size
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void showInputForm(HttpServletRequest request, HttpServletResponse response) 
	throws IOException{
		String formString = "<html>" +
				"<body>" +
				"<h1>PSP11 Relative Size Estimations Program!</h1>" +
				"<p>Please, write numbers to calculate in semicolon (;) separated values.</p>" +
				"<form action=\"relative\" method=\"post\"><br/>" +
				"Input Data: <input type=\"text\" name=\"list\"><br/>" +
				"<input type=\"submit\" value=\"Show Relative Sizes\">" +
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
	public static void showRelativeSizesFromInput(HttpServletRequest request, HttpServletResponse response, 
			LinkedList<Double> values, RelativeSizeCalculator theCalc)
	throws IOException{
		
		Hashtable<String, Double> relativeSizes = new Hashtable<String, Double>();
		relativeSizes = theCalc.calculateRelativeSizeRanges(values);
		
		System.out.println("Input Data: " + values.toString());
		System.out.println("Very Small: " + relativeSizes.get(RelativeSizeCalculator.VS));
		System.out.println("Small: " + relativeSizes.get(RelativeSizeCalculator.S));
		System.out.println("Medium: " + relativeSizes.get(RelativeSizeCalculator.M));
		System.out.println("Large: " + relativeSizes.get(RelativeSizeCalculator.L));
		System.out.println("Very Large: " + relativeSizes.get(RelativeSizeCalculator.VL));
		
		String resultString = "<html>" +
				"<body>" +
				"<h1>PSP11 Relative Size Estimations Program!</h1>" +
				"<p>Results:</p>" +
				"Inputs :" + values.toString() + "<br/>" +
				"Very Small: " + relativeSizes.get(RelativeSizeCalculator.VS) + "<br/>" +
				"Small: " + relativeSizes.get(RelativeSizeCalculator.S) + "<br/>" +
				"Medium: " + relativeSizes.get(RelativeSizeCalculator.M) + "<br/>" +
				"Large: " + relativeSizes.get(RelativeSizeCalculator.L) + "<br/>" +
				"Very Large: " + relativeSizes.get(RelativeSizeCalculator.VL) + "<br/>" +
				"</body>" +
				"</html>";
		PrintWriter writer = response.getWriter();
		writer.write(resultString);
	}

}
