
package kr.pe.courage.common.code.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.code.ICommonCodeService;

/**
 * 공통코드 조회 서비스 구현체
 * 
 * <pre>
 * kr.pe.courage.common.code.impl
 * CommonCodeServiceImpl.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 5.
 * @Version : 1.0
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
@Service("courageCommonCodeService")
public class CommonCodeServiceImpl implements ICommonCodeService {
	
	@Resource(name =  "courageCommonCodeDAO")
	private CommonCodeDao commonCodeDAO;

	public List<CommonCodeVO> selectCommonCodeList(CommonCodeVO commonCodeVO) throws Exception {
		return commonCodeDAO.selectList(commonCodeVO);
	}

	public CommonCodeVO selectCommonCode(CommonCodeVO commonCodeVo) throws Exception {
		return commonCodeDAO.getById(commonCodeVo);
	}

}
