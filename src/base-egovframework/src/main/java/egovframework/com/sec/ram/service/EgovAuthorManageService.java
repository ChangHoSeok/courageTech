package egovframework.com.sec.ram.service;

import java.util.List;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public interface EgovAuthorManageService {
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) throws Exception;	
	
	/**
	 * 개별사용자에게 할당된 권한리스트 조회
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws Exception;
	
	/**
	 * 개별사용자에게 할당된 권한 조회
	 * @param authorManageVO AuthorManageVO
	 * @exception Exception
	 */
	public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws Exception;
	
	/**
	 * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	public void insertAuthor(AuthorManageVO authorManage) throws Exception;


	/**
	 * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	public void updateAuthor(AuthorManageVO authorManage) throws Exception;
	
	/**
	 * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	public void deleteAuthor(AuthorManageVO authorManage) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 권한 확인 캐시 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 25.
	 * @Method Name : removeAuthorCache
	 * @throws Exception
	 */
	public void removeAuthorCache() throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 권한 메뉴 목록조회 
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 10.
	 * @Method Name : selectAuthorMenuList
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	public List<AuthorManageVO> selectAuthorMenuList(AuthorManageVO authorManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴권한 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 1.
	 * @Method Name : selectMenuAuthor
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	public AuthorManageVO selectMenuAuthor(AuthorManageVO authorManageVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한조회
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 17.
	 * @Method Name : selectUserAuthorList
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	public List<AuthorManageVO> selectUserAuthorList(AuthorManageVO authorManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 권한조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 17.
	 * @Method Name : selectLoginUserAuthorList
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	public List<AuthorManageVO> selectLoginUserAuthorList(AuthorManageVO authorManageVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 등록
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 18.
	 * @Method Name : insertUserAuthManage
	 * @param authorManageVO
	 * @throws Exception
	 */
	public void insertUserAuthManage(AuthorManageVO authorManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 접근 가능 URL 목록 조회 (인증처리 기본 데이터)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 27.
	 * @Method Name : selectAuthorUrlList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	public List<MenuManageVO> selectAuthorUrlList(MenuManageVO menuManageVO) throws Exception;
}
