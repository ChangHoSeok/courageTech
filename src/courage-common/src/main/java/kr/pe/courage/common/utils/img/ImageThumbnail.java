package kr.pe.courage.common.utils.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageThumbnail {
	
	/**
	 * <pre>
	 * 1. 개요 : 이미지 썸네일 생성
	 * 원본 이미지의 width, height 중 큰 값을 기준으로 maxSize로 비율에 맞게 자른다.
	 * 위치는 Center 기준
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : generateImage
	 * @param imageContent
	 * @param maxSize
	 * @return
	 * @throws IOException
	 */
	public byte[] generateImage(byte[] imageContent, int maxSize) throws IOException {
		return this.generateImage(imageContent, maxSize, 1d);
	}
	 
	/**
	 * <pre>
	 * 1. 개요 : 이미지 썸네일 생성
	 * 원본 이미지의 width, height 중 큰 값을 기준으로 maxSize로 비율에 맞게 자른다.
	 * 지정된 비율을 적용하여 생성
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : generateImage
	 * @param imageContent
	 * @param maxSize
	 * @param xyRatio
	 * @return
	 * @throws IOException
	 */
	public byte[] generateImage(byte[] imageContent, int maxSize, double xyRatio) throws IOException {
		BufferedImage originalImg = ImageIO.read(new ByteArrayInputStream(imageContent));

		// get the center point for crop
		int[] centerPoint = { originalImg.getWidth() / 2, originalImg.getHeight() / 2 };

		// calculate crop area
		int cropWidth = originalImg.getWidth();
		int cropHeight = originalImg.getHeight();
		
		if (cropWidth > maxSize || cropHeight > maxSize) {
			if (cropHeight > cropWidth * xyRatio) {
				// long image
				cropHeight = (int) (cropWidth * xyRatio);
			} else {
				// wide image
				cropWidth = (int) ((float) cropHeight / xyRatio);
			}
		}
		
		// set target image size
		int targetWidth = cropWidth;
		int targetHeight = cropHeight;
		
		if (targetWidth > maxSize) {
			// too big image
			targetWidth = maxSize;
			targetHeight = (int) (targetWidth * xyRatio);
		} else if (targetHeight > maxSize) {
			targetHeight = maxSize;
			targetWidth = (int) (targetHeight * xyRatio);
		}
		
		int targetX1 = centerPoint[0] - (int) (cropWidth / 2);
		int targetY1 = centerPoint[1] - (int) (cropHeight / 2);
		int targetX2 = centerPoint[0] + (int) (cropWidth / 2);
		int targetY2 = centerPoint[1] + (int) (cropHeight / 2);

		// processing image
		BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = targetImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, targetWidth, targetHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(originalImg,
				0, 0, targetWidth, targetHeight,
				targetX1, targetY1, targetX2, targetY2,
				null);
		graphics2D.dispose();
		
		ByteArrayOutputStream output = null;
		byte thImage[] = null;
		
		try {
			output = new ByteArrayOutputStream();
			ImageIO.write(targetImage, "png", output);
			thImage = output.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
				output = null;
			}
		}
		
		return thImage;
	}
}
