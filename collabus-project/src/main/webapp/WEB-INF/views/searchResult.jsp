<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List, com.collabus.model.ProjectVO"
%>
	<% 
		String searchKeyword = (String) request.getAttribute("searchKeyword");
		if(searchKeyword != null) {
	%>
		<h3 class="search-result"><%=searchKeyword %> 검색결과</h3>
	<% } %>
 	<%
  		List<ProjectVO> projectList = (List<ProjectVO>) request.getAttribute("projectList");
  		for(ProjectVO vo : projectList) {
  	%>
	    <div id="projects" class="project" onclick="location.href = '/project?project_id=' + <%= vo.getProject_id() %>;" style="cursor: pointer;">
	      <div class="project_title" >
	        <h1><%= vo.getProject_name() %></h1>
	        <p><%= vo.getProject_description() %></p>
	      </div>
	      <div class="progress-bar-container">
	        <div class="progress-bar" style="width:15%"></div>
	      </div>
	    </div>
    <% } %>