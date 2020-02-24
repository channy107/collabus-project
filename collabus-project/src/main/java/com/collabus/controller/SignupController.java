package com.collabus.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collabus.model.UserDTO;
import com.collabus.service.UserService;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class SignupController {

   @Inject
   private UserService user;

   /** 회원 가입 페이지 에서 처음들어갈떄 기능이다.
    *  회원 세션이 있다면 dashboard가 들어가도록 설정한다.
    *  
    *  @Date 2019-10-27
    *  @Author 강정훈 
    */
   @GetMapping(value="/signup")
   public void singupGet() {
      
   }
   
   /**회원 가입 기능이다. 
    * @param dto 회원 가입 시 입력한 정보들을 가져온다. 
    *          -중복체크는 이메일 닉네임 을 중복을 체크 함 
    *          -유효성 검사는 email,nickname,pwd_ 입력한 정보들을 검사한다.
    *           -user.signup 에서 사용되는 데 이건 회원 가입을 할수 있는 기능이다.
    * 
    * @param res 회원가입 성공시 다음 경로를 정해준다 .
    *          - 회원가입 을 하면 다음페이지가 login 창이다.
    * @throws IOException
    * 
    *  @Date 2019-10-27
    *  @Author 강정훈 
    */
   @PostMapping(value="/signup")
   public void singupPost(UserDTO dto, HttpServletResponse res) throws IOException {
      
      if(user.signup(dto)) {;;}
      else {;;}
      res.sendRedirect("/login");
   }
   
   /** 이메일 중복 체크 기능이다.
    * @param user_email  회원 이메일을 가져오기 위해서  사용함  
    *                -회원 이메일 기반으로 db 에서 회원 이메일이 있는지 확인한다.
    * @return 회원정보가있다면 1 없다면 0 을 반환 한다.
    * 
    *  @Date 2019-10-27
    *  @Author 강정훈 
    */
   @ResponseBody
   @PostMapping(value="/signup/checkEmail")
   public int signIdCheck(String user_email) {
      
      String idCheck = user.selectUser(user_email);
      
      try {
         
         if( !(idCheck.equals("")) || idCheck != null) {
            return 1;  
         }

         return 0;
      }catch(NullPointerException e) {
         return 0;
      }
   }
   
   /** 닉네임 중복 체크 기능이다. 
    * @param user_nickname  유저_닉네임 DB 안에 같은 이름이 있는 지 확인할려고 가져오는 닉네임이다. 
    * @return 닉네임 중복이 있으면 1 
    *                없으면 0 값을 반환 한다 .
    *  @Date 2019-10-28
    *  @Author 강정훈 
    */
   @ResponseBody
   @PostMapping(value="/signup/checknickname")
   public int signNickCheck(String user_nickname) {

      int check=0;

      try {
         
         String nicknameCheck = user.selectNickname(user_nickname);
      
         if( nicknameCheck != null) {
            check =1;
         }
         
      }catch(NullPointerException e) {
         check =0; 
         
      }catch(Exception e) {
         check =1;
      }
      
      return check;
   }
   
}