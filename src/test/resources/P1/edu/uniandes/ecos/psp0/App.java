package edu.uniandes.ecos.psp0;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main Application
 */
public class App 
{
    public static void main( String[] args )
    {
    	App myApp = new App();
    	Scanner input = new Scanner(System.in);
        Integer option = 0;
    	
        System.out.println("Calculo de Media y Desviacion Estandar");
        System.out.println("-- Menu --");
        
        System.out.println("1. Entrada por consola");
        System.out.println("2. Lectura de archivo");
        System.out.println("Ingrese la opcion de entrada:");
        
        option = Integer.valueOf(input.nextLine());
        
        if(option < 1 || option > 2){
        	System.out.println("Opcion Invalida. Cerrando...");
        	System.exit(0);
        }
        else if(option == 1){
        	myApp.consoleInput();
        } 
        else if(option == 2){
        	myApp.fileInput();
        }
        input.close();
        
    }
    
    /**
     * Method to set the console input for the numbers
     */
    public void consoleInput(){
    	System.out.println("Por favor, ingrese la cantidad de numeros que desea ingresar:");
        Scanner input = new Scanner(System.in);
        String strElements = input.nextLine();
        Integer elements = 0;
        Double nextElement = 0D;
        LinkedList<Double> numbersList = new LinkedList<Double>();
        StatisticCalculator calculator = new StatisticCalculator();
        
        try{
        	elements = Integer.parseInt(strElements);
        } catch (NumberFormatException ex){
        	System.out.println("Entrada invalida. Cerrando...");
        	System.exit(0);
        }
        for(int i = 0; i < elements; i++){
        	System.out.println("Ingrese Numero " + i + ": ");
        	try{
        		nextElement = Double.valueOf(input.nextLine());
        		numbersList.add(nextElement);
        	} 
        	catch (Exception ex){
        		System.out.println("Entrada invalida. No agregada a la lista.");
        	}
        }
        
        calculator.setInputData(numbersList);
        System.out.println("Numeros ingresados efectivamente: " + numbersList.size());
        System.out.println("Lista: " + numbersList.toString());
        System.out.println("Media: " + calculator.calculateMean());
        System.out.println("Desviacion Estandar: " + calculator.calculateStdDev());
        input.close();
    }
    
    /**
     * Method to set the file input for the numbers
     */
    public void fileInput(){
    	ClassLoader classLoader = this.getClass().getClassLoader();
    	FileInput inputReader = new FileInput();
    	inputReader.getFile(classLoader.getResource("inputs.txt").getFile());
    	inputReader.readNumbersFromFile();
    	LinkedList<Double> values = inputReader.getValuesFromFile(); 
    	
    	StatisticCalculator calculator = new StatisticCalculator();
    	calculator.setInputData(values);
    	
    	System.out.println("Leyendo desde el archivo inputs.txt");
    	System.out.println("Lista: " + values.toString());
        System.out.println("Media: " + calculator.calculateMean());
        System.out.println("Desviacion Estandar: " + calculator.calculateStdDev());
    	
    }
    
}
