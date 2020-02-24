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
    		  message= ".";
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
            // 0�떎�뙣1 �꽦怨�
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
    * �씠 嫄� ajax_ 濡� 鍮꾨�踰덊샇瑜� 諛붽��닔 �엳�뒗吏� �솗�씤�븯�뒗 湲곕뒫�씠�떎 .
    * 
    * @param user_dto       �뒗 SAH �븫�샇�솕�뿉 user_dto 媛앹껜瑜� �꽔�뼱以섏빞�븯湲� �븣臾몄뿉 諛쏆븘�솕�떎
    * @param user_pw        �씠 �븘�옒 李멸퀬
    * @param mypage_user_pw - �씠誘� 濡쒓렇�씤�맂 �뙣�뒪�썙�뱶瑜� 媛��졇�삩�떎 �씠�뙣�뒪�썙�뱶�뒗 �씠誘� �븫�샇�솕媛� �씠猷⑥썙吏� �긽�깭�씠�떎 - �쁽�옱 鍮꾨�踰덊샇
    *                       �엯�젰移몄뿉 濡쒓렇�씤�븳 �뙣�뒪�썙�뱶瑜� �엯�젰�븳�떎 �씠嫄� 濡쒓렇�씤�븳 �궗�슜�옄媛� �옞�떆 �뼱�뵒媛붾떎�삱�뻹 �늻援곌� �뙣�뒪�썙�뱶瑜�
    *                       諛붽�源� ��鍮꾪븳嫄곕떎 - �쁽�옱 �뙣�뒪�썙�뱶瑜� �엯�젰�빐 �븫�샇�솕 �떆�궓�떎 �씠 �븫�샇�솕�뒗 媛� �몢媛쒓� �븘�슂�븳�� �븯�굹�뒗
    *                       user_email �삉�븯�굹�뒗 user_pw 媛� �엳�뼱�빞 �셿踰쏀븳 - �븫�샇�솕媛� �씠猷⑥뼱吏꾨떎.
    * @param user_email     �쐞�뿉 李멸퀬
    * @param change_pw      �뒗 �쁽�옱 �궗�슜�옄�쓽 鍮꾨�踰덊샇瑜� 諛붽퓭以��떎
    * @param user_id        �뒗 鍮꾨�踰덊샇瑜� 諛붽퓭以꾨뻹 �궗�슜�옄瑜� 鍮꾨쾲怨� �삉�븯�굹�쓽 媛믪쓣 鍮꾧탳�븯�뒗�뜲 �씠�뻹 鍮꾨쾲�씠 �븘二� �옉�� �솗瑜좎씠吏�留�
    *                       媛숈쓣�닔媛� �엳�뼱�꽌 �삉�븯�굹�쓽 媛믪씠 �븘�슂�빐�꽌 �떎 - 鍮꾨쾲�쓣 諛붽��뻹 �궗�슜�릺�뒗 嫄� change_pw 怨�
    *                       user_id �씠�떎
    * @return 0 怨� 1 怨� 2 媛� �엳�떎 -0 �� 紐⑤뱺寃껋씠 �븞留욎쓣 �븣 �떎�뻾�맂�떎 -1 �� 鍮꾨�踰덊샇源뚯� �꽔�뼱以��떎 -2 �뒗 鍮꾨�踰덊샇媛� ���졇�쓣�뻹
    *         由ы꽩�빐以��떎 -* 留뚯빟 �샊�떆�씪�룄 update �룄 �걹�굹怨� 媛믪씠 2�씠�긽 �뱾�뼱�삱�뻹 transactionManager.rollback
    *         �쓣 �꽦�젙�뻽�꽬 �뀖
    *
    * @throws NoSuchAlgorithmException update �떆 遺숇뒗 exctioon �씠�떎
    * @Date 2019-10-27
    * @Author 媛뺤젙�썕
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
          * userPw �썝�옒 鍮꾨쾲 
          * isPw �엯�젰�븳 鍮꾨쾲
          * change 諛붽� 鍮꾨쾲
          * email �씠硫붿씪
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
              System.out.println("鍮꾨쾲 ��由� ");
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
       * �븫�샇�솕 湲곕뒫 �씠�떎 留ㅻ쾲 �몢踰� �샇異쒗빐二쇰뒗嫄� �븯�굹濡� �빐寃고븯湲� �쐞�빐 留뚮뱾�뼱�쟾嫄곕떎
       * 
       * @param pw  �븫�샇�솕 �빐以� 鍮꾨�踰덊샇
       * @param dto �븫�샇�솕�떆 �븘�슂�븳 媛앹껜 �씠�떎
       * @throws NoSuchAlgorithmException
       * 
       * @Date 2019-10-26
       * @Author 媛뺤젙�썕
       */    public static void sha(String pw,UserDTO dto) throws NoSuchAlgorithmException{
          // �븫�샇�솕 �븿�닔
          dto.setUser_pw(pw);
          dto.setUser_pw(SHA.sha256(dto)); 
         }
          

}