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
public class CheckVO {
	
	private int confirm_task;
	private int confirm_user;
	private int isChecked;
}
