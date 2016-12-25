package kr.pe.courage.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import kr.pe.courage.common.utils.img.ImageThumbnail;

public class TestThumbImg {
	public static void main(String[] args) {
		ImageThumbnail imageThumb = new ImageThumbnail();
		
		File imageFile = new File("C:\\courage\\project\\20151024_080401354_iOS.jpg");
		
		try {
			byte[] orginFile = IOUtils.toByteArray(new FileInputStream(imageFile));
			byte[] thFile = imageThumb.generateImage(orginFile, 300, 1d);
			
			FileUtils.writeByteArrayToFile(new File(imageFile.getParent() + File.separator + "th_aaa.png"), thFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
