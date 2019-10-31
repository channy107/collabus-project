<%@ page 
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, com.collabus.model.ProjectVO"
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <link rel="stylesheet" href="/resources/css/projects-style.css" />
  <link rel="stylesheet" href="/resources/css/userDropmenu.css" />
  <link href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet" />

  <!-- modal Popup JQuery-->
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

  <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

  <script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ko.js"></script>
  <!-- modal Popup JQuery-->

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">


  <title>Collabus</title>
</head>

<body id="home">
  <!-- Navbar -->
  <nav id="navbar">
    <a href="/" class="logo-link">	
      <h1 class="logo">
        <span class="text-primary">C</span>ollab
        <span class="text-primary">U</span>s
      </h1>
    </a>

    <div class="search">
      <span class="icon"><i class="fa fa-search"></i></span>
      <input type="search" id="search" placeholder="Search..." />
    </div>
    <div class="logined-user">
    	
    </div>
    <div >
	    <i id="user-circle" class="fas fa-user-circle"></i>
	    
		<ul class="dropmenu">
		    <li> <a id="mypage"> 마이페이지</a> </li>
		  	<li> <a id="logout"> 로그아웃 </a> </li>
		</ul>
		
	</div>
  </nav>
  <!-- Projects -->
  <section id="projects_main">
  	<%
  		List<ProjectVO> projectList = (List<ProjectVO>) request.getAttribute("projectList");
  		for(ProjectVO vo : projectList) {
  	%>
	    <div id="projects" class="project">
	      <div class="project_title" >
	      	<div class="project-name">
	      		<h1><%= vo.getProject_name() %></h1>	
	      	</div>
	        
	        
	        <div class="delete-project">
	        	<i class="fas fa-trash-alt"></i>
	        </div>
	        
	        
	        <div class="update-project" onclick="document.getElementById('add_project').style.display='block'">
	        	<i class="fas fa-edit"></i>
	        </div>
	        
	        
	        
	      </div>
	      <div class="clear"></div>
	      <div class="project_description">
	      	<%
	      		if (vo.getProject_description().length()> 15) {
	      	%>
	      		<p><%= vo.getProject_description().substring(0, 15) %> ...</p>
	      	<%
	      		} else {
	      	%>
	      		<p><%= vo.getProject_description() %> </p>
	      	<%
	      		}
	      	%>
	      </div>
	      <div class="progress-bar-container">
	        <div class="progress-bar" style="width:15%"></div>
	      </div>
	      <div class="project-buttons">
		  	<button type="submit" class="add_project_user_btn" onclick="document.getElementById('add_user_project').style.display='block'">회원 초대</button>

	      	<button type="button" class="detail_project_btn" onclick="location.href = '/project/' + <%= vo.getProject_id() %>;" >상세보기</button>
	   	  </div>	

	      

	      
	   
	</div>
    <% } %>
    <div id="projects">
      <button class="add_project_button" onclick="document.getElementById('add_project').style.display='block'">
        <div class="enroll">
          <i class="material-icons" id="enroll">
            add
          </i>
          <h1>프로젝트 생성</h1>
        </div>
      </button>
      
      <!-- 프로젝트 생성 modal 창-->
      <div id="add_project" class="add_project_modal">

        <form class="add_project_modal-content animate" action="/dashboard/addproject" method="POST">
          <div class="add_project_title">
            <h1 class="form-logo">
              <span class="text-primary">C</span>ollab
              <span class="text-primary">U</span>s
            </h1>
            <span onclick="document.getElementById('add_project').style.display='none'" class="add_project_close"
              title="취소">×</span>
          </div>
          <div class="add_project_body">
            <p><b>프로젝트이름</b></p>
            <input type="text" placeholder="이름을 입력해주세요" name="project_name" required>

            <p><b>프로젝트설명</b></p>
            <input type="text" placeholder="내용을 입력해주세요" name="project_description" required>
            <div id="project_date">
              <b>시작 날짜</b>
              <input type="text" id="add_project_txtDate_start" class="add_project_txtDate" name="project_startdate">
            </div>
            <div id="project_date">
              <b>마감 날짜</b>
              <input type="text" id="add_project_txtDate_end" class="add_project_txtDate" name="project_enddate">
            </div>

            <div class="add_project_clearfix">
              <button type="submit" class="add_project_signupbtn">등록</button>
              <button type="button" onclick="document.getElementById('add_project').style.display='none'"
                class="add_project_cancelbtn">취소</button>

            </div>
          </div>
        </form>
      </div>
      <!-- 프로젝트 생성 modal 창-->
      
      <!-- 프로젝트 수정 modal 창-->
      <div id="update_project" class="update_project_modal">

        <form class="update_project_modal-content animate" action="/dashboard/updateproject" method="POST">
          <div class="update_project_title">
            <h1 class="form-logo">
              <span class="text-primary">C</span>ollab
              <span class="text-primary">U</span>s
            </h1>
            <span onclick="document.getElementById('update_project').style.display='none'" class="add_project_close"
              title="취소">×</span>
          </div>
          <div class="update_project_body">
            <p><b>프로젝트이름</b></p>
            <input type="text" placeholder="이름을 입력해주세요" name="project_name" required>

            <p><b>프로젝트설명</b></p>
            <input type="text" placeholder="내용을 입력해주세요" name="project_description" required>
            <div id="project_date">
              <b>시작 날짜</b>
              <input type="text" id="update_project_txtDate_start" class="update_project_txtDate" name="project_startdate">
            </div>
            <div id="project_date">
              <b>마감 날짜</b>
              <input type="text" id="update_project_txtDate_end" class="update_project_txtDate" name="project_enddate">
            </div>

            <div class="update_project_clearfix">
              <button type="submit" class="update_project_signupbtn">등록</button>
              <button type="button" onclick="document.getElementById('update_project').style.display='none'"
                class="update_project_cancelbtn">취소</button>

            </div>
          </div>
        </form>
      </div>
      <!-- 프로젝트 수정 modal 창-->
      
      <!-- 팀원 초대 modal 창-->
      <div id="add_user_project" class="add_user_project_modal">

        <form class="add_user_project_modal-content animate" method="POST">
          <div class="add_user_project_title">
            <h1 class="form-logo">
              <span class="text-primary">C</span>ollab
              <span class="text-primary">U</span>s
            </h1>
            <span onclick="document.getElementById('add_user_project').style.display='none'" class="add_project_close"
              title="취소">×</span>
          </div>
          <div class="add_user_project_body">
          	
            <p><b>회원 검색</b></p>
            <input type="text" placeholder="회원 이름을 입력해주세요" name="project_name" required>
		  </div>
  		  <div class="search-user-list">
  			<div class="user-name">
  				헌찬
  			</div>
  			<div class="before-invite">
  				<button type="button" class="before-invite-button">
  				초대 전
  				</button>
  			</div>
 		  </div>
 		  
 		  <div class="clear" style="clear:both;"></div>
 		  <div class="project-bottom-line"></div>
 		  <div class="search-user-list">
  			<div class="user-name">
  				창환
  			</div>
  			<div class="before-invite">
  				<button type="button" class="before-invite-button">
  				초대 전
  				</button>
  			</div>
 		  </div>
 		  
 		  <div class="clear" style="clear:both;"></div>
 		  <div class="project-bottom-line"></div>
 		  <div class="search-user-list">
  			<div class="user-name">
  				성범
  			</div>
  			<div class="before-invite">
  				<button type="button" class="before-invite-button">
  				초대 전
  				</button>
  			</div>
 		  </div>
  		  <div class="clear" style="clear:both;"></div>
  		  <div class="project-bottom-line"></div>

        </form>
      </div>
      <!-- 팀원 초대 modal 창-->
    </div>
  </section>

