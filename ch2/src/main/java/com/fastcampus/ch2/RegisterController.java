package com.fastcampus.ch2;



import java.net.URLEncoder;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
		System.out.println("conversionService="+conversionService);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class,"hobby", new StringArrayPropertyEditor("#"));
	}
	
	
	
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	// @GetMapping("/add")
	public String register() {
		return "registerForm";  // WEB-INF/views/registerForm.jsp
	}
	
	
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/save")  //스프링 4.3부터
	public String save(User user, BindingResult result, Model m) throws Exception {
		System.out.println("result="+result);
		System.out.println("user="+user);
		
		// 1. 유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("아이디를 잘못입력하셨습니다.","utf-8") ;
			
			m.addAttribute("msg", msg);  //모델로 전달하는방법1
			return "forward:/register/add";  //redirect는 재요청   31번코드와32번코드 합친게 33번코드이다
//			return "redirect:/register/add?msg="+msg;  // URL 재작성(rewriting)2
		}
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

private boolean isValid(User user) {
	return true;
}
	
	
	
}
