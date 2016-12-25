
package kr.pe.courage.common.hiercode;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * kr.pe.courage.cmm.code.service
 * HierCodeMap.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2013. 2. 4.
 * @Version : 1.0
 */
public class HierCodeMap {
	static private HierCodeMap m_HierCodeMap;
	static private HashMap<Object, List<HierCodeVO>> codeValues;
	static private String hierCodeImplClass;
	static private String hierDetailCodeImplClass;

	private HierCodeMap() {
		super();
	}

	static public HierCodeMap getInstance() {
		if (m_HierCodeMap == null) {
			synchronized (HierCodeMap.class) {
				if (m_HierCodeMap == null) {
					m_HierCodeMap = new HierCodeMap();
				}
			}
		}

		return m_HierCodeMap;
	}

	public void setHierCode(HashMap<Object, List<HierCodeVO>> codeValues) {
		HierCodeMap.codeValues = codeValues;
	}

	public List<HierCodeVO> getHierCode(String codeType) {
		List<HierCodeVO> resultList = null;
		
		if (codeValues != null) {
			resultList = codeValues.get(codeType);
		}
		
		return resultList;
	}

	public String getHierCodeName(String groupId, String code) {
		String codeName = "";
		List<HierCodeVO> codeList = codeValues.get(groupId);

		for (HierCodeVO codeVo : codeList) {
			if (StringUtils.equals(codeVo.getCode(),code) ) {
				codeName = codeVo.getCodeNm();
			}
		}

		return codeName;
	}
	
	public String getUpperCode(String groupId, String code) {
		String upperCode = "";
		List<HierCodeVO> codeList = codeValues.get(groupId);

		for (HierCodeVO codeVo : codeList) {
			if (StringUtils.equals(codeVo.getCode(),code) ) {
				upperCode = codeVo.getUpperCode();
			}
		}

		return upperCode;
	}

	public String getHierCodeImplClass() {
		return hierCodeImplClass;
	}

	public void setHierCodeImplClass(String hierCodeImplClass) {
		HierCodeMap.hierCodeImplClass = hierCodeImplClass;
	}

	public String getHierDetailCodeImplClass() {
		return hierDetailCodeImplClass;
	}

	public void setHierDetailCodeImplClass(String hierDetailCodeImplClass) {
		HierCodeMap.hierDetailCodeImplClass = hierDetailCodeImplClass;
	}
}
