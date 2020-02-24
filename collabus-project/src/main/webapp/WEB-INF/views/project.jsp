<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,java.lang.Math,java.util.Date,java.text.SimpleDateFormat, com.collabus.model.TaskVO"
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    
    <link rel="stylesheet" href="/resources/css/tasks.css" />
    
   <link rel="stylesheet" href="/resources/css/userDropmenu.css" />
    <link href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />
    <link rel="stylesheet" type="text/css" href="/resources/slick/slick-theme.css" />
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
    
    <title>프로젝트</title>
</head>
<nav id="navbar">
    <a href="/dashboard" class="logo-link">
        <h1 class="logo">
            <span class="text-primary">C</span>ollab
            <span class="text-primary">U</span>s
        </h1>
    </a>

    <div class="search">
            <span class="icon"><i class="fa fa-search"></i></span>
            <input type="search" id="search" placeholder="Search..." />
    </div>
<div >
	    <i id="user-circle" class="fas fa-user-circle"></i>
	    
		<ul class="dropmenu">
		    <li> <a id="mypage"> 마이페이지</a> </li>
		  	<li> <a id="logout"> 로그아웃 </a> </li>
		</ul>
		
	</div>
</nav>
<%	TaskVO taskVO = (TaskVO) request.getAttribute("taskVO"); %>
<section id="project">
    <div class="task-header">
        <div class="task-status">
            <div class="task-status-text">1주 전</div>
            <div class="task-status-warn"></div>
            <div class="task-status-text">한달 전</div>
            <div class="task-status-progress"></div>
            <div class="task-status-text">한달 이상</div>
            <div class="task-status-complete"></div>
        </div>
        <!--task-status-->
        <div class="clear"></div>
        <div class="enroll-container">
            <button class="add_task_button" onclick="document.getElementById('add-task').style.display='block'">
                <div class="enroll">
                    <i class="material-icons" id="enroll">add</i>
                </div>
            </button>
        </div>
        <!--enroll-container-->
        <div id="add-task" class="add-task-modal">
            <form class="add-task-modal-content animate" action=<%= "/project/addtask?project_id=" + request.getAttribute("project_id") %> method="POST">
                <div class="add-task-title">
                    <h1 class="form-logo">
                        <span class="text-primary">C</span>ollab
                        <span class="text-primary">U</span>s
                    </h1>
                    <span onclick="document.getElementById('add-task').style.display='none'" class="add-task-close" title="취소">×</span>
                </div>
                <div class="form-wrap">
                    <div class="add-task-body">
                        <label for="task_name">업무명</label>
                        <input type="text" name="task_name" placeholder="업무명을 입력해주세요"required>
                    </div>
                    <div class="add-task-body">
                        <label for="task_description">업무 설명</label>
                        <input type="text" placeholder="내용을 입력해주세요" name="task_description" required>
                    </div>
                    <div class="add-task-body">
                        <div id="task-date">
                            <label for="add-task-txtDate-start">시작 일</label>
                            <input type="text" id="add-task-txtDate-start" class="add-task-txtDate" name="task_startdate" required>
                        </div>
                        <div id="task-date">
                            <label for="add-task-txtDate-end">마감 일</label>
                            <input type="text" id="add-task-txtDate-end" class="add-task-txtDate" name="task_enddate" required>
                        </div>
                    </div>  

                    <div id="task-importance">
                        <div class="add-task-importance">중요도</div>
                        <div class="add-task-importance-radio-container">
                            <input type="radio" name="task_importance" class="task-importance-radio" value="1" required> 보통
                            <input type="radio" name="task_importance" class="task-importance-radio" value="2"> 약간중요
                            <input type="radio" name="task_importance" class="task-importance-radio" value="3"> 중요
                            <input type="radio" name="task_importance" class="task-importance-radio" value="4"> 매우중요
                        </div>
                    </div>
                    <div class="add-task-clearfix">
                        <button type="submit" class="add-task-signupbtn">등록</button>
                        <button type="button" onclick="document.getElementById('add-task').style.display='none'"
                        class="add-task-cancelbtn">취소</button>
                    </div>
                </div>
            </form>
        </div>
        
        <!-- update-task-modal -->
		<div id="update-task" class="update-task-modal">
            <form class="update-task-modal-content animate" method="POST" >
                <div class="update-task-title">
                    <h1 class="form-logo">
                        <span class="text-primary">C</span>ollab
                        <span class="text-primary">U</span>s
                    </h1>
                    <span onclick="document.getElementById('update-task').style.display='none'" class="update-task-close" title="취소">×</span>
                </div>
                <div class="form-wrap">
                    <div class="update-task-body">
                        <label for="task_name">업무명</label>
                        <input type="text" id="update-task-name" name="task_name" placeholder="업무명을 입력해주세요"required>
                    </div>
                    <div class="update-task-body">
                        <label for="task_description">업무 설명</label>
                        <input type="text" id="update-task-description" placeholder="내용을 입력해주세요" name="task_description" required>
                    </div>
                    <div class="update-task-body">
                        <div id="task-date">
                            <label for="update-task-txtDate-start">시작 일</label>
                            <input type="text" id="update-task-txtDate-start" class="update-task-txtDate" name="task_startdate"  required>
                        </div>
                        <div id="task-date">
                            <label for="update-task-txtDate-end">마감 일</label>
                            <input type="text" id="update-task-txtDate-end" class="update-task-txtDate" name="task_duedate"  required>
                        </div>
                    </div>  

                    <div id="task-importance">
                        <div class="update-task-importance">중요도</div>
                        <div class="update-task-importance-radio-container">
                            <input type="radio" name="task_importance" class="task-importance-radio" value="1" required> 보통
                            <input type="radio" name="task_importance" class="task-importance-radio" value="2"> 약간중요
                            <input type="radio" name="task_importance" class="task-importance-radio" value="3"> 중요
                            <input type="radio" name="task_importance" class="task-importance-radio" value="4"> 매우중요
                        </div>
                    </div>
                    <div class="update-task-clearfix">
                        <button type="submit" class="update-task-signupbtn">등록</button>
                        <button type="button" onclick="document.getElementById('update-task').style.display='none'"
                        class="update-task-cancelbtn">취소</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- update-task-modal -->

        <div class="clear"></div>
    <!--task-header-->
    
    <%
        List<TaskVO> taskList = (List<TaskVO>) request.getAttribute("taskList");
        List<Integer> user_idList = (List<Integer>) request.getAttribute("user_idList");
        List<Integer> user_nicknameList = (List<Integer>) request.getAttribute("user_nicknameList");
        
        for (int i = 0; i < user_idList.size(); i ++) {
        	
    %>
	    <div class="task-container">
	        <div class="task-wrapper">
	            <h2 class="task-name"> <%= user_idList.get(i) == null ? "담당자 미정 업무" : user_nicknameList.get(i) + "님의 업무"%></h2>
	            
	            <div class="task-selectbox">
	                <select id="select">
	                    <option selected>마감기한 순</option>
	                    <option>미 등록</option>
	                    <option>등록 순</option>
	                </select>
	            </div>
	        </div>
	        <div class="clear"></div>
	        <div class="bottom-line"></div>
            <div class="task-content-wrapper">
    <%
        for(TaskVO vo : taskList) {
        	
            if(user_idList.get(i) == vo.getTask_user()) {
    %>
                <div class="task-content" id="task-content-wrap" data-id="<%=vo.getTask_id()%>" data-toggle="front">
                    <div class="task" id="task-click-<%=vo.getTask_id()%>" data-id="<%=vo.getTask_id()%>">
                        <div class="face front">
                        	<div class="task-header">
	                            <div class="task-deadline" data-duedate="<%= (vo.getTask_enddate()).getTime() %>">
	                            </div>
	                            
	                            <div class="task-delete" data-id="<%= vo.getTask_id() %>">
	                            	<i class="fas fa-trash-alt"></i>
	                            </div>
	                            
	                            
		                            <div class="task-update" data-id="<%= vo.getTask_id() %>">
		                            	<button class="update_task_button" onclick="document.getElementById('update-task').style.display='block'">
		                            		<i class="fas fa-edit"></i>
		                            	</button>
		                            </div>
	                            
                            </div>
                            <div class="clear"></div>
                            
                            <h2><%= vo.getTask_name() %></h2>
                            <div class="task-bottom-line"></div>
                            <p class="task-description">
                            <%
                            	if( vo.getTask_description().length() > 20) {
                            		
                            %>
                            	<%= vo.getTask_description().substring(0,20)+ "..." %>
                            <%		
                            	} else {
                            %>
                            	<%= vo.getTask_description() %>
                            <%		
                            	}
                            %>
                                
                            </p>
                            <div class="progress-bar-container">
                                <div class="progress-bar" style="width:<%= vo.getTask_percomplete() * 100 %>"></div>
                            </div>
                            <i class="fas fa-sync-alt rotate" data-id="<%=vo.getTask_id()%>" data-toggle="front"></i>
                        </div>
                        <div class="face back">
                            <canvas id="myChart" width="300" height="300"></canvas>
                            <a href="/project/<%= vo.getTask_project() %>/task/<%= vo.getTask_id() %>" class="btn">상세보기</a>
                            <i class="fas fa-sync-alt rotate" data-id="<%=vo.getTask_id()%>" data-toggle="back"></i>
                        </div>
                        
                    </div>
                </div>
        <%  } // if %>
    <%  } // for %>
            </div>
        </div>
        <div class="clear-dmz"></div>
    <% } // for %>
