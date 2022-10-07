package com.flc.CapturarChavePdf.services;

// this is second program


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;


public class ReadFilesWithPdfBox {

	/*
	 * this class ready the file pdfs.txt and ready line per line opening the files pdf
	 * composer a name file with initial string of name, folder and page of the pdf read. by sample:
	
		C:\folders\2022.01
		C:\folders\2022.01\05 a 07.01 - Unimax.pdf
		C:\folders\2022.01\10.01 - Unimax RH.pdf
		C:\folders\2022.01\10.01 - Unimax..pdf
		C:\folders\2022.01\11.01 - Unimax..pdf
		...
	 
	 * finally save every page like one image file and in another page
	 * The final, by example, is "2022.01-05 a 07.01 - Unimax-1.png"
	 * and next, if have another page: "2022.01-05 a 07.01 - Unimax-2.png"
	 */

		private static Path output = Paths.get("C:\\folders\\init\\txts\\imgs.txt");
		private static List<String> filesList = new ArrayList<>();	 	
	
	public static void findAllFiles(String file ) throws IOException {
		
		/*
		 * This method recive from main method a string contend full path of the "output.txt" file
		 * in the file, your content need have a list of file. this method is called from main  
		 */

		FileInputStream stream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(stream);
		
		try (BufferedReader br = new BufferedReader(reader)) {
			
			// this part of code, declare vars strings for composer name of file
			String fileName = br.readLine();
			String extension = "png";
			String nomeDir ="";
			String nomeArq ="";
			int tamanhoNomeDir = 0;
			//String nomeDirFull = ""; fileName.substring(0, 20);
			
			while(fileName != null) {
						   
			   File newFileName = new File(fileName);
			   
				if (!newFileName.isFile()) {
					
					// if file not a file, is a folder. We need get name folder in nomeDir
					fileName = br.readLine();
					nomeDir = fileName.substring(21, 28);
					
				}
				
				// here is verify file really is a file. So get the part of name plus name of folder
				tamanhoNomeDir = (fileName.length())-4;
				nomeArq=nomeDir+"-"+fileName.substring(29, tamanhoNomeDir);
				
							 
				PDDocument document = PDDocument.load(new File(fileName));
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				
				
				// in the for, get number of pages on the each file, and open, get page by page and save the page like a image
				// the name of page is compose by the name folder and with the name file, a -, and one extension.
				// this code can upgrade for path get from string or another configuration
				for (int page = 0; page < document.getNumberOfPages(); ++page) {
			        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			        
			        filesList.add(String.format("c://folders/init/imgs//%s-%d.%s",nomeArq, page + 1, extension));
			        ImageIOUtil.writeImage(bim,String.format("c://folders/init/imgs//%s-%d.%s",nomeArq, page + 1, extension), 300);
			     }
			    
				document.close();
				
				//next line of file
				fileName = br.readLine();
			} // {Close while}
			//Files.write(output, filesList);
		} // {close try}
		Files.write(output, filesList);
	} // {close method findAllFiles}
	
	public static void main(String[] args) throws IOException {
		
		// output.txt is file and folder with name of files PDFs for make image files of each page 
		String fileTxtWithNamesOfFiles = new String("C:\\folders\\init\\txts\\pdfs.txt");
		findAllFiles(fileTxtWithNamesOfFiles);

	}

}
