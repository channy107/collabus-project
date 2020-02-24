package com.collabus.model;

import lombok.Value;

@Value
public class SubtaskVO {
   private int subtask_id;
   private int subtask_task;
   private String subtask_name;
   private String subtask_description;
   private String subtask_filepath;
   private String subtask_filename;
   private Boolean subtask_ischecked;
}
