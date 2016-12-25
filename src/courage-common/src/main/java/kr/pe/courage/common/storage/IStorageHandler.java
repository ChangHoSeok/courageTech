
package kr.pe.courage.common.storage;

import java.io.File;


public interface IStorageHandler {
	/**
	 * <pre>
	 * 1. 처리내용 : 스토리지 파일 저장
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2013. 2. 4.
	 * @Method Name : put
	 * @param storageFileVo
	 * @return 저장파일명
	 * @throws Exception
	 */
	public String put(StorageFile storageFileVo) throws Exception;

	/**
	 * <pre>
	 * 1. 처리내용 : 스토리지 파일 가져오기
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2013. 2. 4.
	 * @Method Name : get
	 * @param storageFileVo
	 * @return File
	 * @throws Exception
	 */
	public File get(StorageFile storageFileVo) throws Exception;

	/**
	 * <pre>
	 * 1. 처리내용 : 스토리지 파일삭제
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2013. 2. 4.
	 * @Method Name : deleteFile
	 * @param storageFileVo
	 * @return true : 삭제성공, false : 삭제실패
	 * @throws Exception
	 */
	public boolean deleteFile(StorageFile storageFileVo) throws Exception;

	/**
	 * <pre>
	 * 1. 처리내용 : 스토리지 디렉토리 삭제
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2013. 2. 4.
	 * @Method Name : deleteDir
	 * @param storageFileVo
	 * @return true : 삭제성공, false : 삭제실패
	 * @throws Exception
	 */
	public boolean deleteDir(StorageFile storageFileVo) throws Exception;
}
