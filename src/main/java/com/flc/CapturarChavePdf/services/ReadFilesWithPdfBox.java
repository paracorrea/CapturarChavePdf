package com.flc.CapturarChavePdf.services;

import java.awt.image.BufferedImage;
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
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class ReadFilesWithPdfBox {

	private static List<String> filesList = new ArrayList<>();
	
	
	public static void findAllFiles(String file ) throws IOException {
		
		//File file1 = new File(file);
		
		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		
		try (BufferedReader br = new BufferedReader(reader)) {
			
			//String linha ="";
			String fileName = br.readLine();
			System.out.println("primeiro PDF = "+fileName);
			String extension = "png";
			String nomeDir ="";
			String nomeArq ="";
			int tamanhoNomeDir = 0;
			
			while(fileName != null) {
			   // LOG.info("Linha capturada: "+linha);
			   
			  
			   File file1 = new File(fileName);
			   
			   
			 
				if (!file1.isFile()) {
					
					fileName = br.readLine();
					nomeDir = fileName.substring(11, 18);
					System.out.println("primeiro PDF = "+nomeDir);
				}
				
				tamanhoNomeDir = (fileName.length())-4;
				nomeArq=nomeDir+"-"+fileName.substring(19, tamanhoNomeDir);
				
				// PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(fileName)); 
				// parser.parse(); 
				// COSDocument cosDoc = parser.getDocument(); 
				// PDFTextStripper pdfStripper = new PDFTextStripper(); 
				// PDDocument pdDoc = new PDDocument(cosDoc); 
				 
				PDDocument document = PDDocument.load(new File(fileName));
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				
				for (int page = 0; page < document.getNumberOfPages(); ++page) {
			        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			        ImageIOUtil.writeImage(bim, 
			          String.format("c://tempor//%s-%d.%s",nomeArq, page + 1, extension), 300);
			         //String.format("src/output/pdf-%d.%s", page + 1, extension), 300);
			    }
			    document.close();
				
				/*
				 * for (int i = 1;i <= pdDoc.getNumberOfPages(); i++) {
				 * pdfStripper.setStartPage(i); pdfStripper.setEndPage(i); String parsedText =
				 * pdfStripper.getText(pdDoc); System.out.println("Página " + i + ": " +
				 * parsedText);
				 * 
				 * }
				 */
				 fileName = br.readLine();
				// cosDoc.close();
				// pdDoc.close();
			}
		}
		
		
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		String file = new String("C:\\folders\\output.txt");
		findAllFiles(file);

	}

	
	
	
}
