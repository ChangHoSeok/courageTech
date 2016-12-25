package kr.pe.courage.common.net.rss;

public class RssHeaderParameter {
	private String feedType = "rss_2.0";
	private String lang = "ko";
	private String encoding = "UTF-8";
	private String fileNm;
	
	private String topTitle;
	private String topLink;
	private String topDesc;
	
	public String getFeedType() {
		return feedType;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getTopTitle() {
		return topTitle;
	}
	public void setTopTitle(String topTitle) {
		this.topTitle = topTitle;
	}
	public String getTopLink() {
		return topLink;
	}
	public void setTopLink(String topLink) {
		this.topLink = topLink;
	}
	public String getTopDesc() {
		return topDesc;
	}
	public void setTopDesc(String topDesc) {
		this.topDesc = topDesc;
	}
}
