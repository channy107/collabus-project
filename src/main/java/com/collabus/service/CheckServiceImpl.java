package com.collabus.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.CheckMapper;
import com.collabus.model.CheckDTO;

@Service
public class CheckServiceImpl implements CheckService {

	@Inject
	private CheckMapper mapper;


	
	@Override
	public void unCheck(CheckDTO dto) throws Exception {
		mapper.unCheck(dto);
		
	}
	
	@Override
	   public int checkedConfirm(CheckDTO dto) throws Exception {
	      return mapper.checkedConfirm(dto);
	   }

	@Override
	public int countConfirm(CheckDTO dto) throws Exception {
	   return mapper.countConfirm(dto);
	 }

	@Override
	public void Checked(CheckDTO dto) throws Exception {
		mapper.Checked(dto);
		
	}

	@Override
	public void InsertCheck(CheckDTO dto) throws Exception {
		mapper.InsertCheck(dto);
		
	}

	@Override
	public int checkedTU(CheckDTO dto) throws Exception {
		return mapper.countTU(dto);
	}

	@Override
	public int projectUser(int project_id) throws Exception {
		return mapper.projectUser(project_id);
	}
	
	   

}