</section>
<!--project-->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/resources/slick/slick.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ko.js"></script>
</body>
<script>
	var ctx = document.getElementById("myChart").getContext("2d");
	
	var data = {
			
			
	    datasets: [
	        {
	            data: [3, 4, 5, 3],
	            backgroundColor: [
	                "#FF6384",
	                "#E7E9ED",
	                "#E7E9ED",
	                "#E7E9ED",
	            ],
	            label: "My dataset"
	        }, {
	            data: [20, 50, 10, 5, 10],
	            backgroundColor: [
	                "#FF6384",
	                "#E7E9ED",
	                "#E7E9ED",
	                "#E7E9ED",
	                "#E7E9ED"
	            ],
	            label: "My dataset"
	        }
	    ],
	};
	
	var myDoughnutChart = new Chart(ctx, {
	    type: "pie",
	    data: data
	    // options: options
	});
</script>

<script>
$(document).on("click", ".task-update", function () { 
	var task_id = $(this).data("id");
	console.log(task_id);
	console.log("task-update invokded");
	
	$.ajax({
    	type: 'GET',
    	url: '/project/updatetask/'+task_id,
    	data: {
    		task_id : task_id
    	},
    	
    	success: function(res) {
    		console.log('success');
    		console.log(res)
    		var task_name = res.task_name;
    		var task_description = res.task_description;
    		var task_due_date = res.task_due_date;
    		var task_importance = res.task_importance;
    		var task_startdate = new Date(res.task_startdate);
    		var task_enddate = new Date(res.task_enddate);
    		console.log(task_startdate.getMonth())
    		
    		var startYear = task_startdate.getFullYear();
    		var startMonth =  task_startdate.getMonth()+ 1;
    		var startDate = task_startdate.getDate();
    		
    		var endYear = task_enddate.getFullYear();
    		var endMonth =  task_enddate.getMonth()+ 1;
    		var endDate = task_enddate.getDate();
    		
    		task_startdate = startYear + '-' + startMonth + '-' + startDate;
    		task_enddate = endYear + '-' + endMonth + '-' + endDate;
    		
    		var url = "/project/updatetask/" + res.task_id;
    		
			$(".update-task-modal-content").attr('action', url);
    		$("#update-task-name").attr('value', task_name);
    		$("#update-task-description").attr('value', task_description);
    		$("#update-task-txtDate-start").attr('value', task_startdate);
    		$("#update-task-txtDate-end").attr('value', task_enddate);
    		$("input:radio[name='task_importance']:radio[value='"+task_importance+"']").prop('checked', true);
    		
    		
    	}
    })
	
	
})
</script>

