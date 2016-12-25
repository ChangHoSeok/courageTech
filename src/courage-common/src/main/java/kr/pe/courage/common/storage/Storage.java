
package kr.pe.courage.common.storage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.pe.courage.common.utils.Util;

import org.apache.commons.io.FilenameUtils;

/**
 * <pre>
 * kr.pe.courage.cmm.storage
 * Storage.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2013. 2. 4.
 * @Version : 1.0
 */
public class Storage {
	static private Storage m_Storage;
	static private String m_fileSyncId;
	
	public final static String STORAGE_PROTOCOL_LOCAL = "0";
	public final static String STORAGE_PROTOCOL_FTP = "1";
	public final static String STORAGE_PROTOCOL_SFTP = "2";

	public final static String FILE_LOCATION_STORAGE = "1";
	public final static String FILE_LOCATION_CONTEXT = "2";
	
	public final static String FILE_FLAG_DELETE = "1";
	
	public final static String FILE_SEPARAT0R = "/";
	
	private String[] storageList;
	private String saveStorage;
	private String protocol;
	private String host;
	private String port;
	private String user;
	private String password;
	private String tempPath;
	private String contextRealPath;
	private String contextPath;
	private String storageContextPath;
	private String excelExportPath;
	private String jxlsPath;

	private Storage() {
		super();
	}

	static public Storage getInstance() {
		if (m_Storage == null) {
			m_Storage = new Storage();
		}

		return m_Storage;
	}
	
	public synchronized String getFileSyncId() {
		return getFileSyncId("");
	}
	
	public synchronized String getFileSyncId(String fileName) {
		String dateFormat = "yyyyMMddHHmmssSSS";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String tempFileSyncId = "";
		
		while(true) {
			tempFileSyncId = sdf.format(new Date());
			if (!tempFileSyncId.equals(m_fileSyncId)) {
				break;
			}
		}
		
		m_fileSyncId = tempFileSyncId;
		
		if (!Util.nvl(fileName).equals("")) {
			m_fileSyncId += "." + FilenameUtils.getExtension(fileName);
		}
		
		return m_fileSyncId;
	}

	public String getContextRealPath() {
		return contextRealPath;
	}
	
	public String getContextRealPath(String path) {
		path = FilenameUtils.separatorsToUnix(path);
		
		if (!path.startsWith("/")) {
			path = File.separator + path;
		}
		
		return contextRealPath + path;
	}

	public void setContextRealPath(String contextRealPath) {
		this.contextRealPath = contextRealPath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTempPath() {
		return tempPath;
	}

	public String getTempPath(String path) {
		path = FilenameUtils.separatorsToUnix(path);
		
		if (!path.startsWith("/")) {
			path = File.separator + path;
		}
		
		return tempPath + path;
	}

	public void setTempPath(String tempPath) {
		tempPath = FilenameUtils.separatorsToUnix(tempPath);
		
		if (!tempPath.startsWith("/")) {
			tempPath = File.separator + tempPath;
		}
		
		this.tempPath = tempPath;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getStorageList() {
		return storageList;
	}

	public void setStorageList(String[] storageList) {
		this.storageList = storageList;
	}

	public String getSaveStorage() {
		return saveStorage;
	}
	
	public String getSaveStorage(String path) {
		path = FilenameUtils.separatorsToUnix(path);
		
		if (!path.startsWith("/")) {
			path = File.separator + path;
		}
		
		return saveStorage + path;
	}

	public void setSaveStorage(String saveStorage) {
		this.saveStorage = saveStorage;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getStorageContextPath() {
		return storageContextPath;
	}
	
	public String getStorageContextPath(String path) {
		path = FilenameUtils.separatorsToUnix(path);
		
		if (!path.startsWith("/")) {
			path = File.separator + path;
		}
		
		return storageContextPath + path;
	}

	public void setStorageContextPath(String storageContextPath) {
		this.storageContextPath = storageContextPath;
	}

	public String getExcelExportPath() {
		return excelExportPath;
	}

	public void setExcelExportPath(String excelExportPath) {
		this.excelExportPath = excelExportPath;
	}

	public String getJxlsPath() {
		return jxlsPath;
	}

	public void setJxlsPath(String jxlsPath) {
		this.jxlsPath = jxlsPath;
	}
}
