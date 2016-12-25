
package test.egovframework.com.rte.fdl.cryptography;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cryptography.CourageCryptoService;
import egovframework.rte.fdl.cryptography.EgovCryptoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test/egovframework/spring/context-*.xml",
		"classpath*:egovframework/spring/context-*.xml",
		"classpath*:kr/pe/courage/spring/common/context-*.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TestEgovEncryption extends TestCase {
	
	@Resource(name = "ARIACryptoService")
	private EgovCryptoService cryptoService;

	@Resource(name = "courageARIACryptoService")
	private CourageCryptoService courageCryptoService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testCryptoService() {
		String testString = "문자열 ARIA 암호화 동해물과백두산이 마르고 닳도록 하느님이 보우하사";
		System.out.println("testCryptoService===");
		try {
			System.out.println("testString = " + testString);
			byte[] encrypted = cryptoService.encrypt(testString.getBytes(), "dydrl090714@");
			System.out.println("encrypted = " + Base64.encodeBase64String(encrypted));
			
			byte[] decrypted = cryptoService.decrypt(encrypted, "dydrl090714@");
			System.out.println("decrypted = " + new String(decrypted));
			
			assertEquals(testString, new String(decrypted));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCourageCryptoService() {
		String testString = "문자열 ARIA 암호화 동해물과백두산이 마르고 닳도록 하느님이 보우하사";
		System.out.println("testCryptoService===");
		try {
			System.out.println("testString = " + testString);
			byte[] encrypted = courageCryptoService.encrypt(testString.getBytes());
			System.out.println("encrypted = " + Base64.encodeBase64String(encrypted));
			
			byte[] decrypted = courageCryptoService.decrypt(encrypted);
			System.out.println("decrypted = " + new String(decrypted));
			
			assertEquals(testString, new String(decrypted));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