<script>
$(document).on("click", ".task-delete", function () { 
	var task_id = $(this).data("id");
	
	alert("정말로 삭제 하시겠습니까?")
	
	$.ajax({
    	type: 'POST',
    	url: '/project/deletetask/'+task_id,
    	data: {
    		task_id : task_id
    	},
    	
    	success: function(res) {
    		console.log("success");
    		location.reload();
    		/* $("#task-click-"+task_id).remove(); */
    	}
    })
	
	
})
</script>

<script>
        var add_task_modal = document.getElementById('add-task');
      
        window.onclick = function (event) {
          if (event.target == add_task_modal) {
            add_task_modal.style.display = "none";
          }
        }
        $(document).ready(function () {
      
          $(".add-task-txtDate").datepicker({
            showOn: "button",
            buttonImage: "/resources/img/icon_date.gif",
            buttonImageOnly: true
          });
          
          $("img.ui-datepicker-trigger").attr("style", "margin-left:5px; vertical-align:middle; cursor:pointer;");
      
        });
        
        var update_task_modal = document.getElementById('update-task');
        
        window.onclick = function (event) {
          if (event.target == update_task_modal) {
        	  update_task_modal.style.display = "none";
          }
        }
        $(document).ready(function () {
      
          $(".update-task-txtDate").datepicker({
            showOn: "button",
            buttonImage: "/resources/img/icon_date.gif",
            buttonImageOnly: true
          });
          
          $("img.ui-datepicker-trigger").attr("style", "margin-left:5px; vertical-align:middle; cursor:pointer;");
      
        });
      </script>

