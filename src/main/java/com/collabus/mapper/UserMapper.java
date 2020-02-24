package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;

public interface UserMapper { //User
	

	   /** 회원 가입 기능이다. 
	    * @param dto 회원가입 페이지에서 입력한 값들을 가져온다.
	    * @throws Exception
	    * 
	    * @Date 2019-20-28
	    * @Author 강정훈
	    */
	   @Insert(value = "INSERT INTO user(user_email, user_pw, user_nickname) VALUES (#{user_email}, #{user_pw}, #{user_nickname})")
	   public void signup(UserDTO dto) throws Exception;


	   /** 사용자  이메일을 찾는 기능이다. 
	    * @param user_email 이메일이 존재하는지 비교할 값을 가져온다.
	    * @return 검색한 이메일을 반환한다.
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Select(value = "select user_email from user where user_email = #{user_email} ")
	   public String selectUser(String user_email) throws NullPointerException;

	   @Select(value = "select * from user where user_id = #{user_id} &&user_isclosed=0")
	   public UserVO selectUserVO(int user_id) throws NullPointerException;
	   
	   
	   /** 로그인된 회원 검색 기능이다. 
	    * @param dto 사용자를 검색하기 위해 이메일 비밀번호를 가져온다.
	    * @return 사용자의 정보를 반환한다. 
	    * @throws NullPointerException
	    * 
	    * 
	    * @Author - 
	    */
	   @Select(value = "select * from user where user_email = #{user_email} && user_pw = #{user_pw}  && user_isclosed=0")
	   public List<UserVO> selectLogin(UserDTO dto) throws NullPointerException;


	   /**회원 로그인 가능 검색이다
	    * @param dto 아이디 비밀번호가 같은 db가 있는지 확인하는 칸이다. 
	    * @return 있다면 1 을 반환하는데 이떄 회원가입을 못하게 하는거다. 0 을반환하면 정상적인 회원가입 루트로 갈수있다.
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Select(value = "select user_id from user where user_email = #{user_email} && user_pw = #{user_pw} && user_isclosed=0 ")
	   public Integer selectLoginNO(UserDTO dto) throws NullPointerException;

	   
	   /** 마지막 로그인, 로그아웃 에 시간을 체크하는 기능이다. 
	    * @param dto 이 기능을 수행하기위해 user_id값이 필요해서 가져왔다.
	    * @return 1 정상적으로 로그인,로그아웃 시간을 체크한다. 0 회원정보를 찾을수없어 업데이트를 못한다. 
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Update(value="update user set user_enddate = now() where user_id=#{user_id}  && user_isclosed=0")
	   public int LoginNow( UserDTO dto) throws NullPointerException;

	   
	   /** 회원정보를 mypage_에 List 로 화면을 보여주기 위한 기능 이다.
	    * @param id 회원 유저가 있는지 확인한다
	    * @return dto 를 List 에 담아 반환한다.
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Select(value = "select * from user where user_id = #{id} &&user_isclosed=0 ")
	   public List<UserVO> selectMypage(@Param("id") int id) throws NullPointerException;

	   
	   /** 닉네임 중복 체크 기능이다. 
	    * @param user_nickname 닉네임 을 비교할 값을 가져온다 .
	    * @return db에 검색된 닉네임을 반환한다. 
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Select(value="select user_nickname from user where user_nickname= #{user_nickname} &&user_isclosed=0")
	   public String selectNickname(@Param("user_nickname") String user_nickname) throws NullPointerException;
	   
	   
	   /** 메일 찾기 기능이다. 
	    * @param user_id 유저 정보로 메일을 검색한다. 
	    * @return 메일을 리턴함 
	    * @throws NullPointerException
	    * 
	    * @Date 2019-10-28
	    * @Author 강정훈 
	    */
	   @Select(value="select user_email from user where user_id = #{user_id} && user_isclosed=0")
	   public String selectEmail(@Param("user_id") int user_id) throws NullPointerException;
	   
	
	@Select("SELECT user_id, user_nickname " + 
			"FROM user " + 
			"WHERE user_id IN (SELECT work_user FROM work WHERE work_project = #{project_id}) " +
			"ORDER BY CASE WHEN user_id = #{user_id} THEN 0 ELSE 1 END, user_id")
	public List<UserVO> selectUserNickname(@Param("project_id") int project_id, @Param("user_id") int user_id) throws Exception;
	
	@Select("SELECT user_nickname " + 
			"FROM user " + 
			"WHERE user_id = #{user_id} " 
			)
	public String selectOneUserNickname(int user_id) throws Exception;

}