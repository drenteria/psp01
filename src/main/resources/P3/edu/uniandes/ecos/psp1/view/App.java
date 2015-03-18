package edu.uniandes.ecos.psp1.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.psp1.controller.WebController;
import edu.uniandes.ecos.psp1.model.RegressionCorrelationCalculator;


/**
 * Regression and Correlation Web Application
 * @author drenteria
 *
 */
public class App extends HttpServlet
{
    private static final long serialVersionUID = 1L;

	public static void main( String[] args )
    {
    	Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			WebController.showInputForm(req, resp);
		} catch (IOException e) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		String xValues = req.getParameter("listX");
		String yValues = req.getParameter("listY");
		String xkValue = req.getParameter("xk");
		
		try{
			ArrayList<Double> listX = new ArrayList<Double>();
			ArrayList<Double> listY = new ArrayList<Double>();
			Double xk = Double.valueOf(xkValue);
			
			String[] xVals = xValues.split(";");
			for(String xCurrentVal : xVals){
				listX.add(Double.valueOf(xCurrentVal.trim()));
			}
			
			String[] yVals = yValues.split(";");
			for(String yCurrentVal : yVals){
				listY.add(Double.valueOf(yCurrentVal.trim()));
			}
			
			RegressionCorrelationCalculator theCalc = new RegressionCorrelationCalculator();
			theCalc.setInputSetX(listX);
			theCalc.setInputSetY(listY);
			
			WebController.showRegressionCorrelationResponse(req, resp, listX, listY, xk, theCalc);
			
		}
		catch (Exception ex){
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		
	}
}
