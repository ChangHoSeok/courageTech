package kr.pe.courage.common.utils.img;

import java.util.Locale;

import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

public class UserImageWriteParam extends JPEGImageWriteParam {
	public UserImageWriteParam() {
        super(Locale.getDefault());
    }
	
	public void setCompressionQuality(float quality) {
        if (quality < 0.0F || quality > 1.0F) {
            throw new IllegalArgumentException("Quality out-of-bounds!");
        }
        this.compressionQuality = 256 - (quality * 256);
    }
}
