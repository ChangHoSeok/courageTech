package kr.pe.courage.tech.uss.mpe.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.ValidationUtil;
import kr.pe.courage.tech.uss.mpe.service.AvatarVO;
import kr.pe.courage.tech.uss.mpe.service.MyPageService;

/**
 * <pre>
 * kr.pe.courage.tech.uss.mpe.service.impl
 * MyPageServiceImpl.java
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
@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {
	
	@Resource(name = "myPageDAO")
	private MyPageDAO myPageDAO;

	@Override
	public List<AvatarVO> selectAvatarList() throws EgovBizException {
		List<AvatarVO> resultList = null;
		String propFaceIconDir = PropertiesMap.getInstance().getValue("system.faceicon.path");
		
		if (!ValidationUtil.isRequired(propFaceIconDir)) {
			throw new EgovBizException("설정된 아이콘 경로가 없습니다 system.faceicon.path : " + propFaceIconDir);
		}
		
		File faceIconDir = new File(Storage.getInstance().getContextRealPath(propFaceIconDir));
		
		if (!faceIconDir.exists() || !faceIconDir.isDirectory() || !faceIconDir.canRead()) {
			throw new EgovBizException("지정된 경로에 접근할 수 없습니다. " + faceIconDir.getPath());
		}
		
		resultList = new ArrayList<AvatarVO>();
		for (File iconFile : faceIconDir.listFiles()) {
			if (isImageFile(iconFile)) {
				AvatarVO faceIconVO = new AvatarVO();
				faceIconVO.setIconPath(propFaceIconDir);
				faceIconVO.setIconNm(iconFile.getName());
				
				resultList.add(faceIconVO);
			}
			
		}
		
		return resultList;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : File 객체가 이미지 유형의 파일인지 확인한다.
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : isImageFile
	 * @param file
	 * @return
	 */
	private boolean isImageFile(File file) {
		boolean resultFlag = false;
		String imageFileExts[] = {"jpg", "jpeg", "gif", "png", "ico", "bmp"};
		
		if (file.isFile()) {
			String fileExt = FilenameUtils.getExtension(file.getName());
			
			for (String imageExt : imageFileExts) {
				if (imageExt.equals(fileExt.toLowerCase())) {
					resultFlag = true;
					break;
				}
			}
		}
		
		return resultFlag;
	}

	@Override
	public void updateAvatar(AvatarVO avatarVO) {
		myPageDAO.updateAvatar(avatarVO);
	}
}
