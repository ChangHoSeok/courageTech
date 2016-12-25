
package kr.pe.courage.common.prop;

import java.util.HashMap;

public class PropertiesMap {
	static private PropertiesMap m_PropertiesMap;
	static private HashMap<Object, Object> propertiesValues;
	static private Object etcObj;

	private PropertiesMap() {
		super();
	}

	static public PropertiesMap getInstance() {
		if (m_PropertiesMap == null) {
			synchronized (PropertiesMap.class) {
				if (m_PropertiesMap == null) {
					m_PropertiesMap = new PropertiesMap();
				}
			}
		}

		return m_PropertiesMap;
	}

	public void setProperties(HashMap<Object, Object> propertiesValues) {
		PropertiesMap.propertiesValues = propertiesValues;
	}

	public HashMap<Object, Object> getProperties() {
		return PropertiesMap.propertiesValues;
	}

	public String getValue(String key) {
		String resultStr = "";

		if (propertiesValues.get(key) != null) {
			resultStr = propertiesValues.get(key).toString();
		}

		return resultStr;
	}
	
	public void setEtcObj(Object obj) {
		PropertiesMap.etcObj = obj;
	}
	
	public Object getEtcObj() {
		return PropertiesMap.etcObj;
	}
}
