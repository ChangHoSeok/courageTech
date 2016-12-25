package egovframework.com.sym.prm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/** 
 * 프로그램목록관리 및 프로그램변경관리에 관한 비즈니스 구현 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */
@Service("ProgrmManageService")
public class EgovProgrmManageServiceImpl extends AbstractServiceImpl implements EgovProgrmManageService {

	@Resource(name="ProgrmManageDAO")
    private ProgrmManageDAO progrmManageDAO;

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo  ComDefaultVO
	 * @return Integer
	 * @exception Exception
	 */
    public int selectProgrmListTotCnt(ProgrmManageVO progrmManageVO) throws Exception {
        return progrmManageDAO.selectProgrmListTotCnt(progrmManageVO);
	}
	
	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List 
	 * @exception Exception
	 */
	public List<ProgrmManageVO> selectProgrmList(ProgrmManageVO progrmManageVO) throws Exception {
   		return progrmManageDAO.selectProgrmList(progrmManageVO);
	}
	

	/**
	 * 프로그램 상세정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmManageVO 
	 * @exception Exception
	 */
	public ProgrmManageVO selectProgrm(ProgrmManageVO progrmManageVO) throws Exception{
         return progrmManageDAO.selectProgrm(progrmManageVO);
	}
	
	@Override
	@Cacheable(cacheName = "progrmNmCache")
	public String selectProgrmNm(ProgrmManageVO progrmManageVO) throws Exception {
		return progrmManageDAO.selectProgrmNm(progrmManageVO);
	}

	/**
	 * 프로그램 정보를 등록
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = {"progrmNmCache", "progrmRequestCheckCache"}, removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void insertProgrm(ProgrmManageVO vo) throws Exception {
    	progrmManageDAO.insertProgrm(vo);
	}

	/**
	 * 프로그램 정보를 수정
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = {"progrmNmCache", "progrmRequestCheckCache"}, removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void updateProgrm(ProgrmManageVO vo) throws Exception {
    	progrmManageDAO.updateProgrm(vo);
	}
	
	@TriggersRemove(cacheName = {"progrmNmCache", "progrmRequestCheckCache"}, removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void deleteProgrm(ProgrmManageVO progrmManageVO) throws Exception {
		progrmManageDAO.deleteProgrmMenuMapping(progrmManageVO); // 메뉴 매핑정보 삭제
		progrmManageDAO.deleteProgrmAuthorMapping(progrmManageVO); // 권한 매핑정보 삭제
		progrmManageDAO.deleteProgrm(progrmManageVO); // 프로그램 정보 삭제
	}
	
	@TriggersRemove(cacheName = {"progrmNmCache", "progrmRequestCheckCache"}, removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void deleteProgrmList(ProgrmManageVO progrmManageVO) throws Exception {
		for (String progrmFileNm : progrmManageVO.getDeletelProgrmFileNmList()) {
			progrmManageVO.setProgrmFileNm(progrmFileNm);
			
			progrmManageDAO.deleteProgrmMenuMapping(progrmManageVO); // 메뉴 매핑정보 삭제
			progrmManageDAO.deleteProgrmAuthorMapping(progrmManageVO); // 권한 매핑정보 삭제
			progrmManageDAO.deleteProgrm(progrmManageVO); // 프로그램 정보 삭제
		}
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int  
	 * @exception Exception
	*/
	public int selectProgrmNMTotCnt(ProgrmManageVO progrmManageVO) throws Exception{
		return progrmManageDAO.selectProgrmNMTotCnt(progrmManageVO);
	}

	public List<ProgrmManageVO> selectProgrmListPopup(ProgrmManageVO progrmManageVO) throws Exception {
		return progrmManageDAO.selectProgrmListPopup(progrmManageVO);
	}
	
	public List<ProgrmManageVO> selectProgrmManageIncludeList(ProgrmManageVO progrmManageVO) throws Exception {
		return progrmManageDAO.selectProgrmManageIncludeList(progrmManageVO);
	}

	public List<ProgrmManageVO> selectProgrmLoginCheckList() throws Exception {
		return progrmManageDAO.selectProgrmLoginCheckList();
	}

	@Override
	@Cacheable(cacheName = "progrmRequestCheckCache")
	public List<ProgrmManageVO> selectProgrmRequestCheckList(ProgrmManageVO progrmManageVO) throws Exception {
		return progrmManageDAO.selectProgrmRequestCheckList(progrmManageVO);
	}
}