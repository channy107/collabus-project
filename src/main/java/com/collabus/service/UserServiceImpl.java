package com.collabus.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collabus.mapper.UserMapper;
import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;
import com.collabus.service.security.SHA;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements UserService{
	

	   @Setter(onMethod_ = { @Autowired })
	   private UserMapper mapper;

	   @Override
	   public boolean signup(UserDTO dto) {

	      String eqId = selectUser(dto.getUser_email());

	      if (eqId == null || eqId.equals("")) {

	         try {
	            dto.setUser_pw(SHA.sha256(dto));
	            mapper.signup(dto);

	            return true;
	         } catch (Exception e) {
	            return false;
	         }

	      } else {
	         return false;
	      }

	   }

	   @Override
	   public String selectUser(String id) {

	      String eqId;

	      try {

	         if (id == null) {

	            throw new Exception();
	         }

	         eqId = mapper.selectUser(id);

	      } catch (Exception e) {

	         eqId = null;
	      }

	      return eqId;
	   }// end selectUser

	   @Override
	   public boolean selectLogin(UserDTO dto) {

	      List<UserVO> eqlogin = null;

	      try {

	         if (dto.getUser_email() != null && dto.getUser_pw() != null) {

	            dto.setUser_pw(SHA.sha256(dto));
	            eqlogin = mapper.selectLogin(dto);
	         }

	         if (eqlogin != null) {
	            return true;
	         }

	         else {
	            throw new Exception();
	         }

	      } catch (Exception e) {
	         return false;
	      }
	   }

	   @Override
	   public Integer selectLoginNO(UserDTO dto) throws NullPointerException {
	      return mapper.selectLoginNO(dto);
	   }

	  

	   @Override
	   public List<UserVO> selectMypage(int id) throws NullPointerException {
	      return mapper.selectMypage(id);
	   }

	   @Override
	   public int LoginNow(UserDTO dto) throws Exception {
	      
	      
	      return mapper.LoginNow(dto);
	   }

	   @Override
	   public String selectNickname(String user_nickname) throws NullPointerException {
	      return mapper.selectNickname(user_nickname);
	   }

	   @Override
	   public String selectEmail(int user_id) throws NullPointerException {
	      return mapper.selectEmail(user_id);
	   }
	   
	@Override
	public List<UserVO> retrieveUserNickname(int project_id, int user_id) throws Exception {
		
		return mapper.selectUserNickname(project_id, user_id);
	}
	
	@Override
	public String retrieveOneUserNickname(int user_id) throws Exception {
		
		return mapper.selectOneUserNickname(user_id);
	}

	@Override
	public UserVO selectUserVO(int user_id) throws NullPointerException {
		// TODO Auto-generated method stub
		return mapper.selectUserVO(user_id);
	}


}
