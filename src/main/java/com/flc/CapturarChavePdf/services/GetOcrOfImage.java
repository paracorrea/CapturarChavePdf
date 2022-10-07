package com.flc.CapturarChavePdf.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



public class GetOcrOfImage {

 static String imput = "C:\\folders\\init\\txts\\imgsAndKeys.txt";
 static Path output = Paths.get("C:\\folders\\init\\txts\\jaguaryLab2022.txt");
 static String nameFinal ="";
 static String keyFinal="";
 static List<String> filesList = new ArrayList<>();
 static Boolean status=true;
 String[] nomeNovo;	
	
	public static void main(String[] args) throws IOException {
		
		String fileTxtWithNamesOfFiles = new String(imput);
		getPartsOfFiles(fileTxtWithNamesOfFiles);
	}


	
	private static void getPartsOfFiles(String file) throws IOException {
		
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		try (BufferedReader br = new BufferedReader(reader)) {
			
			String linha = br.readLine();
			
			while (linha != null) {
			
				String[] partes = linha.split(";");
				
				nameFinal=partes[0];
				
			
				if (partes[1].contains("00000000000000000000000000000000000000000000") || partes[1].length()<44 ) {
					
					
					getOcrFromImage(partes[0]);
					
					
				}
				
				if (status) {
					keyFinal=partes[1];
				}
				
				filesList.add(nameFinal+";"+keyFinal);
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
		
		Files.write(output, filesList);
	}
	
	




	private static void getOcrFromImage(String parte1) throws TesseractException {
		
		
				
		String fileName=parte1;
		File image = new File(parte1);
		Tesseract tesseract = new Tesseract();
		// versao windows 2 - trocar eu por fernando.correa
		tesseract.setDatapath("C:\\Users\\eu\\AppData\\Local\\Tesseract-OCR\\tessdata");
		tesseract.setLanguage("por");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		String result = tesseract.doOCR(image);
		System.out.println("---------------------------------------------");
		System.out.println("Nome do arquivo :"+fileName);
		System.out.println("---------------------------------------------");
		//System.out.println(result);
		
		String nf[] = result.split("\n");
		status=true;
		for ( int i =0; i <nf.length; i++) {
			String result1 = nf[i].toString().toUpperCase().replaceAll("\\s+","");
			//System.out.println("LInha: "+i+ ":" +result1+":");
			
			Pattern pattern = Pattern.compile("\\d{38,44}"); 
			Matcher matcher = pattern.matcher(result1);
			
			if (matcher.find()) {
				
				 
		         MatchResult myResult = matcher.toMatchResult();
				 int start = myResult.start();
				 int end = myResult.end();
				
				
				String resultCaptured = result1.substring(start, end);
			
				
				String info ="Encontrada uma correspondência no arquivo: "+fileName+ " --> "+"LInha "+i;
				String info1 ="A string capturada foi: "+result1;
				
				System.out.println(info);
				System.out.println(info1);
				System.out.println("A chave encontrada foi --> "+resultCaptured);
				
				System.out.println("*******************************************************");
				
				
				status=false;
				
				 
				
				if (resultCaptured.length()<44) {
					System.out.println("*******************************************************");
					System.out.println("*******************************************************");
					info ="Encontrada uma correspondência no arquivo: "+fileName+ " --> "+"LInha "+i;
					info1 ="A string capturada foi: "+result1;
					
					
					System.out.println(info);
					System.out.println(info1);
					System.out.println("A chave encontrada foi --> "+resultCaptured);
					System.out.println("*******************************************************");
					System.out.println("Verifique este arquivo...");
					System.out.println("*******************************************************");
					
					resultCaptured = result1.substring(start,end)+";"+"Verifique este arquivo, pode conter uma chave";
				}
				
				keyFinal=resultCaptured;
			}
				
		}
 		
		

			
	}
	
	
	}


	

