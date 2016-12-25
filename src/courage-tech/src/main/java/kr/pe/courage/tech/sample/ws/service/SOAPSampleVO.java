
package kr.pe.courage.tech.sample.ws.service;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "soapSampleVO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SOAPSampleVO")
public class SOAPSampleVO implements Serializable {
	private static final long serialVersionUID = 529068555418153149L;
	
	private String name;
	private String deptNm;
	private String fileNm;
	
	@XmlMimeType("application/octet-stream")
	protected DataHandler attachFile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	
	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	
	public DataHandler getAttachFile() {
		return attachFile;
	}
}
