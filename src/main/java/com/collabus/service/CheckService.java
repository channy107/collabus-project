package com.collabus.service;

import com.collabus.model.CheckDTO;


public interface CheckService {

	public abstract void InsertCheck(CheckDTO dto) throws Exception;
	
	
	public abstract void Checked(CheckDTO dto) throws Exception;
	
	public abstract void unCheck(CheckDTO dto) throws Exception;
	
	public abstract int checkedTU(CheckDTO dto) throws Exception;

	public abstract int checkedConfirm(CheckDTO dto) throws Exception;

    public abstract int countConfirm(CheckDTO dto) throws Exception;
    
    public abstract int projectUser(int project_id) throws Exception;
}
