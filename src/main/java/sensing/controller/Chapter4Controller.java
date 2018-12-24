package sensing.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sensing.model.Code;
import sensing.model.UserModel;
import sensing.service.CodeService;

@Controller
public class Chapter4Controller {

	@Autowired CodeService codeService;
	
	final String uri = "spring/mvc/param";
	/**
	 * Vol2 4.2.1 메소드 파라미터의 종류
	 */
	//1. HttpServletRequest, HttpServletResponse
	@RequestMapping("/param1")
	public String param1(HttpServletRequest req, HttpServletResponse res, Model model) {
		String val = req.getParameter("test");
		model.addAttribute("val",val);
		return "spring/mvc/param";
	}
	
	//2. HttpSession
	@RequestMapping("/param2")
	public String param2(HttpSession session, Model model) {
		String val = session.getId();
		model.addAttribute("val",val);
		return "spring/mvc/param";
	}
	
	//3. WebRequest, NativeWebRequest
	
	//4. Locale
	@RequestMapping("/param4")
	public String param4(Locale locale, Model model) {
		String val = locale.getCountry();
		model.addAttribute("val", val);
		
		return uri;
	}
	
	//5. InputStream, Reader
	
	//6. OutputStream, Writer
	
	//7. @PathVariable
	@RequestMapping("/param7/{id}")
	public String param7(@PathVariable int id, Model model) {
		String val = "ID: " + id;
		model.addAttribute("val",val);
		return uri;
	}
	
	//8. @RequestParam
	
	//9. @CookieValue
	@RequestMapping("/param9")
	public String param9(@CookieValue(value="auth", required=false) String auth, Model model) {
		model.addAttribute("val", auth);
		return uri;
	}
	
	//10. @ModelAttribute
	@RequestMapping("/param10")
	public String param10(@ModelAttribute UserModel userModel, Model model) {
		
		return uri;
	}
	
	
//	@ModelAttribute("codes")
	public List<Code> codes() {
		return codeService.getAllCodes();
	}
	
	@RequestMapping("/returnParam")
	public String init() {
		return "/spring/mvc/returnParam";
	}
	
	/**
	 * 4.2.2 리턴 타입의 종류
	 */
	@RequestMapping(value="/return1", method=RequestMethod.POST)
	public ModelAndView return1(@ModelAttribute("userModel") UserModel userModel, Model model) {
		userModel.setPoint(1234567);
		model.addAttribute("user",userModel);
		return new ModelAndView("/spring/mvc/resultParam");
	}
	
	@RequestMapping("/return2")
	public ModelAndView return2(@ModelAttribute("userModel") UserModel userModel, Model model) {
		userModel.setPoint(1234567);
		userModel.setName("test");
		model.addAttribute("user",userModel);
		return new ModelAndView("/spring/mvc/resultParam2");
	}
}