package com.flc.CapturarChavePdf.services;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
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

	public static void main(String[] args) {
		
		File fimage = new File("C:\\tempor\\2022.01-05 a 07.01 - Unimax-8.png");
		BufferedImage bufImage;
		try {
			bufImage = ImageIO.read(fimage);
			LuminanceSource source = new BufferedImageLuminanceSource(bufImage);
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Reader reader = new MultiFormatReader();
			try {
			
			Map<DecodeHintType,Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
	           tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
	            //tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
	           // tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
				Result result =  reader.decode(bitmap, tmpHintsMap);
				
				System.out.println(result.getText().toString());
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


	}


