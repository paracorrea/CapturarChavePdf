package TestCamelDependency;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BarcodeImageDecoder {

	public static void main(String[] args) throws BarcodeDecodingException, FileNotFoundException {
		
		File file = new File("C:\\tempor\\2022.01-05 a 07.01 - Unimax-8.png");
		InputStream stream = new FileInputStream(file);
		//InputStreamReader reader = new InputStreamReader(stream);
		
		decodeImage(stream);
		// TODO Auto-generated method stub

	}

    public static BarcodeInfo decodeImage(InputStream inputStream) throws BarcodeDecodingException {
        try {
        	  BufferedImage image;
        	     image = ImageIO.read(inputStream);
        	     BufferedImage cropedImage = image.getSubimage(0, 0, 914, 400);
        	     
        	     
        	     // using the cropedImage instead of image
        	     LuminanceSource source = new BufferedImageLuminanceSource(cropedImage);
          
        	     BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        	     // barcode decoding
        	     QRCodeReader reader = new QRCodeReader();
        	     Result result = null;
        	     
            if (bitmap.getWidth() < bitmap.getHeight()) {
                if (bitmap.isRotateSupported()) {
                    bitmap = bitmap.rotateCounterClockwise();
                }
            }
            return decode(bitmap);
        } catch (IOException e) {
            throw new BarcodeDecodingException(e);
        }
    }
	
    private static BarcodeInfo decode(BinaryBitmap bitmap) throws BarcodeDecodingException {
        Reader reader = new MultiFormatReader();
        try {
            Result result = reader.decode(bitmap);
            return new BarcodeInfo(result.getText(), result.getBarcodeFormat().toString());
        } catch (Exception e) {
            throw new BarcodeDecodingException(e);
        }
    }
    
    public static class BarcodeInfo {
        
        private final String text;
        private final String format;

        
        BarcodeInfo(String text, String format) {
            this.text = text;
            this.format = format;
        }


		public String getText() {
			return text;
		}


		public String getFormat() {
			return format;
		}
        
        
    }
    
    

    public static class BarcodeDecodingException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		BarcodeDecodingException(Throwable cause) {
            super(cause);
        }
    }
}
