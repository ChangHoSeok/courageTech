package kr.pe.courage.common.storage;

import java.util.List;

/**
 * 첨부파일 관리 인터페이스 정의
 * <pre>
 * kr.pe.courage.common.storage
 * IStorageService.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 22.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 *  2013.11.22    석창호                                            최초등록
 * ================================================================
 * </pre>
 */
public interface IStorageService {
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 22.
	 * @Method Name : selectFileList
	 * @param storageFile
	 * @return 파일목록
	 * @throws Exception
	 */
	public List<StorageFile> selectFileList(StorageFile storageFile) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 갯수 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 24.
	 * @Method Name : selectFileListCount
	 * @param storageFile
	 * @return 첨부파일 갯수
	 * @throws Exception
	 */
	public int selectFileListCount(StorageFile storageFile) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 24.
	 * @Method Name : selectFile
	 * @param storageFile
	 * @return 첨부파일 정보
	 * @throws Exception
	 */
	public StorageFile selectFile(StorageFile storageFile) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 22.
	 * @Method Name : insertFile
	 * @param storageFile
	 * @return 파일아이디
	 * @throws Exception
	 */
	public String insertFile(List<StorageFile> storageFileList) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 수정
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 22.
	 * @Method Name : updateFile
	 * @param storageFileList
	 * @throws Exception
	 */
	public void updateFile(List<StorageFile> storageFileList) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 삭제 (단일파일)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 22.
	 * @Method Name : deleteFile
	 * @param storageFile
	 * @throws Exception
	 */
	public void deleteFile(List<StorageFile> storageFileList) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 존재여부 확인
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 24.
	 * @Method Name : isExistsFile
	 * @param storageFile
	 * @return 파일 존재여부
	 * @throws Exception
	 */
	public boolean isExistsFile(StorageFile storageFile) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : atchFileId에 해당하는 전체 파일 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 24.
	 * @Method Name : truncateFile
	 * @param storageFile
	 * @throws Exception
	 */
	public void truncateFile(StorageFile storageFile) throws Exception;
}
