package com.collabus.model;

import java.util.Date;

import lombok.Data;

@Data
public class TaskVO {

	private int task_id;
	private int task_project;
	private Integer task_user; // nullable�̱� ������ int�� �ƴ� Integer�� ����
	private String task_name;
	private String task_description;
	private Date task_startdate;
	private Date task_enddate;
	private boolean task_isdone;
	private float task_percomplete;
	private int task_importance;
	private String task_user_nickname;
	
}
