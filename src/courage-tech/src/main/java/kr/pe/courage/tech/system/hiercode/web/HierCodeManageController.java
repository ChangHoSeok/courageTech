
package kr.pe.courage.tech.system.hiercode.web;

import kr.pe.courage.common.hiercode.web.AbstractHierCodeController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.pe.courage.tech.system.hiercode.web
 * HierCodeManageController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013.11.29     석창호                                           최초등록
 * ================================================================
 * </pre>
 */
@Controller("hierCodeManageController")
@RequestMapping("/system/hiercode/*")
public class HierCodeManageController extends AbstractHierCodeController {
	
	@Value("#{systemView['formHierCodeManageView']}")
	private String formHierCodeManageView;
	
	@Value("#{systemView['hierCodeManageDetailView']}")
	private String hierCodeManageDetailView;
	
	@Override
	protected String getHierCodeDetailView() {
		return hierCodeManageDetailView;
	}

	@Override
	protected String getHierCodeSubListView() {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "formHierCodeManage.*")
	public ModelAndView formCodeManage() throws Exception {
		return new ModelAndView(formHierCodeManageView);
	}
}
