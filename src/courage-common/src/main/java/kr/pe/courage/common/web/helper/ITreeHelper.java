package kr.pe.courage.common.web.helper;

import java.util.List;
import java.util.Map;

public interface ITreeHelper {

	public List<Map<String, Object>> process(List<Map<String, Object>> param, String selectedId);
}
