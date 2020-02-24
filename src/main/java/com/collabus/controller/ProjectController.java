package com.collabus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;
import com.collabus.model.UserVO;
import com.collabus.service.ProjectService;
import com.collabus.service.TaskService;

@Controller
public class ProjectController {

   @Inject
   private TaskService taskService;
   
   @Inject
   private ProjectService projectService;
   
//   @GetMapping("/project/{project_id}/addtask")
//   public UserVO selectProjectMembers(
//            @PathVariable int project_id,
//            HttpSession session,
//            HttpServletResponse res) throws Exception {
//      UserVO vo = (UserVO) projectService.selectProjectMembers(project_id);
//      
//      return vo;
//   }
         
   
   @PostMapping("/project/addtask")
   public void addTask(TaskDTO dto, @RequestParam("user_nickname") int user_nickname, HttpServletResponse res, int project_id) throws Exception {
      System.out.println("addtask invoked-------------------");
      System.out.println(user_nickname);
      if (user_nickname == 0) {
         dto.setTask_project(project_id);
         taskService.unAssignedRegist(dto);
      } else {
         dto.setTask_project(project_id);
         dto.setTask_user(user_nickname);
         System.out.println(dto.getTask_user());
         taskService.assignedRegist(dto);
      }
      
      projectService.projectPercomplete(project_id);
      
      res.sendRedirect("/project/" + project_id);
   }
   
   @GetMapping("/project/updatetask/{task_id}")
   @ResponseBody
   public TaskVO getUpdateTask(
         @PathVariable int task_id,
         HttpSession session,
         HttpServletResponse res
         ) throws Exception {
      
      
      TaskVO dto = (TaskVO)taskService.retrieveTask(task_id);
      
      return dto;
   }
   
   @PostMapping("/project/updatetask/{task_id}")
   @ResponseBody
   public void postUpdateTask(
         TaskDTO dto,
         @PathVariable int task_id,
         @RequestParam("user_nickname") int user_nickname,
         HttpSession session,
         HttpServletResponse res) throws Exception {

      if (user_nickname == 0) {
         taskService.unAssignedUpdateTask(dto);
      } else {
         dto.setTask_user(user_nickname);
         taskService.assignedUpdateTask(dto);
      }
      
      
      TaskVO vo = taskService.retrieveTask(dto.getTask_id());
      int project_id = vo.getTask_project();
      System.out.println("project_id----------------"+project_id);
      res.sendRedirect("/project/" + project_id);

   }
   
   @PostMapping("/project/deletetask/{task_id}")
   @ResponseBody
   public void deleteTask(
         @PathVariable int task_id,
         HttpSession session,
         HttpServletResponse res) throws Exception {

      TaskVO vo = taskService.retrieveTask(task_id);
      int project_id = vo.getTask_project();

      taskService.deleteTask(task_id);
      projectService.projectPercomplete(project_id);
      res.sendRedirect("/project/" + project_id);

   }

   @GetMapping("/project/{project_id}")
   public String getTasks(@PathVariable int project_id, Model model, HttpSession session) throws Exception {
      
      int user_id = (int) session.getAttribute("user_id");
      
      System.out.println("getTasks invoked-----------------");
      List<UserVO> projectMembers = projectService.selectProjectMembers(project_id);
      model.addAttribute("projectMembers", projectMembers);
      List<TaskVO> taskList = taskService.listTasks(project_id, user_id);
      List<Integer> user_idList = new ArrayList<>();
      List<String> user_nicknameList = new ArrayList<>();
      for(TaskVO vo : taskList) {
         if(!user_idList.contains(vo.getTask_user())) {
            user_idList.add(vo.getTask_user());
            user_nicknameList.add(vo.getTask_user_nickname());
         }
      }
      
      int project_leader = projectService.getProject(project_id).getProject_leader();
      
      
      projectService.projectPercomplete(project_id);
      model.addAttribute("project_id", project_id);
      model.addAttribute("project_leader", project_leader);
      model.addAttribute("taskList", taskList);
      model.addAttribute("user_idList", user_idList);
      model.addAttribute("user_nicknameList", user_nicknameList);
      
      return "project";
   }
   
}