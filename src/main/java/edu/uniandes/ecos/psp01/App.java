package edu.uniandes.ecos.psp01;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        WebOutput.showMainWindow(req, resp);
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		ProgramLOCCounter projectCounter = new ProgramLOCCounter();
		projectCounter.sendToLOCCounter("src/main/resources/P1");
		
		WebOutput.showLOCCountResult(req, resp, projectCounter);
		
	}
	
}
