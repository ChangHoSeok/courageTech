
package kr.pe.courage.common.net.mail;


public class SMTPMail {
	private String protocol = "smtp"; // 메일 프로토콜
	private String type = "text/html; charset=KSC5601"; // mime type

	private String userId; // 보내는 메일서버 사용자 아이디
	private String password; // 보내는 메일서버 패스워드
	private String host; // 서버 아이피
	private int port; // 서버 포트
	private boolean starttlsEnable = false; // 암호화 연결 TLS 사용여부
	private boolean sslEnable = false; // 암호화 연결 SSL 사용여부

	private String toUserAdres; // 받는사람 메일주소
	private String toUserName; // 받는사람 이름
	private String fromUserAdres; // 보내는 사람 메일 주소
	private String fromUserName; // 보내는 사람 이름

	private String mailSubject; // 메일 제목
	private String mailContent; // 메일 내용
	
	private String atch_file_id; // 첨부파일

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getToUserAdres() {
		return toUserAdres;
	}

	public void setToUserAdres(String toUserAdres) {
		this.toUserAdres = toUserAdres;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserAdres() {
		return fromUserAdres;
	}

	public void setFromUserAdres(String fromUserAdres) {
		this.fromUserAdres = fromUserAdres;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getAtch_file_id() {
		return atch_file_id;
	}

	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
}
