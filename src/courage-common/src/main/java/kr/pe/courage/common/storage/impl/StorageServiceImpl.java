
package kr.pe.courage.common.storage.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.StorageUtils;
import kr.pe.courage.common.utils.Util;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 첨부파일 관리 서비스 구현 클래스
 * 
 * <pre>
 * kr.pe.courage.common.storage.impl
 * StorageServiceImpl.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 22.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 *  2013.11.22    석창호                                           최초등록
 * ================================================================
 * </pre>
 */
@Service("courageStorageService")
public class StorageServiceImpl implements IStorageService {

	@Resource(name = "courageStorageDAO")
	private StorageDAO storageDAO;
	
	@Resource(name = "courageFileIdGnrService")
	private EgovIdGnrService idgenService;

	public List<StorageFile> selectFileList(StorageFile storageFile) throws Exception {
		return storageDAO.selectList(storageFile);
	}
	
	public int selectFileListCount(StorageFile storageFile) throws Exception {
		return storageDAO.selectListCount(storageFile);
	}
	
	public StorageFile selectFile(StorageFile storageFile) throws Exception {
		return storageDAO.getById(storageFile);
	}

	public String insertFile(List<StorageFile> storageFileList) throws Exception {
		String atchFileId = idgenService.getNextStringId();
		StorageFile fileMaster = new StorageFile();
		
		// 파일 정보생성 (아이디)
		fileMaster.setAtchFileId(atchFileId);
		storageDAO.insertFileMaster(fileMaster);
		
		for (StorageFile storageFile : storageFileList) {
			storageFile.setAtchFileId(atchFileId);
			// 파일 저장
			StorageUtils.put(storageFile);
			
			// 파일 상세정보 등록
			storageDAO.insert(storageFile);
		}

		return atchFileId;
	}
	
	// 물리적파일 삭제 및 정보의 삭제는 관리대상(유지보수)으로 넘김 (삭제한 대상을 조회하여 직접 삭제)
	public void updateFile(List<StorageFile> storageFileList) throws Exception {
		for (StorageFile storageFile : storageFileList) {
			
			if (Util.nvl(storageFile.getDeleteFlag()).equals(Storage.FILE_FLAG_DELETE)) { // 파일 삭제
				StorageFile deleteFile = storageDAO.getById(storageFile); // 삭제파일 상세정보 조회
				//StorageUtils.delete(deleteFile); 물리파일 삭제처리 안함
				storageDAO.delete(deleteFile);
			} else { // 파일 추가
				// 파일 저장
				StorageUtils.put(storageFile);
				// 파일 상세정보 등록
				storageDAO.insert(storageFile);
			}
		}
	}

	public void deleteFile(List<StorageFile> storageFileList) throws Exception {
		for (StorageFile storageFile : storageFileList) {
			storageDAO.delete(storageFile);
		}
	}

	public boolean isExistsFile(StorageFile storageFile) throws Exception {
		return storageDAO.isExist(storageFile);
	}

	public void truncateFile(StorageFile storageFile) throws Exception {
		storageDAO.deleteFileMaster(storageFile);
	}
}
