package edu.uniandes.ecos.psp2.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.psp2.controller.WebController;

/**
 * Web View!
 * @author drenteria
 *
 */
public class WebApp extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static void main( String[] args )
    {
    	Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new WebApp()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			WebController.showInputForm(req, resp);
		} catch (IOException e) {
			Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		Integer degreesOfFreedom = new Integer(req.getParameter("dof"));
		Double xi = new Double(req.getParameter("xi"));
		Integer initialSegments = new Integer(req.getParameter("init"));
		Double allowedError = new Double(req.getParameter("error"));
		
		try{
			WebController.showIntegrationValueFromInput(req, resp, degreesOfFreedom, xi, initialSegments, allowedError);
			
		}
		catch (Exception ex){
			Logger.getLogger(WebApp.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
		
	}

}
