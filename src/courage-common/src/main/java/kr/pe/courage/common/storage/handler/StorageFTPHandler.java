package kr.pe.courage.common.storage.handler;

import java.io.File;

import kr.pe.courage.common.net.ftp.FtpManager;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.IStorageHandler;
import kr.pe.courage.common.storage.StorageUtils;

import org.apache.commons.io.FilenameUtils;

public class StorageFTPHandler implements IStorageHandler {
	private String ftpHost;
	private int ftpPort = 21;
	private String ftpUser;
	private String ftpPasswd;
	
	public StorageFTPHandler() {
		super();
	}
	
	public StorageFTPHandler(String ftpHost, int ftpPort, String ftpUser, String ftpPasswd) {
		this.ftpHost = ftpHost;
		this.ftpPort = ftpPort;
		this.ftpUser = ftpUser;
		this.ftpPasswd = ftpPasswd;
	}

	public String put(StorageFile storageFile) throws Exception {
		String resultFileName = "";
		String filePath = StorageUtils.filePathGen();
		
		FtpManager ftpManager = new FtpManager(ftpHost, ftpPort, ftpUser, ftpPasswd);
		ftpManager.connection();
		ftpManager.login();
		
		String fileExt = FilenameUtils.getExtension(storageFile.getUploadFile().getName());
		resultFileName = Storage.getInstance().getFileSyncId() + "." + fileExt;
		ftpManager.put(Storage.getInstance().getSaveStorage(filePath) + Storage.FILE_SEPARAT0R, storageFile.getUploadFile(), resultFileName);
		
		ftpManager.logout();
		ftpManager.disconnect();
		
		storageFile.setFileExtsn(fileExt);
		storageFile.setFilePath(filePath);
		storageFile.setUniqFileNm(resultFileName);
		storageFile.setFileSize(storageFile.getUploadFile().length() + "");
		
		return resultFileName;
	}

	public File get(StorageFile storageFileVo) throws Exception {
		File resultFile = null;
		String fileFullPath = "";
		String targetFile = "";
		
		FtpManager ftpManager = new FtpManager(ftpHost, ftpPort, ftpUser, ftpPasswd);
		ftpManager.connection();
		ftpManager.login();
		
		for(String storagePath : Storage.getInstance().getStorageList()) {
			fileFullPath = storagePath + storageFileVo.getFilePath() + File.separator + storageFileVo.getUniqFileNm();
			targetFile = Storage.getInstance().getContextRealPath() + Storage.getInstance().getTempPath() + Storage.FILE_SEPARAT0R
					+ storageFileVo.getFileNm();
			
			if (ftpManager.get(fileFullPath, targetFile)) {
				resultFile = new File(targetFile);
				break;
			}
		}
		
		ftpManager.logout();
		ftpManager.disconnect();
		
		return resultFile;
	}

	public boolean deleteFile(StorageFile storageFileVo) throws Exception {
		boolean resultFlag = false;
		
		FtpManager ftpManager = new FtpManager(ftpHost, ftpPort, ftpUser, ftpPasswd);
		ftpManager.connection();
		ftpManager.login();
		
		for(String storagePath : Storage.getInstance().getStorageList()) {
			if (ftpManager.delete(storagePath + storageFileVo.getFilePath() + Storage.FILE_SEPARAT0R
					+ storageFileVo.getFileNm())) {
				resultFlag = true;
				break;
			}
		}
		
		ftpManager.logout();
		ftpManager.disconnect();
		
		return resultFlag;
	}

	public boolean deleteDir(StorageFile storageFileVo) throws Exception {
		// TODO :chseok82:디렉토리 전체 삭제 기능 구현
		return false;
	}

}
