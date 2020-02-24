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
	public void assignedRegist(TaskDTO dto) throws Exception {
		mapper.assignedInsertTask(dto);
	}
	
	@Override
	public void unAssignedRegist(TaskDTO dto) throws Exception {
		mapper.unAssignedInsertTask(dto);
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
	public void assignedUpdateTask(TaskDTO dto) throws Exception {
		mapper.assignedUpdateTask(dto);
		
	}
	
	@Override
	public void unAssignedUpdateTask(TaskDTO dto) throws Exception {
		mapper.unAssignedUpdateTask(dto);
		
	}

	 @Override
	   public void deleteTask(int task_id) throws Exception {
	      mapper.deleteTask(task_id);
	      
	   }

	
	@Override
	   public void taskPercomplete(int task_id) throws Exception {
	      mapper.taskPercomplete(task_id);
	      
	   }

	   @Override
	   public void taskIsdone(int task_id) throws Exception {
	      mapper.taskIsdone(task_id);
	   }

	   @Override
	   public void taskIsnotdone(int task_id) throws Exception {
	      mapper.taskIsnotdone(task_id);
	   }
	   

		@Override
		public int countTask(int project_id) throws Exception {
			 return mapper.countTask(project_id);
		}


}
