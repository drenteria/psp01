package edu.uniandes.ecos.psp01;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LOCCounterTest extends TestCase {
	
	private LOCCounter theCounter;
	
	public LOCCounterTest(String testName){
		super(testName);
		theCounter = new LOCCounter();
	}
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(LOCCounterTest.class);
    }
    
    private String getTestFilePath(){
    	ClassLoader classLoader = this.getClass().getClassLoader();
    	return classLoader.getResource("LOCCountTest.txt").getFile();
    }
    
    /**
     * Test the initialization for file
     */
    public void testInitFile(){
    	assertFalse(theCounter.initializeFile("NoFileProvided.txt"));
    	assertTrue(theCounter.initializeFile(getTestFilePath()));
    }
    
    /**
     * Test the line count functions
     */
    public void testCountLines(){
    	 try {
			theCounter.countLines(getTestFilePath());
			assertEquals(12, theCounter.getTotalLines());
			assertEquals(6, theCounter.getEfffectiveLines());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
    }

}
