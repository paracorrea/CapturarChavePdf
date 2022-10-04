package com.flc.CapturarChavePdf.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



public class GetOcrOfImage {

 static String imput = "C:\\folders\\init\\txts\\imgsAndKeys.txt";
 Path output = Paths.get("C:\\folders\\init\\txts\\ImgsAndKeysOcr.txt");
 static String nameFinal ="";
 static String keyFinal="";
 List<String> filesList = new ArrayList<>();
 String[] nomeNovo;	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String fileTxtWithNamesOfFiles = new String(imput);
		getPartsOfFiles(fileTxtWithNamesOfFiles);
	}


	
	private static void getPartsOfFiles(String file) throws FileNotFoundException {
		
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		try (BufferedReader br = new BufferedReader(reader)) {
			
			String linha = br.readLine();
			
			while (linha != null) {
			
				String[] partes = linha.split(";");
				
				nameFinal=partes[0];
				keyFinal=partes[1];
				
				if (partes[1].contains("00000000000000000000000000000000000000000000") || partes[1].length()<44 ) {
					
					
					getOcrFromImage(partes[0]);
					
					
				}
				
				
				
				
				linha = br.readLine();
				
				
			}
			
			
		} catch (IOException e) {
			// try do bufferReader, 
			e.printStackTrace();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//getOcrFromImage(partes[0]);
	}
	
	




	private static void getOcrFromImage(String parte1) throws TesseractException {
		
		
				
		
		File image = new File(parte1);
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C:\\folders\\init\\txts");
		tesseract.setLanguage("por");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		String result = tesseract.doOCR(image);
			
		System.out.println(result);
		}
			
			
			

	
	
	}


	

