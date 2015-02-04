package edu.uniandes.ecos.psp01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class lists and read the source files from a prespecified
 * path and then passes each file to the LOC counter. At the end, the program
 * LOC counter consolidates the statistics for all files
 * @author Daniel Felipe
 *
 */
public class ProgramLOCCounter {
	
	private File workingDirectory;
	
	private int totalLines;
	
	private int effectiveLines;
	
	private ArrayList<File> filesToCount;
	
	private LOCCounter fileLocCounter;
	 
	
	public ProgramLOCCounter(){
		workingDirectory = null;
		filesToCount = new ArrayList<File>();
		totalLines = 0;
		effectiveLines = 0;
	}
	
	/**
	 * Initializes the current working directory
	 * @param dirPath The path to the current directory
	 * @return <code>true</code> if directory exists. 
	 * <code>false</code> otherwise
	 */
	protected boolean initDirectory(String dirPath){
		workingDirectory = new File(dirPath);
		return (workingDirectory.exists() && workingDirectory.isDirectory());
	}
	
	/**
	 * This method retrieves a <code>FilenameFilter</code> instance
	 * to retrieve the Java source files from a directory. 
	 * @return
	 */
	private FilenameFilter getJavaFileFilter(){
		return new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		};
	}
	
	public int getProjectTotalLines(){
		return this.totalLines;
	}
	
	public int getProjectEffectiveLines(){
		return this.effectiveLines;
	}
	
	public void sendToLOCCounter(String dirName){
		
		Path currentPath = Paths.get(dirName);
		String absolutePath = currentPath.toAbsolutePath().toString();
		if(!initDirectory(absolutePath)){
			System.out.println("ProgramLOCCounter: Error loading current work directory");
			return;
		}
		System.out.println("ProgramLOCCounter: Project Path: " + absolutePath);
		try {
			retrieveFiles(absolutePath);
			//Counts the LOC inside project
			for(File javaFile : filesToCount){
				fileLocCounter = new LOCCounter();
				fileLocCounter.countLines(javaFile.getAbsolutePath());
				totalLines += fileLocCounter.getTotalLines();
				effectiveLines += fileLocCounter.getEfffectiveLines();
			}
			
			System.out.println("--- Project Statistics ---");
			System.out.println("Total Lines of Code: " + totalLines);
			System.out.println("Effective Lines of Code: " + effectiveLines);
			
		} catch (IOException e) {
			System.out.println("ProgramLOCCounter: Error trying to count LOC for Project");
			e.printStackTrace();
			return;
		}
		
		
	}
	
	/**
	 * Retrieves recursively all Java files from given path
	 * @param filePath The directory path to retrieve Java files
	 * @throws IOException
	 */
	public void retrieveFiles(String filePath) throws IOException{
		File theFile = new File(filePath);
		File[] filesInside = theFile.listFiles();
		
		for(File nextFile : filesInside){
			if(nextFile.isDirectory()){
				retrieveFiles(nextFile.getAbsolutePath());
			}
		}
		File[] javaFiles = theFile.listFiles(getJavaFileFilter());
		for(File nextJavaFile : javaFiles){
			filesToCount.add(nextJavaFile);
		}
	}

}
