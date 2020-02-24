
package com.collabus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.SubtaskMapper;
import com.collabus.model.SubtaskDTO;
import com.collabus.model.SubtaskVO;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.SubtaskMapper;
import com.collabus.model.SubtaskDTO;

@Service
public class SubtaskServiceImpl implements SubtaskService{
	
	@Inject
	private SubtaskMapper mapper;

	@Override
	public void addSubtask(SubtaskDTO dto) throws Exception {
		 
		mapper.insertSubtask(dto);
		
	}

	@Override
	public Object retrieve(int task_id) throws Exception {
		return mapper.selectSubtask(task_id);
	}

//	@Override
//	public void upload(SubtaskDTO dto) throws Exception {
//		
//		mapper.upload(dto);		
//	}

	@Override
	public void delSubtask(int subtask_id) throws Exception{
		
		mapper.delSubtask(subtask_id);
		
	}

	@Override
	public void updateSubtask(SubtaskDTO dto) throws Exception {
		
		mapper.updateSubtask(dto);	
	}
	
	@Override
	public void upload(SubtaskDTO dto) throws Exception {
	      
	   mapper.upload(dto);      
	}
	
	   
	   @Override
	   public void checkSubtask(SubtaskDTO dto) throws Exception {
	      mapper.checkSubtask(dto);
	      
	   }

	   @Override
	   public void unCheckSubtask(SubtaskDTO dto) throws Exception {
	      mapper.unCheckSubtask(dto);
	      
	   }
	
}

