package com.collabus.service;

import com.collabus.model.CheckDTO;


public interface CheckService {

	public abstract void addCheck(CheckDTO dto) throws Exception;
	
	public abstract void unCheck(CheckDTO dto) throws Exception;
}
