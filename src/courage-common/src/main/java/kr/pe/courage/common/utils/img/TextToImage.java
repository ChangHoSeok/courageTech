package kr.pe.courage.common.utils.img;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import kr.pe.courage.common.utils.file.FileUtil;

public class TextToImage {
	public boolean createTextToImage(String text, String fontName, int fontSize, int width, int height, File outputFile) {
		boolean resultFlag = false;
		Graphics2D graphic = null;
		try {
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			graphic = bufferedImage.createGraphics();
			Font font = new Font(fontName, Font.PLAIN, fontSize);
			
			graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphic.setColor(Color.WHITE);
			graphic.fillRect(0, 0, width, height);
			
			graphic.setFont(font);
			graphic.setColor(Color.BLACK);
			
			Dimension dimenstion = new Dimension(width, height);
			FontMetrics fm = graphic.getFontMetrics();
			
			int x = (dimenstion.width - fm.stringWidth(text)) / 2;
			int y = (fm.getAscent() + (dimenstion.height - (fm.getAscent() + fm.getDescent())) / 2);
			
			graphic.drawString(text, x, y);
			graphic.dispose();
			
			FileUtil.folderCreate(outputFile.getPath());
			ImageIO.write(bufferedImage, "BMP", outputFile);
			
			resultFlag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(graphic != null)
				try{graphic.dispose();}catch(Exception e){}
		}
		
		return resultFlag;
	}
}
