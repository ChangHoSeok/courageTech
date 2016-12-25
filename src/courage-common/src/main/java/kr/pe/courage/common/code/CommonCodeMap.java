
package kr.pe.courage.common.code;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * kr.pe.courage.cmm.code.service
 * CommonCodeMap.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2013. 2. 4.
 * @Version : 1.0
 */
public class CommonCodeMap {
	static private CommonCodeMap m_CommonCodeMap;
	static private HashMap<Object, List<CommonCodeVO>> codeValues;
	static private String commonCodeImplClass;
	static private String commonDetailCodeImplClass;

	private CommonCodeMap() {
		super();
	}

	static public CommonCodeMap getInstance() {
		if (m_CommonCodeMap == null) {
			synchronized (CommonCodeMap.class) {
				if (m_CommonCodeMap == null) {
					m_CommonCodeMap = new CommonCodeMap();
				}
			}
		}

		return m_CommonCodeMap;
	}

	public void setCommonCode(HashMap<Object, List<CommonCodeVO>> codeValues) {
		CommonCodeMap.codeValues = codeValues;
	}

	public List<CommonCodeVO> getCode(String codeType) {
		List<CommonCodeVO> resultList = null;
		
		if (codeValues != null) {
			resultList = codeValues.get(codeType);
		}
		
		return resultList;
	}

	public String getCodeName(String codeId, String code) {
		String codeName = "";
		List<CommonCodeVO> codeList = codeValues.get(codeId);

		for (CommonCodeVO codeVo : codeList) {
			if (StringUtils.equals(codeVo.getCode(),code)) {
				codeName = codeVo.getCodeNm();
			}
		}

		return codeName;
	}

	public String getCommonCodeImplClass() {
		return commonCodeImplClass;
	}

	public void setCommonCodeImplClass(String commonCodeImplClass) {
		CommonCodeMap.commonCodeImplClass = commonCodeImplClass;
	}

	public String getCommonDetailCodeImplClass() {
		return commonDetailCodeImplClass;
	}

	public void setCommonDetailCodeImplClass(String commonDetailCodeImplClass) {
		CommonCodeMap.commonDetailCodeImplClass = commonDetailCodeImplClass;
	}
}
