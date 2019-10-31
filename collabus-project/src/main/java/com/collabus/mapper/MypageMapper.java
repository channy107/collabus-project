package com.collabus.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.UserDTO;

public interface MypageMapper {

	
	@Update(value ="update user set user_pw = #{user_pw} where user_id=#{user_id}  ")
	public int pwdUpdate(UserDTO dto) throws Exception;
	
	
	
}
