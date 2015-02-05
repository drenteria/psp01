package edu.uniandes.ecos.psp01;

public class ConsoleOutput {

	public static void main(String[] args) {
		
		System.out.println("Project 1");
		ProgramLOCCounter project1Counter = new ProgramLOCCounter();
		project1Counter.sendToLOCCounter("src/main/resources/P1");
		System.out.println("Project Summary:");
		System.out.println("Total Lines: " + project1Counter.getProjectTotalLines());
		System.out.println("Effective Lines: " + project1Counter.getProjectEffectiveLines());
		
		System.out.println();
		System.out.println("Project 2");
		ProgramLOCCounter project2Counter = new ProgramLOCCounter();
		project2Counter.sendToLOCCounter("src/main/resources/P2");
		System.out.println("Project Summary:");
		System.out.println("Total Lines: " + project2Counter.getProjectTotalLines());
		System.out.println("Effective Lines: " + project2Counter.getProjectEffectiveLines());

	}

}
