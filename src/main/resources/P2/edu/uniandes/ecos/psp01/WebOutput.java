package edu.uniandes.ecos.psp01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebOutput {

	public static void showMainWindow(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
        pw.write("<html>");
        pw.println("<h1>PSP01 Lines Of Code Count Program!</h1>");
        pw.println("<p>Click button to start!</p>");
        pw.write("<form action=\"count\" method=\"post\"> \n");
        pw.write("<input type=\"submit\" value=\"Show LOC Count\">\n");
        pw.write("</form>");        
        pw.write("</html>");
	}
	
	public static void showLOCCountResult(HttpServletRequest request, HttpServletResponse response,
			ProgramLOCCounter counter) 
			throws IOException{
		PrintWriter pw = response.getWriter();
		pw.write("<html>");
		pw.write("<body>");
        pw.println("<h1>PSP01 Lines Of Code Count Program!</h1>");
        
        pw.println("<b>Project Folder:</b>" + counter.getWorkingDirectoryPath() + " <br/>");
        pw.println("<b>Total Lines:</b>" + counter.getProjectTotalLines() + " <br/>");
        pw.println("<b>Effective Lines:</b>" + counter.getProjectEffectiveLines() + " <br/>");
        pw.write("</br></br>");
        
        ListIterator<LOCCounter> projectCounters = counter.getCountersList().listIterator();
        while(projectCounters.hasNext()){
        	LOCCounter currentCounter = projectCounters.next();
        	pw.println("<b>File:</b>" + currentCounter.getSourceFileName() + " <br/>");
        	pw.println("<b>Total Lines:</b>" + currentCounter.getTotalLines() + " <br/>");
        	pw.println("<b>Effective Lines:</b>" + currentCounter.getEfffectiveLines() + " <br/>");
        	pw.println("<b>Methods:</b>" + currentCounter.getMethodsNumber() + " <br/>");
        }
        pw.write("</body>");
        pw.write("</html>");
	}
	
}
