package com.collabus.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckDTO {

	private int confirm_task;
	private int confirm_user;
	private int isChecked;
}
