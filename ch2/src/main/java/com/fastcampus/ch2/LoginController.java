package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	
	@PostMapping("/login")
	public String login(@CookieValue("id") String cookieId, String id, String pwd,String toURL, boolean rememberId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		System.out.println("rememberId="+rememberId);
		// 1. 아이디와 패스워드 확인
		if(!loginCheck(id,pwd)) {
			// 2-1 일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.","utf-8");
	 		
			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. 아이디와 패스워드가 일치하면,
		// 세션 객체 얻어오기
		HttpSession session = request.getSession();	
		// 세션 객체애 id를 저장
		session.setAttribute("id",id);
		
		if(rememberId) {
			// 쿠키를 생성
			//1. 쿠키를 생성
			Cookie cookie = new Cookie("id",id);  //임포트추가
			response.addCookie(cookie);
		}else {
			// 쿠키를 삭제
			Cookie cookie = new Cookie("id",id);  //임포트추가
			cookie.setMaxAge(0);  //쿠키삭제
			//		2. 응답에 저장
			response.addCookie(cookie);
		}
		toURL = toURL==null || toURL.equals("") ? "/" : toURL;

		//		3. 홈으로 이동
		return "redirect:"+toURL; 
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
