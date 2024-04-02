package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogOutControllerServlet extends HttpServlet{
	
	@PostMapping("/login/logOut.do")
	protected String logOut(HttpServletRequest req){
		req.getSession().invalidate();
		return "redirect:/";
	}
}
