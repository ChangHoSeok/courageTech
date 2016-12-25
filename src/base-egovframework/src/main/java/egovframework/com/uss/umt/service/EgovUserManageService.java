
package egovframework.com.uss.umt.service;

import java.util.List;

import org.antlr.grammar.v3.ANTLRParser.exceptionGroup_return;

/**
 * 사용자관리에 관한 인터페이스클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 * 
 * </pre>
 */
public interface EgovUserManageService {

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return List<UserManageVO> 업무사용자 목록정보
	 * @throws Exception
	 */
	public List<UserManageVO> selectUserList(UserManageVO userManageVO) throws Exception;

	/**
	 * 기 등록된 특정 사용자목록의 전체수를 확인
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return 총사용자갯수(int)
	 * @throws Exception
	 */
	public int selectUserListTotCnt(UserManageVO userManageVO) throws Exception;

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param emplyrId
	 *            상세조회대상 업무사용자 아이디
	 * @return userManageVO 업무사용자 상세정보
	 * @throws Exception
	 */
	public UserManageVO selectUser(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 방사선 안전재단 사용자 정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 12.
	 * @Method Name : selectUserKorsafe
	 * @param userManageVO
	 * @return
	 * @throws Exception
	 */
	public UserManageVO selectUserKorsafe(UserManageVO userManageVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 부서 사용자 조회
	 * </pre>
	 * 
	 * @Author : selectDeptSubUserList
	 * @Date : 2013. 12. 2.
	 * @Method Name : selectDeptSubUserList
	 * @return
	 * @throws exceptionGroup_return
	 */
	public List<UserManageVO> selectDeptSubUserList(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 아이디 중복 확인
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 12.
	 * @Method Name : isExistsUser
	 * @param userManageVO
	 * @return
	 * @throws Exception
	 */
	public boolean isExistsUser(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 25.
	 * @Method Name : insertUser
	 * @param userManageVO
	 * @throws Exception
	 */
	public void insertUser(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 등록 (한국방사선안전재단 확장)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 25.
	 * @Method Name : insertUserKorsafe
	 * @param userManageVO
	 * @throws Exception
	 */
	public void insertUserKorsafe(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 25.
	 * @Method Name : updateUser
	 * @param userManageVO
	 * @throws Exception
	 */
	public void updateUser(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정 (한국방사선안전재단)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 25.
	 * @Method Name : updateUserKorsafe
	 * @param userManageVO
	 * @throws Exception
	 */
	public void updateUserKorsafe(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 패스워드 변경
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 13.
	 * @Method Name : updateUserPassword
	 * @param userManageVO
	 * @throws Exception
	 */
	public void updateUserPassword(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 임시 비밀번호 생성
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 13.
	 * @Method Name : updateUserTempPassword
	 * @param userManageVO
	 * @throws Exception
	 */
	public String createUserTempPassword(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 11. 25.
	 * @Method Name : deleteUser
	 * @param userManageVO
	 * @throws Exception
	 */
	public void deleteUser(UserManageVO userManageVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 상태변경
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 9.
	 * @Method Name : updateUserSttus
	 * @param userManageVO
	 * @throws Exception
	 */
	public void updateUserSttus(UserManageVO userManageVO) throws Exception;
}