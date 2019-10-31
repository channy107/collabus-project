
package com.collabus.service;

import java.util.List;

import com.collabus.model.SubtaskDTO;
import com.collabus.model.SubtaskVO;


public interface SubtaskService {

	public abstract void addSubtask(SubtaskDTO dto) throws Exception;
	
//	public abstract void upload(SubtaskDTO dto) throws Exception;
	
	public abstract Object retrieve(int task_id) throws Exception;
	
	public abstract void upload(SubtaskDTO dto) throws Exception;

	public abstract void delSubtask(int subtask_id) throws Exception;

	public abstract void updateSubtask(SubtaskDTO dto) throws Exception;

}

