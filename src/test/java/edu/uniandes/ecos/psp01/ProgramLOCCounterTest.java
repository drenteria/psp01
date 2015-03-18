package edu.uniandes.ecos.psp01;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProgramLOCCounterTest extends TestCase {
	
	private ProgramLOCCounter programCounter;
	
	private static final String PROJECT_NAME = "PSP0";
	
	public ProgramLOCCounterTest(String testName){
		super(testName);
		programCounter = new ProgramLOCCounter(PROJECT_NAME);
	}
	
	public static Test suite(){
		return new TestSuite(ProgramLOCCounterTest.class);
	}
	
	public void testProgramLOCCount(){
		programCounter.sendToLOCCounter("src/test/resources/P1");
		assertEquals(PROJECT_NAME, programCounter.getProjectName());
		assertEquals(246, programCounter.getProjectTotalLines());
		assertEquals(159, programCounter.getProjectEffectiveLines());
		assertEquals(3, programCounter.getCountersList().size());
	}

}
