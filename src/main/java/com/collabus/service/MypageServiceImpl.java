package com.collabus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collabus.mapper.MypageMapper;
import com.collabus.mapper.UserMapper;
import com.collabus.model.InviteDTO;


@Service
public class MypageServiceImpl implements MypageService{

   @Inject
   private MypageMapper mapper;
   
   @Inject
   private UserMapper user;

   @Transactional(rollbackFor = Exception.class)
   @Override
   public int inviteAccept(int user_id, int project_id) throws Exception {
      if(mapper.inviteAccept(user_id, project_id) > 0) {
         //성공적 초대 컬럼 변경 
         System.out.println("초대 성공적으로 보냄");
         if(projectMamber(user_id,project_id) > 0) {
            System.out.println("프로젝트 테이블 성공적으로 넣어줌 ");
            //프로젝트 테이블에 값 추가
            try {
               
               inviteReject(user_id,project_id); // 그럼 값 삭제 
               System.out.println("값 삭제 성공적 ");
               return 1;
            }catch(Exception e) {
               //아마도 여기서 롤백이 될걸세 
               throw new Exception();
            }
         }else {
            throw new Exception();
         }
      }
      System.out.println("하지만 서비스 객체안에서 0반환");
      return 0;
   }
   
   @Transactional
   @Override
   public int projectMamber(int work_user, int work_project) throws Exception {
   
      return mapper.projectMamber(work_user, work_project);
   }
   
   @Transactional(rollbackFor = Exception.class)
   @Override
   public void inviteReject(int user_id, int project_id) throws Exception {
      mapper.inviteReject(user_id, project_id);
   }

   
   @Override
   public  List<InviteDTO> userinviteselects(int user_id) throws Exception{
      return mapper.userinviteselects(user_id);
   }

   @Override
   public int pwdUpdate(String user_pw, int user_id) {
      // TODO Auto-generated method stub
      return mapper.pwdUpdate(user_pw, user_id);
   }

	@Override
	public int userTaltoe_1(int user_id) {
		return mapper.userTaltoe_1(user_id);
	}
	
	@Override
	public int userTaltoe_2(int user_id, int reasonclose_number, String message) {
		// TODO Auto-generated method stub
		return mapper.userTaltoe_2(user_id, reasonclose_number, message);
	}
	
	@Override
	public int userTaltoe_3(int number,int user_id) {
		// TODO Auto-generated method stub
		return mapper.userTaltoe_3(number,user_id);
	}

}