
package kr.pe.courage.tech.das.log.service;

import java.util.Date;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogDetailVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 8. 11.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LogDetailVO extends CommonDefaultVO {
	private static final long serialVersionUID = -7178804279308974820L;

	private String groupId;
	private String rceptId;
	private long logSn;
	private Date logDt;
	private String logDataChrctr1;
	private String logDataChrctr2;
	private String logDataChrctr3;
	private String logDataChrctr4;
	private String logDataChrctr5;
	private long logDataNumber1;
	private long logDataNumber2;
	private long logDataNumber3;
	private long logDataNumber4;
	private long logDataNumber5;
	
	@KeepCondition
	private String condGroupId;
	@KeepCondition
	private String condStanDe;
	@KeepCondition
	private String condStartHour;
	@KeepCondition
	private String condEndHour;
	@KeepCondition
	private String condUnlimited;
	@KeepCondition
	private int condLimitPTStart;
	@KeepCondition
	private int condLimitPTEnd;

	public LogDetailVO() {

	}

	public LogDetailVO(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRceptId() {
		return rceptId;
	}

	public void setRceptId(String rceptId) {
		this.rceptId = rceptId;
	}

	public long getLogSn() {
		return logSn;
	}

	public void setLogSn(long logSn) {
		this.logSn = logSn;
	}

	public Date getLogDt() {
		return logDt;
	}

	public void setLogDt(Date logDt) {
		this.logDt = logDt;
	}

	public String getLogDataChrctr1() {
		return logDataChrctr1;
	}

	public void setLogDataChrctr1(String logDataChrctr1) {
		this.logDataChrctr1 = logDataChrctr1;
	}

	public String getLogDataChrctr2() {
		return logDataChrctr2;
	}

	public void setLogDataChrctr2(String logDataChrctr2) {
		this.logDataChrctr2 = logDataChrctr2;
	}

	public String getLogDataChrctr3() {
		return logDataChrctr3;
	}

	public void setLogDataChrctr3(String logDataChrctr3) {
		this.logDataChrctr3 = logDataChrctr3;
	}

	public String getLogDataChrctr4() {
		return logDataChrctr4;
	}

	public void setLogDataChrctr4(String logDataChrctr4) {
		this.logDataChrctr4 = logDataChrctr4;
	}

	public String getLogDataChrctr5() {
		return logDataChrctr5;
	}

	public void setLogDataChrctr5(String logDataChrctr5) {
		this.logDataChrctr5 = logDataChrctr5;
	}

	public long getLogDataNumber1() {
		return logDataNumber1;
	}

	public void setLogDataNumber1(long logDataNumber1) {
		this.logDataNumber1 = logDataNumber1;
	}

	public long getLogDataNumber2() {
		return logDataNumber2;
	}

	public void setLogDataNumber2(long logDataNumber2) {
		this.logDataNumber2 = logDataNumber2;
	}

	public long getLogDataNumber3() {
		return logDataNumber3;
	}

	public void setLogDataNumber3(long logDataNumber3) {
		this.logDataNumber3 = logDataNumber3;
	}

	public long getLogDataNumber4() {
		return logDataNumber4;
	}

	public void setLogDataNumber4(long logDataNumber4) {
		this.logDataNumber4 = logDataNumber4;
	}

	public long getLogDataNumber5() {
		return logDataNumber5;
	}

	public void setLogDataNumber5(long logDataNumber5) {
		this.logDataNumber5 = logDataNumber5;
	}

	public String getCondGroupId() {
		return condGroupId;
	}

	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}

	public String getCondStanDe() {
		return condStanDe;
	}

	public void setCondStanDe(String condStanDe) {
		this.condStanDe = condStanDe;
	}

	public String getCondStartHour() {
		return condStartHour;
	}

	public void setCondStartHour(String condStartHour) {
		this.condStartHour = condStartHour;
	}

	public String getCondEndHour() {
		return condEndHour;
	}

	public void setCondEndHour(String condEndHour) {
		this.condEndHour = condEndHour;
	}

	public String getCondUnlimited() {
		return condUnlimited;
	}

	public void setCondUnlimited(String condUnlimited) {
		this.condUnlimited = condUnlimited;
	}

	public int getCondLimitPTStart() {
		return condLimitPTStart;
	}

	public void setCondLimitPTStart(int condLimitPTStart) {
		this.condLimitPTStart = condLimitPTStart;
	}

	public int getCondLimitPTEnd() {
		return condLimitPTEnd;
	}

	public void setCondLimitPTEnd(int condLimitPTEnd) {
		this.condLimitPTEnd = condLimitPTEnd;
	}
}
