package kr.pe.courage.tech.uss.mpe.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.uss.mpe.service
 * MyPageService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 10. 23.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 10. 23., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface MyPageService {
	/**
	 * <pre>
	 * 1. 개요 : 사용자 설정 아이콘 목록 조회
	 * system.faceicon.path 설정의 경로에 존재하는 이미지 파일의 목록을 조회한다.
	 * 지원 확장자 : "jpg", "jpeg", "gif", "png", "ico", "bmp"
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : selectAvatarList
	 * @return
	 */
	public List<AvatarVO> selectAvatarList() throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 아이콘 설정정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 3.
	 * @Method Name : updateAvatar
	 * @param avatarVO
	 */
	public void updateAvatar(AvatarVO avatarVO);
}
