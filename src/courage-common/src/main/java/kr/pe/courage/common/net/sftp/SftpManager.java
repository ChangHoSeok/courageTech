package kr.pe.courage.common.net.sftp;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpManager {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;

	public boolean connection(String host, String userNm, String password, int port) {
		JSch jsch = new JSch();
		boolean connectionFlag = false;
		
		try {
			session = jsch.getSession(userNm, host, port);
			session.setPassword(password);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			this.channelSftp = (ChannelSftp) channel;

			connectionFlag = true;
		} catch (Exception ex) {
			connectionFlag = false;
			logger.error(ex);
		}
		
		return connectionFlag;
	}

	public void disConnection() {
		this.channelSftp.disconnect();
		channel.disconnect();
		session.disconnect();
	}

	public boolean isConnected() {
		if (this.channelSftp != null) {
			return this.channelSftp.isConnected();
		} else {
			return false;
		}
	}
	
	public void get(String remotePath, String remoteFileNm, String localPath) throws SftpException {
		this.get(remotePath, remoteFileNm, localPath, remoteFileNm);
	}
	
	public void get(String remotePath, String remoteFileNm, String localPath, String localFileNm) throws SftpException {
		if (this.channelSftp.isConnected()) {
			this.channelSftp.cd(remotePath);
			this.channelSftp.get(remoteFileNm, localPath + File.separator + localFileNm, null, ChannelSftp.OVERWRITE);
		}
	}
	
	public void put(String localPath, String localFileNm, String remotePath) throws SftpException {
		this.put(localPath, localFileNm, remotePath, localFileNm);
	}

	public void put(String localPath, String localFileNm, String remotePath, String remoteFileNm) throws SftpException {
		if (this.channelSftp.isConnected()) {
			this.channelSftp.put(localPath + File.separator + localFileNm, remotePath + "/" + remoteFileNm, null, ChannelSftp.OVERWRITE);
		}
	}
	
	public void rm(String filePath) throws SftpException {
		if (this.channelSftp.isConnected()) {
			this.channelSftp.rm(filePath);
		}
	}
	
	public Vector<LsEntry> ls() throws SftpException {
		if (this.channelSftp.isConnected()) {
			return this.ls(channelSftp.pwd());
		} else {
			logger.info("is not connected");
			return null;
		}
	}
	
	public Vector<LsEntry> ls(String path) throws SftpException {
		if (this.channelSftp.isConnected()) {
			return this.channelSftp.ls(path);
		} else {
			logger.info("is not connected");
			return null;
		}
	}
}
