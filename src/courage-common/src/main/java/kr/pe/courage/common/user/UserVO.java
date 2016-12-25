
package kr.pe.courage.common.user;

import java.io.Serializable;

public class UserVO implements Serializable {
	private static final long serialVersionUID = -3467869616512948477L;
	private String id; /* 아이디 */
	private String uniqId; /* 사용자 고유 아이디 */
	private String name; /* 이름 */
	private String password; /* 비밀번호 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
