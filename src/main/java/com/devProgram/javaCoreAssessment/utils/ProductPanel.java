package com.devProgram.javaCoreAssessment.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.devProgram.javaCoreAssessment.repositories.ProductRepository;

public class ProductPanel extends JPanel {

	BufferedImage bufferedImg;

	@Autowired
	ProductRepository productRepository;

	public ProductPanel() {

		setSize(600, 600);
		setVisible(true);

	}

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

	// convert byte[] to BufferedImage
	public static BufferedImage toBufferedImage(byte[] bytes) throws IOException {

		InputStream is = new ByteArrayInputStream(bytes);

		BufferedImage bi = ImageIO.read(is);

		return bi;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		byte[] imgByte = null;

		try {
			imgByte = convertImageByte(new URL(
					"https://s-media-cache-ak0.pinimg.com/236x/ac/bb/d4/acbbd49b22b8c556979418f6618a35fd.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			bufferedImg = toBufferedImage(imgByte);
			if(bufferedImg == null)
				throw new IOException();
		} catch (IOException e) {
			e.printStackTrace();
		}

		graphics.drawImage(bufferedImg, 0, 0, 600, 600, this);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(600, 600);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new ProductPanel());
			}
		});
	}

}
