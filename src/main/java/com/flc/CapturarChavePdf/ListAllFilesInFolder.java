package com.flc.CapturarChavePdf;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//; This is initial program
// This Class, ready all file and all folder in one folder initial, and save all names in the file output.txt



@SpringBootApplication
public class ListAllFilesInFolder {

	private static Path output = Paths.get("C:\\folders\\init\\txts\\pdfs.txt");
	private static List<String> filesList = new ArrayList<>();
	
	
	public static void main(String[] args) throws ParseException {
		
		// initial folder where are all folders with pfds files
		
		
		
		File folder = new File("C:\\folders\\init\\pdfs");
					
		findAllFilesInFolder(folder);
		
		//System.out.println("Pasta inexistente. Crie a pasta primeiro e copie os arquivos");
		
	}
	
	
	public static void findAllFilesInFolder(File folder) {
		
		
		for (File file : folder.listFiles()) {
			
			if (!file.isDirectory()) {
				filesList.add(file.toString());
				System.out.println(file.getName());
			} else {
				System.out.println("Folder...:"+file.getName());
				filesList.add(file.toString());
				findAllFilesInFolder(file);
			}
		}
		
	    try {
	    	
	    	// files.write Write a file txt with all pdfs founds
	        Files.write(output, filesList);
	        System.out.println(output.toFile().getAbsolutePath());
	    } catch (Exception e) {
	        System.out.println("Erro ao gravar o arquivo: "+e);
	    }
	}

}
	

