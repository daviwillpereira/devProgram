package com.devProgram.javaCoreAssessment;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageUtils {
	
	public static byte[] convertImageByte(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = new BufferedInputStream(url.openStream());
			byte[] byteChunk = new byte[4096];
			int n;
			while ((n = is.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return null;
	}

    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi, String format)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes)
        throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

   public static void popUpProductImage(byte[] image) throws IOException {

       // BufferedImage bi = ImageIO.read(new File("/Users/daviwillpereira/Desktop/gil.png"));

        // convert BufferedImage to byte[]
        byte[] bytes = convertImageByte(new URL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tudocelular.com%2Fcuriosidade%2Fnoticias%2Fn118802%2Fnicolas-cage-midias-sociais.html&psig=AOvVaw0S9Icw3iw-XsIrxt1VJ-n0&ust=1616968270905000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCICW_6660e8CFQAAAAAdAAAAABAD"));

        //encode the byte array for display purpose only, optional
        String bytesBase64 = Base64.encodeBase64String(bytes);
        
        System.out.println(bytesBase64);

        // decode byte[] from the encoded string
        byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);

        // convert the byte[] back to BufferedImage
        BufferedImage newBi = toBufferedImage(bytesFromDecode);

        // save it somewhere
        ImageIO.write(newBi, "png", new File("/Users/daviwillpereira/Desktop/result.png"));

    }
}
