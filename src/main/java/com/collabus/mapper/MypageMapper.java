package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.InviteDTO;

public interface MypageMapper {

   @Update("update invite set project_invite= 1 where reserve_id = #{reserve_id} and project_id=#{project_id}")
   public int inviteAccept(@Param("reserve_id")int reserve_id,@Param("project_id") int project_id) throws Exception;
   //   초대 수락시 
   
   @Insert("insert into work(work_user,work_project) values(#{work_user},#{work_project})")
   public int projectMamber(@Param("work_user")int work_user,@Param("work_project")int work_project) throws Exception;
   //  초대수락시 set2 - 프로젝트 맴버에 추가 
   
   @Delete("delete from invite where reserve_id =#{reserve_id} and project_id=#{project_id}")
   public void inviteReject(@Param("reserve_id")int reserve_id,@Param("project_id")int project_id) throws Exception;
   // -- 초대 거부 할시  && 수락하고 일어남 
   
   @Select("select * from invite where reserve_id = #{user_id}")
   public List<InviteDTO> userinviteselects(int user_id) throws Exception;
   
   @Update("update user set user_pw = #{user_pw} where user_id = #{user_id} ")
   public int pwdUpdate(@Param("user_pw") String user_pw, @Param("user_id") int user_id);
 
   @Update("update user set user_isclosed = true where user_id = #{user_id}")
   public int userTaltoe_1(@Param("user_id") int user_id);
   
   @Insert("insert into reasonclose(reasonclose_id,reasonclose_number,reasonclose_reason) values(#{user_id},#{reasonclose_number},#{message})")
   public int userTaltoe_2(@Param("user_id") int user_id , @Param("reasonclose_number")int reasonclose_number , @Param("message")String message);
   
   @Update("update user set user_reasonclose = #{number} where user_id=#{user_id}")
   public int userTaltoe_3(@Param("number") int number,@Param("user_id") int user_id);
   
}