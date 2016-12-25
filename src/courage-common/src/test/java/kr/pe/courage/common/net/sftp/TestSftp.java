package kr.pe.courage.common.net.sftp;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp.LsEntry;

public class TestSftp {
	public static void main(String[] args) {
		String host = "chseok82.iptime.org";
		String id = "root";
		String pw = "";
		int port = 2222;
		
		SftpManager sftp = null;
		
		try {
			sftp = new SftpManager();
			
			if (sftp.connection(host, id, pw, port)) {
				System.out.println(sftp.isConnected());
				Vector<LsEntry> ls = sftp.ls();
				
				for (LsEntry lsEntry : ls) {
//					System.out.println(lsEntry.getFilename());
//					System.out.println(lsEntry.getLongname());
					System.out.println(lsEntry.toString());
//					System.out.println(lsEntry.getAttrs().toString());
				}
				
//				sftp.get("/root", "install.log.syslog", "D:\\backbone");
//				sftp.put("D:\\backbone", "example source.zip", "/storage/storage2/", "source.zip");
//				sftp.rm("/storage/storage2/source.zip");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sftp != null) {
				sftp.disConnection();
			}
		}
	}
}