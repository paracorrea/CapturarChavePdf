package com.flc.CapturarChavePdf.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class OutroTeste {

	public static void main(String[] args) {
	
		readQRCode();
	
	}
		
		public static String readQRCode(String filePath, String charset, Map hintMap)
				throws FileNotFoundException, IOException, NotFoundException {
				    Map < DecodeHintType, Object > tmpHintsMap = new EnumMap < DecodeHintType, Object > (
				        DecodeHintType.class);

				    //tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.FALSE);
				    //tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
				    tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

				    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				        new BufferedImageLuminanceSource(
				            ImageIO.read(new FileInputStream(filePath)))));

				    Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, tmpHintsMap);

				    System.out.println(qrCodeResult.getText());
				
	}
}