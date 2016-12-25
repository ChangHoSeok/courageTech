
package egovframework.com.sym.ccm.cde.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;

/**
 * 
 * 공통상세코드에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 * 
 * </pre>
 */
@Repository("cmmnDetailCodeManageDAO")
public class CmmnDetailCodeManageDAO extends EgovComAbstractDAO {

	/**
	 * 공통상세코드를 삭제한다.
	 * 
	 * @param cmmnDetailCode
	 * @throws Exception
	 */
	public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		delete("CmmnDetailCodeManage.deleteCmmnDetailCode", cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 등록한다.
	 * 
	 * @param cmmnDetailCode
	 * @throws Exception
	 */
	public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		insert("CmmnDetailCodeManage.insertCmmnDetailCode", cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * 
	 * @param cmmnDetailCode
	 * @return CmmnDetailCode(공통상세코드)
	 */
	public CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return (CmmnDetailCodeVO) selectByPk("CmmnDetailCodeManage.selectCmmnDetailCodeDetail", cmmnDetailCodeVO);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 상세조회 (코드명으로 조회)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 2. 3.
	 * @Method Name : selectCmmnDetailCodeByName
	 * @param cmmnDetailCodeVO
	 * @return
	 * @throws Exception
	 */
	public CmmnDetailCodeVO selectCmmnDetailCodeByName(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return (CmmnDetailCodeVO) selectByPk("CmmnDetailCodeManage.selectCmmnDetailCodeByName", cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return List(공통상세코드 목록)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CmmnDetailCodeVO> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return list("CmmnDetailCodeManage.selectCmmnDetailCodeList", cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int(공통상세코드 총 갯수)
	 */
	public int selectCmmnDetailCodeListTotCnt(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("CmmnDetailCodeManage.selectCmmnDetailCodeListTotCnt", cmmnDetailCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 마지막 순번 정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 5.
	 * @Method Name : selectCmmnDetailCodeMaxOrdr
	 * @param cmmnDetailCodeVO
	 * @return
	 * @throws SQLException
	 */
	public int selectCmmnDetailCodeMaxOrdr(CmmnDetailCodeVO cmmnDetailCodeVO) throws SQLException {
		return (Integer) getSqlMapClientTemplate().queryForObject("CmmnDetailCodeManage.selectCmmnDetailCodeMaxOrdr", cmmnDetailCodeVO);
	}

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param cmmnDetailCode
	 * @throws Exception
	 */
	public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		update("CmmnDetailCodeManage.updateCmmnDetailCode", cmmnDetailCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : operation name의 sqlmap id query 호출
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : selectOperationCodeList
	 * @param cmmnDetailCodeVo
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CmmnDetailCodeVO> selectOperationCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		return list(cmmnDetailCodeVO.getOperation(), cmmnDetailCodeVO);
	}
	
	public void truncateCmmnDetailCode() throws Exception {
		delete("CmmnDetailCodeManage.truncateCmmnDetailCode", null);
	}
}
