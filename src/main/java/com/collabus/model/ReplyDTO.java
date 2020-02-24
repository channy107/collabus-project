package com.collabus.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

	private int reply_id;
	private int reply_task;
	private int reply_user;
	private String reply_user_name;
	private String reply_text;
	private Date created_at;
	private Date updated_at;
}
