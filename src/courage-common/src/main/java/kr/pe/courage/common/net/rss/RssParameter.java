package kr.pe.courage.common.net.rss;

public class RssParameter {
	private String entryTitle;
	private String entryLink;
	private String entryPubDate;
	private String entryAuthor;
	
	private String descType = "text/plain";
	private String descValue;
	
	public String getEntryTitle() {
		return entryTitle;
	}
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	public String getEntryLink() {
		return entryLink;
	}
	public void setEntryLink(String entryLink) {
		this.entryLink = entryLink;
	}
	public String getEntryPubDate() {
		return entryPubDate;
	}
	public void setEntryPubDate(String entryPubDate) {
		this.entryPubDate = entryPubDate;
	}
	public String getEntryAuthor() {
		return entryAuthor;
	}
	public void setEntryAuthor(String entryAuthor) {
		this.entryAuthor = entryAuthor;
	}
	public String getDescType() {
		return descType;
	}
	public void setDescType(String descType) {
		this.descType = descType;
	}
	public String getDescValue() {
		return descValue;
	}
	public void setDescValue(String descValue) {
		this.descValue = descValue;
	}
}
