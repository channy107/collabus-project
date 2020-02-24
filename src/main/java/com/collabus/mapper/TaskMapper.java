package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.CheckDTO;
import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;

public interface TaskMapper {

	@Insert("INSERT INTO task("
			+ "task_project, task_user, task_name, task_description, task_startdate,task_enddate, task_importance) "
			+ "VALUES (#{task_project}, #{task_user}, #{task_name}, #{task_description}, #{task_startdate}, #{task_enddate}, #{task_importance})")
	public int assignedInsertTask(TaskDTO dto) throws Exception;
	
	@Insert("INSERT INTO task("
			+ "task_project, task_name, task_description, task_startdate,task_enddate, task_importance) "
			+ "VALUES (#{task_project}, #{task_name}, #{task_description}, #{task_startdate}, #{task_enddate}, #{task_importance})")
	public int unAssignedInsertTask(TaskDTO dto) throws Exception;
	
	@Update("UPDATE task SET "
	         + "task_user = #{task_user}, task_name = #{task_name}, task_description = #{task_description},"
			 + "task_startdate=#{task_startdate}, task_enddate=#{task_enddate}, task_importance=#{task_importance} "
	         + "WHERE (task_id = #{task_id});")
	public void assignedUpdateTask(TaskDTO dto) throws Exception;
	
	@Update("UPDATE task SET "
	         + "task_name = #{task_name}, task_description = #{task_description},"
			 + "task_startdate=#{task_startdate}, task_enddate=#{task_enddate}, task_importance=#{task_importance} "
	         + "WHERE (task_id = #{task_id});")
	public void unAssignedUpdateTask(TaskDTO dto) throws Exception;
	
	   @Delete("DELETE FROM task WHERE task_id = #{task_id}")
	   public int deleteTask(int task_id) throws Exception;
	   
	@Select("SELECT task.*, user.user_nickname AS task_user_nickname " + 
			"FROM task LEFT JOIN user ON task_user = user_id " + 
			"WHERE task_project = #{project_id} " + 
			"ORDER BY CASE WHEN task_user = #{user_id} THEN 0 ELSE 1 END, task_user")
	@ResultType(TaskVO.class)
	public List<TaskVO> selectTasks(@Param("project_id") int project_id, @Param("user_id") int user_id) throws Exception;

	@Select("SELECT * FROM task WHERE task_id = #{task_id}")
	public TaskVO selectTask(int task_id) throws Exception;
	
	@Select("SELECT count(*) FROM task WHERE task_project = #{project_id}")
	public int countTask(int task_id) throws Exception;
	
	@Select("SELECT count(*) FROM work WHERE work_project = #{project_id}")
	public int getProjectMember(int project_id) throws Exception;
	
	@Select("SELECT * FROM confirm WHERE confirm_task = #{task_id}")
	@ResultType(CheckDTO.class)
	public List<CheckDTO> getCheckedMember(int task_id) throws Exception;
	
	@Select("SELECT * FROM confirm WHERE confirm_task= #{task_id} and confirm_user = #{user_id}")
	@ResultType(CheckDTO.class)
	public List<CheckDTO> isCheckedUser(@Param("task_id") int task_id, @Param("user_id") int user_id) throws Exception;
	
	@Update("UPDATE task SET "
            +"task_percomplete = "
            + "((SELECT COUNT(*) FROM subtask WHERE subtask_task = #{task_id} AND subtask_ischecked = 1) / "
            + "(SELECT COUNT(*) FROM subtask WHERE subtask_task = #{task_id})) * 100 "
            + "WHERE task_id = #{task_id}")
     public int taskPercomplete(int task_id) throws Exception;
   
     @Update("UPDATE task SET "
            +"task_isdone = 1 "
            + "WHERE task_id = #{task_id} ")
     public int taskIsdone(int task_id) throws Exception;
   
     @Update("UPDATE task SET "
            +"task_isdone = 0 "
            +"WHERE task_id = #{task_id} ")
     public int taskIsnotdone(int task_id) throws Exception;
     
}
