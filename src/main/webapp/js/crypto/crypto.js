/**
 * namespace 구조 정의
 */
WCrypto = {};
WCrypto.rsa = {};

WCrypto.rsa.encrypt = function(publicKey, plainStr) {
	var rsa = new RSAKey();
	rsa.setPublic(publicKey.__RSA_MODULUS__, publicKey.__RSA_PUBLIC_EXPONENT__);
	return hex2b64(rsa.encrypt(plainStr));
}
