package com.collabus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;
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
	
	@GetMapping("/dashboard")
	public String getProjects(Model model, HttpSession session) throws Exception {
		int user_id = (int) session.getAttribute("user_id");
		
		
		List<ProjectVO> projectList = service.retrieve(user_id);
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
	
}