<script>
$(document).ready(function () {
            $('.task-content-wrapper').slick({
                slidesToShow: 3,
                slidesToScroll: 1,
                autoplay: false,
                autoplaySpeed: 2000,
            });
            
            $('.autoplay').slick({
                slidesToShow: 3,
                slidesToScroll: 1,
                autoplay: false,
                autoplaySpeed: 2000,
            });
        });


</script>
<script>

	$(document).on("click", ".rotate", function () {
		var id = $(this).data("id");
		var toggle = $(this).data("toggle");
		
		if (toggle == "front") {
			$("#task-click-"+id).css("transform", "rotateY(180deg)");
			$(this).data("toggle", "back");
		} else {
			$("#task-click-"+id).css("transform", "");
			$(this).data("toggle", "front");
		}
    	
	}); 
	
	
	$(document).ready(function() {
		var taskContent = $(".task-content");

		for(var i=0; i<taskContent.length; i++) {

			var taskCon = taskContent[i];
			var taskWrap = taskContent.is($("#task-content-wrap"));
			console.log(taskWrap);
			if(taskWrap == true){ 
				
				var taskDeadline = $(taskCon).find(".task-deadline");
				var duedate = $(taskDeadline).attr("data-duedate");
				var date = Date.now();
				var day = 1000 * 60 * 60 * 24 ;
				var gap = (duedate - date)/day; 
				
				if(gap <= 7){ // ~7
					$(taskDeadline).addClass("task-deadline-warn");
				}else if(gap >= 8 && gap <= 30){ // 8 ~ 30
					$(taskDeadline).addClass("task-deadline-common");
				}else{
					$(taskDeadline).addClass("task-deadline-relax");
				}//if(gap)
				
 			}//if(taskCon==true) 
		}//for
		$(document).on("click", ".slick-prev", ".slick-next", function() {
		
			var taskContent = $(".task-content");
			for(var i=0; i<taskContent.length; i++) {

				var taskCon = taskContent[i];

				var contentWrap = $(".task-content").find("#task-content-wrap");
				if(contentWrap == true){ //존재한다면
					
					var taskDeadline = $(taskCon).find(".task-deadline");
					var duedate = $(taskDeadline).attr("data-duedate");
					var date = Date.now();
					var day = 1000 * 60 * 60 * 24 ;
					var gap = (duedate - date)/day; 
					
					if(gap <= 7){ // ~7
						$(taskDeadline).addClass("task-deadline-warn");
					}else if(gap >= 8 && gap <= 30){ // 8 ~ 30
						$(taskDeadline).addClass("task-deadline-common");
					}else{
						$(taskDeadline).addClass("task-deadline-relax");
					}//if(gap)
					
				}//if(taskCon==true)
			}//for
		});
	});
	
	/* $(document).on("click", ".slick-prev", ".slick-next", function() {
		
		//$(".task-deadline").each(function(){
		//	var duedate = $(".task-deadline").attr("data-duedate");
		
		var taskDeadline = $(".task-content");
		
		
		console.log(taskDeadline);
		

		for(var i=0; i<taskDeadline.length; i++) {
			console.log("----------------------");
			var tc = taskDeadline[i];
			
			var boll = $(tc).hasClass("slick-cloned");
			var sss = $(tc).is("#task-content-wrap");
			console.log(boll);
			console.log("#######"+sss);

			if(sss ==true){
				if(boll == false){
				var dl = $(tc).find(".task-deadline");
				
				console.log(dl);
				var duedate = $(dl).attr("data-duedate");
				
				var date = Date.now();
				var day = 1000 * 60 * 60 * 24 ;
				var gap = (duedate - date)/day; 
				
				console.log(duedate);
				console.log(date);
				console.log(gap);
				
				if(gap <= 7){
					$(dl).addClass("task-deadline-warn");
				}else if(gap >= 7 && gap <= 30){ 
					$(dl).addClass("task-deadline-common");
				}else{
					$(dl).addClass("task-deadline-relax");
				}
				
			}
			}
		}
	});
	 */
</script>


<script type="text/javascript" src="/resources/js/userCircle.js"></script>

</html>