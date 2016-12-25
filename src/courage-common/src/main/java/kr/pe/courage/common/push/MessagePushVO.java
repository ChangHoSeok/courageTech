package kr.pe.courage.common.push;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.common.push
 * MessagePushVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 4. 27.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : , 수정자 : ChangHo Seok, 수정내용 : 
 * </pre>
 */
public class MessagePushVO extends CommonDefaultVO {

	private static final long serialVersionUID = 1560081392559523009L;
	
	private String mssageId; // 메시지 아이디
	private String mssageCn; // 메시지 내용
	private String mssageSndngAt; // 메시지 발송 여부
	private String frstRegisterId; // 최초등록자ID
	private String frstRegisterNm; // 최초등록자명
	private String frstRegistPnttm; // 최초등록시점
	
	@KeepCondition
	private String condMssageSndngAt;
	@KeepCondition
	private String condFrstRegisterId;
	@KeepCondition
	private String condFrstRegisterNm;
	
	public MessagePushVO() {
		setCondOrder("FRST_REGIST_PNTTM");
		setCondAlign("DESC");
	}

	public String getMssageId() {
		return mssageId;
	}

	public void setMssageId(String mssageId) {
		this.mssageId = mssageId;
	}

	public String getMssageCn() {
		return mssageCn;
	}

	public void setMssageCn(String mssageCn) {
		this.mssageCn = mssageCn;
	}

	public String getMssageSndngAt() {
		return mssageSndngAt;
	}

	public void setMssageSndngAt(String mssageSndngAt) {
		this.mssageSndngAt = mssageSndngAt;
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

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getCondMssageSndngAt() {
		return condMssageSndngAt;
	}

	public void setCondMssageSndngAt(String condMssageSndngAt) {
		this.condMssageSndngAt = condMssageSndngAt;
	}

	public String getCondFrstRegisterId() {
		return condFrstRegisterId;
	}

	public void setCondFrstRegisterId(String condFrstRegisterId) {
		this.condFrstRegisterId = condFrstRegisterId;
	}

	public String getCondFrstRegisterNm() {
		return condFrstRegisterNm;
	}

	public void setCondFrstRegisterNm(String condFrstRegisterNm) {
		this.condFrstRegisterNm = condFrstRegisterNm;
	}
}
