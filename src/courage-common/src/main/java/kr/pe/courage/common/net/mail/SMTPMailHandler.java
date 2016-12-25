package kr.pe.courage.common.net.mail;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.StorageUtils;
import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("courageSmtpMailHandler")
public class SMTPMailHandler {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource(name = "courageStorageService")
	private IStorageService storageService;

	public boolean send(SMTPMail smtpMail) {
		boolean sendState = false;
		
		if (Util.nvl(smtpMail.getToUserName()).equals("")) {
			smtpMail.setToUserName(smtpMail.getToUserAdres());
		}
		
		if (Util.nvl(smtpMail.getFromUserName()).equals("")) {
			smtpMail.setFromUserName(smtpMail.getFromUserAdres());
		}

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", smtpMail.getProtocol());
			props.put("mail.smtp.host", smtpMail.getHost());
			props.put("mail.smtp.port", String.valueOf(smtpMail.getPort()));

			Authenticator authenticator = null;
			if (Util.hasText(smtpMail.getUserId())) {
				props.put("mail.smtp.auth", "true");
				authenticator = new SMTPAuthenticator(smtpMail.getUserId(), smtpMail.getPassword());
			}

			if (smtpMail.isSslEnable()) {
				props.put("mail.transport.protocol", "smtps");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.enable", Boolean.toString(smtpMail.isSslEnable()));
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");
				props.put("mail.smtp.socketFactory.port", String.valueOf(smtpMail.getPort()));
			} else if (smtpMail.isStarttlsEnable()) {
				props.put("mail.smtp.starttls.enable", Boolean.toString(smtpMail.isStarttlsEnable()));
			}
			
			Session session = Session.getInstance(props, authenticator);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(smtpMail.getFromUserAdres(), smtpMail.getFromUserName()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(smtpMail.getToUserAdres(), smtpMail.getToUserName()));
			message.setSubject(smtpMail.getMailSubject());
			
			if (smtpMail.getAtch_file_id() != null) {
				List<StorageFile> storageFileList = storageService.selectFileList(new StorageFile(smtpMail.getAtch_file_id()));
				// 파일 첨부
				if (storageFileList != null && storageFileList.size() > 0) {
					Multipart multiiPart = new MimeMultipart();
					MimeBodyPart content = new MimeBodyPart();
					content.setContent(smtpMail.getMailContent(), smtpMail.getType());
					multiiPart.addBodyPart(content);
					
					for (StorageFile storageFile : storageFileList) {
						MimeBodyPart atchFile = new MimeBodyPart();
						storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
						DataSource fileDataSrc = new FileDataSource(StorageUtils.get(storageFile));
						atchFile.setDataHandler(new DataHandler(fileDataSrc));
						atchFile.setFileName(MimeUtility.encodeText(storageFile.getFileNm(), "euc-kr", "B"));
						
						multiiPart.addBodyPart(atchFile);
					}
					
					message.setContent(multiiPart);
				}
			} else {
				message.setContent(smtpMail.getMailContent(), smtpMail.getType());
			}
			
			Transport.send(message);

			sendState = true;
		} catch (Exception ex) {
			sendState = false;
			logger.error(ex);
			ex.printStackTrace();
		}

		return sendState;
	}

	class SMTPAuthenticator extends Authenticator {
		PasswordAuthentication passwordAuthentication;

		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName,
					password);
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}
}
