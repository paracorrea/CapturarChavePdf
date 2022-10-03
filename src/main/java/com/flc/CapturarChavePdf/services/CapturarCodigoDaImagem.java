package com.flc.CapturarChavePdf.services;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
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

	private static List<String> filesList = new ArrayList<>();
	private static Path output = Paths.get("C:\\tempor1\\teste.txt");
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\tempor1");
		
		
		for (File fimage : file.listFiles()) {
		
		
		BufferedImage bufImage;
		
		
		System.out.println("Arquivo encontrado foi: "+fimage.getName().toString());
		
		try {
			bufImage = ImageIO.read(fimage);
			LuminanceSource source = new BufferedImageLuminanceSource(bufImage);
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Reader reader = new MultiFormatReader();
			
            if (bitmap.getWidth() < bitmap.getHeight()) {
                if (bitmap.isRotateSupported()) {
                    bitmap = bitmap.rotateCounterClockwise();
                }
            }
			
			
			try {
			
				
				
			Map<DecodeHintType,Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
	           tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
	           tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
	           tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
			   Result result =  reader.decode(bitmap, tmpHintsMap);
				
			   System.out.println("Result: "+result.getText().toString());
			   
			   String nameFile=fimage.getName().toString();
			   
			   filesList.add(nameFile+"::"+result.getText().toString());
			   System.out.println("Nome e chave completo "+nameFile+"::"+result.getText().toString());
			   
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Código de barras não encontrado");
				e.printStackTrace();
			} catch (ChecksumException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
			
	}
		
	try {
		Files.write(output, filesList);
		System.out.println("Aqui passando"+output.toFile().getAbsolutePath());
	 } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		
	}

	}


