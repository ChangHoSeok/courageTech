package kr.pe.courage.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kr.pe.courage.common.utils.img.ImageCroper;
import kr.pe.courage.common.utils.img.ImageResizer;

public class TestImage extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testImageCrop() {
		ImageCroper imageCroper = new ImageCroper();
		
		File imageFile = new File("C:\\courage\\project\\20160524_020048000_iOS.jpg");
		
		try {
			byte[] orginFile = IOUtils.toByteArray(new FileInputStream(imageFile));
			byte[] cropFile = imageCroper.cropImage(orginFile, 50, 32, 133, 175);
			
			FileUtils.writeByteArrayToFile(new File(imageFile.getParent() + File.separator + "crop_aaa.png"), cropFile);
		} catch (FileNotFoundException e) {
			fail();
			e.printStackTrace();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void testImageResize() {
		ImageResizer imageResizer = new ImageResizer();
		
		File imageFile = new File("C:\\courage\\project\\20160524_020048000_iOS.jpg");
		
		try {
			byte[] orginFile = IOUtils.toByteArray(new FileInputStream(imageFile));
			byte[] resizeFile = imageResizer.resizeImage(orginFile, 50, 32, 133, 175);
			
			FileUtils.writeByteArrayToFile(new File(imageFile.getParent() + File.separator + "resize_aaa.png"), resizeFile);
		} catch (FileNotFoundException e) {
			fail();
			e.printStackTrace();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
}
