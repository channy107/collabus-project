package com.collabus.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.collabus.model.UserDTO;
import com.collabus.service.UserService;

public class SignupInterceptor  extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = req.getSession(false);

		if (session != null) {

			Object authInfo = session.getAttribute("user_id");

			if (authInfo != null) {
				response.sendRedirect("/dashboard");
				return false;
			}

		}

		return true;
	}

	
	
}
