package sensing.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sensing.model.UserModel;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/param/{id}")
	public ModelAndView main(@PathVariable("id")int id) {
		return new ModelAndView("param").addObject("id",id);
	}
	
	@RequestMapping("/modelAttribute")
	public String modelAttributeTest(@ModelAttribute UserModel userModel, BindingResult bindingResult, Model model) {
		model.addAttribute("id", userModel.getId());
		model.addAttribute("name",userModel.getName());
		model.addAttribute("password", userModel.getPassword());
		List<ObjectError> errors = bindingResult.getAllErrors();
		for(ObjectError e : errors) {
			System.out.println(e.getDefaultMessage());
		}
		return "modelAttribute";
	}
	
	@RequestMapping("/requestParam")
	public String requestParamTest(@RequestParam("id") int id, @RequestParam("name") String name,@RequestParam("password") String password, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name",name);
		model.addAttribute("password", password);
		
		return "modelAttribute";
	}
	
	@RequestMapping("/ajax")
	public String ajaxTest() {
		return "ajax";
	}
	
//	@RequestMapping(value="/checkLoginId/{loginId}", method= RequestMethod.GET)
//	@ResponseBody
//	public Result checkLogin(@PathVariable String loginId) {
//		Result result = new Result();
//		result.setDuplicated(true);
//		result.setAvailableId(loginId+(int)Math.random()*1000);
//		return result;
//	}
	
	@RequestMapping("/ajax2")
	public String ajaxTest2() {
		return "ajaxTest";
	}
	
	
	@RequestMapping(value="/ajaxTest", method= RequestMethod.GET)
	@ResponseBody
	public String getTime() {
	      Random rand = new Random();
	      float r = rand.nextFloat() * 100;
	      String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
	      System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
	      return result;
	}
}
