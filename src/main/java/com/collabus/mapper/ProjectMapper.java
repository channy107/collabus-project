package com.collabus.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.InviteDTO;
import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;
import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;
import com.collabus.model.WorkDTO;

public interface ProjectMapper {

	@Insert("INSERT INTO "
			+ "project("
			+ "project_leader, project_name, "
			+ "project_description, project_startdate, "
			+ "project_enddate, project_percomplete) " + 
			"VALUES ("
			+ "#{project_leader}, #{project_name}, "
			+ "#{project_description}, #{project_startdate}, "
			+ "#{project_enddate}, 0)")
	@Options(useGeneratedKeys = true, keyProperty = "project_id")
	public int insertProject(ProjectDTO dto) throws Exception;
	
	@Insert("INSERT INTO work VALUES (#{work_user}, #{work_project})")
	public void insertWork(Map<String, Integer> map) throws Exception;
	
	@Select("SELECT project.* "
			+ "FROM project "
			+ "LEFT JOIN work "
			+ "ON project.project_id = work.work_project "
			+ "WHERE work.work_user = #{user_id}")
	@ResultType(ProjectVO.class)
	public List<ProjectVO> selectProjects(int user_id) throws Exception;

	@Select("SELECT project.* "
			+ "FROM project "
			+ "LEFT JOIN work "
			+ "ON project.project_id = work.work_project "
			+ "WHERE work.work_user = #{user_id} "
			+ "AND project.project_name LIKE #{searchKeyword}")
	@ResultType(ProjectVO.class)
	public List<ProjectVO> selectProjectsByName(@Param("searchKeyword") String searchKeyword, @Param("user_id") int user_id) throws Exception;
	
	@Select("SELECT user.* "
			+ "FROM user "
			+ "LEFT JOIN work "
			+ "ON user.user_id = work.work_user "
			+ "WHERE work.work_project = #{project_id} "
			)
	@ResultType(UserVO.class)
	public List<UserVO> selectProjectMembers(int project_id) throws Exception;
	
	@Select("select user_nickname from user  where user_email = #{value} || user_nickname = #{value} &&user_isclosed=0")
	public List<UserDTO> SelectEmailAndNickname (String value) throws Exception;
	
	//회원번호로 회원 이름 찾기 
	@Select("select user_nickname from user where user_id = #{user_id} &&user_isclosed=0")
	public String selectName (@Param("user_id")int  user_id);
	
	@Insert("insert into invite (send_name ,reserve_id , project_id) values( #{send_name},#{reserve_id} , #{project_id})")
	public int userInvite(@Param("send_name")String send_name,@Param("reserve_id")int  reserve_id ,@Param("project_id") int project_id ) throws Exception;
	// 초대 테이블
	
	@Select("select reserve_id from invite where reserve_id = #{reserve_id} && project_id = #{project_id}")
	public List<InviteDTO> userInviteSelect(@Param("reserve_id")int  reserve_id ,@Param("project_id") int project_id ) throws Exception;
	// 초대 테이블 확인 함
	
	@Select(" select user_id from user where user_nickname=#{name} &&user_isclosed=0")
	public int userInviteId(String name) throws Exception;
	//   초대할 회원 번호 검색 
	
	
	@Select("select work_user from work where work_user=#{work_user} && work_project =#{work_project}")
	public List<WorkDTO> userWorkListMamber(@Param("work_user") int work_user,@Param("work_project") int work_project)throws Exception;
	 
	@Select("select project_id from project where project_leader =#{reserve_id} &&project_id =#{project_id}   ")
	public int projectIdSelect(@Param("reserve_id")int reserve_id, @Param("project_id") int project_id)throws Exception;
	//프로젝트 방장 구함 
	
	@Update("UPDATE project SET "
	         +"project_name = #{project_name}, "
	         +"project_description = #{project_description}, "
	         +"project_startdate = #{project_startdate}, "
	         +"project_enddate = #{project_enddate} "
	         +"WHERE project_id = #{project_id} ")
	   public void updateProject(ProjectDTO dto) throws Exception;
	   
	   @Select("SELECT * "
	         + "FROM project "
	         + "WHERE project_id = #{project_id}")
	   public ProjectVO getProject(int project_id) throws Exception;

	   @Delete("DELETE FROM project "
	         +"WHERE project_id = #{project_id} ")
	   public void delProject(int project_id) throws Exception;
	   
	   @Delete("DELETE from task "
	         +"Where task_project = #{project_id}")
	   public void delTasks(int project_id) throws Exception;
	   
	   @Delete("DELETE from work "
	         +" Where work_project = #{project_id}")
	   public void delWork(int project_id) throws Exception;
	   
	     @Update("UPDATE project SET "
	            +"project_percomplete = "
	            + "((SELECT COUNT(*) FROM task WHERE task_project = #{project_id} AND task_isdone = 1) / "
	            + "(SELECT COUNT(*) FROM task WHERE task_project = #{project_id})) * 100 "
	            + "WHERE project_id = #{project_id}")
	     public int projectPercomplete(int task_id) throws Exception;
	
}
