package kr.pe.courage.common.dao;

import java.util.List;
import java.util.Map;
/**
 * DAO에서 공통적으로 사용하는 메소드의 인터페이스 
 * @author nowone
 *
 */
public interface IBaseDAO {
	 /**
     * 입력 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 입력 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 입력 처리 SQL mapping 입력 데이터를 세팅한 파라메터
     *        객체(보통 VO 또는 Map)
     * @return 입력 시 selectKey 를 사용하여 key 를 딴 경우 해당 key
     */
    public Object insert(String queryId, Object parameterObject);

    /**
     * 수정 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 수정 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 수정 처리 SQL mapping 입력 데이터(key 조건 및 변경
     *        데이터)를 세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return DBMS가 지원하는 경우 update 적용 결과 count
     */
    public int update(String queryId, Object parameterObject);

    /**
     * 삭제 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 삭제 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 삭제 처리 SQL mapping 입력 데이터(일반적으로 key 조건)를
     *        세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return DBMS가 지원하는 경우 delete 적용 결과 count
     */
    public int delete(String queryId, Object parameterObject);
    
    /**
     * 삭제 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 삭제 처리 SQL mapping 쿼리 ID
     * @return DBMS가 지원하는 경우 delete 적용 결과 count
     */
    public int delete(String queryId);

    /**
     * pk 를 조건으로 한 단건조회 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 단건 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 단건 조회 처리 SQL mapping 입력 데이터(key)를 세팅한
     *        파라메터 객체(보통 VO 또는 Map)
     * @return 결과 객체 - SQL mapping 파일에서 지정한
     *         resultClass/resultMap 에 의한 단일 결과 객체(보통
     *         VO 또는 Map)
     */
    public Object selectByPk(String queryId, Object parameterObject);

    public List list(String queryId);
    
    /**
     * 리스트 조회 처리 SQL mapping 을 실행한다.
     * @param queryId
     *        - 리스트 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를
     *        세팅한 파라메터 객체(보통 VO 또는 Map)
     * @return 결과 List 객체 - SQL mapping 파일에서 지정한
     *         resultClass/resultMap 에 의한 결과 객체(보통 VO
     *         또는 Map)의 List
     */
    public List list(String queryId, Object parameterObject);

    /**
     * 부분 범위 리스트 조회 처리 SQL mapping 을 실행한다. (부분 범위 -
     * pageIndex 와 pageSize 기반으로 현재 부분 범위 조회를 위한
     * skipResults, maxResults 를 계산하여 ibatis 호출)
     * @param queryId
     *        - 리스트 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject
     *        - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를
     *        세팅한 파라메터 객체(보통 VO 또는 Map)
     * @param pageIndex
     *        - 현재 페이지 번호
     * @param pageSize
     *        - 한 페이지 조회 수(pageSize)
     * @return 부분 범위 결과 List 객체 - SQL mapping 파일에서 지정한
     *         resultClass/resultMap 에 의한 부분 범위 결과
     *         객체(보통 VO 또는 Map) List
     */
    public List listWithPaging(String queryId, Object parameterObject,
            int pageIndex, int pageSize);
    
    
    /**
	 * 단순 카운트 조회시 사용 ex) select count(*) as cnt from user
	 * 
	 * @param queryId
	 * @param params
	 * @return
	 * @throws Exception
	 */
    public int getCount(String queryId, Object params);
	
	/**
	 * 하나의 객체를 가져오는 메소드 
	 * 반드시 리터값을 VO가 되어야 된다.
	 * @param queryId
	 * @param params
	 * @return
	 */
    public Object get(String queryId, Object params);
    
    public Map<Object, Object> map(String queryId, Object params, String key);
    
    public Map<Object, Object> map(String queryId, Object params, String key, String value);
    
    /**
     * <pre>
     * 1. 개요 : 배치등록처리
     * </pre>
     * 
     * @Author	: ChangHo Seok
     * @Date	: 2016. 10. 10.
     * @Method Name : batchInsert
     * @param queryId
     * @param list
     * @return
     */
    public Integer batchInsert(final String queryId, final List<Object> list);
}
