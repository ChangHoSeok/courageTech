package kr.pe.courage.common.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:courage/spring/common/context-*.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class StorageFileTest extends TestCase {
	
	@Resource(name = "courageStorageService")
	IStorageService storageService;
	
	@Before
	public void setUp() throws Exception {
		String [] saveStorageList = {"E:\\savestorage\\storage1", "E:\\savestorage\\storage2"};
		Storage storage = Storage.getInstance();
		storage.setProtocol(Storage.STORAGE_PROTOCOL_LOCAL);
		storage.setStorageList(saveStorageList);
		storage.setSaveStorage("E:\\savestorage\\storage1");
	}
	
	@Test
	public void testInsertFile() {
		
		String[] testFileInfo = {"test1.txt", "test2.txt", "test3.txt"};
		
		try {
			List<StorageFile> storageFileList = new ArrayList<StorageFile>();
			for (String attachFile : testFileInfo) {
				String fileFullPath = "E:\\testfile\\" + attachFile;
				
				File uploadFile = new File(fileFullPath);
				StorageFile storageFile = new StorageFile();
				storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
				storageFile.setUploadFile(uploadFile);
				
				storageFileList.add(storageFile);
			}
			
			String atchFileId = storageService.insertFile(storageFileList);
			assertNotNull(atchFileId);
			
			System.out.println("fileId ::: " + atchFileId);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSelectFileList() {
		StorageFile storageFile = new StorageFile("CYLFILE_000000000056");
		
		try {
			List<StorageFile> storageFileList = storageService.selectFileList(storageFile);
			assertNotNull(storageFileList);
			
			for (StorageFile file : storageFileList) {
				file.setFileLocation(Storage.FILE_LOCATION_STORAGE);
				File downFile = StorageUtils.get(file);
				
				assertNotNull(downFile);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateFileList() {
		String[] testFileInfo = {"test4.txt"};
		String[] deleteFileInfo = {"CYLFILE_000000000056;1", "CYLFILE_000000000056;3"};
		String atchFileId = "CYLFILE_000000000056";
		
		try {
			// 파일 추가
			List<StorageFile> storageFileList = new ArrayList<StorageFile>();
			for (String attachFile : testFileInfo) {
				String fileFullPath = "E:\\testfile\\" + attachFile;
				
				File uploadFile = new File(fileFullPath);
				StorageFile storageFile = new StorageFile(atchFileId);
				storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
				storageFile.setUploadFile(uploadFile);
				
				storageFileList.add(storageFile);
			}
			
			// 파일 삭제
			for (String deleteFile : deleteFileInfo) {
				String [] atchFileInfo = deleteFile.split(";");
				
				StorageFile storageFile = new StorageFile(atchFileInfo[0]);
				storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
				storageFile.setFileSn(atchFileInfo[1]);
				storageFile.setDeleteFlag("1");
				
				storageFileList.add(storageFile);
			}
			
			storageService.updateFile(storageFileList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
