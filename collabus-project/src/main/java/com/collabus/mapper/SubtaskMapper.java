package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.SubtaskDTO;
import com.collabus.model.SubtaskVO;

public interface SubtaskMapper {

   
   @Insert("INSERT INTO subtask (subtask_id, subtask_task, subtask_name, subtask_description)" + 
         "VALUES (#{subtask_id}, #{subtask_task}, #{subtask_name}, #{subtask_description})")
   public void insertSubtask(SubtaskDTO dto) throws Exception;
   
   @Update("UPDATE subtask SET " +
         " subtask_filepath = #{subtask_filepath}, subtask_filename = #{subtask_filename} " + 
         "WHERE subtask_id = #{subtask_id}")
   public void upload(SubtaskDTO dto) throws Exception;
   
   @Select("SELECT subtask_filepath, subtask_filename FROM subtask WHERE subtask_id = #{subtask_id}")
   public void download(SubtaskDTO dto) throws Exception;
   
   @Delete("DELETE FROM subtask WHERE subtask_id = #{subtask_id}")
   public void delSubtask(int subtask_id) throws Exception;
   
   @Select("SELECT * FROM subtask WHERE subtask_task = #{task_id}")
   @ResultType(SubtaskVO.class)
   public List<SubtaskVO> selectSubtask(int task_id) throws Exception;
   
   @Update("UPDATE subtask SET " +
         "subtask_name = #{subtask_name}, subtask_description = #{subtask_description} " +
         "WHERE subtask_id = #{subtask_id}" )
   public void updateSubtask(SubtaskDTO dto) throws Exception;
}