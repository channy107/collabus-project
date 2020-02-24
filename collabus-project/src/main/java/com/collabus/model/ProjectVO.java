package com.collabus.model;

import java.util.Date;

import lombok.Value;

@Value
public class ProjectVO {

	private int project_id;
	private int project_leader;
	private String project_name;
	private String project_description;
	private Date project_startdate;
	private Date project_enddate;
	private Date project_createddate;
	private Date project_updateddate;
}
