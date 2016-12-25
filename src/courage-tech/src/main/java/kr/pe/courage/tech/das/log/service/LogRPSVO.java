
package kr.pe.courage.tech.das.log.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogRPSVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 20.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LogRPSVO extends CommonDefaultVO {
	private static final long serialVersionUID = 6123724087474530255L;

	private String groupId;
	private String stdrDt;
	private String stdrDe;
	private int rps;
	
	@KeepCondition
	private String condGroupId;
	@KeepCondition
	private String condStdrDe;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getStdrDt() {
		return stdrDt;
	}

	public void setStdrDt(String stdrDt) {
		this.stdrDt = stdrDt;
	}

	public String getStdrDe() {
		return stdrDe;
	}

	public void setStdrDe(String stdrDe) {
		this.stdrDe = stdrDe;
	}

	public int getRps() {
		return rps;
	}

	public void setRps(int rps) {
		this.rps = rps;
	}

	public String getCondGroupId() {
		return condGroupId;
	}

	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}

	public String getCondStdrDe() {
		return condStdrDe;
	}

	public void setCondStdrDe(String condStdrDe) {
		this.condStdrDe = condStdrDe;
	}
}
