package kr.pe.courage.common.storage.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.common.storage.StorageFile;

@Repository("courageStorageDAO")
public class StorageDAO extends AbstractDAO<StorageFile> {

	@Override
	protected String getNameSpace() {
		return "courageStorage";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 파일 마스터 정보 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 22.
	 * @Method Name : insertFileMaster
	 * @param storageFile
	 * @throws Exception
	 */
	public void insertFileMaster(StorageFile storageFile) throws Exception {
		insert("insertFileMaster", storageFile);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 파일 마스터 정보 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 24.
	 * @Method Name : deleteFileMaster
	 * @param storageFile
	 * @throws Exception
	 */
	public void deleteFileMaster(StorageFile storageFile) throws Exception {
		delete("truncateFile", storageFile);
		delete("deleteFileMaster", storageFile);
	}
}
