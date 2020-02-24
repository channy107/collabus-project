package com.collabus.service;

import java.util.List;

import com.collabus.model.CheckDTO;
import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;
import com.collabus.model.WorkVO;

public interface TaskService {

	public abstract void assignedRegist(TaskDTO dto) throws Exception;
	
	public abstract void unAssignedRegist(TaskDTO dto) throws Exception;
	
	public abstract void assignedUpdateTask(TaskDTO dto) throws Exception;
	
	public abstract void unAssignedUpdateTask(TaskDTO dto) throws Exception;
	
	public abstract void deleteTask(int task_id) throws Exception;
	
	public abstract List<TaskVO> listTasks(int project_id, int user_id) throws Exception;
	
	public abstract TaskVO retrieveTask(int task_id) throws Exception;
	
	public abstract int countTask(int project_id) throws Exception;
	
	public abstract int getProjectMember(int project_id) throws Exception;
	
	public abstract List<CheckDTO> getCheckedMember(int task_id) throws Exception;
	
	public abstract List<CheckDTO> isCheckedUser(int task_id, int user_id) throws Exception;
	
	public abstract void taskPercomplete(int task_id) throws Exception;
	   
	public abstract void taskIsdone(int task_id) throws Exception;
	   
	public abstract void taskIsnotdone(int task_id) throws Exception;
	
}
