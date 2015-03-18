package edu.uniandes.ecos.psp11.view;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.psp11.controller.WebController;
import edu.uniandes.ecos.psp11.model.RelativeSizeCalculator;


/**
 * Web View!
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
		String entryValues = req.getParameter("list");
		
		try{
			LinkedList<Double> values = new LinkedList<Double>();
			
			
			String[] inValues = entryValues.split(";");
			for(String currentVal : inValues){
				values.add(Double.valueOf(currentVal.trim()));
			}
			RelativeSizeCalculator theCalc = new RelativeSizeCalculator();
			WebController.showRelativeSizesFromInput(req, resp, values, theCalc);
			
		}
		catch (Exception ex){
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		
	}
}
