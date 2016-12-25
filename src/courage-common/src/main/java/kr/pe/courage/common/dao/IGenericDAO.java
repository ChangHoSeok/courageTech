package kr.pe.courage.common.dao;

import java.util.List;

public interface IGenericDAO <T>{
	
	/**
	 * 목록을 가져온다.
	 * @param param
	 * @return
	 */
	public List<T> selectList(Object param );
	
	/**
	 * 목록의 전체 개수를 가져온다.
	 * @param param
	 * @return
	 */
	public int selectListCount(Object param );
	
	/**
	 * 입력한다.
	 * @param param
	 * @return
	 */
	public Object insert( Object param);
	
	/**
	 * 수정 한다.
	 * @param param
	 * @return
	 */
	public int update( Object param);
	
	/**
	 * 삭제 한다.
	 * @param id
	 * @return
	 */
	public int delete( Object param );

	/**
	 * 아이디를  이용해서 가져온다.
	 * @param id
	 * @return
	 */
	public T getById(Object param);
	
	/**
	 * 이미 모델이 있는지 검사
	 * @param id
	 * @return
	 */
	public boolean isExist(Object param);
}
