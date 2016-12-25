
package kr.pe.courage.common.code.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.code.ICommonDetailCodeService;

/**
 * 공통 상세코드 조회 서비스 구현체
 * 
 * <pre>
 * kr.pe.courage.common.code.impl
 * CommonDetailCodeService.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 7.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-05		석창호					최초등록
 * ================================================================
 * </pre>
 */
@Service("courageCommonDetailCodeService")
public class CommonDetailCodeService implements ICommonDetailCodeService {

	@Resource(name = "courageCommonDetailCodeDAO")
	private CommonDetailCodeDao commonDetailCodeDAO;

	public List<CommonCodeVO> selectCommonDetailCodeList(CommonCodeVO commonCodeVO) throws Exception {
		return commonDetailCodeDAO.selectList(commonCodeVO);
	}

	public CommonCodeVO selectCommonDetailCode(CommonCodeVO commonCodeVo) throws Exception {
		return commonDetailCodeDAO.getById(commonCodeVo);
	}

	public List<CommonCodeVO> selectOperationCodeList(CommonCodeVO commonCodeVO) throws Exception {
		return commonDetailCodeDAO.selectOperationCodeList(commonCodeVO);
	}

}
