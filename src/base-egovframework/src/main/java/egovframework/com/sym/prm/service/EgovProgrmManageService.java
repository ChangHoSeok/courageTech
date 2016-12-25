
package egovframework.com.sym.prm.service;

import java.util.List;

/**
 * 프로그램관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface EgovProgrmManageService {

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	int selectProgrmListTotCnt(ProgrmManageVO progrmManageVO) throws Exception;

	/**
	 * 프로그램 목록을 조회
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<ProgrmManageVO> selectProgrmList(ProgrmManageVO progrmManageVO) throws Exception;

	/**
	 * 프로그램 상세정보를 조회
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO) throws Exception;
	
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
	public String selectProgrmNm(ProgrmManageVO progrmManageVO) throws Exception;

	/**
	 * 프로그램 정보를 등록
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @exception Exception
	 */
	void insertProgrm(ProgrmManageVO vo) throws Exception;

	/**
	 * 프로그램 정보를 수정
	 * 
	 * @param vo
	 *            ProgrmManageVO
	 * @exception Exception
	 */
	void updateProgrm(ProgrmManageVO vo) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 삭제 (단일)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 8.
	 * @Method Name : deleteProgrm
	 * @param vo
	 * @throws Exception
	 */
	void deleteProgrm(ProgrmManageVO vo) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램 삭제 (목록)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 8.
	 * @Method Name : deleteProgrmList
	 * @param vo
	 * @throws Exception
	 */
	void deleteProgrmList(ProgrmManageVO progrmManageVO) throws Exception;

	/**
	 * 프로그램 파일 존재여부를 조회
	 * 
	 * @param vo
	 *            ComDefaultVO
	 * @return int
	 * @exception Exception
	*/
	int selectProgrmNMTotCnt(ProgrmManageVO progrmManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램 목록 팝업조회
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 10.
	 * @Method Name : selectProgrmListPopup
	 * @param progrmManageVO
	 * @return
	 * @throws Exception
	 */
	List<ProgrmManageVO> selectProgrmListPopup(ProgrmManageVO progrmManageVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 메뉴 하위 프로그램 목록조회 
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 9.
	 * @Method Name : selectProgrmManageIncludeList
	 * @param progrmManageVO
	 * @return
	 * @throws Exception
	 */
	List<ProgrmManageVO> selectProgrmManageIncludeList(ProgrmManageVO progrmManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 체크 프로그램 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 14.
	 * @Method Name : selectProgrmLoginCheckList
	 * @return
	 * @throws Exception
	 */
	public List<ProgrmManageVO> selectProgrmLoginCheckList() throws Exception;
	
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
	 * @throws Exception
	 */
	public List<ProgrmManageVO> selectProgrmRequestCheckList(ProgrmManageVO progrmManageVO) throws Exception;
}