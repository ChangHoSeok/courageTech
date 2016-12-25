
package egovframework.com.sym.prm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * 프로그램 목록관리및 프로그램변경관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 * 
 * </pre>
 */

@Repository("ProgrmManageDAO")
public class ProgrmManageDAO extends EgovComAbstractDAO {

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectProgrmListTotCnt(ProgrmManageVO progrmManageVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProgrmManage.selectProgrmListTotCnt_S", progrmManageVO);
	}

	/**
	 * 프로그램 목록을 조회
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return List
	 * @exception Exception
	 */

	public List<ProgrmManageVO> selectProgrmList(ProgrmManageVO progrmManageVO) throws Exception {
		return list("ProgrmManage.selectProgrmList_D", progrmManageVO);
	}

	/**
	 * 프로그램 기본정보를 조회
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	public ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO) throws Exception {
		return (ProgrmManageVO) selectByPk("ProgrmManage.selectProgrm_D", progrmManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램명 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 8.
	 * @Method Name : selectProgrmNm
	 * @param progrmManageVO
	 * @return
	 * @throws Exception
	 */
	public String selectProgrmNm(ProgrmManageVO progrmManageVO) throws Exception {
		return (String) selectByPk("ProgrmManage.selectProgrmNm", progrmManageVO);
	}

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @exception Exception
	 */
	public void insertProgrm(ProgrmManageVO vo) {
		insert("ProgrmManage.insertProgrm_S", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @exception Exception
	 */
	public void updateProgrm(ProgrmManageVO vo) {
		update("ProgrmManage.updateProgrm_S", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @exception Exception
	 */
	public void deleteProgrm(ProgrmManageVO vo) {
		delete("ProgrmManage.deleteProgrm_S", vo);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램 메뉴 매핑정보 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 1.
	 * @Method Name : deleteProgrmMenuMapping
	 * @param progrmManageVO
	 * @throws Exception
	 */
	public void deleteProgrmMenuMapping(ProgrmManageVO progrmManageVO) throws Exception {
		delete("ProgrmManage.deleteProgrmMenuMapping", progrmManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램 권한 매핑정보 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 1.
	 * @Method Name : deleteProgrmAuthorMapping
	 * @param progrmManageVO
	 * @throws Exception
	 */
	public void deleteProgrmAuthorMapping(ProgrmManageVO progrmManageVO) throws Exception {
		delete("ProgrmManage.deleteProgrmAuthorMapping", progrmManageVO);
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @return int
	 * @exception Exception
	*/ 
	public int selectProgrmNMTotCnt(ProgrmManageVO progrmManageVO) throws Exception {
		return (Integer) selectByPk("ProgrmManage.selectProgrmNMTotCnt", progrmManageVO);
	}

	public List<ProgrmManageVO> selectProgrmListPopup(ProgrmManageVO progrmManageVO) throws Exception {
		return list("ProgrmManage.selectProgrmListPopup", progrmManageVO);
	}
	
	public List<ProgrmManageVO> selectProgrmManageIncludeList(ProgrmManageVO progrmManageVO) throws SQLException {
		return list("ProgrmManage.selectProgrmManageIncludeList", progrmManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 체크 프로그램 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 14.
	 * @Method Name : selectProgrmLoginCheckList
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ProgrmManageVO> selectProgrmLoginCheckList() throws SQLException {
		return getSqlMapClientTemplate().queryForList("ProgrmManage.selectProgrmLoginCheckList");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Request 체크 프로그램 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 1.
	 * @Method Name : selectProgrmRequestCheckList
	 * @param progrmManageVO
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<ProgrmManageVO> selectProgrmRequestCheckList(ProgrmManageVO progrmManageVO) throws SQLException {
		return list("ProgrmManage.selectProgrmRequestCheckList", progrmManageVO);
	}
}