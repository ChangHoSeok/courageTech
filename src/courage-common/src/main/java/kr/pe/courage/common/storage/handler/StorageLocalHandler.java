package kr.pe.courage.common.storage.handler;

import java.io.File;
import java.io.FileNotFoundException;

import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.IStorageHandler;
import kr.pe.courage.common.storage.StorageUtils;
import kr.pe.courage.common.utils.file.FileUtil;

import org.apache.commons.io.FilenameUtils;


public class StorageLocalHandler implements IStorageHandler {

	public String put(StorageFile storageFile) throws Exception {
		String resultFileName = "";
		String filePath = StorageUtils.filePathGen();
		
		if (storageFile.getUploadFile().exists()) {
			String fileExt = FilenameUtils.getExtension(storageFile.getUploadFile().getName());
			resultFileName = Storage.getInstance().getFileSyncId() + "." + fileExt;
			File saveFile = new File(Storage.getInstance().getSaveStorage(filePath));
			
			if (!saveFile.isDirectory()) {
				saveFile.mkdirs();
			}
			
			saveFile = new File(Storage.getInstance().getSaveStorage(filePath) + Storage.FILE_SEPARAT0R + resultFileName);
			
			FileUtil.copyFile(storageFile.getUploadFile(), saveFile);
			storageFile.setFileExtsn(fileExt);
			storageFile.setFilePath(filePath);
			storageFile.setUniqFileNm(resultFileName);
			storageFile.setFileSize(saveFile.length() + "");
		} else {
			throw new FileNotFoundException("등록할 첨부파일이 존재하지 않습니다.");
		}
		
		return resultFileName;
	}

	public File get(StorageFile storageFile) throws Exception {
		File resultFile = null;
		String fileFullPath = "";
		
		for (String storagePath : Storage.getInstance().getStorageList()) {
			fileFullPath = storagePath + storageFile.getFilePath() + File.separator + storageFile.getUniqFileNm();
			resultFile = new File(fileFullPath);
			
			if (resultFile.exists()) {
				break;
			}
		}
		
		return resultFile;
	}

	public boolean deleteFile(StorageFile storageFile) throws Exception {
		boolean resultFlag = false;
		File deleteFile = null;
		
		for (String storagePath : Storage.getInstance().getStorageList()) {
			deleteFile = new File(storagePath + storageFile.getFilePath() + Storage.FILE_SEPARAT0R + storageFile.getFileNm());
			
			if (deleteFile.exists()) {
				deleteFile.delete();
				resultFlag = true;
				break;
			}
		}
		
		return resultFlag;
	}

	public boolean deleteDir(StorageFile storageFile) throws Exception {
		// TODO :chseok82:디렉토리 전체 삭제 기능 구현
		return false;
	}
}
