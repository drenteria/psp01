package edu.uniandes.ecos.psp2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uniandes.ecos.psp2.model.SimpsonRuleIntegration;

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
				"<h1>PSP2 T-Distribution integration using Simpson Rule Integration!</h1>" +
				"<p>Please, write numbers to calculate the integration value.</p>" +
				"<form action=\"integrate\" method=\"post\"><br/>" +
				"Degrees of Freedom: <input type=\"text\" name=\"dof\"><br/>" +
				"From 0 to Xi: <input type=\"text\" name=\"xi\"><br/>" +
				"Allowed Error (E): <input type=\"text\" name=\"error\"><br/>" +
				"Initial Segments (mul_seg, and even positive value): <input type=\"text\" name=\"init\"><br/>" +
				"<input type=\"submit\" value=\"Integrate!\">" +
				"</body>" +
				"</html>";
		PrintWriter writer = response.getWriter();
		writer.write(formString);
	}
	
	/**
	 * Show results 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public static void showIntegrationValueFromInput(HttpServletRequest request, HttpServletResponse response, 
			Integer degreesOfFreedom, Double xi, Integer initialSegments, Double allowedError)
	throws Exception{
		
		SimpsonRuleIntegration integrator = new SimpsonRuleIntegration(
				initialSegments, 
				xi, 
				allowedError, 
				degreesOfFreedom);
		
		String resultString = "<html>" +
				"<body>" +
				"<h1>PSP11 Relative Size Estimations Program!</h1>" +
				"<p>Results:</p>" +
				"Degrees of Freedom:" + String.valueOf(degreesOfFreedom) + "<br/>" +
				"From 0 to Xi:" + String.valueOf(xi) + "<br/>" +
				"Allowed Error:" + String.valueOf(allowedError) + "<br/>" +
				"Initial Segments:" + String.valueOf(initialSegments) + "<br/>" +
				"Area under the curve (p):" + String.valueOf(integrator.getIntegralApproximation()) + "<br/>" +
				"</body>" +
				"</html>";
		PrintWriter writer = response.getWriter();
		writer.write(resultString);
	}

}
