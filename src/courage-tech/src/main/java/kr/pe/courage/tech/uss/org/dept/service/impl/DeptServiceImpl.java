package kr.pe.courage.tech.uss.org.dept.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.excel.CourageExcelService;
import egovframework.rte.fdl.excel.impl.CourageExcelServiceImpl;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.tech.uss.org.dept.service.DeptBatchVO;
import kr.pe.courage.tech.uss.org.dept.service.DeptService;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.service.impl
 * DeptServiceImpl.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 29.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Resource(name = "deptDAO")
	private DeptDAO deptDAO;
	
	@Override
	public List<DeptVO> selectDeptList(DeptVO deptVO) {
		return deptDAO.selectList(deptVO);
	}

	@Override
	@Cacheable(cacheName = "deptTreeCache")
	public List<DeptVO> selectDeptTreeList(DeptVO deptVO) {
		return deptDAO.selectDeptTreeList(deptVO);
	}

	@Override
	public int selectDeptListCount(DeptVO deptVO) {
		return deptDAO.selectListCount(deptVO);
	}

	@Override
	public DeptVO selectDeptDetail(DeptVO deptVO) {
		return deptDAO.getById(deptVO);
	}

	@Override
	public boolean isExist(DeptVO deptVO) {
		return deptDAO.isExist(deptVO);
	}

	@Override
	@TriggersRemove(cacheName = "deptTreeCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void createDept(DeptVO deptVO) {
		deptDAO.insert(deptVO);
	}

	@Override
	@TriggersRemove(cacheName = "deptTreeCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void updateDept(DeptVO deptVO) {
		deptDAO.update(deptVO);
	}

	@Override
	@TriggersRemove(cacheName = "deptTreeCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void deleteDept(DeptVO deptVO) {
		deptDAO.delete(deptVO);
	}

	@Override
	@TriggersRemove(cacheName = "deptTreeCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public List<DeptVO> createDeptBatch(DeptBatchVO deptBatchVO) throws EgovBizException {
		List<DeptVO> errorList = null;
		FileInputStream fis = null;
		
		if (deptBatchVO.getUploadAtchFile() != null) {
			CourageExcelService courageExcelService = new CourageExcelServiceImpl();
			
			String tempStoragePath = Storage.getInstance().getTempPath(deptBatchVO.getJsessionId());
			File uploadFile = new File(tempStoragePath + File.separator + deptBatchVO.getUploadAtchFile());
			List<Object> dataList = null;
			
			errorList = new ArrayList<DeptVO>();
			
			try {
				courageExcelService.setApplicationContext(applicationContext);
				
				if (FilenameUtils.getExtension(deptBatchVO.getUploadAtchFile()).toLowerCase().equals("xls")) {
					courageExcelService.setMapBeanName("deptExcelJXLMapper");
					dataList = courageExcelService.parseJXLExcel(uploadFile, 1);
				} else if (FilenameUtils.getExtension(deptBatchVO.getUploadAtchFile()).toLowerCase().equals("xlsx")) {
					courageExcelService.setMapBeanName("deptExcelXSSFMapper");
					fis = new FileInputStream(uploadFile);
					dataList = courageExcelService.parseXSSFSheetExcel(fis, 1);
				}
				
				// ErrorList 분류
				Iterator<Object> iter = dataList.iterator();
				while (iter.hasNext()) {
					DeptVO checkVO = (DeptVO) iter.next();
					
					if (checkVO.hasErrors()) {
						errorList.add(checkVO);
					}
				}
				
				// 데이터 검증 후 오류가 없을때만 등록
				if (errorList.size() <= 0) {
					if (Util.nvl(deptBatchVO.getDeleteFlag()).equals("delete")) {
						// 삭제 후 재등록
						deptDAO.truncateDept();
						deptDAO.insertDeptList(dataList);
					} else {
						// 추가 및 수정
						deptDAO.insertOrReplaceDeptList(dataList);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new EgovBizException("Excel 처리 중 오류발생");
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return errorList;
	}
}
