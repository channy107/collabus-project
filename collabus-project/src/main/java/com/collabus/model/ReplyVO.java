package com.collabus.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {

	private int reply_id;
	private int reply_task;
	private int reply_user;
	private String reply_text;
	private Date created_at;
	private Date updated_at;
	
}
