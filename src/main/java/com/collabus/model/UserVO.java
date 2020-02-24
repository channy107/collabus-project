package com.collabus.model;

import java.sql.Date;

import lombok.Data;

@Data
public class UserVO {

	   private int user_id;
	   private String user_email;
	   private String user_pw;
	   private String user_nickname;
	   private int user_isclosed;
	   private Date user_joindate;
	   private Date user_enddate;
}
