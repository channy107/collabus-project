package com.collabus.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.CheckDTO;


public interface CheckMapper {

	@Insert("INSERT INTO confirm (confirm_task, confirm_user, isChecked) value ( #{confirm_task}, #{confirm_user}, 1)")
	public void InsertCheck(CheckDTO dto) throws Exception;

	@Update("UPdate confirm set isChecked = 1 WHERE confirm_task = #{confirm_task} and confirm_user = #{confirm_user}")
	public void Checked(CheckDTO dto) throws Exception;
	
	@Update("UPdate confirm set isChecked = 0 WHERE confirm_task = #{confirm_task} and confirm_user = #{confirm_user}")
	public void unCheck(CheckDTO dto) throws Exception;
	   
	
	@Select("SELECT count(confirm_user) From confirm "
			+ "Where confirm_task = #{confirm_task} AND confirm_user = #{confirm_user}")
	public int countTU(CheckDTO dto) throws Exception;
	  
	@Select("SELECT count(confirm_user) From confirm "
			+ "Where confirm_task = #{confirm_task}")
	public int countConfirm(CheckDTO dto) throws Exception;
	   
	@Select("SELECT count(isChecked) From confirm "
			+ "Where confirm_task = #{confirm_task} and isChecked = 1")
	public int checkedConfirm(CheckDTO dto) throws Exception;
	
	@Select("SELECT count(work_user) From work "
			+ "Where work_project = #{projcet_id}")
	public int projectUser(int project_id) throws Exception;
	
}
