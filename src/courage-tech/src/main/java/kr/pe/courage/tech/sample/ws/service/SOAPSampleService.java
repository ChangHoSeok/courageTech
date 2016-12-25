package kr.pe.courage.tech.sample.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;

@WebService(endpointInterface = "kr.pe.courage.tech.sample.ws.service.SOAPSampleService", serviceName = "SOAPSampleServiceImpl", portName = "SOAPSamplePort", targetNamespace = "courage.pe.kr")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@MTOM
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SOAPSampleService {
	@WebMethod(operationName = "retrieveSampleVO")
	public SOAPSampleVO retrieveSampleVO() throws SOAPException;
	
	@WebMethod(operationName = "createSampleVO")
	public void createSampleVO(@WebParam(name = "soapSampleVO") SOAPSampleVO soapSampleVO) throws SOAPException;
}
