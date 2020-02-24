package com.collabus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.collabus.model.InviteDTO;
import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;
import com.collabus.model.UserDTO;
import com.collabus.model.WorkDTO;
import com.collabus.service.ProjectService;

@Controller
public class DashboardController {

	@Inject
	private ProjectService service;

	@PostMapping("/dashboard/addproject")
	public void addProject(ProjectDTO dto, HttpSession session, HttpServletResponse res) throws Exception {
		
		int user_id = (int) session.getAttribute("user_id");
		System.out.println("********************user_id: " + user_id);
		dto.setProject_leader(user_id);
		
		service.regist(dto);
		
		res.sendRedirect("/dashboard");
	}
	
	@GetMapping("/dashboard/updateProject/{project_id}")
	   @ResponseBody
	   public ProjectVO getupdateProject(
	         @PathVariable int project_id,
	         HttpSession session,
	         HttpServletResponse res) throws Exception {
	      ProjectVO dto = (ProjectVO)service.getProject(project_id);
	      
	      return dto;
	   }
	   
	   @PostMapping("/dashboard/updateProject/{project_id}")
	   @ResponseBody
	   public void postupdateProject(
	         ProjectDTO dto,
	         @PathVariable int project_id,
	         HttpSession session,
	         HttpServletResponse res) throws Exception {

	      
	      dto.setProject_id(project_id);
	      service.updateProject(dto);
	      
	      res.sendRedirect("/dashboard/");
	   }
	    
	
	@GetMapping("/dashboard")
	public String getProjects(Model model, HttpSession session) throws Exception {
		int user_id = (int) session.getAttribute("user_id");
		
		
		List<ProjectVO> projectList = service.retrieve(user_id);
		model.addAttribute("user_id", user_id);
		model.addAttribute("projectList", projectList);
		
		return "dashboard";
	}
	
	@GetMapping("/dashboard/search")
	public ModelAndView searchProject(String searchKeyword, Model model, HttpSession session) throws Exception {
		int user_id = (int) session.getAttribute("user_id");
		
		if(searchKeyword.equals("") || searchKeyword == null) {
			List<ProjectVO> projectList = service.retrieve(user_id);
			model.addAttribute("projectList", projectList);
			
			return new ModelAndView("searchResult");
		} else {
			
			List<ProjectVO> projectList = service.search(searchKeyword, user_id);
			model.addAttribute("projectList", projectList);
			model.addAttribute("searchKeyword", searchKeyword);
			
			return new ModelAndView("searchResult");
		}
	}
	
	@ResponseBody
	   @PostMapping(value="/dashboard/userListInvitePost"
	               ,produces = "application/json; charset=utf8")
	   public int userListInvite(String name,int project_id, HttpSession session) {
	      
	      int value = 0;
	      
	      try {
	         
	         int user_id =service.userInviteId(name);
	         
	         if(user_id == 0) {
	            //에초에 0은 없다 
	            return 0;
	         }
	         
	         try {

	            List<WorkDTO> userWork = service.userWorkListMamber(user_id, project_id);
	            
	            if(userWork.size() > 0){
	               //1이상 이면 이미 테이블 안에 존재함 
	               return 3;
	            }
	            
	         }catch(Exception e) {
	            e.printStackTrace();
	         } // null 이면 가능 은 해서 아무처리 없음 
	         
	         try {
	            List userInvite = service.userInviteSelect(user_id,project_id);
	            
	            if(userInvite.size() >= 1) {
	               System.out.println("vt.size(): "+userInvite.size());
	               return 2;
	            }
	            
	            
	         }catch(Exception e) {
	            e.printStackTrace();
	            
	         }
	         int send_id = (int) session.getAttribute("user_id");
	         String send_name = service.selectName(send_id);
	         
	         value =service.userInvite(send_name,user_id, project_id);
	         
	         return value;
	      } catch (Exception e) {
	      }
	      
	      return value;
	   }
	
	  @PostMapping("/dashboard/delproject")
	   public void delProject(int project_id, ProjectDTO dto, HttpSession session, HttpServletResponse res) throws Exception{
//	      
//	      int user_id = (int) session.getAttribute("user_id");
	      service.delProject(project_id);
	      
	      res.sendRedirect("/dashboard");
	   }

	   @ResponseBody
	   @PostMapping(value="/dashboard/userselect",produces = "application/text; charset=utf8")
	   public String userSelect(String query,int project_id,Model model, HttpServletRequest req) {
	      String value= query;
	      try {
	         
	         if(!(value.equals("") || value == null)) {
	            
	            List<UserDTO> userList =  service.SelectEmailAndNickname(value);
	            
	            if(userList.get(0).getUser_nickname() != null) {
	               String name = userList.get(0).getUser_nickname();
	               
	               try {
	                  int user_id =service.userInviteId(name);
	                  
	                  List<WorkDTO> userWork = service.userWorkListMamber(user_id, project_id);
	                  
	                  try {
	                     
	                     List<InviteDTO> userinvite =service.userInviteSelect(user_id, project_id); 
	                     try {
	                        int projectUser = service.projectIdSelect(user_id, project_id);
	                        
	                        if( projectUser > 0) {
	                           return name +'@';
	                        }
	                     }catch(Exception e) {
	                        e.printStackTrace();
	                     }
	                     
	                     if(userinvite.get(0).getReserve_id() > 0) {
	                        if(userWork.size() > 0) {
	                           return name + '?';
	                        }
	                        return name + '!';
	                     }
	                     
	                     
	                  }catch(Exception e) {;;}
	                  
	                  if(userWork.size() > 0){
	                     
	                     return name + '?';
	                  }
	                  
	               }catch(Exception e) {
	                  e.printStackTrace();
	               } 
	               
	               return name;               
	            }
	            
	         }
	         
	      }catch(Exception e) {;;}
	      
	      return null;
	   }
	   
	
}
