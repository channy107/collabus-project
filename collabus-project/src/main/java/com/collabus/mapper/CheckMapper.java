package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.CheckDTO;
import com.collabus.model.CheckVO;
import com.collabus.model.ReplyDTO;


public interface CheckMapper {

	@Insert("INSERT INTO confirm (confirm_task, confirm_user, isChecked) " + 
			"VALUES (#{confirm_task}, #{confirm_user}, #{isChecked})")
	public void insertConfirm(CheckDTO dto) throws Exception;
	
	@Delete("DELETE FROM confirm WHERE confirm_task = #{confirm_task} and confirm_user =#{confirm_user}")
	public void unCheck(CheckDTO dto) throws Exception;
}
