
package kr.pe.courage.common.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public abstract class AbstractDAO<T> implements IGenericDAO<T> {

	protected IBaseDAO baseDAO;

	@Resource(name = "courageCom.baseDAO")
	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public IBaseDAO getBaseDAO() {
		return this.baseDAO;
	}

	protected abstract String getNameSpace();

	protected String getQueryId(String queryId) {
		return getNameSpace() + "." + queryId;
	}

	protected int getCount(String queryId, Object params) {
		return baseDAO.getCount(queryId, params);
	}

	@SuppressWarnings("unchecked")
	protected T get(String queryId, Object params) {
		return (T) baseDAO.get(queryId, params);
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated
	protected T selectByPk(String queryId, Object parameterObject) {
		return (T) baseDAO.get(queryId, parameterObject);
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated
	protected List<T> list(String queryId) {
		return (List<T>) baseDAO.list(queryId);
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	protected List<T> list(String queryId, Object parameterObject) {
		return (List<T>) baseDAO.list(queryId, parameterObject);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectList(Object param) {
		return (List<T>) baseDAO.list(getQueryId("selectList"), param);
	}

	@SuppressWarnings("rawtypes")
	protected List selectList(String queryId, Object param) {
		return baseDAO.list(getQueryId(queryId), param);
	}

	public int selectListCount(Object param) {
		return baseDAO.getCount(getQueryId("selectListCount"), param);
	}
	
	protected int selectListCount(String queryId, Object param) {
		return baseDAO.getCount(getQueryId(queryId), param);
	}

	public Object insert(Object param) {
		return baseDAO.insert(getQueryId("insert"), param);
	}
	
	protected Object insert(String queryId, Object param) {
		return baseDAO.insert(getQueryId(queryId), param);
	}

	public int update(Object param) {
		return baseDAO.update(getQueryId("update"), param);
	}

	protected int update(String queryId, Object param) {
		return baseDAO.update(getQueryId(queryId), param);
	}

	public int delete(Object param) {
		return baseDAO.delete(getQueryId("delete"), param);
	}
	
	protected int delete(String queryId, Object param) {
		return baseDAO.delete(getQueryId(queryId), param);
	}
	
	protected int delete(String queryId) {
		return baseDAO.delete(getQueryId(queryId));
	}

	public T getById(Object param) {
		return get(getQueryId("getById"), param);
	}
	
	protected Object getById(String queryId, Object param) {
		return get(getQueryId(queryId), param);
	}
	
	protected Object selectObject(String queryId, Object params) {
		return baseDAO.get(getQueryId(queryId), params);
	}

	protected Map map(String queryId, Object params, String key) {
		return baseDAO.map(getQueryId(queryId), params, key);
	}

	protected Map map(String queryId, Object params, String key, String value) {
		return baseDAO.map(getQueryId(queryId), params, key, value);
	}

	public boolean isExist(Object param) {
		int count = baseDAO.getCount(getQueryId("isExist"), param);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isExist(String queryId, Object param) {
		int count = baseDAO.getCount(getQueryId(queryId), param);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	protected Integer batchInsert(String queryId, List<Object> list) {
		return baseDAO.batchInsert(getQueryId(queryId), list);
	}
}
