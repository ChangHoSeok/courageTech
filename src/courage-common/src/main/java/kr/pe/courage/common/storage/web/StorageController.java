
package kr.pe.courage.common.storage.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.web.view.FileDownView;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.pe.courage.common.storage.web
 * StorageController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 24.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 *  2013.11.24    석창호                                           최초등록
 * ================================================================
 * </pre>
 */
@Controller("courageStorageController")
@RequestMapping(value = "/storage/*")
public class StorageController {
	
	@Resource(name = "courageStorageService")
	private IStorageService storageService;

	@RequestMapping(value = "storageFileDownload.*")
	public ModelAndView storageFileDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("storageFile") StorageFile storageFile,
			ModelMap model) throws Exception {
		
		storageFile = storageService.selectFile(storageFile);
		
		if (storageFile != null) {
			storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
		}
		
		model.addAttribute(FileDownView.FILE_PARAM, storageFile);
		
		return new ModelAndView("fileDownView", model);
	}
}
