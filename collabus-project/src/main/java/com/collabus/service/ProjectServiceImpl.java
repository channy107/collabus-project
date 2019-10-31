package com.collabus.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.ProjectMapper;
import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Inject
	private ProjectMapper mapper;

	@Override
	public void regist(ProjectDTO dto) throws Exception {
		
		mapper.insertProject(dto);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("work_user", dto.getProject_leader());
		map.put("work_project", dto.getProject_id());
		
		
		mapper.insertWork(map);
	}

	@Override
	public List<ProjectVO> retrieve(int user_id) throws Exception {
		
		return mapper.selectProjects(user_id);
	}

	@Override
	public List<ProjectVO> search(String searchKeyword, int user_id) throws Exception {
		return mapper.selectProjectsByName("%" + searchKeyword + "%", user_id);
	}
}
