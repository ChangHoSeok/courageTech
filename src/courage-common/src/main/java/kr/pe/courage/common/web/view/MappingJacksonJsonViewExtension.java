
package kr.pe.courage.common.web.view;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class MappingJacksonJsonViewExtension extends MappingJacksonJsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		Object result = super.filterModel(model);
		if (!(result instanceof Map)) {
			return result;
		}
		
		Map map = (Map) result;
		
		if (map.size() == 1) {
			return map.values().toArray()[0];
		}
		
		return map;
	}
}
