package com.collabus.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collabus.model.UserDTO;
import com.collabus.service.UserService;

import lombok.extern.log4j.Log4j;

/**
 * @author kgh22
 * @Date 2019-10-27
 */

@Log4j
@Controller
public class LoginController {

   @Inject
   private UserService user;

   /** 로그인 페이지*  에 들어갈수 있다
    *  인터셉터 쪽에서 어느 페이지로 갈지 결정해준다
    *  user_id 세션이 있다면 dashboard 로 가고 없다면 현제 페이지 에서 가만이 있는다. 
    * @throws IOException
    * @Date 2019 - 10
    */
   @GetMapping(value = "/login")
   public void loginGet() throws IOException {
      
   }
   
   /** 로그인 기능을 담당함 
    *  다음 페이지를 어디로 이동할지 정한다 
    * @param dto 로그인할떄 입력받은 ID ,PW 을 받기 위해서 가져왔다 + 로그인 시간체크를 하기위해 선언함 
    * @param res 다음 이동 경로를 가기 위해 받아왔다 
    * @param req 값을  인터셉터쪽에서 처리할수있도록 한 기능 
    * @throws IOException 
    * 
    * @Date 2019-10-26
    * @author 강정훈 
    */
   @PostMapping(value = "/login")
   public void loginPost(UserDTO dto, HttpServletResponse res, HttpServletRequest req)
         throws IOException {
      try {
         if (user.selectLogin(dto)) {
            dto.setUser_id(user.selectLoginNO(dto));
            req.setAttribute("user", dto.getUser_id());
            user.LoginNow(dto);
         }
      } catch (Exception e) {;;}
      
      res.sendRedirect("/dashboard");

   }

   /** 로그아웃 기능을 한다. 
    * @param res 로그아웃 성공시 login 페이지로돌아간다 이떄 session 이 없는 체로 돌아가서 다시 로그인 해줘야함 
    * @param session 로그인할떄 얻은 세션을 지우기 위해 session 을 import 해서 가져왔다  
    * @param dto 로그인 , 로그아웃 시간을 재기 위해서 가져옴 
    * @throws Exception
    * 
    * @date 2019-10-27
    * @Author 강정훈 
    */
   @RequestMapping(value = "/login/logout")
   public void logout(HttpServletResponse res, HttpSession session,UserDTO dto) throws Exception {
      session.removeAttribute("user_id");
      user.LoginNow(dto);
      res.sendRedirect("/login");
   }
}