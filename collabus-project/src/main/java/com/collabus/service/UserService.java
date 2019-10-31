package com.collabus.service;

import java.util.List;

import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;

public interface UserService {
	
	/** 회원가입 기능 
	    * @param dto
	    * @return
	    */
	   public boolean signup(UserDTO dto);
	   
	   /** 이메일 중복체크 기능 
	    * @param email
	    * @return
	    * @throws NullPointerException
	    */
	   public String selectUser(String email) throws NullPointerException;
	   
	   /** 로그인 기능 
	    * @param dto
	    * @return
	    * @throws NullPointerException
	    */
	   public boolean selectLogin(UserDTO dto) throws NullPointerException;
	   
	   /** 로그인,로그아웃 시간 체크 기능
	    * @param dto
	    * @return
	    * @throws Exception
	    */
	   public int LoginNow(UserDTO dto) throws Exception;
	   
	   /** 회원 번호 구하기 기능
	    * @param dto
	    * @return
	    * @throws NullPointerException
	    */
	   public Integer selectLoginNO(UserDTO dto) throws NullPointerException;
	   
	   /** 회원 닉네임 중복 기능 
	    * @param user_nickname
	    * @return
	    * @throws NullPointerException
	    */
	   public String selectNickname(String user_nickname) throws NullPointerException;
	   
	   /** 회원 정보 반환 기능 
	    * @param id
	    * @return
	    * @throws NullPointerException
	    */
	   public List<UserVO> selectMypage(int id) throws NullPointerException;
	   
	   /** 이메일 찾기 기능 
	    * @param user_id
	    * @return
	    * @throws NullPointerException
	    */
	   public String selectEmail(int user_id) throws NullPointerException;
	
	// 특정 프로젝트에 참여하는 회원들의 닉네임 얻어오기
	public List<UserVO> retrieveUserNickname(int project_id, int user_id) throws Exception;
	
	public String retrieveOneUserNickname(int user_id) throws Exception;
}
