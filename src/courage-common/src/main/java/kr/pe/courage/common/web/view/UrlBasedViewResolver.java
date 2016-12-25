package kr.pe.courage.common.web.view;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.tiles2.TilesView;

/**
 * <pre>
 * kr.pe.courage.cmm.view
 * UrlBasedViewResolver.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 4. 5.
 * @Version	: 1.0
 */
public class UrlBasedViewResolver extends org.springframework.web.servlet.view.UrlBasedViewResolver implements Ordered {

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		
		if (viewObj instanceof TilesView) {
			TilesView tilesView = (TilesView) viewObj;
			
			if (!isTilesViewName(tilesView.getBeanName())) {
				return null;
			}
		}
		
		return super.loadView(viewName, locale);
	}
	
	private boolean isTilesViewName(String viewName) {
		boolean resultFlag = false;
		
		if (viewName.startsWith("layout.")) {
			resultFlag = true;
		} else if (viewName.startsWith("error.")) {
			resultFlag = true;
		}
		
		return resultFlag;
	}
	
}
