package com.devProgram.javaCoreAssessment.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

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

   public static void main(String args[]) throws IOException {

        // convert BufferedImage to byte[]
        byte[] bytes = convertImageByte(new URL("https://s-media-cache-ak0.pinimg.com/236x/ac/bb/d4/acbbd49b22b8c556979418f6618a35fd.jpg"));

        // convert the byte[] back to BufferedImage
        BufferedImage newBi = toBufferedImage(bytes);

        // save it 
        ImageIO.write(newBi, "png", new File("C:\\"));

    }
}
