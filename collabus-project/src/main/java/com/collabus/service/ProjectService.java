package com.collabus.service;

import java.util.List;

import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;

public interface ProjectService {

	public abstract void regist(ProjectDTO dto) throws Exception;
	
	public abstract List<ProjectVO> retrieve(int user_id) throws Exception;
	
	public abstract List<ProjectVO> search(String searchKeyword, int user_id) throws Exception;
}
