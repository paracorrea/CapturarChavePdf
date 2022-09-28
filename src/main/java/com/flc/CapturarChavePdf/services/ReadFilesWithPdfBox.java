package com.flc.CapturarChavePdf.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadFilesWithPdfBox {

	private static List<String> filesList = new ArrayList<>();
	
	
	public static void findAllFiles(String file ) throws IOException {
		
		File file1 = new File(file);
		
		FileInputStream stream = new FileInputStream(file1);
		InputStreamReader reader = new InputStreamReader(stream);
		
		try (BufferedReader br = new BufferedReader(reader)) {
			
			//String linha ="";
			String linha = br.readLine();
			

			while(linha != null) {
			   // LOG.info("Linha capturada: "+linha);
			   
				
				
			    System.out.println("primeiro PDF = "+linha);
			    
			    
				
				 PDFParser parser = new PDFParser(new
				 RandomAccessBufferedFileInputStream(linha)); 
				 parser.parse(); 
				 COSDocument cosDoc = parser.getDocument(); 
				 PDFTextStripper pdfStripper = new PDFTextStripper(); 
				 PDDocument pdDoc = new PDDocument(cosDoc); 
				 for (int i = 1;i <= pdDoc.getNumberOfPages(); i++) { 
					 pdfStripper.setStartPage(i);
					 pdfStripper.setEndPage(i);
					 String parsedText = pdfStripper.getText(pdDoc);
					 System.out.println("Página " + i + ": " + parsedText);
				 
				  }
				 linha = br.readLine();
			    
			}
		}
		
		
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		String file = new String("C:\\folders\\output.txt");
		findAllFiles(file);

	}

	
	
	
}
