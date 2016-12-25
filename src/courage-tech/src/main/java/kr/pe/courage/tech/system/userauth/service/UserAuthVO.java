
package kr.pe.courage.tech.system.userauth.service;

import egovframework.com.sec.ram.service.AuthorManageVO;
import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.tech.system.user.service.UserVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.userauth.service
 * UserAuthVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 3. 2.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 3. 2., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class UserAuthVO extends UserVO {
	private static final long serialVersionUID = -5700264080833358850L;

	public UserAuthVO() {
		setCondOrder("USER_NM");
		setCondAlign("ASC");

		authVO = new AuthorManageVO();
	}

	private AuthorManageVO authVO; // 사용자 권한 정보

	private String authorNm; // 권한명
	private String authorCode; // 권한코드
	private String defaultAuthorYn; // 기본권한 여부

	@KeepCondition
	private String condAuthor;

	public AuthorManageVO getAuthVO() {
		return authVO;
	}

	public void setAuthVO(AuthorManageVO authVO) {
		this.authVO = authVO;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getDefaultAuthorYn() {
		return defaultAuthorYn;
	}

	public void setDefaultAuthorYn(String defaultAuthorYn) {
		this.defaultAuthorYn = defaultAuthorYn;
	}

	public String getCondAuthor() {
		return condAuthor;
	}

	public void setCondAuthor(String condAuthor) {
		this.condAuthor = condAuthor;
	}
}