package kr.pe.courage.common.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

public class CourageIBatisBaseDAO extends EgovAbstractDAO implements IBaseDAO {
	
	@Resource(name="courageCom.sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
    public List list(String queryId) {
        return getSqlMapClientTemplate().queryForList(queryId);
    }
	
	/**
	 * 단순 카운트 조회시 사용 ex) select count(*) as cnt from user
	 * 
	 * @param queryId
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int getCount(String queryId, Object params) {
		Object abc = getSqlMapClientTemplate().queryForObject(queryId, params);
		Integer count = (Integer)abc;
		
		if(count==null) {
			return 0;
		} else {
			return count.intValue();
		}
	}
	
	/**
	 * 하나의 객체를 가져오는 메소드 
	 * 반드시 리터값을 VO가 되어야 된다.
	 * @param queryId
	 * @param params
	 * @return
	 */
	public Object get(String queryId, Object params) {
		return getSqlMapClientTemplate().queryForObject(queryId, params);
		
	}

	public int delete(String queryId) {
		return getSqlMapClientTemplate().delete(queryId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> map(String queryId, Object params, String key) {
		return getSqlMapClientTemplate().queryForMap(queryId, params, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> map(String queryId, Object params, String key, String value) {
		return getSqlMapClientTemplate().queryForMap(queryId, params, key, value);
	}
	
	@SuppressWarnings("unchecked")
	public Integer batchInsert(final String queryId, final List<Object> list) {
        return (Integer) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor)
                    throws SQLException {

                executor.startBatch();

                for (Iterator<Object> itr = list.iterator(); itr.hasNext();) {
                    executor.insert(queryId, itr.next());
                }

                return executor.executeBatch();
            }
        });
    }
}
