package sensing.util;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import sensing.framework.crypto.RsaCrypto;

/**
 * @author summit.park
 * 웹구간 암호화와 관련한 정적 매소드를 제공
 */
public class WebSecurityUtil {
	public static Map<String, Key> generateAsymmetricKey() {
		Map<String, Key> asymmetricKey = new HashMap<String, Key>();
		RsaCrypto rsaCrypto = RsaCrypto.getInstance();
		PublicKey publicKey = rsaCrypto.getPublicKey();
		PrivateKey privateKey = rsaCrypto.getPrivateKey();
		asymmetricKey.put(RsaCrypto.RSA_PUBLIC_KEY, publicKey);
		asymmetricKey.put(RsaCrypto.RSA_PRIVATE_KEY, privateKey);
		
		return asymmetricKey;
	}
	
	public static String decrypt(PrivateKey privateKey, String encryptedStr) {
		return RsaCrypto.decrypt(privateKey, encryptedStr);
	}
}
