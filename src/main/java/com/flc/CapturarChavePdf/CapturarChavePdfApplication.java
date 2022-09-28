package com.flc.CapturarChavePdf;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapturarChavePdfApplication {

	private static Path output = Paths.get("C:\\folders\\output.txt");
	private static List<String> filesList = new ArrayList<>();
	
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
	        Files.write(output, filesList);
	        System.out.println(output.toFile().getAbsolutePath());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void main(String[] args) throws ParseException {
		File folder = new File("C:\\folders");
		findAllFilesInFolder(folder);
	}
}
	

