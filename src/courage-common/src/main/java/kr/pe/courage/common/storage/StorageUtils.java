package kr.pe.courage.common.storage;

import java.io.File;
import java.io.FileNotFoundException;

import kr.pe.courage.common.storage.handler.StorageFTPHandler;
import kr.pe.courage.common.storage.handler.StorageLocalHandler;
import kr.pe.courage.common.storage.handler.StorageSFTPHandler;
import kr.pe.courage.common.utils.DateUtil;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;

public class StorageUtils {
	public static File get(StorageFile storageFile) throws Exception {
		File resultFile = null;
		IStorageHandler storageHandler = null;
		
		if (storageFile.getFileLocation().equals(Storage.FILE_LOCATION_CONTEXT)) {
			String fileFullPath = storageFile.getFilePath() + File.separator + storageFile.getUniqFileNm();
			resultFile = new File(fileFullPath);
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_LOCAL)
				&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
			storageHandler = new StorageLocalHandler();
			resultFile = storageHandler.get(storageFile);
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_FTP)
				&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
			String host = Storage.getInstance().getHost();
    		int port = Integer.parseInt(Storage.getInstance().getPort());
    		String id = Storage.getInstance().getUser();
    		String password = Storage.getInstance().getPassword();
    		
			storageHandler = new StorageFTPHandler(host, port, id, password);
			resultFile = storageHandler.get(storageFile);
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_SFTP)
				&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
			storageHandler = new StorageSFTPHandler();
			// TODO :chseok82:SFTP protocol 구현
		}
		
		return resultFile;
	}
	
	public static String put(StorageFile storageFile) throws Exception {
		String fileSyncId = "";
		String fileExt = "";
		IStorageHandler storageHandler = null;
		String basePath = "";
		
		if (storageFile.getUploadFile().exists()) {
			File saveFile = null;
			storageFile.setFileNm(storageFile.getUploadFile().getName());
			
			if (storageFile.getFileLocation().equals(Storage.FILE_LOCATION_CONTEXT)) {
				fileExt = FilenameUtils.getExtension(storageFile.getUploadFile().getName());
				fileSyncId = Storage.getInstance().getFileSyncId();
				basePath = Storage.getInstance().getStorageContextPath(filePathGen());
				saveFile = new File(Storage.getInstance().getContextRealPath(basePath));
				
				if (!saveFile.isDirectory()) {
					saveFile.mkdirs();
				}
				
				saveFile = new File(Storage.getInstance().getContextRealPath(basePath) + Storage.FILE_SEPARAT0R + fileSyncId);
				
				FileUtil.copyFile(storageFile.getUploadFile(), saveFile);
				
				storageFile.setFileExtsn(fileExt);
				storageFile.setFilePath(basePath);
				storageFile.setUniqFileNm(fileSyncId);
				storageFile.setFileSize(saveFile.length() + "");
				
			} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_LOCAL)
					&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
				storageHandler = new StorageLocalHandler();
				fileSyncId = storageHandler.put(storageFile);
			} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_FTP)
					&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
				String host = Storage.getInstance().getHost();
	    		int port = Integer.parseInt(Storage.getInstance().getPort());
	    		String id = Storage.getInstance().getUser();
	    		String password = Storage.getInstance().getPassword();
	    		
				storageHandler = new StorageFTPHandler(host, port, id, password);
				fileSyncId = storageHandler.put(storageFile);
			} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_SFTP)
					&& storageFile.getFileLocation().equals(Storage.FILE_LOCATION_STORAGE)) {
				storageHandler = new StorageSFTPHandler();
				// TODO :chseok82:SFTP protocol 구현
			}
		} else {
			throw new FileNotFoundException("등록할 첨부파일이 존재하지 않습니다.");
		}
		
		return fileSyncId;
	}
	
	public static boolean delete(StorageFile storageFile) throws Exception {
		boolean deleteFlag = false;
		IStorageHandler storageHandler = null;
		
		if (storageFile.getFileLocation().equals(Storage.FILE_LOCATION_CONTEXT)) {
			String fileFullPath = storageFile.getFilePath() + File.separator + storageFile.getUniqFileNm();
			File deleteFile = new File(fileFullPath);
			deleteFile.delete();
			deleteFlag = true;
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_LOCAL)) {
			storageHandler = new StorageLocalHandler();
			storageHandler.deleteFile(storageFile);
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_FTP)) {
			String host = Storage.getInstance().getHost();
    		int port = Integer.parseInt(Storage.getInstance().getPort());
    		String id = Storage.getInstance().getUser();
    		String password = Storage.getInstance().getPassword();
    		
			storageHandler = new StorageFTPHandler(host, port, id, password);
			storageHandler.deleteFile(storageFile);
		} else if (Storage.getInstance().getProtocol().equals(Storage.STORAGE_PROTOCOL_SFTP)) {
			// TODO :chseok82: SFTP 파일삭제 기능 구현
		}
		
		return deleteFlag;
	}
	
	public static String filePathGen() {
		String filePath = "";
		
		try {
			filePath = "/courage/" + DateUtil.getYear() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/" + DateUtil.getHour();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filePath;
	}
}
