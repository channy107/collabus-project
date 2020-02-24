package com.collabus.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.collabus.service.UserService;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

   @Inject
   private UserService user;

   @Override
   public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
      try {
      HttpSession session = req.getSession(false);
      
      if(session != null) {
         Object authInfo = session.getAttribute("user_id");
         
         if(authInfo != null) {

            response.sendRedirect("/dashboard");
            return false;
         }
      }
      }catch(Exception e) {
         
      }
      log.info("pre -end ");
      return true;
   
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
      HttpSession session = request.getSession(false);
      log.info("post-start");
      
      if(request.getAttribute("user") != null) {
         //로그인 성공시 
         log.info("post-handle-if");
         int id = (int)request.getAttribute("user");
         session.setAttribute("user_id", id);
      }else {
         
      }
      log.info("postHandle");
   }


}