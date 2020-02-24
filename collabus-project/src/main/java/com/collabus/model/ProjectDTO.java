package com.collabus.model;

import lombok.Data;

@Data
public class ProjectDTO {
	private int project_id;
	private int project_leader;
	private String project_name;
	private String project_description;
	private String project_startdate;
	private String project_enddate;
	private String project_createddate;
	private String project_updateddate;
	
	public ProjectDTO(
			String project_name,
			String project_description,
			String project_startdate,
			String project_enddate,
			String project_createddate,
			String project_updateddate
			) {
		this.project_name = project_name;
		this.project_description = project_description;
		this.project_startdate = project_startdate;
		this.project_enddate = project_enddate;
		this.project_createddate = project_createddate;
		this.project_updateddate = project_updateddate;
	}
	
}
