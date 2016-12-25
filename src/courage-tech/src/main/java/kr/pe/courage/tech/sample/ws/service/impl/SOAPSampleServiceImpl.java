package kr.pe.courage.tech.sample.ws.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.soap.SOAPException;

import org.apache.commons.io.IOUtils;

import common.Logger;
import kr.pe.courage.tech.sample.ws.service.SOAPSampleService;
import kr.pe.courage.tech.sample.ws.service.SOAPSampleVO;

public class SOAPSampleServiceImpl implements SOAPSampleService {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public SOAPSampleVO retrieveSampleVO() throws SOAPException {
		SOAPSampleVO resultVO = new SOAPSampleVO();
		resultVO.setDeptNm("부서명");
		resultVO.setName("홍길동");
		
		return resultVO;
	}

	@Override
	public void createSampleVO(SOAPSampleVO soapSampleVO) throws SOAPException {
		logger.info("soapSampleVO.getDeptNm() - " + soapSampleVO.getDeptNm());
		logger.info("soapSampleVO.getName() - " + soapSampleVO.getName());
		
		if (soapSampleVO.getAttachFile() != null) {
			InputStream is = null;
			OutputStream os = null;
			
			try {
				is = soapSampleVO.getAttachFile().getInputStream();
				os = new FileOutputStream(new File("C:\\courage\\down\\" + soapSampleVO.getFileNm()));
				
				IOUtils.copy(is, os);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
			}
		}
	}
}
