package com.flc.CapturarChavePdf.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFilesWithPdfBox {


	public static void findAllFiles(String file ) throws IOException {
		
		File file1 = new File(file);
		
		FileInputStream stream = new FileInputStream(file1);
		InputStreamReader reader = new InputStreamReader(stream);
		
		try (BufferedReader br = new BufferedReader(reader)) {
			String linha = br.readLine();
		}
		
		
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		String file = new String("C:\\folders\\output.txt");
		findAllFiles(file);

	}

	
	
	
}
