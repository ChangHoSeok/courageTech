package kr.pe.courage.common.utils.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {
	public byte[] resizeImage(byte[] imageContent, int x, int y, int width, int height) throws IOException {
		BufferedImage originalImg = ImageIO.read(new ByteArrayInputStream(imageContent));
		BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics2D = targetImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, width, height);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(originalImg,
				0, 0, width, height,
				null);
		graphics2D.dispose();
		
		ByteArrayOutputStream output = null;
		byte resizeImage[] = null;
		
		try {
			output = new ByteArrayOutputStream();
			ImageIO.write(targetImage, "png", output);
			resizeImage = output.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
				output = null;
			}
		}
		
		return resizeImage;
	}
}
