package kr.pe.courage.tech.sample.encpt.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.pe.courage.tech.sample.encpt
 * RSAEncptController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 7. 20.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("rasEncptController")
@RequestMapping("/sample/encpt/*")
public class RSAEncptController {
	
	private static final String PRIVATE_KEY = "MIIEowIBAAKCAQEAnVZ6nTJwVgLEc2wAbch/iEvpeLNwBUpJsNXmO21IJfkhxv+s/hX16wc++76W6U7czPeJDQFVT31cLpZDNwyYZ8U4fk8fIc5OlTkTbYdcwsfEX+JlKWca68I7CVwgqkppMOdvmdVx5EnUlwoDru4IuIbXpyudJBrWODpKL/vTgOqigr/V5zlYlFxMiWSA/atBQUS789HMYt4mM4PstWD/1QataxQo9oJLsHb90WV32w99gV4gFhMKpn9zMsFItHE03dkJuI67HUPPV0q3DAbBbFQr136c3rdbjeZJZg+e9qCp7FptgsHIqCgcn6yJpvQnAPQIUSoPciKXAW/SRpgaVwIDAQABAoIBAHJzauQYwcC/aJoxkSpK9AJ4fhAwLyeZ/3rkTgMinp4I2PpCGFwIK4hZEAbegDadCF8NHaPZOW6GWV0GL0THI8Iwo8S114am5WEFUM2W7vI9kOrS7BWvC9U9x+YqHxTg5h4N7I+xxZRz5HIudbl1K+DXLd5MCpYt83CZbkfzXspiO3GM5NVKOZIAOnx+U4ZraO6oqNXTZa+RtwuXd0004A+D8+eIZ4Q4P64W7kkNhfLQexbUQ1bV4hWsLTzzAhva3PqO26ZDsrGxQwETm1T3pr9FJn1X/BnjS+uriaY9f2rHbR+XVC/aKbilDXV3W1/ghNaf+zAM11yY+UIoySlfXYECgYEA7y+ZzAkYttqUgUeDlyORLZT46PLZk1O6Q3oGpsO9yJw/k/K7jyethyBBwBWicQJMXPKV/q6D3ihKLY1L8hOCui9Z/iPAPzECMaSIDPrL+kzwVViixxVnfkWGHYyAzp+1XrC2QHHh0ra+pTtCZTwYgXnBXHbZe50Pc0q3eglmtkECgYEAqGXvpQUJnfXOcza0bHMeMvUx6sd3odONrjBkhLsP6YA60aUWKfiAwCs9UiOjdP4c8IWtiEqlfdmAKVBgRvs/kjtYPFmmU/wWSaxjbppneSFgngG59ZOzs/CeOA0tloUpCaZsztrqgm/pjI7DRIG8AjIr8Zz6u1YgzoTyNCyAGpcCgYEAgLRLfNg0PUiW+o3ByFUyLKXp3me3HijCpMaHx7qyZ710FKdbt6dxH7ICz1qU41yKPMWoYrgDa0qgx6wtfqBzCr6qHS+dpiM3/AB92QYnNpOqCzK7vIbO/spoZYQSV5zm2oIaP8lH67u8EGs3ygOxaBJdMuUdGAAmrTI6Ume2pkECgYAktpSYlZCTL5Pi6npLao2a7noDhZk/Xq/02fgQN/gwIBSywBsk61imjMbBsaNhTkVg6sP6gsuxtEmMuElFMGGbr/R8s87fYIY7aaoD4wXMEsrNp/Ky55C2Gi9kaPsXZdCcmfv+3RtjdKKmUjmgJeinUejitCa10QE2BSYI2erT4QKBgADnqgTBfdtpJX6mZ+X6r4ljgP4iXk3r4ph9WJHSkEt4G0RvUsGNsm/s/ku1VKXrvsG+WvmMH/pFecuOTbpLpASYfpmf9pkN2mySCLFnNEgG/p4INcx6oyJxVnU7PxY9czUmMnaAOkaOO/+aUQWo/DPJ+5cBnh6i5NIVShepn34b";
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnVZ6nTJwVgLEc2wAbch/iEvpeLNwBUpJsNXmO21IJfkhxv+s/hX16wc++76W6U7czPeJDQFVT31cLpZDNwyYZ8U4fk8fIc5OlTkTbYdcwsfEX+JlKWca68I7CVwgqkppMOdvmdVx5EnUlwoDru4IuIbXpyudJBrWODpKL/vTgOqigr/V5zlYlFxMiWSA/atBQUS789HMYt4mM4PstWD/1QataxQo9oJLsHb90WV32w99gV4gFhMKpn9zMsFItHE03dkJuI67HUPPV0q3DAbBbFQr136c3rdbjeZJZg+e9qCp7FptgsHIqCgcn6yJpvQnAPQIUSoPciKXAW/SRpgaVwIDAQAB";
	
	@Value("#{sampleView['formRSAEncptView']}")
	private String formRSAEncptView;
	
	@Value("#{sampleView['rsaEncptCreateView']}")
	private String rsaEncptCreateView;

	@Value("#{sampleView['rsaEncptDetailView']}")
	private String rsaEncptDetailView;
	
	/**
	 * <pre>
	 * 1. 개요 : RSA 암호화 메인 form
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : formRSAEncpt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formRSAEncpt.*")
	public ModelAndView formRSAEncpt() throws Exception {
		return new ModelAndView(formRSAEncptView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : RSA 암호화 샘플 데이터 입력 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : createRSAEncpt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createRSAEncpt.*")
	public ModelAndView createRSAEncpt(ModelMap model) throws Exception {
		
		model.addAttribute("publicKey", PUBLIC_KEY);
		
		return new ModelAndView(rsaEncptCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : RSA 암호화 샘플 데이터 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 21.
	 * @Method Name : retrieveRSAEncptDetail
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveRSAEncptDetail.*")
	public ModelAndView retrieveRSAEncptDetail(@RequestParam("data1") String data1, @RequestParam("data2") String data2, ModelMap model) throws Exception {
		
		// 개인키로 데이터 복호화 처리
		
		return new ModelAndView(rsaEncptDetailView);
	}
}
