package kr.pe.courage.common.utils.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <pre>
 * kr.pe.courage.common.utils.img
 * ImageCroper.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 10. 23.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 10. 23., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class ImageCroper {
	public byte[] cropImage(byte[] imageContent, int x, int y, int width, int height) throws IOException {
		BufferedImage originalImg = ImageIO.read(new ByteArrayInputStream(imageContent));
		BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics2D = targetImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, width, height);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(originalImg,
				0, 0, width, height,
				x, y, x + width, y + height,
				null);
		graphics2D.dispose();
		
		ByteArrayOutputStream output = null;
		byte cropImage[] = null;
		
		try {
			output = new ByteArrayOutputStream();
			ImageIO.write(targetImage, "png", output);
			cropImage = output.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
				output = null;
			}
		}
		
		return cropImage;
	}
}
