package kr.pe.courage.tech.uss.mpe.service.impl;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.uss.mpe.service.AvatarVO;
import kr.pe.courage.tech.uss.mpe.service.MyPageVO;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * kr.pe.courage.tech.uss.mpe.service.impl
 * MyPageDAO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 11. 3.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 11. 3., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Repository("myPageDAO")
public class MyPageDAO extends AbstractDAO<MyPageVO> {

	@Override
	protected String getNameSpace() {
		return "myPageDAO";
	}
	
	public void updateAvatar(AvatarVO avatarVO) {
		this.update("updateAvatar", avatarVO);
	}
}
