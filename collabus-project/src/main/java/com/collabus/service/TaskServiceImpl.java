package com.collabus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.TaskMapper;
import com.collabus.model.CheckDTO;
import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;
import com.collabus.model.WorkVO;

@Service
public class TaskServiceImpl implements TaskService {

	@Inject
	private TaskMapper mapper;
	
	@Override
	public void regist(TaskDTO dto) throws Exception {
		mapper.insertTask(dto);
	}

	@Override
	public List<TaskVO> listTasks(int project_id, int user_id) throws Exception {
		
		return mapper.selectTasks(project_id, user_id);
	}

	@Override
	public TaskVO retrieveTask(int task_id) throws Exception {
		
		return mapper.selectTask(task_id);
	}

	@Override
	public int getProjectMember(int project_id) throws Exception {
		
		return mapper.getProjectMember(project_id);
	}

	@Override
	public List<CheckDTO> getCheckedMember(int task_id) throws Exception {
		
		return mapper.getCheckedMember(task_id);
	}

	@Override
	public List<CheckDTO> isCheckedUser(int task_id, int user_id) throws Exception {
		
		return mapper.isCheckedUser(task_id, user_id);
	}

	@Override
	public void updateTask(TaskDTO dto) throws Exception {
		mapper.updateTask(dto);
		
	}

	@Override
	public void deleteTask(int task_id) throws Exception {
		mapper.deleteTask(task_id);
		
	}

	@Override
	public void deleteAllReply() throws Exception {
		mapper.deleteAllReply();
		
	}

	@Override
	public void deleteAllSubtask() throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteAllSubTask();
	}
}
