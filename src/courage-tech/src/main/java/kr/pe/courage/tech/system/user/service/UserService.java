package kr.pe.courage.tech.system.user.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.system.user.service
 * UserService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 2. 18.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 2. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface UserService {
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : selectUserList
	 * @param userVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<UserVO> selectUserList(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : selectUserListCount
	 * @param userVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectUserListCount(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : selectUserDetail
	 * @param userVO
	 * @return
	 * @throws EgovBizException
	 */
	public UserVO selectUserDetail(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 아이디 존재여부 확인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : selectUserIdExists
	 * @param userVO
	 * @return
	 * @throws EgovBizException
	 */
	public boolean selectUserIdExists(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : insertUser
	 * @param userVO
	 * @throws EgovBizException
	 */
	public void insertUser(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : updateUser
	 * @param userVO
	 * @throws EgovBizException
	 */
	public void updateUser(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 승인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : updateUserSbscrbConfm
	 * @param userVO
	 * @throws EgovBizException
	 */
	public void updateUserSbscrbConfm(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 비밀번호 초기화
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : updateUserInitPassword
	 * @param userVO
	 * @throws EgovBizException
	 */
	public void updateUserInitPassword(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : deleteUser
	 * @param userVO
	 * @throws EgovBizException
	 */
	public void deleteUser(UserVO userVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 패스워드 인증
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : userPasswordCertificate
	 * @param userVO
	 * @return
	 * @throws EgovBizException
	 */
	public boolean userPasswordCertificate(UserVO userVO) throws EgovBizException;
}
