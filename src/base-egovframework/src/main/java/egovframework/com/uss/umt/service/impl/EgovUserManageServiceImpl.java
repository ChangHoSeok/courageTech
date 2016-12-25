
package egovframework.com.uss.umt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.utils.EncryString;
import kr.pe.courage.common.utils.PasswordGenerater;

import org.springframework.stereotype.Service;

import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 사용자관리에 관한 비지니스 클래스를 정의한다.
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
@Service("userManageService")
public class EgovUserManageServiceImpl extends AbstractServiceImpl implements EgovUserManageService {

	/** userManageDAO */
	@Resource(name = "userManageDAO")
	private UserManageDAO userManageDAO;
	
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return List<UserManageVO> 업무사용자 목록정보
	 * @throws Exception
	 */
	public List<UserManageVO> selectUserList(UserManageVO userManageVO) {
		return userManageDAO.selectUserList(userManageVO);
	}

	/**
	 * 기 등록된 특정 사용자목록의 전체수를 확인 3
	 * 
	 * @param userSearchVO
	 *            검색조건
	 * @return 총사용자갯수(int)
	 * @throws Exception
	 */
	public int selectUserListTotCnt(UserManageVO userManageVO) {
		return userManageDAO.selectUserListTotCnt(userManageVO);
	}

	/**
	 * 기 등록된 사용자 중 검색조건에 맞는 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
	 * 
	 * @param uniqId
	 *            상세조회대상 업무사용자 아이디
	 * @return userManageVO 업무사용자 상세정보
	 * @throws Exception
	 */
	public UserManageVO selectUser(UserManageVO userManageVO) {
		return userManageDAO.selectUser(userManageVO);
	}

	public UserManageVO selectUserKorsafe(UserManageVO userManageVO) throws Exception {
		return userManageDAO.selectUserKorsafe(userManageVO);
	}

	public List<UserManageVO> selectDeptSubUserList(UserManageVO userManageVO) throws Exception {
		return userManageDAO.selectDeptSubUserList(userManageVO);
	}

	public boolean isExistsUser(UserManageVO userManageVO) throws Exception {
		return userManageDAO.isExistsUser(userManageVO);
	}

	public void insertUser(UserManageVO userManageVO) throws Exception {
		userManageVO.setEsntlId(idgenService.getNextStringId());
		userManageVO.setPassword(EncryString.encSHA512(userManageVO.getPassword()));
		userManageDAO.insertUser(userManageVO);
	}

	public void insertUserKorsafe(UserManageVO userManageVO) throws Exception {
		userManageVO.setEsntlId(idgenService.getNextStringId());
		userManageVO.setPassword(EncryString.encSHA512(userManageVO.getPassword()));
		userManageDAO.insertUserKorsafe(userManageVO);
	}

	public void updateUser(UserManageVO userManageVO) throws Exception {
		userManageDAO.updateUser(userManageVO);
	}

	public void updateUserKorsafe(UserManageVO userManageVO) throws Exception {
		userManageDAO.updateUserKorsafe(userManageVO);
	}

	public void updateUserPassword(UserManageVO userManageVO) throws Exception {
		userManageVO.setPassword(EncryString.encSHA512(userManageVO.getPassword()));
		userManageDAO.updateUserPassword(userManageVO);
	}

	public String createUserTempPassword(UserManageVO userManageVO) throws Exception {
		String tempPassword = PasswordGenerater.generateRandomPasswd(10);
		
		userManageVO.setPassword(tempPassword);
		updateUserPassword(userManageVO); // 패스워드 변경
		
		return tempPassword;
	}

	public void deleteUser(UserManageVO userManageVO) throws Exception {
		userManageDAO.deleteUser(userManageVO);
	}

	public void updateUserSttus(UserManageVO userManageVO) throws Exception {
		userManageDAO.updateUserSttus(userManageVO);
	}
}