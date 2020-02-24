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
  		int user_id = (int) session.getAttribute("user_id");
  		for(ProjectVO vo : projectList) {
  	%>
	    <div id="projects" class="project">
	      <div class="project_title" >
	      	<div class="project-name">
	      		<h1><%= vo.getProject_name() %></h1>	
	      	</div>
	        
	        <%if(vo.getProject_leader() == user_id){ %>
			<form class="del-project" action="/dashboard/delproject?project_id=<%=vo.getProject_id()%>" method="POST">
              <button class="delete-project" onclick="alert('정말로 삭제하시겠습니까?')">
                 <i class="fas fa-trash-alt"></i>
              </button>
           </form>
           
           <div class="update-project" data-id="<%= vo.getProject_id()%>" onclick="document.getElementById('update_project').style.display='block'">
              <i class="fas fa-edit"></i>
           </div>
	        
	        <%} %>
	        
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
	        <div class="progress-bar" style="width:<%=vo.getProject_percomplete()%>%"></div>
	      </div>
	      <div class="project-buttons">
		  	<button type="submit" value="<%=vo.getProject_id()%>" class="add_project_user_btn" 
		  	  onclick="document.getElementById('add_user_project').style.display='block'">회원 초대</button>

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
              <input type="text" id="add_project_txtDate_start" class="add_project_txtDate" name="project_startdate" required disabled>
            </div>
            <div id="project_date">
              <b>마감 날짜</b>
              <input type="text" id="add_project_txtDate_end" class="add_project_txtDate" name="project_enddate" required disabled>
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

        <form class="update_project_modal-content animate"  method="POST">
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
            <input type="text" id="update-project-name" placeholder="이름을 입력해주세요" name="project_name" required>

            <p><b>프로젝트설명</b></p>
            <input type="text" id="update-project-description" placeholder="내용을 입력해주세요" name="project_description" required>
            <div id="project_date">
              <b>시작 날짜</b>
              <input type="text" id="update_project_txtDate_start"  class="update_project_txtDate" name="project_startdate" disabled>
            </div>
            <div id="project_date">
              <b>마감 날짜</b>
              <input type="text" id="update_project_txtDate_end"  class="update_project_txtDate" name="project_enddate" disabled>
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

            <div class="add_user_project_modal-content animate">
               <div class="add_user_project_title">
                  <h1 class="form-logo">
                     <span class="text-primary">C</span>ollab <span
                        class="text-primary">U</span>s
                  </h1>
                  <span
                     onclick="document.getElementById('add_user_project').style.display='none'"
                     class="add_project_close" title="취소">×</span>
               </div>

               <div class="add_user_project_body">

                  <p>
                     <b>회원 검색</b>
                  </p>
                  <input id="userSelect" type="text" placeholder="회원 이름을 입력해주세요"
                     required>
               </div>
               <div class="userList"></div>

            </div>
         </div>
         <!-- 팀원 초대 modal 창-->
    </div>
  </section>

</body>
<script>
var nickPattern = RegExp(/^[a-zA-Z0-9-ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}$/);

var userList = new Array();
var listLength =0;
var isList=true;

var projectBtnId=0;

$('.add_project_user_btn').click(function(e){
   if(!$(e.target).hasClass('#add_user_project')){
      $('.userList').empty();
      userList = new Array();
      listLength=0;
      isList=true;
      projectBtnId=$(this).val();
   }
});

$(document).on("click","#userListButton",function(){
   
   
   
   
   var userName = $(this).val();
   var listBtn=false;
   console.log("button 값은 무엇일까 : "+name);/*좋아 버튼 마다 이름은 찾았다.  */
   console.log("id: "+projectBtnId);
   var zy = $(this);
   
   var query ={ name : userName , project_id : projectBtnId };
     $.ajax({
      url :"/dashboard/userListInvitePost",
      type : "post",
      data : query ,
      success : function(data){
         console.log("data::::::::::"+data);
         
         if(data ==2){
            alert("이미 초대 가 되었습니다.");
            console.log("이미초대 : "+listBtn);
            $(zy).html("초대 중");
         }else if(data ==1){ 
            alert("초대하기 성공 ");
            console.log("초대하기 : "+listBtn);
            $(zy).html("초대 중");
         }else if(data ==0 ){ 
            alert("프로젝트 와 상대방이 있는지 다시한번 확인해 주세요.");
         }else if(data == 3){  
            alert("초대를 받은 유저 입니다.");
            $(zy).html("초대 받음");
         }else{
            alert("알수없는 오류로 초대를 하지 못합니다.");
         }

           console.log("listBTN1 : "+listBtn);
         
      }
   }); 
     

     console.log("listBTN2 : "+listBtn);
 //   $('button[value='+name+']').html("전송");
 
});


   $("#userSelect").keydown(function(key) {
       if (key.keyCode == 13) {
          
   var query ={  query : $("#userSelect").val() ,project_id : projectBtnId };
   
   console.log("입력할떄 input "+query);
   
   
    $.ajax({
       
          url :"/dashboard/userselect",
           type :"post",
           data : query,
           contentType: "application/x-www-form-urlencoded; charset=UTF-8",
           success : function(data){
              
              console.log("data == ::: "+ data);
              
              if(data != '' ){
                 
                 isList=true;
                 
                 console.log("data: "+data);
                 var vite = "초대 하기";
                 var dataTemp="";
                 for(var i=0;i<data.length;i++){
                    
                    if(data.charAt(i) == '?' ){
                       isList =false;
                       alert("이미 참여한 유저입니다.");
                    }else if(data.charAt(i) == '!'){
                       vite ="초대 중";
                    }else if(data.charAt(i) == '@'){
                       isList=false;
                       alert("프로젝트 생성자는 초대할수 없습니다.");
                    }else{
                       dataTemp+=data.charAt(i);
                    }
                 }
                 
                 data=dataTemp;
                 if(isList == true){
                    for(var i=0;i<listLength;i++){
                       if(userList[i] == data ){
                          isList=false;
                       }
                    } //for end 
                 }

              
              
                 if(isList == true){
                    var select = $(
                         "<div class='search-user-list'>"+
                          " <div class='user-name'>"+ data +
                          " </div>"+
                         " <div class='before-invite'>"+
                         " <button id='userListButton' type='button' class='before-invite-button' value="+data+">"+
                          vite +
                          " </button>"+
                          " </div>"+
                        "</div>"+
                        " <div class='clear' style='clear:both;'></div>"+ 
                         " <div class='project-bottom-line'></div>   ");
   
                        $(select).appendTo('.userList'); 
                        console.log("button.value() : "+ $('.before-invite-button').val());
                    
                       userList[listLength++] = data;
                       
                  }//if end 
                  
              }else{
                 alert("검색된 회원이 없습니다.");
              }// if -else end 
           }//success : function()  end 
           
     });//ajax end
      $('.user-name').text();

       }
   });

</script>
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
  
  $(document).on("click", ".update-project", function () { 
	   var project_id = $(this).data("id");
	   console.log(project_id);
	   console.log("update-project invokded");
	   
	   $.ajax({
	       type: 'GET',
	       url: '/dashboard/updateProject/'+project_id,
	       data: {
	          project_id : project_id
	       },
	       
	       success: function(resp) {
	          console.log('success');
	          console.log(resp);
	          var project_name = resp.project_name;
	          var project_description = resp.project_description;
	          var project_startdate = new Date(resp.project_startdate);
	          var project_enddate = new Date(resp.project_enddate);
	          console.log(project_startdate.getMonth());
	          
	          var startYear = project_startdate.getFullYear();
	          var startMonth =  project_startdate.getMonth()+ 1;
	          var startDate = project_startdate.getDate();
	          
	          var endYear = project_enddate.getFullYear();
	          var endMonth =  project_enddate.getMonth()+ 1;
	          var endDate = project_enddate.getDate();
	          
	          project_startdate = startYear + '-' + startMonth + '-' + startDate;
	          project_enddate = endYear + '-' + endMonth + '-' + endDate;
	          
	          var url = "/dashboard/updateProject/" + resp.project_id;
	          
	         $(".update_project_modal-content").attr('action', url);
	          $("#update-project-name").attr('value', project_name);
	          $("#update-project-description").attr('value', project_description);
	          $("#update_project_txtDate_start").attr('value', project_startdate);
	          $("#update_project_txtDate_end").attr('value', project_enddate);
	          
	       }
	    })
	   
	   
	})   

   

</script>


<script type="text/javascript" src="/resources/js/userCircle.js"></script>
</html>