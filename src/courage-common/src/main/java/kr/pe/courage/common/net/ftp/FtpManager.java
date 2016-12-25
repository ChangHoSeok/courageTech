package kr.pe.courage.common.net.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import kr.pe.courage.common.storage.Storage;

import org.apache.log4j.Logger;

import com.oroinc.net.ftp.FTP;
import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPReply;


public class FtpManager {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private String host = "";
	private int port = 21;
	private String id = "";
	private String password = "";
	FTPClient ftpClient;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FtpManager(){}
	
	public FtpManager(String host, int port, String id, String password) {
		this.host = host;
		this.port = port;
		this.id = id;
		this.password = password;
		ftpClient = new FTPClient();
	}
	
	public FtpManager(String host, int port, String id, String password, int defaultTimeOut) {
		this.host = host;
		this.port = port;
		this.id = id;
		this.password = password;
		ftpClient = new FTPClient();
		ftpClient.setDefaultTimeout(defaultTimeOut);
	}
	
	public boolean connection() {
		int reply;
		boolean resultFlag = false;
		
		try {
			ftpClient.connect(host, port);
			reply = ftpClient.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.error("## 서버로 부터 연결을 거부당했습니다.");
			} else {
				resultFlag = true;
			}
		} catch (Exception ex) {
			logger.error("## 서버에 연결할 수 없습니다.");
			logger.error("## host :::: " + host);
			logger.error("## port :::: " + port);
			logger.error(ex);
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception ee) {
					logger.error(ee);
				}
			}
		}
		
		return resultFlag;
	}
	
	public void setDataTimeOut(int ms) {
		try {
			ftpClient.setDataTimeout(ms);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	public void disconnect() {
		try {
			ftpClient.disconnect();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	public boolean login() {
		boolean resultFlag = false;
		
		try {
			resultFlag = ftpClient.login(this.id, this.password);
		} catch (IOException e) {
			logger.error(e);
		}
		
		return resultFlag;
	}
	
	public boolean logout() {
		boolean resultFlag = false;
		
		try {
			resultFlag = ftpClient.logout();
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return resultFlag;
	}
	
	public boolean delete(String source) {
		boolean resultFlag = false;
		
		try {
			ftpClient.enterLocalPassiveMode();
			resultFlag = ftpClient.deleteFile(source);
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return resultFlag;
	}
	
	public boolean get(String source, String target) {
		boolean resultFlag = false;
		OutputStream os = null;
		
		logger.info("## 파일전송시작");
		logger.info("## 대상파일 :::: ftp://" + source);
		logger.info("## 저장파일명 :::: " + target);
		
		try {
			File local = new File(target);
			os = new FileOutputStream(local);
			
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			
			if(ftpClient.retrieveFile(source, os)) {
				resultFlag = true;
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return resultFlag;
	}
	
	public boolean put(String ftpPath, File localFile, String saveFileName) {
		boolean resultFlag = false;
		BufferedInputStream bis = null;
		
		logger.info("## 파일전송시작");
		logger.info("## 대상파일 :::: " + localFile.getPath());
		logger.info("## 업로드경로 :::: ftp://" + ftpPath);
		logger.info("## 저장파일명 :::: " + saveFileName);
		
		ftpPath = ftpPath.replace("\\", Storage.FILE_SEPARAT0R);
		
		// 앞에 separator가 없는 경우 makeDir에 버그 존재
		ftpPath = ftpPath.startsWith(Storage.FILE_SEPARAT0R)?ftpPath:Storage.FILE_SEPARAT0R+ftpPath;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(localFile));
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			
			if(!ftpClient.changeWorkingDirectory(ftpPath)) {
				if(makeDir(ftpPath)) {
					ftpClient.changeWorkingDirectory(ftpPath);
				} else {
					logger.error("파일서버에 경로를 생성할 수 없습니다.");
				}
			}
			
			if(bis != null) {
				if(ftpClient.storeFile(saveFileName, bis)) {
					logger.info("## 파일전송 완료");
					resultFlag = true;
				} else {
					logger.info("## 파일전송 실패");
				}
			} else {
				logger.error("파일을 읽을수 없습니다 :::: " + localFile.getPath());
			}
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		logger.info("## 파일전송종료");
		
		return resultFlag;
	}
	
	private boolean makeDir(String path) {
		boolean resultFlag = false;
		String[] pathDeth = path.split("/");
		String tmpPath = "";
		
		try {
			if(pathDeth.length <= 0) {
				tmpPath = path;
				ftpClient.makeDirectory(tmpPath);
			} else {
				for (int i = 0; i < pathDeth.length; i++) {
					tmpPath += "/" + pathDeth[i];
					if(!ftpClient.changeWorkingDirectory(tmpPath)) {
						ftpClient.makeDirectory(tmpPath);
					}
				}
			}
			
			resultFlag = true;
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return resultFlag;
	}
}
