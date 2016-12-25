
package kr.pe.courage.tech.cop.bbs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.utils.img.HtmlImageParser;
import kr.pe.courage.common.utils.img.ImageElement;
import kr.pe.courage.common.utils.img.ImageThumbnail;
import kr.pe.courage.tech.cop.bbs.exception.ExistsAnswerException;
import kr.pe.courage.tech.cop.bbs.exception.ExistsReplyNTTException;
import kr.pe.courage.tech.cop.bbs.service.BoardService;
import kr.pe.courage.tech.cop.bbs.service.BoardVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * BoardServiceImpl.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 31.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 31., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;
	
	@Resource(name = "courageStorageService")
	private IStorageService storageService;

	@Override
	public int selectBoardListCount(BoardVO boardVO) throws EgovBizException {
		return boardDAO.selectListCount(boardVO);
	}

	@Override
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws EgovBizException {
		return boardDAO.selectList(boardVO);
	}

	@Override
	public BoardVO selectBoard(BoardVO boardVO) throws EgovBizException {
		BoardVO resultVO = boardDAO.getById(boardVO);
		
		try {
			if (resultVO != null && !"".equals(Util.nvl(resultVO.getAtchFileId()))) {
				resultVO.setListAtchFiles(storageService.selectFileList(new StorageFile(resultVO.getAtchFileId())));
			}
			
			if (resultVO != null && !"".equals(Util.nvl(resultVO.getMvpFileId()))) {
				resultVO.setListMvpFiles(storageService.selectFileList(new StorageFile(resultVO.getMvpFileId())));
			}
		} catch (Exception e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("첨부파일 정보 처리중 오류발생");
		}
		
		return resultVO;
	}

	@Override
	public void updateBoardRdcntIncrs(BoardVO boardVO) throws EgovBizException {
		try {
			boardDAO.updateBoardRdcntIncrs(boardVO);
		} catch (SQLException e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("DB 오류발생");
		}
	}
	
	@Override
	public void insertBoard(BoardVO boardVO) throws EgovBizException {
		InputStream imageInputStream = null;
		String tempStoragePath = Storage.getInstance().getTempPath(boardVO.getJsessionId());
		
		// 첨부파일 등록 처리
		if (boardVO.getUploadAtchFiles() != null) {
			boardVO.setAtchFileId(insertFile(tempStoragePath, boardVO.getUploadAtchFiles(), Storage.FILE_LOCATION_STORAGE));
		}

		// 동영상 등록 처리
		if (boardVO.getUploadMvpFiles() != null) {
			boardVO.setMvpFileId(insertFile(tempStoragePath, boardVO.getUploadMvpFiles(), Storage.FILE_LOCATION_CONTEXT));
		}
		
		try {
			/**
			 * Editor에서 썸네일 추출
			 * 갤러리형 게시판의 경우 일반 이미지 갤러리와 동영상 갤러리가 중복될 수 있음
			 * 우선순위는 동영상 썸네일이 직접 등록함으로 1순위도 둠
			 */
			if (Util.nvl(boardVO.getThumbUrl()).equals("")) {
				List<ImageElement> imgList = HtmlImageParser.getImageTagElement(boardVO.getNttCn());
				
				if (imgList != null && imgList.size() > 0) {
					for (ImageElement thumbImg : imgList) {
						if (!thumbImg.getImagePath().startsWith("http")) {
							String contextPath = Storage.getInstance().getContextPath().replaceAll("/", "");
							String contextFileNm = thumbImg.getImagePath().replaceAll(contextPath, "") + thumbImg.getImageNm();
							
							String imgFileNm = Storage.getInstance().getContextRealPath() + contextFileNm;
							String thumbFileNm = FilenameUtils.getFullPath(imgFileNm) + FilenameUtils.getBaseName(imgFileNm) + "_th." + FilenameUtils.getExtension(imgFileNm);
							
							imageInputStream = new FileInputStream(imgFileNm);
							// 썸네일 이미지 생성
							ImageThumbnail imageThumb = new ImageThumbnail();
							
							byte[] orginFile = IOUtils.toByteArray(imageInputStream);
							byte[] thFile = imageThumb.generateImage(orginFile, 320);
							
							FileUtils.writeByteArrayToFile(new File(thumbFileNm), thFile);
							
							thumbFileNm = "/" + FilenameUtils.getPath(contextFileNm) + FilenameUtils.getName(thumbFileNm);
							boardVO.setThumbUrl(thumbFileNm);
							
							break;
						}
					}
				}
			}
		
			// 저장하는 대상글이 답변글이면 정렬번호 조회, 최상위 글리면 그룹번호 조회
			if (boardVO.getParntsSntncNo() <= 0) {
				boardVO.setParntsSntncNo(0);
				boardVO.setGroupNo(boardDAO.selectBoardGroupNo(boardVO));
			} else {
				boardVO.setSortOrdr(boardDAO.selectBoardGroupSortOrdr(boardVO));
			}
		} catch (SQLException se) {
			logger.error("Exception Trace - ", se);
			throw new EgovBizException("DB 오류발생");
		} catch (IOException e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("썸네일 이미지 생성 중 오류발생");
		} finally {
			if (imageInputStream != null) {
				IOUtils.closeQuietly(imageInputStream);
			}
		}

		boardDAO.insert(boardVO);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws EgovBizException {
		InputStream imageInputStream = null;
		String tempStoragePath = Storage.getInstance().getTempPath(boardVO.getJsessionId());
		
		// 첨부파일 수정 (신규등록 파일 또는 삭제대상 파일이 존재할 경우 처리)
		if (boardVO.getUploadAtchFiles() != null || boardVO.getDeleteAtchFiles() != null) {
			if (Util.isNotBlank(boardVO.getAtchFileId())) {
				updateFile(boardVO.getAtchFileId(), tempStoragePath, boardVO.getUploadAtchFiles(), boardVO.getDeleteAtchFiles(), Storage.FILE_LOCATION_STORAGE);
			} else {
				boardVO.setAtchFileId(updateFile(boardVO.getAtchFileId(), tempStoragePath, boardVO.getUploadAtchFiles(), boardVO.getDeleteAtchFiles(), Storage.FILE_LOCATION_STORAGE));
			}
		}
		
		// 동영상 파일 수정 (신규등록 파일 또는 삭제대상 파일이 존재할 경우 처리)
		if (boardVO.getUploadMvpFiles() != null || boardVO.getDeleteMvpFiles() != null) {
			if (Util.isNotBlank(boardVO.getMvpFileId())) {
				updateFile(boardVO.getMvpFileId(), tempStoragePath, boardVO.getUploadMvpFiles(), boardVO.getDeleteMvpFiles(), Storage.FILE_LOCATION_CONTEXT);
			} else {
				boardVO.setMvpFileId(updateFile(boardVO.getMvpFileId(), tempStoragePath, boardVO.getUploadMvpFiles(), boardVO.getDeleteMvpFiles(), Storage.FILE_LOCATION_CONTEXT));
			}
		}
		
		try {
			/**
			 * Editor에서 썸네일 추출
			 * 갤러리형 게시판의 경우 일반 이미지 갤러리와 동영상 갤러리가 중복될 수 있음
			 * 우선순위는 동영상 썸네일이 직접 등록함으로 1순위도 둠
			 */
			if (Util.nvl(boardVO.getThumbUrl()).equals("")) {
				List<ImageElement> imgList = HtmlImageParser.getImageTagElement(boardVO.getNttCn());
				
				if (imgList != null && imgList.size() > 0) {
					for (ImageElement thumbImg : imgList) {
						if (!thumbImg.getImagePath().startsWith("http")) {
							String contextPath = Storage.getInstance().getContextPath().replaceAll("/", "");
							String contextFileNm = thumbImg.getImagePath().replaceAll(contextPath, "") + thumbImg.getImageNm();
							
							String imgFileNm = Storage.getInstance().getContextRealPath() + contextFileNm;
							String thumbFileNm = FilenameUtils.getFullPath(imgFileNm) + FilenameUtils.getBaseName(imgFileNm) + "_th." + FilenameUtils.getExtension(imgFileNm);
							
							imageInputStream = new FileInputStream(imgFileNm);
							// 썸네일 이미지 생성
							ImageThumbnail imageThumb = new ImageThumbnail();
							
							byte[] orginFile = IOUtils.toByteArray(imageInputStream);
							byte[] thFile = imageThumb.generateImage(orginFile, 320);
							
							FileUtils.writeByteArrayToFile(new File(thumbFileNm), thFile);
							
							thumbFileNm = "/" + FilenameUtils.getPath(contextFileNm) + FilenameUtils.getName(thumbFileNm);
							boardVO.setThumbUrl(thumbFileNm);
							
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("썸네일 이미지 생성 중 오류발생");
		} finally {
			if (imageInputStream != null) {
				IOUtils.closeQuietly(imageInputStream);
			}
		}
		
		boardDAO.update(boardVO);
	}

	@Override
	public void deleteBoard(BoardVO boardVO) throws EgovBizException, ExistsReplyNTTException, ExistsAnswerException {
		BoardVO checkVO = boardDAO.getById(boardVO);
		
		if (checkVO.getAnswerCnt() > 0) {
			throw new ExistsAnswerException("댓글이 존재하여 게시글을 삭제할 수 없습니다.");
		}
		
		if (checkVO.getReplyCnt() > 0) {
			throw new ExistsReplyNTTException("답글이 존재하여 게시글을 삭제할 수 없습니다.");
		}
		
		try {
			if (!"".equals(Util.nvl(checkVO.getAtchFileId()))) {
				storageService.truncateFile(new StorageFile(checkVO.getAtchFileId()));
			}
		} catch (Exception e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("첨부파일 정보 처리중 오류발생");
		}
		
		boardDAO.delete(boardVO);
	}

	@Override
	@Cacheable(cacheName = "noticeRollingListCache")
	public List<BoardVO> selectNoticeRollingList() throws EgovBizException {
		List<BoardVO> resultList = null;
		
		try {
			resultList = boardDAO.selectNoticeRollingList();
		} catch (RuntimeException e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("공지사항 목록 조회 오류발생");
		}
		
		return resultList;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 등록
	 * </pre>
	 * 
	 * @Author	: schkk
	 * @Date	: 2016. 12. 13.
	 * @Method Name : insertFile
	 * @param tempStoragePath
	 * @param atchFiles
	 * @return
	 * @throws EgovBizException
	 */
	private String insertFile(String tempStoragePath, String[] atchFiles, String location) throws EgovBizException {
		String atchFileId = "";
		List<StorageFile> storageFileList = new ArrayList<StorageFile>();
		
		for (String atchFile : atchFiles) {
			File uploadFile = new File(tempStoragePath + File.separator + atchFile);
			
			StorageFile storageFile = new StorageFile();
			storageFile.setFileLocation(location);
			storageFile.setUploadFile(uploadFile);
			
			storageFileList.add(storageFile);
		}
		
		try {
			atchFileId = storageService.insertFile(storageFileList);
		} catch (Exception e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("첨부파일 정보 처리중 오류발생");
		}
		
		return atchFileId;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 수정
	 * </pre>
	 * 
	 * @Author	: schkk
	 * @Date	: 2016. 12. 13.
	 * @Method Name : updateFile
	 * @param atchFileId
	 * @param tempStoragePath
	 * @param atchFiles
	 * @param deleteFiles
	 * @return
	 * @throws EgovBizException
	 */
	private String updateFile(String atchFileId, String tempStoragePath, String[] atchFiles, String[] deleteFiles, String location) throws EgovBizException {
		String resultAtchFileId = "";
		List<StorageFile> storageFileList = new ArrayList<StorageFile>();
		
		// 신규파일
		if (atchFiles != null) {
			for (String atchFile : atchFiles) {
				File uploadFile = new File(tempStoragePath + File.separator + atchFile);
				
				StorageFile storageFile = new StorageFile(atchFileId);
				storageFile.setFileLocation(location);
				storageFile.setUploadFile(uploadFile);
				
				storageFileList.add(storageFile);
			}
		}
		
		// 삭제파일
		if (deleteFiles != null) {
			for (String atchFile : deleteFiles) {
				String []atchFileInfo = atchFile.split(";");
				
				StorageFile storageFile = new StorageFile(atchFileId);
				storageFile.setFileLocation(location);
				storageFile.setDeleteFlag(Storage.FILE_FLAG_DELETE);
				storageFile.setAtchFileId(atchFileInfo[0]);
				storageFile.setFileSn(atchFileInfo[1]);
				
				storageFileList.add(storageFile);
			}
		}
		
		try {
			if (Util.isNotBlank(atchFileId)) {
				storageService.updateFile(storageFileList);
			} else {
				resultAtchFileId = storageService.insertFile(storageFileList);
			}
		} catch (Exception e) {
			logger.error("Exception Trace - ", e);
			throw new EgovBizException("첨부파일 정보 처리중 오류발생");
		}
		
		return resultAtchFileId;
	}
}