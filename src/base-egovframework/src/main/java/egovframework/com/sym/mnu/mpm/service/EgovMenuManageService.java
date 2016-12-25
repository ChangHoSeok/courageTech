package  egovframework.com.sym.mnu.mpm.service;

import java.util.List;

/** 
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는 
 *   							selectUpperMenuNoByPk() 메서드 추가
 *
 * </pre>
 */

public interface EgovMenuManageService {
	
	/**
	 * 메뉴목록 총건수를 조회한다.
	 */
	
	int selectMenuManageListTotCnt(MenuManageVO menuManageVO) throws Exception;
	/**
	 * 메뉴 목록을 조회
	 */
	List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) throws Exception;

	/**
	 * 메뉴 상세정보를 조회
	 */
	MenuManageVO selectMenuManage(MenuManageVO menuManageVO) throws Exception;

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 */
	int selectMenuNoByPk(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * 메뉴 정보를 등록
	 */
	void insertMenuManage(MenuManageVO menuManageVO) throws Exception;

	/**
	 * 메뉴 정보를 수정
	 */
	void updateMenuManage(MenuManageVO menuManageVO) throws Exception;

	/**
	 * 메뉴 정보를 삭제
	*/
	void deleteMenuManage(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * 메뉴 정보를 삭제 (다중)
	*/
	void deleteMenuManageList(MenuManageVO menuManageVO) throws Exception;

	/**
	 * 상위메뉴?
	 */
	int selectUpperMenuNoByPk(MenuManageVO vo) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 메뉴 목록 팝업조회
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 9.
	 * @Method Name : selectMenuManageListPopup
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	List<MenuManageVO> selectMenuManageListPopup(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 목록 include조회
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 9.
	 * @Method Name : selectMenuManageIncludeList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	List<MenuManageVO> selectMenuManageIncludeList(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층형 메뉴조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 17.
	 * @Method Name : selectHierMenuManageList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	List<MenuManageVO> selectHierMenuManageList(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 단일 레벨 메뉴 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 18.
	 * @Method Name : selectSingleMenuManageList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	List<MenuManageVO> selectSingleMenuManageList(MenuManageVO menuManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 번호에 해당되는 네비게이션 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 8.
	 * @Method Name : selectMenuNaviList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	List<MenuManageVO> selectMenuNaviList(MenuManageVO menuManageVO) throws Exception;
}