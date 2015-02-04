package edu.uniandes.ecos.psp0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileInput {
	
	private File inputFile = null;
	
	private LinkedList<Double> valuesFromFile;
	
	/**
	 * Public constructor
	 */
	public FileInput(){
		valuesFromFile = new LinkedList<Double>();
	}
	
	/**
	 * This method tries to retrieve the file specified by fileName parameter.
	 * If this file could not be retrieved, an Exception is thrown
	 * @param fileName The file to be retrieved
	 * @throws IOException If file could not be retrieved
	 */
	public boolean getFile(String fileName) {
		
		if(fileName == null || fileName.isEmpty()){
			System.out.println("No hay archivo especificado!");
			return false;
		}
		
		inputFile = new File(fileName);
		if(!inputFile.exists()){
			System.out.println("El archivo " + fileName + " no existe");
			return false;
		}
		return true;
	}
	
	/**
	 * This class retrieves the numbers from a specified file.
	 */
	public void readNumbersFromFile(){
		if(inputFile == null || !inputFile.exists()){
			System.out.println("No se puede leer. El archivo no existe!");
			return;
		}
		
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader buffer = new BufferedReader(fileReader);
			String nextLine = null;
			Double nextNumber = 0D;
			while((nextLine = buffer.readLine()) != null){
				try{
					nextNumber = Double.parseDouble(nextLine);
					valuesFromFile.add(nextNumber);
					System.out.println("Added number: " + nextLine);
				} catch (Exception ex){
					System.out.println("Invalid number: " + nextLine);
				}
			}
			buffer.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Error al intentar leer el archivo!");
			e.printStackTrace();
		} 
	}
	
	/**
	 * This method retrieves the current instance of the numbers linked list
	 * @return The current numbers list
	 */
	public LinkedList<Double> getValuesFromFile(){
		return this.valuesFromFile;
	}

}
