package com.collabus.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;

public interface ProjectMapper {

	@Insert("INSERT INTO "
			+ "project("
			+ "project_leader, project_name, "
			+ "project_description, project_startdate, "
			+ "project_enddate) " + 
			"VALUES ("
			+ "#{project_leader}, #{project_name}, "
			+ "#{project_description}, #{project_startdate}, "
			+ "#{project_enddate})")
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
	
}
