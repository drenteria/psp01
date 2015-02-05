package edu.uniandes.ecos.psp01;

public class ConsoleOutput {

	public static void main(String[] args) {
		
		ProgramLOCCounter project1Counter = new ProgramLOCCounter();
		project1Counter.sendToLOCCounter("src/main/resources/P1");
		
		ProgramLOCCounter project2Counter = new ProgramLOCCounter();
		project1Counter.sendToLOCCounter("src/main/resources/P2");

	}

}
