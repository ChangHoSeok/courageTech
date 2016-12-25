
package kr.pe.courage.tech.uss.mpe.service;

/**
 * <pre>
 * kr.pe.courage.tech.uss.mpe.service
 * AvatarVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 10. 21.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 10. 21., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class AvatarVO {
	private String iconPath;
	private String iconNm;
	private String esntlId;

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconNm() {
		return iconNm;
	}

	public void setIconNm(String iconNm) {
		this.iconNm = iconNm;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
}