</body>
<script>
  var add_project_modal = document.getElementById('add_project');

  window.onclick = function (event) {
    if (event.target == add_project_modal) {
      add_project_modal.style.display = "none";
    }
  }
  $(document).ready(function () {

    $(".add_project_txtDate").datepicker({
      showOn: "button",
      buttonImage: "/resources/img/icon_date.gif",
      buttonImageOnly: true
    });
    
    $("img.ui-datepicker-trigger").attr("style", "margin-left:5px; vertical-align:middle; cursor:pointer;");
    
    $("#search").keydown(function (e) { 
        if (e.keyCode === 13) {
        	e.preventDefault();
        
        	$.ajax({
        		type: "GET",
        		url: "/dashboard/search",
        		data: {
        			searchKeyword: this.value
        		},
        		success(res) {
        			$(".project").remove();
        			$(".search-result").remove();
        			$("#projects_main").prepend(res);
        		}
        	});
        } // if
        
      });
    });	  

</script>

<script>
  var update_project_modal = document.getElementById('update_project');

  window.onclick = function (event) {
    if (event.target == update_project_modal) {
    	update_project_modal.style.display = "none";
    }
  }
  $(document).ready(function () {

    $(".update_project_txtDate").datepicker({
      showOn: "button",
      buttonImage: "/resources/img/icon_date.gif",
      buttonImageOnly: true
    });
    
    $("img.ui-datepicker-trigger").attr("style", "margin-left:5px; vertical-align:middle; cursor:pointer;");
    
    
    });	  

</script>

<script>
  var add_user_project_modal = document.getElementById('add_user_project');

  window.onclick = function (event) {
    if (event.target == add_user_project_modal) {
    	add_user_project_modal.style.display = "none";
    }
  }
  $(document).ready(function () {

    $(".add_user_project_txtDate").datepicker({
      showOn: "button",
      buttonImage: "/resources/img/icon_date.gif",
      buttonImageOnly: true
    });
    
    $("img.ui-datepicker-trigger").attr("style", "margin-left:5px; vertical-align:middle; cursor:pointer;");
    
    
    });	  

</script>


<script type="text/javascript" src="/resources/js/userCircle.js"></script>
</html>