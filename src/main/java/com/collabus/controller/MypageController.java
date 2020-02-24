package com.collabus.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collabus.model.InviteDTO;
import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;
import com.collabus.service.MypageService;
import com.collabus.service.UserService;
import com.collabus.service.security.SHA;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MypageController {

   @Autowired
   private UserService userService;

   @Inject
   private MypageService mypage;

   @GetMapping("/mypage")
   public String mypage(Model model, HttpSession session) throws Exception {
      try {
         UserVO userVo = userService.selectUserVO((int) session.getAttribute("user_id"));
         List<InviteDTO> inviteDTO = mypage.userinviteselects(userVo.getUser_id());

         model.addAttribute("inviteDTO", inviteDTO);
         model.addAttribute("userVo", userVo);
      } catch (Exception e) {
      }

      return "mypage";
   }
   
   ///mypage/userTaltoe

   @Transactional(rollbackFor = Exception.class)
   @ResponseBody
   @PostMapping(value = "/mypage/userTaltoe")
   public int userTalotoe(String number, String message,HttpSession session) throws Exception {
      int user_id = (int) session.getAttribute("user_id");
      
       System.out.println("==========================================="+message+"\n ,,"+number);
      int reasonclose_number=numberChange(number);
      
      try {
    	  
    	  if(message == null || message =="" || message ==" "){
    		  message=".";
    	  }
    	  
         if (mypage.userTaltoe_1(user_id) == 1) {
        	 if(mypage.userTaltoe_2(user_id,reasonclose_number, message) ==1){
        		 if(mypage.userTaltoe_3(reasonclose_number,user_id)==1){
        			 return 1;
        		 }
        	 }
         }
         throw new Exception();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
   private int numberChange(String number) {
	   int num=0;
	   
	   System.out.println(num+"()()()()()()()()");
	   for(int i=0;i<number.length();i++){
		   
		   num+=number.charAt(i)-49;
		   if(number.length()-1 != i){
			   num*=10;
		   }
	   }
	   System.out.println(num+"()()()()()()()()");
		return num;
	}
   
   
   

@ResponseBody
   @PostMapping(value = "/mypage/isInvite")
   public int myInvite(int project_id, HttpSession session) {
      int user_id = (int) session.getAttribute("user_id");
      try {
         System.out.println("===========================================");
         if (mypage.inviteAccept(user_id, project_id) == 1) {
            // 0실패1 성공
            return 1;
         }

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }

   @ResponseBody
   @PostMapping(value = "/mypage/isInviteNot")
   public int myInviteNot(int user_id, int project_id) {
      try {
         mypage.inviteReject(user_id, project_id);
         return 1;
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   /**
    * 이 건 ajax_ 로 비밀번호를 바꿀수 있는지 확인하는 기능이다 .
    * 
    * @param user_dto       는 SAH 암호화에 user_dto 객체를 넣어줘야하기 때문에 받아왔다
    * @param user_pw        이 아래 참고
    * @param mypage_user_pw - 이미 로그인된 패스워드를 가져온다 이패스워드는 이미 암호화가 이루워진 상태이다 - 현재 비밀번호
    *                       입력칸에 로그인한 패스워드를 입력한다 이건 로그인한 사용자가 잠시 어디갔다올떄 누군가 패스워드를
    *                       바꿀까 대비한거다 - 현재 패스워드를 입력해 암호화 시킨다 이 암호화는 값 두개가 필요한대 하나는
    *                       user_email 또하나는 user_pw 가 있어야 완벽한 - 암호화가 이루어진다.
    * @param user_email     위에 참고
    * @param change_pw      는 현재 사용자의 비밀번호를 바꿔준다
    * @param user_id        는 비밀번호를 바꿔줄떄 사용자를 비번과 또하나의 값을 비교하는데 이떄 비번이 아주 작은 확률이지만
    *                       같을수가 있어서 또하나의 값이 필요해서 다 - 비번을 바꿀떄 사용되는 건 change_pw 과
    *                       user_id 이다
    * @return 0 과 1 과 2 가 있다 -0 은 모든것이 안맞을 때 실행된다 -1 은 비밀번호까지 넣어준다 -2 는 비밀번호가 틀렸을떄
    *         리턴해준다 -* 만약 혹시라도 update 도 끝나고 값이 2이상 들어올떄 transactionManager.rollback
    *         을 성정했ㄷ ㅏ
    *
    * @throws NoSuchAlgorithmException update 시 붙는 exctioon 이다
    * @Date 2019-10-27
    * @Author 강정훈
    */
   @ResponseBody
   @PostMapping(value = "/mypage/password")
   public int changePwd(
         String userPw,
         String isPw,
         String changePw,
         String user_email,
         UserDTO user_dto,
         HttpSession session) {
      try {
         /*
          * userPw 원래 비번 
          * isPw 입력한 비번
          * change 바꿀 비번
          * email 이메일
          * 
          */
           int user_id = (int) session.getAttribute("user_id");
          
           String isUserPassword= userPw;
           user_dto.setUser_email(user_email);
           
           
           sha(isPw,user_dto);
           
           String isUserPasswordIs =(String) user_dto.getUser_pw();
           
           System.out.println("post equals+================================++++");
           
           if(!(isUserPassword.equals(isUserPasswordIs)))
              {
              System.out.println("비번 틀림 ");
              return 2;
             }
           
           sha(changePw,user_dto);
           
           System.out.println("post sha - |||||||||||||||||||||||||||||");
           
           if(mypage.pwdUpdate(user_dto.getUser_pw(),user_id) > 0 )
              {
             
              return 1;
              }
          
           } catch (Exception e) {;;}
      
           System.out.println("post end--------------------------");
      return 0;
   }

         /**
       * 암호화 기능 이다 매번 두번 호출해주는걸 하나로 해결하기 위해 만들어전거다
       * 
       * @param pw  암호화 해줄 비밀번호
       * @param dto 암호화시 필요한 객체 이다
       * @throws NoSuchAlgorithmException
       * 
       * @Date 2019-10-26
       * @Author 강정훈
       */    public static void sha(String pw,UserDTO dto) throws NoSuchAlgorithmException{
          // 암호화 함수
          dto.setUser_pw(pw);
          dto.setUser_pw(SHA.sha256(dto)); 
         }
          

}