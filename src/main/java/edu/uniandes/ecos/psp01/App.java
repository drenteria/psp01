package edu.uniandes.ecos.psp01;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App myApp = new App();
        try {
			myApp.countLinesOfCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public void countLinesOfCode() throws IOException{
    	ProgramLOCCounter programCounter = new ProgramLOCCounter();
    	programCounter.sendToLOCCounter("src/main/resources/P1");
    }
    
}
