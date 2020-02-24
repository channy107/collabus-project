package com.collabus.service;

import java.util.List;

import com.collabus.model.CheckDTO;
import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;
import com.collabus.model.WorkVO;

public interface TaskService {

	public abstract void regist(TaskDTO dto) throws Exception;
	
	public abstract void updateTask(TaskDTO dto) throws Exception;
	
	public abstract void deleteTask(int task_id) throws Exception;
	
	public abstract void deleteAllReply() throws Exception;
	
	public abstract void deleteAllSubtask() throws Exception;
	
	public abstract List<TaskVO> listTasks(int project_id, int user_id) throws Exception;
	
	public abstract TaskVO retrieveTask(int task_id) throws Exception;
	
	public abstract int getProjectMember(int project_id) throws Exception;
	
	public abstract List<CheckDTO> getCheckedMember(int task_id) throws Exception;
	
	public abstract List<CheckDTO> isCheckedUser(int task_id, int user_id) throws Exception;
	
}
