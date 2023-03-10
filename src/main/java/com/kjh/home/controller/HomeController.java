package com.kjh.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjh.home.dao.Mapper;
import com.kjh.home.dto.MemberDto;

@Controller
public class HomeController {
	
	@Autowired//의존주입(DI)
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		
		return "join";
	}

@RequestMapping(value = "/index")
public String index2() {
	
	return "index";
}
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request,Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail  = request.getParameter("memail");
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		dao.joinDao(mid, mpw, mname, memail);
		model.addAttribute("mname",mname);
		
		return "joinOk";
	}
	@RequestMapping(value = "/login")
		public String login() {
		
		return "login";
	}
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request,Model model) {
		
		String mid= request.getParameter("mid");
		String mpw= request.getParameter("mpw");
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		int loginCheck = dao.logincheck(mid, mpw);
		//1이면 아이디와 비밀번호가 모두 일치하는 데이터가 존재->로그인 성공
		model.addAttribute("loginCheck", loginCheck);
		System.out.println(loginCheck);
		if(loginCheck == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionid", mid);
			session.setAttribute("ValidMem", "Yes");
			model.addAttribute("memberid", mid);
			
		}
		return "loginOk";
	}
	@RequestMapping(value = "/memberinfo")
	public String memberinfo(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionid");// 현재 로그인한 회원의 아이디
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		
		 MemberDto memberDto = dao.memberinfo(sessionId);
		
		 model.addAttribute("memberDto",memberDto);
		
		return"memberInfo";
	}
	@RequestMapping(value = "/memberDelete")
	public String memberDelete(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionid");// 현재 로그인한 회원의 아이디
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		
		 dao.memberDelete(sessionId);//회원정보삭제->회원탈퇴
		
		 
		
		return"redirect:index";
	
	}
	@RequestMapping(value = "/writeForm")
	public String writeForm(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionid");// 현재 로그인한 회원의 아이디
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		
		if(sessionId == null) {// 로그인 하지 않은 상태
			model.addAttribute("mid", "GUEST");
			model.addAttribute("mname", "비회원");
			
		}else {//로그인 한 상태
			MemberDto memberDto =dao.memberinfo(sessionId);//현재 로그인한 회원의 모든정보 가져오기
			model.addAttribute("mid", memberDto.getMid());
			model.addAttribute("mname", memberDto.getMname());
		}
		
	
		
		return"writeForm";
	}
		@RequestMapping(value = "/write")
		public String write(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionid");// 현재 로그인한 회원의 아이디
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		
		String btitle = request.getParameter("btitle");//글제목
		String bcontent = request.getParameter("bcontent");//글내용
		String bmid = null;
		String bmname = null;
		
		if(sessionId == null) {// 로그인 하지 않은 상태
			bmid = "GUEST";
			bmname = "비회원";
			
		}else {//로그인 한 상태
			MemberDto memberDto =dao.memberinfo(sessionId);//현재 로그인한 회원의 모든정보 가져오기
			bmid = memberDto.getMid();
			bmname = memberDto.getMname();
		}
		
		dao.writeDao(btitle,bcontent,bmid,bmname);
		
		return "redirect:list";
	}
			
}