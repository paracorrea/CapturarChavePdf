package com.flc.CapturarChavePdf.services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


public class CapturarCodigoDaImagem {

	private static String imput = "C:\\folders\\init\\txts\\imgs.txt";
	private static Path output = Paths.get("C:\\folders\\init\\txts\\imgsAndKeys.txt");
	private static String nameFinal ="";
	private static List<String> filesList = new ArrayList<>();
	//static File file = null;
		
	public static void main(String[] args) throws IOException {
		
		
		String fileTxtWithNamesOfFiles = new String(imput);
		getLineFromFileImages(fileTxtWithNamesOfFiles);
		
		
	}
	
	public static String getLineFromFileImages(String fileImg ) {
		
		
		File file = new File(fileImg);
		FileInputStream stream=null;
		
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo inexistente: "+e);
			
		}
		InputStreamReader reader = new InputStreamReader(stream);
		
		BufferedReader br = new BufferedReader(reader);
		
		try {
			String fileName = br.readLine();
			
			while (fileName != null) {
				
				
				nameFinal=fileName+";";
				System.out.println(nameFinal);
				getBarCode(fileName);
				fileName = br.readLine();
				
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Nada encontrado na linha do arquivo: "+e);
		} catch (ChecksumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	  public static void getBarCode(String file) throws ChecksumException, FormatException {
	  
		File file1 = new File(file);
	   	BufferedImage bufImage = null;
	  
	   	try {
			bufImage = ImageIO.read(file1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		LuminanceSource source = new BufferedImageLuminanceSource(bufImage);
 	 
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); 
		
		
		Reader reader = new MultiFormatReader();
  
		if (bitmap.getWidth() < bitmap.getHeight()) {
			
			if (bitmap.isRotateSupported())	{ 
				bitmap = bitmap.rotateCounterClockwise(); 
			} 
		}
  
		
		
		try {
			Map<DecodeHintType,Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class); 
			tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE); 
			tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS,EnumSet.allOf(BarcodeFormat.class));
			tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
			
			Result result = reader.decode(bitmap, tmpHintsMap);
  
			String barCode = result.getText().toString();
			//System.out.println("Result: "+result.getText().toString());
  
			if (barCode.length()<40) {
				barCode="00000000000000000000000000000000000000000000";
			}
  
			filesList.add(nameFinal+barCode);
			
			
		}  catch (NotFoundException e) { 
			// TODO Auto-generated catch block
			System.out.println("não foi possivel converter o arquivo em imagem: "+e);
			filesList.add(nameFinal+"00000000000000000000000000000000000000000000");
			//e.printStackTrace();
			
  	}
	  
	  try { 
		  Files.write(output, filesList);
	   }  catch (Exception e) { e.printStackTrace(); 
	   
	   }
	   
	  
	  }
	  
	  
	 
	  
	  
	  
	
	  }




