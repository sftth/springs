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
import sensing.framework.session.SessionModel;
import sensing.login.service.LoginService;
import sensing.util.WebSecurityUtil;

@Controller
public class LoginController {
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login")
	public String init() {
		LOGGER.info("Called init.");
		return "/login/login";
	}

	@RequestMapping(value="/signin", method= RequestMethod.GET)
	public String signinView() {
		LOGGER.info("Called signin view");
		return "/login/signin";
	}

	@RequestMapping(value="/signup", method= RequestMethod.GET)
	public String signupView() {
		LOGGER.info("Called signup view");
		return "/login/signup";
	}

	@ResponseBody
	@RequestMapping(value="/encrypt", method= RequestMethod.GET)
	public Map<String, String> encrypt(HttpSession session, ModelMap modelMap) {
		Map<String, String> publicKeyInfo = new HashMap<String, String>();
		try {
			LOGGER.info("Called Encrypt.");

			PublicKey publicKey = (PublicKey) SessionManager.getAttribute(session, RsaCrypto.RSA_PUBLIC_KEY);
			PrivateKey privateKey = (PrivateKey) SessionManager.getAttribute(session, RsaCrypto.RSA_PRIVATE_KEY);

			if (publicKey == null || privateKey == null) {
				Map<String, Key> asymmetricKey = WebSecurityUtil.generateAsymmetricKey();
				publicKey = (PublicKey) asymmetricKey.get(RsaCrypto.RSA_PUBLIC_KEY);
				privateKey = (PrivateKey) asymmetricKey.get(RsaCrypto.RSA_PRIVATE_KEY);
				SessionManager.setAttribute(session, RsaCrypto.RSA_PUBLIC_KEY, publicKey);
				SessionManager.setAttribute(session, RsaCrypto.RSA_PRIVATE_KEY, privateKey);
			}

			publicKeyInfo.put(RsaCrypto.RSA_MODULUS, RsaCrypto.extractModulus(publicKey));
			publicKeyInfo.put(RsaCrypto.RSA_PUBLIC_EXPONENT, RsaCrypto.extractPublicExponent(publicKey));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return publicKeyInfo;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/signInProcess", method=RequestMethod.POST)
	public ResponseModel sigin(@RequestBody LoginModel loginModel, HttpServletRequest request, ModelMap modelMap) {

		LOGGER.info("Called Sigin.");
		ResponseModel responseModel = new ResponseModel();
		try{
			String decryptedPassword = decrytPassword(request, loginModel);

			LoginModel selectLoginModel = loginService.getloginModel(loginModel.getEmail());

			if(null != selectLoginModel) {
				if(decryptedPassword.equals(selectLoginModel.getPassword())) {
					responseModel.setSuccess("Y");
					loginModel.setPassword(decryptedPassword);
					loginModel.setName(selectLoginModel.getName());

					if(null == SessionManager.getUserInfo(request)) {
						SessionModel sessionModel = new SessionModel();
						sessionModel.setUserId(loginModel.getEmail());
						sessionModel.setName(loginModel.getName());
						sessionModel.setUserIp(getIp(request));

						SessionManager.setNewSessionData(request, sessionModel);
						//For Log
						SessionModel modelForLogging= SessionManager.getUserInfo(request);
						LOGGER.info("=====session 설정 결과 User ID: " + modelForLogging.getUserId());
						LOGGER.info("=====session 설정 결과 Name: " + modelForLogging.getName());
					}
				} else {
					responseModel.setSuccess("N");
					responseModel.setTrMsg("Error Password is empty or incorrect.");
				}
			} else {
				responseModel.setSuccess("N");
				responseModel.setTrMsg("Error User is not correct.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return responseModel;
		}

	}

	@ResponseBody
	@RequestMapping(value="/signUpProcess", method=RequestMethod.POST)
	public ResponseModel sigup(@RequestBody LoginModel loginModel, HttpServletRequest request, ModelMap modelMap) {
		LOGGER.info("Called Sigup.");
		ResponseModel responseModel = new ResponseModel();

		String decryptedPassword = decrytPassword(request,loginModel);

		if(decryptedPassword != null) {
			responseModel.setSuccess("Y");
			loginModel.setPassword(decryptedPassword);
			loginService.insertUser(loginModel);
		} else {
			responseModel.setSuccess("N");
			responseModel.setTrMsg("Error Password is null.");
		}

		return responseModel;
	}
	
	@RequestMapping(value="/body", method= RequestMethod.GET)
	public String body(HttpServletRequest request, ModelMap model) {
		String returnUrl = "";
		try {
			String userIp = (String) getIp(request);
			SessionModel sessionModel = SessionManager.getUserInfo(request);


			if(null != sessionModel) {
				model.addAttribute("userName", sessionModel.getName());
				LOGGER.info("Called Body.");
				returnUrl = "/login/body";
			} else {
				returnUrl = "/login/signin";
			}
		} catch(Exception e){
			e.printStackTrace();
		}

		return returnUrl;
	}

	private String decrytPassword(HttpServletRequest request, LoginModel loginModel){
		String encryptedPassword = loginModel.getPassword();

		PrivateKey privateKey = (PrivateKey) SessionManager.getAttribute(request, RsaCrypto.RSA_PRIVATE_KEY);
		String decryptedPassword = null;
		try {
			decryptedPassword = WebSecurityUtil.decrypt(privateKey, encryptedPassword);
		} catch (Exception e) {
			LOGGER.error("Failed to decrypt encrypted password", e);
			decryptedPassword = null;
		}

		return decryptedPassword;
	}

	private String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		LOGGER.info(">>>> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			LOGGER.info(">>>> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
			LOGGER.info(">>>> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			LOGGER.info(">>>> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			LOGGER.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		LOGGER.info(">>>> Result : IP Address : "+ip);

		return ip;

	}
}
