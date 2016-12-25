package kr.pe.courage.common.net.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;

public class SMTPMailSendManager {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private String protocol = "smtp";
	private String type = "text/html; charset=KSC5601";

	private String userName;
	private String password;
	private String host;
	private int port;
	private boolean starttlsEnable = false;
	private boolean sslEnable = false;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public boolean isStarttlsEnable() {
		return starttlsEnable;
	}

	public void setStarttlsEnable(boolean starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}

	public boolean isSslEnable() {
		return sslEnable;
	}

	public void setSslEnable(boolean sslEnable) {
		this.sslEnable = sslEnable;
	}

	public boolean send(String toAddress, String toName, String fromAddress,
			String fromName, String subject, String content) {
		boolean sendState = false;

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", protocol);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", String.valueOf(port));

			Authenticator authenticator = null;
			if (Util.hasText(this.userName)) {
				props.put("mail.smtp.auth", "true");
				authenticator = new SMTPAuthenticator(this.userName, this.password);
			}

			if (this.starttlsEnable) {
				props.put("mail.smtp.starttls.enable", Boolean.toString(this.starttlsEnable));
			}
			
			if (this.sslEnable) {
				props.put("mail.transport.protocol", "smtps");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.enable", Boolean.toString(this.sslEnable));
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");
				props.put("mail.smtp.socketFactory.port", String.valueOf(port));
			}

			Session session = Session.getInstance(props, authenticator);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress, fromName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress, toName));
			message.setSubject(subject);
			message.setContent(content, type);
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
	
	public static void main(String[] args) {
		SMTPMailSendManager mailManager = new SMTPMailSendManager();
		mailManager.setHost("smtp.cafe24.com");
		mailManager.setPort(587);
		mailManager.setUserName("schkkh@naver.com");
		mailManager.setPassword("ckdgh");
		mailManager.setSslEnable(false);
		
		mailManager.send("schkkh@naver.com", "너님", "schkkh@naver.com", "창호님", "테스트", "메일입니다.");
		
		System.out.println("끝!");
	}
}
