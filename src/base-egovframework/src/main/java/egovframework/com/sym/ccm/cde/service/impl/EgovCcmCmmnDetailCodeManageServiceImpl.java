package egovframework.com.sym.ccm.cde.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * 
 * 공통상세코드에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
@Service("cmmnDetailCodeManageService")
public class EgovCcmCmmnDetailCodeManageServiceImpl extends AbstractServiceImpl implements EgovCcmCmmnDetailCodeManageService {

    @Resource(name="cmmnDetailCodeManageDAO")
    private CmmnDetailCodeManageDAO cmmnDetailCodeManageDAO;
    
	/**
	 * 공통상세코드를 삭제한다.
	 */
	public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		cmmnDetailCodeManageDAO.deleteCmmnDetailCode(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 등록한다.
	 */
	public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
    	cmmnDetailCodeManageDAO.insertCmmnDetailCode(cmmnDetailCodeVO);
	}

	public CmmnDetailCodeVO insertCmmnDetailCodeCustom(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		int maxOrdr = cmmnDetailCodeManageDAO.selectCmmnDetailCodeMaxOrdr(cmmnDetailCodeVO) + 1;
		String customCode = "CUSTOM_" + maxOrdr;
		
		cmmnDetailCodeVO.setCode(customCode);
		cmmnDetailCodeVO.setOrdr("" + maxOrdr);
		cmmnDetailCodeVO.setUseAt("Y");
		
		cmmnDetailCodeManageDAO.insertCmmnDetailCode(cmmnDetailCodeVO);
		
		return cmmnDetailCodeVO;
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 */
	public CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
    	CmmnDetailCodeVO ret = cmmnDetailCodeManageDAO.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
    	return ret;
	}

	public CmmnDetailCodeVO selectCmmnDetailCodeByName(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return cmmnDetailCodeManageDAO.selectCmmnDetailCodeByName(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	public List<CmmnDetailCodeVO> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		List<CmmnDetailCodeVO> resultList = null;
		
		if (cmmnDetailCodeVO.getOperation() == null || cmmnDetailCodeVO.getOperation().trim().equals("")) {
			resultList = cmmnDetailCodeManageDAO.selectCmmnDetailCodeList(cmmnDetailCodeVO);
		} else {
			resultList = cmmnDetailCodeManageDAO.selectOperationCodeList(cmmnDetailCodeVO);
		}
		
        return resultList;
	}

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 */
	public int selectCmmnDetailCodeListTotCnt(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
        return cmmnDetailCodeManageDAO.selectCmmnDetailCodeListTotCnt(cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 수정한다.
	 */
	public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		cmmnDetailCodeManageDAO.updateCmmnDetailCode(cmmnDetailCodeVO);
	}

}
