
package egovframework.com.uss.umt.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.umt.service.UserManageVO;

/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("userManageDAO")
public class UserManageDAO extends EgovComAbstractDAO {

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return List 업무사용자 목록정보
	 */
	@SuppressWarnings("unchecked")
	public List<UserManageVO> selectUserList(UserManageVO userManageVO) {
		return list("User.selectUserList_S", userManageVO);
	}

	/**
	 * 사용자총 갯수를 조회한다.
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return int 업무사용자 총갯수
	 */
	public int selectUserListTotCnt(UserManageVO userManageVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("User.selectUserListTotCnt_S", userManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param uniqId
	 *            상세조회대상 업무사용자아이디
	 * @return UserManageVO 업무사용자 상세정보
	 */
	public UserManageVO selectUser(UserManageVO userManageVO) {
		return (UserManageVO) selectByPk("User.selectUser_S", userManageVO);
	}
	
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
	 * @throws SQLException
	 */
	public UserManageVO selectUserKorsafe(UserManageVO userManageVO) throws SQLException {
		return (UserManageVO) selectByPk("User.selectUser_Korsafe", userManageVO);
	}
	
	public UserManageVO selectLoginUser(UserManageVO userManageVO) throws Exception {
		return (UserManageVO) selectByPk("User.selectLoginUser", userManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 아이디 중복확인
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 12.
	 * @Method Name : isExistsUser
	 * @param userManageVO
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistsUser(UserManageVO userManageVO) throws SQLException {
		boolean resultFlag = false;
		int cnt = (Integer) getSqlMapClientTemplate().queryForObject("User.isExistsUser", userManageVO);
		
		if (cnt <= 0) {
			resultFlag = true;
		}
		
		return resultFlag;
	}

	@SuppressWarnings("unchecked")
	public List<UserManageVO> selectDeptSubUserList(UserManageVO userManageVO) throws Exception {
		return list("User.selectDeptSubUserList", userManageVO);
	}
	
	public void insertUser(UserManageVO userManageVO) throws Exception {
		insert("User.insertUser_S", userManageVO);
	}
	
	public void insertUserKorsafe(UserManageVO userManageVO) throws SQLException {
		insert("User.insertUser_Korsafe", userManageVO);
	}
	
	public void updateUser(UserManageVO userManageVO) throws Exception {
		update("User.updateUser_S", userManageVO);
	}
	
	public void updateUserKorsafe(UserManageVO userManageVO) throws Exception {
		update("User.updateUser_Korsafe", userManageVO);
	}
	
	public void updateUserPassword(UserManageVO userManageVO) throws SQLException {
		update("User.updateUserPassword", userManageVO);
	}
	
	public void deleteUser(UserManageVO userManageVO) throws Exception {
		update("User.deleteUser_S", userManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 상태 변경
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 9.
	 * @Method Name : updateUserSttus
	 * @param userManageVO
	 * @throws SQLException
	 */
	public void updateUserSttus(UserManageVO userManageVO) throws SQLException {
		update("User.updateUserSttus", userManageVO);
	}

}