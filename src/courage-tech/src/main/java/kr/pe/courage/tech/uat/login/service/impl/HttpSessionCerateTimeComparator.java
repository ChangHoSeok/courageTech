package kr.pe.courage.tech.uat.login.service.impl;

import java.util.Comparator;

import javax.servlet.http.HttpSession;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.service.impl
 * HttpSessionCerateTimeComparator.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 28.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 28., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class HttpSessionCerateTimeComparator implements Comparator<HttpSession> {

	@Override
	public int compare(HttpSession session1, HttpSession session2) {
		long createTime1 = session1.getCreationTime();
		long createTime2 = session2.getCreationTime();
		
		return createTime1 < createTime2 ? -1 : (createTime1 == createTime2 ? 0 : 1);
	}

}
