
package kr.pe.courage.tech.cop.bbs.service;

import java.util.Date;

import kr.pe.courage.common.annotation.DisallowedField;
import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * AnswerVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 30.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class AnswerVO extends CommonDefaultVO {
	private static final long serialVersionUID = -4351106730454711534L;
	
	public static final String USE_AT_Y = "Y";
	public static final String USE_AT_N = "N";

	@DisallowedField private String bbsId;
	@DisallowedField private int nttId = 0;
	private int answerNo = 0;
	@DisallowedField private String wrterId;
	@DisallowedField private String wrterNm;
	private String answerCn;
	@DisallowedField private String useAt;
	private String password;
	@DisallowedField private String frstRegisterId;
	@DisallowedField private String frstRegisterNm;
	@DisallowedField private Date frstRegistPnttm;
	@DisallowedField private String lastUpdusrId;
	@DisallowedField private Date lastUpdtPnttm;

	@KeepCondition private String condAnswerCn;
	@KeepCondition private String condWrterNm;

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public int getNttId() {
		return nttId;
	}

	public void setNttId(int nttId) {
		this.nttId = nttId;
	}

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}

	public String getWrterId() {
		return wrterId;
	}

	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getWrterNm() {
		return wrterNm;
	}

	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	public String getAnswerCn() {
		return answerCn;
	}

	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	public Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getCondAnswerCn() {
		return condAnswerCn;
	}

	public void setCondAnswerCn(String condAnswerCn) {
		this.condAnswerCn = condAnswerCn;
	}

	public String getCondWrterNm() {
		return condWrterNm;
	}

	public void setCondWrterNm(String condWrterNm) {
		this.condWrterNm = condWrterNm;
	}
}
