package sensing.login.controller;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sensing.cmmn.model.LoginModel;
import sensing.cmmn.model.ResponseModel;
import sensing.framework.crypto.RsaCrypto;
import sensing.framework.session.SessionManager;
import sensing.login.service.LoginService;
import sensing.util.WebSecurityUtil;

@Controller
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginServie;
	
	@RequestMapping("/login")
	public String init() {
		LOGGER.info("Called init.");
		return "/login/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/encrypt", method= RequestMethod.GET)
	public Map<String, String> encrypt(HttpSession session, ModelMap modelMap) {
		LOGGER.info("Called Encrypt.");		
		Map<String, String> publicKeyInfo = new HashMap<String, String>();
		PublicKey publicKey = (PublicKey) SessionManager.getAttribute(session, RsaCrypto.RSA_PUBLIC_KEY);
		PrivateKey privateKey = (PrivateKey) SessionManager.getAttribute(session, RsaCrypto.RSA_PRIVATE_KEY);
		
		if(publicKey == null || privateKey == null) {
			Map<String, Key> asymmetricKey = WebSecurityUtil.generateAsymmetricKey();
			publicKey = (PublicKey) asymmetricKey.get(RsaCrypto.RSA_PUBLIC_KEY);
			privateKey = (PrivateKey) asymmetricKey.get(RsaCrypto.RSA_PRIVATE_KEY);
			SessionManager.setAttribute(session, RsaCrypto.RSA_PUBLIC_KEY, publicKey);
			SessionManager.setAttribute(session, RsaCrypto.RSA_PRIVATE_KEY, privateKey);
		}
		
		publicKeyInfo.put(RsaCrypto.RSA_MODULUS,RsaCrypto.extractModulus(publicKey));
		publicKeyInfo.put(RsaCrypto.RSA_PUBLIC_EXPONENT, RsaCrypto.extractPublicExponent(publicKey));
		return publicKeyInfo; 
	}
	
	@ResponseBody
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public ResponseModel sigin(@RequestBody LoginModel loginModel, HttpServletRequest request, ModelMap modelMap) {
		LOGGER.info("Called Sigin.");
		ResponseModel responseModel = new ResponseModel();
		
		String encryptedPassword = loginModel.getPassword();
		
		PrivateKey privateKey = (PrivateKey) SessionManager.getAttribute(request, RsaCrypto.RSA_PRIVATE_KEY);
		String password = null;
		try {
			password = WebSecurityUtil.decrypt(privateKey, encryptedPassword);
		} catch (Exception e) {
			LOGGER.error("Failed to decrypt encrypted password", e);
			password = null;
		}
		
		if(password != null) {
			responseModel.setSuccess("Y");
			loginModel.setPassword(password);
			loginServie.insertUser(loginModel);
		} else {
			responseModel.setSuccess("N");
			responseModel.setTrMsg("Error Password is null.");
		}
		
		return responseModel;
	}
	
	@RequestMapping(value="/body", method= RequestMethod.GET)
	public String body() {
		LOGGER.info("Called Body.");
		return "/login/body";
	}
}
