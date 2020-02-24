package com.collabus.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.collabus.model.CheckDTO;
import com.collabus.model.ProjectVO;
import com.collabus.model.ReplyDTO;
import com.collabus.model.ReplyVO;
import com.collabus.model.SubtaskDTO;
import com.collabus.model.TaskDTO;
import com.collabus.model.TaskVO;
import com.collabus.model.UserVO;
import com.collabus.service.CheckService;
import com.collabus.service.ProjectService;
import com.collabus.service.ReplyService;
import com.collabus.service.SubtaskService;
import com.collabus.service.TaskService;
import com.collabus.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TaskController {
	
	@Inject
	private ReplyService replyService;
	
	@Inject
	private SubtaskService subtaskService;
	
	@Inject
	private ProjectService projectService;
	
	@Inject
	private TaskService taskService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private CheckService checkService;

	@PostMapping("/project/{project_id}/task/{task_id}/addsubtask")
	public void addSubtask(@PathVariable int project_id, @PathVariable int task_id,
		SubtaskDTO dto, HttpSession session, HttpServletResponse res) throws Exception {


//	    if(task_id != null && !task_id.equals("")) {
//	       dto.setSubtask_task(Integer.parseInt(task_id));         
//	    } else {
//	       dto.setSubtask_task(5);
//	    }
		
		dto.setSubtask_task(task_id);
	    subtaskService.addSubtask(dto);
	    taskService.taskPercomplete(task_id);  
	    res.sendRedirect("/project/"+project_id+"/task/"+task_id);
	 }

	
    @PostMapping("/project/{project_id}/task/{task_id}/updateSubtask/{subtask_id}")
    public void updateSubtask(@PathVariable int project_id,@PathVariable int task_id,@PathVariable int subtask_id, SubtaskDTO dto,  HttpSession session, HttpServletResponse res) throws Exception {
//       int subtask_task = (int) session.getAttribute("task_id");

         
//       if(task_id != null && !task_id.equals("")) {
//          dto.setSubtask_task(Integer.parseInt(task_id));         
//       } else {
//          dto.setSubtask_task(5);
//       }
       dto.setSubtask_task(task_id);
       dto.setSubtask_id(subtask_id);
       subtaskService.updateSubtask(dto);
         
       res.sendRedirect("/project/"+project_id+"/task/"+task_id);
      } 
	   
	 @PostMapping("/project/{project_id}/task/{task_id}/delsubtask/{subtask_id}")
	 public void delSubtask(@PathVariable int project_id ,@PathVariable int task_id, @PathVariable int subtask_id, HttpSession session, HttpServletResponse res) throws Exception {
//	    String task_id = (String) session.getAttribute("task_id");
	      
//	    if(task_id != null && !task_id.equals("")) {
//	       dto.setSubtask_task(Integer.parseInt(task_id));         
//	    } else {
//	       dto.setSubtask_task(5);
//	    }
	    subtaskService.delSubtask(subtask_id);
	    taskService.taskPercomplete(task_id);
	    res.sendRedirect("/project/"+project_id+"/task/"+task_id);
	 }
	 
	 @PostMapping("/project/{project_id}/task/{task_id}/checkSubtask/{subtask_id}")
	    public void checkSubtask(@PathVariable int project_id ,@PathVariable int task_id, @PathVariable int subtask_id, SubtaskDTO dto, HttpSession session, HttpServletResponse res) throws Exception{
	       subtaskService.checkSubtask(dto);
	       taskService.taskPercomplete(task_id);
	        res.sendRedirect("/project/"+project_id+"/task/"+task_id);
	    }
	    
	    @PostMapping("/project/{project_id}/task/{task_id}/unCheckSubtask/{subtask_id}")
	    public void unCheckSubtask(@PathVariable int project_id ,@PathVariable int task_id, @PathVariable int subtask_id, SubtaskDTO dto, HttpSession session, HttpServletResponse res) throws Exception{
	       subtaskService.unCheckSubtask(dto);
	       taskService.taskPercomplete(task_id);
	        res.sendRedirect("/project/"+project_id+"/task/"+task_id);
	    }
	 
	    @PostMapping("/task/upload")
	    public void upload(int task_id, SubtaskDTO dto,
	          int subtask_id, String subtask_name,
	            Model model, String name,
	            @RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletResponse res) throws Exception {
	    	
	    	int project_id = taskService.retrieveTask(task_id).getTask_project();
	    	 
	         List<String> successFiles = new ArrayList<>();
	         for(MultipartFile mfile : uploadFiles) {
	            
	            dto.setSubtask_name(subtask_name);
	            new File("C:/Collabus/"+project_id+"/"+task_id).mkdirs();
	            String uploadTempPath = "C:/Collabus/";
	            String uploadTargetPath = "C:/Collabus/"+project_id+"/"+task_id;
	            File file = new File(uploadTempPath, mfile.getOriginalFilename());
	            
	            dto.setSubtask_id(subtask_id);
	            dto.setSubtask_filepath(uploadTargetPath);
	            dto.setSubtask_filename(mfile.getOriginalFilename()); 
	            mfile.transferTo(file);
	            
	            file.renameTo(new File(
	                        uploadTargetPath,
	                        mfile.getOriginalFilename())
	                  );
	            successFiles.add(mfile.getOriginalFilename());
	            log.info("---------------------------------------------");
	            log.info(mfile.getOriginalFilename()); 
	            log.info(uploadTargetPath);
	            log.info("---------------------------------------------");
	         }//enhanced for 
	         subtaskService.upload(dto);
	         model.addAttribute("successFiles", successFiles);
	     
	       
	         res.sendRedirect("/project/"+project_id+"/task/"+task_id);
	      }//uploadFiles


	   
	    
	    @PostMapping("/task/download")
	       public void download(
	             SubtaskDTO dto, String subtask_filepath,
	             String subtask_filename,
	             HttpServletRequest request,HttpServletResponse response) throws IOException {

	         
	         byte fileByte[] = FileUtils.readFileToByteArray(new File(subtask_filepath+subtask_filename));
	         
	         response.setContentType("application/octet-stream");
	         response.setContentLength(fileByte.length);
	         response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(subtask_filename,"UTF-8")+"\";");
	         response.setHeader("Content-Transfer-Encoding", "binary");
	         response.getOutputStream().write(fileByte);
	         
	         response.getOutputStream().flush();
	         response.getOutputStream().close();

	       }


	@GetMapping("/project/{project_id}/task/{task_id}")
	public String task(@PathVariable int project_id, @PathVariable int task_id, Model model, HttpSession session) throws Exception {
		TaskVO taskVO = taskService.retrieveTask(task_id);
		model.addAttribute("taskVO", taskVO);
	    ProjectVO projectVO = projectService.getProject(project_id);
	    model.addAttribute("projectVO", projectVO);
		
		Object subtaskList = subtaskService.retrieve(task_id);
	    model.addAttribute("subtaskList", subtaskList);
		List<ReplyVO> replyList = replyService.retrieveReplies(task_id);
		model.addAttribute("replyList", replyList);
		
		int user_id = (int) session.getAttribute("user_id");
//		int project_id = taskVO.getTask_project();
		int project_members = taskService.getProjectMember(project_id);
		model.addAttribute("project_members", project_members);
		List<CheckDTO> checked_members = taskService.getCheckedMember(task_id);
		model.addAttribute("checked_members", checked_members);
		System.out.println("task_id============="+ task_id);
		List<CheckDTO> is_checked_user = taskService.isCheckedUser(task_id, user_id);
		model.addAttribute("is_checked_user", is_checked_user);
		System.out.println("_____________________________________prid:" + project_id);
		List<UserVO> userList = userService.retrieveUserNickname(project_id, user_id);
		Map<Integer, String> userNicknameMap = new HashMap<>();
		
		userList.forEach(user -> {
			userNicknameMap.put(user.getUser_id(), user.getUser_nickname());
		});
		model.addAttribute("user_id", user_id);
		model.addAttribute("userNicknameMap", userNicknameMap);
		
		return "task";
	}
	
	@PostMapping("/task/infinitescroll")
	@ResponseBody
	public List<ReplyDTO> infiniteScroll(
			ReplyDTO dto,
			Model model,
			HttpSession session
		) throws Exception {
		log.info("infinitescroll invoked");
		TaskVO taskVO = taskService.retrieveTask(dto.getReply_task());
		log.info("-----------------------reply_id : "+dto.getReply_id());
		log.info("-----------------------task_id : "+dto.getReply_task());
		List<ReplyDTO> replyList = replyService.infiniteScroll(dto);
		List<ReplyDTO> replyList2 = new ArrayList<ReplyDTO>();
		log.info("-----------------------replyList : "+replyList);
		
		for (ReplyDTO reply : replyList) {
		
			reply.setReply_user_name(userService.retrieveOneUserNickname(reply.getReply_user()));
			System.out.println(reply.getReply_user_name());
			replyList2.add(reply);
			System.out.println(replyList.toString());
		}
		
		model.addAttribute("replyList2", replyList2);
		
		
		return replyList2;
	}
	
	@PostMapping("/task/addreply")
	@ResponseBody
	public ReplyDTO addReply(
			ReplyDTO dto,
			HttpSession session,
			HttpServletResponse res) throws Exception {
		
		System.out.println("addReply invoked");
		System.out.println("dto reply_id: " + dto.getReply_user());
		
		
		System.out.println("dto reply_name: " + dto.getReply_user_name());
		replyService.addReply(dto);
		
		
		dto = replyService.getRecentlyReply();
		dto.setReply_user_name(userService.retrieveOneUserNickname(dto.getReply_user()));
		System.out.println(dto.getReply_id());
		System.out.println(dto.getReply_text());
		System.out.println(dto.getReply_user());
		System.out.println(dto.getReply_user_name());
		
		return dto;
	}
	
	
	
	
	
	@PostMapping("/task/check")
	@ResponseBody
	public void check(
			int project_id,
			CheckDTO dto,
			HttpSession session,
			HttpServletResponse res) throws Exception {
		
		if(checkService.checkedTU(dto) == 1) {
			checkService.Checked(dto);
		}else {
			checkService.InsertCheck(dto);
		}
		
		if(checkService.projectUser(project_id) == checkService.checkedConfirm(dto)) {
	         taskService.taskIsdone(dto.getConfirm_task());
	         projectService.projectPercomplete(project_id);
	    }else {
	    	taskService.taskIsnotdone(dto.getConfirm_task());
	         projectService.projectPercomplete(project_id);
	    }

	}
	
	@PostMapping("/task/uncheck")
	public void unCheck(
			int project_id,
			CheckDTO dto,
			HttpSession session,
			HttpServletResponse res) throws Exception {


		System.out.println("unchecked invokedd");
		System.out.println(dto.getConfirm_task());
		System.out.println(dto.getConfirm_user());
		checkService.unCheck(dto);
		
		if(checkService.countConfirm(dto) != checkService.checkedConfirm(dto)) {
	         taskService.taskIsnotdone(dto.getConfirm_task());;
	         System.out.println(checkService.countConfirm(dto));
	         projectService.projectPercomplete(project_id);
	      }

	}
	
	@PostMapping("/task/updatereply")
	@ResponseBody
	public ReplyDTO updateReply(
			ReplyDTO dto,
			HttpSession session,
			HttpServletResponse res) throws Exception {
		
		log.info("updateReply invoked");
		
		dto.setReply_user_name(userService.retrieveOneUserNickname(dto.getReply_id()));
		replyService.updateReply(dto);
		dto = replyService.getRecentlyReply();
		dto.setReply_user_name(userService.retrieveOneUserNickname(dto.getReply_user()));
		System.out.println(dto.getReply_id());
		System.out.println(dto.getReply_text());
		
		return dto;
	}

	
	@PostMapping("/task/deleteReply")
	 public void deleteReply(int reply_id, HttpSession session, HttpServletResponse res) throws Exception {

	    replyService.deleteReply(reply_id);

	 }

}
