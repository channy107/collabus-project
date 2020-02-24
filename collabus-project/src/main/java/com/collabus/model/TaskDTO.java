package com.collabus.model;

import lombok.Data;

@Data
public class TaskDTO {

	private int task_id;
	private int task_project;
	private int task_user;
	private String task_name;
	private String task_description;
	private String task_startdate;
	private String task_enddate;
	private String task_createddate;
	private String task_updateddate;
	private Boolean task_isdone;
	private float task_percomplete;
	private int task_importance;
	
	public TaskDTO(
			String task_name,
			String task_description,
			String task_startdate,
			String task_enddate,
			String task_createddate,
			String task_updateddate,
			int task_importance
			) {
		this.task_name = task_name;
		this.task_description = task_description;
		this.task_startdate = task_startdate;
		this.task_enddate = task_enddate;
		this.task_createddate = task_createddate;
		this.task_updateddate = task_updateddate;
		this.task_importance = task_importance;
	}
}
