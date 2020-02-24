package com.collabus.model;
import lombok.Data;

@Data
public class SubtaskDTO {
   private int subtask_id;
   private int subtask_task;
   private String subtask_name;
   private String subtask_description;
   private String subtask_filepath;
   private String subtask_filename;
   private Boolean subtask_ischecked;
   
   public SubtaskDTO(
         String subtask_name,
         String subtask_description,
         String subtask_filepath,
         String subtask_filename,
         Boolean subtask_ischecked
         ) {
      this.subtask_name = subtask_name;
      this.subtask_description = subtask_description;
      this.subtask_filepath = subtask_filepath;
      this.subtask_filename = subtask_filename;
      this.subtask_ischecked = subtask_ischecked;
   }
   
}
