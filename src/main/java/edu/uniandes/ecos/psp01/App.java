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
        System.out.println( "Hello World!" );
        
        App myApp = new App();
        try {
			myApp.readLinesFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public void readLinesFromFile() throws IOException{
    	ClassLoader classLoader = this.getClass().getClassLoader();
    	String filePath = classLoader.getResource("LOCCount.txt").getFile();
    	LOCCounter fileCounter = new LOCCounter();
    	fileCounter.countLines(filePath);
    	
    }
    
}
