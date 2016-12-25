
package kr.pe.courage.common.code.impl;

import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.dao.AbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 공통코드 DAO 클래스
 * <pre>
 * kr.pe.courage.common.code.impl
 * CommonCodeDao.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 5.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-05		석창호					최초등록
 * ================================================================
 * </pre>
 */
@Repository("courageCommonCodeDAO")
public class CommonCodeDao extends AbstractDAO<CommonCodeVO> {

	@Override
	protected String getNameSpace() {
		return "courageCommonCode";
	}
}
