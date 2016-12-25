
package kr.pe.courage.common.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.net.ftp.FtpManager;
import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.Util;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * storage 설정정보 초기화
 * 
 * <pre>
 * kr.pe.courage.common.web.servlet
 * SystemConfigInitServlet.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class SystemConfigInitServlet extends HttpServlet {
	private static final long serialVersionUID = 571311190879279146L;
	
	private static Logger logger = Logger.getLogger(SystemConfigInitServlet.class);
	
	private static final String PROPS_SYSTEM_STORAGE_LIST = "system.storage.list";
	private static final String PROPS_SYSTEM_FILE_PROTOCOL = "system.fileserver.protocol";
	private static final String PROPS_SYSTEM_FILESERVER_HOST = "system.fileserver.host";
	private static final String PROPS_SYSTEM_FILESERVER_PORT = "system.fileserver.port";
	private static final String PROPS_SYSTEM_FILESERVER_USER = "system.fileserver.user";
	private static final String PROPS_SYSTEM_FILESERVER_PASSWORD = "system.fileserver.password";
	private static final String PROPS_SYSTEM_STORAGE_TEMP_PATH = "system.storage.tempPath";
	private static final String PROPS_CONTEXT_STORAGE_PATH = "context.storage.path";
	private static final String PROPS_SYSTEM_JXLS_PATH = "system.jxls.path";
	
	private HashMap<Object, Object> tempMap = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 프로퍼티 초기화
			initSystemConfigInfo();

			// alert 메시지 표시할때 백그라운드 한글깨짐 문제로 삽입
			resp.setHeader("Content-Type", "text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();
			out.println("<script>alert('storage 설정이 초기화되었습니다.');history.back();</script>");
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	@Override
	public void init() throws ServletException {
		// 프로퍼티 초기화
		initSystemConfigInfo();
	}

	private void initSystemConfigInfo() {
		String storagePropertiesPath = Util.isNotBlank(getInitParameter("config"))? getInitParameter("config") : "";
		Storage storage = Storage.getInstance();
		PropertiesMap propMap = PropertiesMap.getInstance();
		logger.info("########################################");
		logger.info("## System 설정값을 초기화 합니다.");
		
		try {
			if ( storagePropertiesPath != null && !"".equals(storagePropertiesPath) ) {
				for(String path : storagePropertiesPath.split(",")){
					propertiesLoad(path);
				}
				
				storage.setContextPath(getServletContext().getContextPath());
				storage.setContextRealPath(FilenameUtils.separatorsToSystem(getServletContext().getRealPath("")));
				
				String storageList = propMap.getValue(PROPS_SYSTEM_STORAGE_LIST);
				String[] storageArray;
				String[] storagePathArray;
				String storageSavePath = "";
				String protocol = "";
				String host = "";
				String port = "";
				String user = "";
				String password = "";
				int storageCnt = 0;
				
				if (Util.nvl(storageList).equals("")) {
					logger.error("## System 설정값이 존재하지 않습니다.");
					logger.error("## 확인 후 재기동 하기시 바랍니다.");
				} else {
					storageArray = storageList.replaceAll(" ", "").split(",");
					storageCnt = storageArray.length;
					
					logger.info("## 스토리지 사용 갯수 :::: " + storageCnt);
					logger.info("## 전체 스토리지 목록을 출력합니다.");
					
					storagePathArray = new String[storageArray.length];
					for (int i = 0; i < storageArray.length; i++) {
						storagePathArray[i] = propMap.getValue("system." + storageArray[i] + ".path");
						logger.info("## " + storageArray[i] + " :::: " + storagePathArray[i]);
					}
					
					storageSavePath = storagePathArray[0];
					
					logger.info("## 스토리지 저장 경로 :::: " + storageSavePath);
					storage.setStorageList(storagePathArray);
					storage.setSaveStorage(storageSavePath);
					
					protocol = propMap.getValue(PROPS_SYSTEM_FILE_PROTOCOL);
					logger.info("## 스토리지 protocol :::: " + protocol);
					storage.setProtocol(protocol);
					
					if (!protocol.equals(Storage.STORAGE_PROTOCOL_LOCAL)) {
						host = propMap.getValue(PROPS_SYSTEM_FILESERVER_HOST);
						port = propMap.getValue(PROPS_SYSTEM_FILESERVER_PORT);
						user = propMap.getValue(PROPS_SYSTEM_FILESERVER_USER);
						password = propMap.getValue(PROPS_SYSTEM_FILESERVER_PASSWORD);
						
						logger.info("## host :::: " + host);
						logger.info("## port :::: " + port);
						logger.info("## user :::: " + user);
						logger.info("## password :::: " + password);
						
						storage.setHost(host);
						storage.setPort(port);
						storage.setUser(user);
						storage.setPassword(password);
					}
					
					if (protocol.equals(Storage.STORAGE_PROTOCOL_FTP)) {
						logger.info("## 파일서버와 테스트 접속을 시도합니다.");
						
						FtpManager ftpManager = new FtpManager(host, Integer.parseInt(port), user, password);
						ftpManager.connection();
						
						if (ftpManager.login()) {
							logger.info("## 파일서버와 접속을 성공했습니다.");
						} else {
							logger.info("## 파일서버와 접속을 실패했습니다.");
							logger.info("## 파일서버 정보를 확인하세요.");
						}
						
						ftpManager.disconnect();
					} else if (protocol.equals(Storage.STORAGE_PROTOCOL_SFTP)) {
						logger.info("##미구현된 protocol 입니다.");
					}
				}
				
				storage.setTempPath(storage.getContextRealPath() + propMap.getValue(PROPS_SYSTEM_STORAGE_TEMP_PATH));
				storage.setExcelExportPath(storage.getContextRealPath() + propMap.getValue(PROPS_SYSTEM_STORAGE_TEMP_PATH));
				storage.setJxlsPath(storage.getContextRealPath() + propMap.getValue(PROPS_SYSTEM_JXLS_PATH));
				storage.setStorageContextPath(propMap.getValue(PROPS_CONTEXT_STORAGE_PATH));
				
				logger.info("## 임시저장 경로 :::: " + storage.getTempPath());
				logger.info("## ContextPath :::: " + storage.getContextPath());
				logger.info("## ExcelExportPath :::: " + storage.getExcelExportPath());
			} else {
				logger.info("## System 설정 파일이 존재하지 않습니다.");
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		logger.info("## System 설정값을 정상적으로 초기화 하였습니다.");
		logger.info("########################################");
	}

	private void propertiesLoad(String propertiesFile) throws Exception {
		
		InputStream stream = SystemConfigInitServlet.class.getClassLoader().getResourceAsStream(propertiesFile);
		Properties props = new Properties();
		props.load(stream);

		PropertiesMap propMap = PropertiesMap.getInstance();
		tempMap = propMap.getProperties();

		if (tempMap == null) {
			tempMap = new HashMap<Object, Object>();
		}

		Enumeration<Object> element = props.keys();

		while (element.hasMoreElements()) {
			Object object = (Object) element.nextElement();

			tempMap.put(object.toString(), props.get(object.toString()));

			logger.info("## " + object.toString() + " :::: " + props.get(object.toString()));
		}

		propMap.setProperties(tempMap);
	}
}
