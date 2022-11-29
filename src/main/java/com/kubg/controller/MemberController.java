package com.kubg.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kubg.domain.MemberVO;
import com.kubg.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	// 회원가입 get
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getSignup() throws Exception {
		logger.info("get signup");
	}
	
	// 회원가입 post
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(MemberVO vo) throws Exception {
		logger.info("post signup");
		
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);
		
		service.signup(vo);
		
		return "redirect:/";
	}
	
	// 로그인 get
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void getSignin() throws Exception {
		logger.info("get signin");
	}
	
	// 로그인 post
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("get signin");
		
		MemberVO login = service.signin(vo);
		HttpSession session = req.getSession();
		
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
		
		if(login != null && passMatch) {  // 아이디가 존재하고(!=null), 비밀번호가 맞으면(passMatch = true) 
			session.setAttribute("member", login);  // member 세션에 로그인 정보를 부여
			
		} else {  // 아이디가 존재하지 않고, 비밀번호가 틀리면
			session.setAttribute("member", null);  // member 세션에 null 부여  
			rttr.addFlashAttribute("msg", false);  // 1회성인 변수인 msg에 false 부여
			return "redirect:/member/signin";  // 로그인 화면으로 이동
		}		
		
		return "redirect:/";
		
	}
	
	// 로그아웃
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception {
		logger.info("get logout");
		
		service.signout(session);
		
		return "redirect:/";
	}

}
