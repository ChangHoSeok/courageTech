package kr.pe.courage.common.utils.img;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class CompressJpeg {
	public String compressJpegFile(String infile, String outfile, float compressionQuality) {
		String error = "";
		ImageOutputStream ios = null;
		File dest = new File(infile);
		File target = new File(outfile);
		
	    try {
	        RenderedImage rendImage = ImageIO.read(dest);

	        ImageWriter writer = null;
	        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
	        if (iter.hasNext()) {
	            writer = (ImageWriter)iter.next();
	        }

	        ios = ImageIO.createImageOutputStream(target);
	        writer.setOutput(ios);

	        ImageWriteParam iwparam = new UserImageWriteParam();
	        iwparam.setCompressionMode(ImageWriteParam.MODE_DEFAULT) ;
	        iwparam.setCompressionQuality(compressionQuality);

	        writer.write(null, new IIOImage(rendImage, null, null), iwparam);

	        ios.flush();
	        writer.dispose();
	        ios.close();
	    } catch (IOException e) {
	    	error = e.toString();
	    }
	    
	    return error;
	}
}
