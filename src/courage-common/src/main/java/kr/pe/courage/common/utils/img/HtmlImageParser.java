/**
 * 클래스설명 : html 파서
 * @version : 2010. 12. 13.
 * @author : ChangHo Seok
 * @분류 : 
 * courage / kr.co.courage.common.parser;
 */

package kr.pe.courage.common.utils.img;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.HTMLElementName;
import au.id.jericho.lib.html.Source;

public class HtmlImageParser {
	private static Logger logger = Logger.getLogger(HtmlImageParser.class);
	
	/**
	 * Method ID  : getImageTagElement
	 * Method 설명 : html에서 img 테그만 추출하여 ImageElement VO 객체로 목록 작성
	 * 최초작성일  : 2010. 12. 14. 
	 * 작성자 : ChangHo Seok
	 * 변경이력 : 
	 * @param htmlStr
	 * @return 추출된 img 테그정보 목록
	 */
	public static List<ImageElement> getImageTagElement(String htmlStr) {
		List<ImageElement> imgElement = null;
		List<Element> tagList = null;
		Source source = null;
		
		try {
			imgElement = new ArrayList<ImageElement>();
			source = new Source(htmlStr);
			source.fullSequentialParse();
			
			tagList = source.findAllElements(HTMLElementName.IMG);
			
			for (int i = 0; i < tagList.size(); i++) {
				ImageElement imageElement = new ImageElement();
				Element imgEle = (Element)tagList.get(i);
				String srcTag = imgEle.getAttributeValue("src");
				if(srcTag == null) continue;
				
				imageElement.setImageNm(FilenameUtils.getName(srcTag));
				imageElement.setImagePath(FilenameUtils.getPath(srcTag));
				
				imgElement.add(imageElement);
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return imgElement;
	}
}
