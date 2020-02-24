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
	public void addCheck(CheckDTO dto) throws Exception {
		mapper.insertConfirm(dto);
		
	}

	@Override
	public void unCheck(CheckDTO dto) throws Exception {
		mapper.unCheck(dto);
		
	}
}
