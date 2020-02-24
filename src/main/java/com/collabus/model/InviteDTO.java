package com.collabus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InviteDTO {
   private String send_name;
   private int reserve_id ;
   private int project_id;
   private int project_invite;
   
}
