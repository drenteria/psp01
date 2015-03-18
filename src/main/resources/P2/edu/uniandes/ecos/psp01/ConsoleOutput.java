package edu.uniandes.ecos.psp01;

import java.util.ListIterator;

public class ConsoleOutput {

	public static void main(String[] args) {
		
		ProgramLOCCounter project1Counter = new ProgramLOCCounter("Project 1 - PSP0");
		project1Counter.sendToLOCCounter("src/main/resources/P1");
		System.out.println("Project Summary: " + project1Counter.getProjectName());
		System.out.println("Total Lines: " + project1Counter.getProjectTotalLines());
		System.out.println("Effective Lines: " + project1Counter.getProjectEffectiveLines());
		System.out.println();
		ListIterator<LOCCounter> counterIterator = project1Counter.getCountersList().listIterator();
		while(counterIterator.hasNext()){
			LOCCounter currentCounter = counterIterator.next();
			System.out.println("File Name: " + currentCounter.getSourceFileName());
			System.out.println("Effective Lines: " + currentCounter.getEfffectiveLines());
			System.out.println("Method Count: " + currentCounter.getMethodCount());
			System.out.println("Method Names:");
			System.out.println(currentCounter.getMethodNamesSummary());
		}
		System.out.println(" ------------- ");
		
		ProgramLOCCounter project2Counter = new ProgramLOCCounter("Project 2 - PSP01");
		project2Counter.sendToLOCCounter("src/main/resources/P2");
		System.out.println("Project Summary: " + project2Counter.getProjectName());
		System.out.println("Total Lines: " + project2Counter.getProjectTotalLines());
		System.out.println("Effective Lines: " + project2Counter.getProjectEffectiveLines());
		System.out.println();
		counterIterator = project2Counter.getCountersList().listIterator();
		while(counterIterator.hasNext()){
			LOCCounter currentCounter = counterIterator.next();
			System.out.println("File Name: " + currentCounter.getSourceFileName());
			System.out.println("Effective Lines: " + currentCounter.getEfffectiveLines());
			System.out.println("Method Count: " + currentCounter.getMethodCount());
			System.out.println("Method Names:");
			System.out.println(currentCounter.getMethodNamesSummary());
		}
		System.out.println(" ------------- ");
	}

}
