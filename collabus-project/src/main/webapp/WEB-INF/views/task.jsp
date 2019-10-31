<%@ page 
 language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"
 import="java.util.List,java.lang.Math ,java.util.Map,java.util.Date, com.collabus.model.ReplyVO, com.collabus.model.CheckDTO, com.collabus.model.WorkVO, com.collabus.model.TaskVO, com.collabus.model.SubtaskVO, com.collabus.model.UserVO, java.text.SimpleDateFormat"
 %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
     <link rel="stylesheet" href="/resources/css/userDropmenu.css" />
    <link rel="stylesheet" href="/resources/css/subtask.css" />

    

    <link href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet" />
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet"> <!--user-icon-->
    <!--font-->
    <link href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet" />
    <title>프로젝트</title>
</head>
<!-- Navbar -->
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

<% 
	TaskVO taskVO = (TaskVO) request.getAttribute("taskVO");
%>
<section id="task">
	<div class="task-deadline" data-duedate="<%=(taskVO.getTask_enddate()).getTime() %>"></div>
	<div class="task-wrapper">
		<div class="task-top">
			<div class="task-top-title">
				<h1><%= taskVO.getTask_name() %></h1>
				<div class="bottom-line"></div>
			</div>
			<div class="task-top-description">
				<p>
				    <%= taskVO.getTask_description() %>
				</p>
			</div>
		</div>
		
        <div class="task-mid">
            <%
                    List<SubtaskVO> subtaskList = (List<SubtaskVO>) request.getAttribute("subtaskList");               
                    for(SubtaskVO vo : subtaskList) {
              %>
            <div class="subtask-mid-business">
                <h2><%= vo.getSubtask_name() %></h2>
            <%-- <% if(taskVO.getTask_user()== (int)session.getAttribute("user_id")) %> --%>
            <button class="update-subtask_button update-subtask_button" onclick="document.getElementById('update-subtask-<%=vo.getSubtask_id() %>').style.display='block'">
                <div class="enroll">
                    <i class="fas fa-edit"></i>
                </div>
            </button>
            <form id="delform" action="/project/<%= taskVO.getTask_project() %>/task/<%=taskVO.getTask_id() %>/delsubtask/<%=vo.getSubtask_id()%>" method="POST">
                	<button class="delete-subtask_button" onclick="alert('정말로 삭제하시겠습니까?')">
                		<i class="fas fa-trash-alt"></i>
                	</button>
            </form>
            
            <form id="upload" class="fileUpload"
                 action="/task/upload?task_id=<%=taskVO.getTask_id()%>&task_name=<%=taskVO.getTask_name()%>&subtask_name=<%=vo.getSubtask_name()%>&subtask_id=<%=vo.getSubtask_id()%>"
                 data-upload="<%=vo.getSubtask_id() %>" method="POST" enctype="multipart/form-data">
                   <input type="file" name="uploadFile" class="fileUpload" id="file-upload-<%=vo.getSubtask_id()%>" />
                   <input type="submit" name="submit" class="filesubmit" id="file-submit-<%=vo.getSubtask_id() %>" style="display:none">
            </form>
            
		    
            <div id="update-subtask-<%=vo.getSubtask_id()%>" class="subtask_modal">
                <form class="subtask_modal-content animate" action="/project/<%= taskVO.getTask_project() %>/task/<%=taskVO.getTask_id()%>/updateSubtask/<%=vo.getSubtask_id()%>" method="POST">
                    <div class="subtask_title">
                        <h1 class="form-logo">
                            <span class="text-primary">C</span>ollab
                            <span class="text-primary">U</span>s
                        </h1>
                        <span onclick="document.getElementById('update-subtask-<%=vo.getSubtask_id() %>').style.display='none'"
                            class="subtask_close" title="취소">×</span>
                    </div>
                    <div class="subtask_body">
                        <p><b>업무 제목</b></p>
                        <input type="text" value="<%=vo.getSubtask_name() %>" name="subtask_name" required>

                        <p><b>업무 설명</b></p>
                        	<textarea name="subtask_description" rows="5" cols="40" style="resize:none;" required><%=vo.getSubtask_description()%></textarea>
                        <div class="subtask_clearfix">
                            <button type="submit" class="subtask_signupbtn">등록</button>
                            <button type="button" onclick="document.getElementById('update-subtask-<%=vo.getSubtask_id() %>').style.display='none'"
                                class="subtask_cancelbtn">취소</button>
                        </div>
                    </div>
                </form>
            </div>
                <p><%= vo.getSubtask_description() %></p>
            	 <%
                   if((vo.getSubtask_filename()) != null) {
                %>
                <form id="download" action="/task/download?task_id=<%=taskVO.getTask_id()%>&subtask_id=<%=vo.getSubtask_id()%>&subtask_filename=<%=vo.getSubtask_filename() %>&subtask_filepath=<%=vo.getSubtask_filepath() %>" method="POST">
                   <button class="subtask-download">
                      <i class="fas fa-download"></i>
                   </button>
                </form>
                <%= vo.getSubtask_filename() %>
            </div>
            <% } %>
  			<% } %> 
            <button class="add-subtask_button" onclick="document.getElementById('add-subtask').style.display='block'">
                <div class="enroll">
                    <h1>세부업무 생성</h1>
                </div>
            </button>
            <div id="add-subtask" class="subtask_modal">

                <form class="subtask_modal-content animate" action="/project/<%= taskVO.getTask_project() %>/task/<%= taskVO.getTask_id() %>/addsubtask" method="POST">
                    <div class="subtask_title">
                        <h1 class="form-logo">
                            <span class="text-primary">C</span>ollab
                            <span class="text-primary">U</span>s
                        </h1>
                        <span onclick="document.getElementById('add-subtask').style.display='none'"
                            class="subtask_close" title="취소">×</span>
                    </div>
                    <div class="subtask_body">
                        <p><b>업무 제목</b></p>
                        <input type="text" placeholder="제목을 입력해주세요 " name="subtask_name" required>

                        <p><b>업무 설명</b></p>
                        	<textarea name="subtask_description" rows="5" cols="40" style="resize:none;" required></textarea>
                        <div class="subtask_clearfix">
                            <button type="submit" class="subtask_signupbtn">등록</button>
                            <button type="button" onclick="document.getElementById('add-subtask').style.display='none'"
                                class="subtask_cancelbtn">취소</button>
                        </div>
                    </div>
                </form>
            </div>


        </div>
    </div>
            <div class="clear"></div>
        <div class="middle-line"></div>

     <div class="task-reply-content">
            <!-- <div class="task-reply-title">
                    <h1>Task Feedback</h1>
                    <div class="bottom-line"></div>
            </div> -->

            
            
            	<%
                	List<ReplyVO> replyList = (List<ReplyVO>) request.getAttribute("replyList");
            		Map<Integer, String> userNicknameMap = 
            				(Map<Integer, String>) request.getAttribute("userNicknameMap");
            		
          	
                	for(ReplyVO vo : replyList) {
                			
                		
                %>
                <div class="task-bottom" data-id="<%= vo.getReply_id()%>">
					<div class="task-bottom-reply" id="reply-content-wrap-<%= vo.getReply_id()%>" data-id="<%= vo.getReply_id()%>">
	                	<div class="reply-content-head">
		                    <h3 class="reply-content-usernickname"><%= userNicknameMap.get(vo.getReply_user()) %></h3>
		                    <%
		                    	if (vo.getReply_user()== (int) session.getAttribute("user_id")) {
		                    %>
		                    <i class="fas fa-edit reply-content-edit-button" id="reply-edit-button" data-id="<%= vo.getReply_id() %>"></i>
		            		<i class="fas fa-trash-alt reply-content-delete-button" id="reply-delete-button" data-id="<%= vo.getReply_id() %>"></i>
		                    <%
		                    }
		                    %>
		            		
		            		<p class="reply-content-write-date"><%=timeCalculate(vo)%></p>
		            	</div>
		            	<div class="clear"></div>
	                    <p class="reply-content-bottom">
	                    	<div class="reply-text" id="text-<%= vo.getReply_id()%>">
	                        	<%= vo.getReply_text() %>
	                        </div>
	                        <form class="input-update-reply" id="reply-update-<%= vo.getReply_id()%>">
	                    		<input type="text" name="reply_content" id="reply-update-form-<%= vo.getReply_id()%>" class="task-reply-update" placeholder="write feedback..." required/>
	                    		<input type="button" class="task-reply-update-submit" id="reply-update-submit" data-id="<%= vo.getReply_id()%>" value="Update" />
	                		</form>
	                    </p>
                    </div>

           		</div>
                	<% } %>
                <div class="append"></div>
                
                <p class="feedback-append">댓글 더보기</a>
              
            <div class="check-and-feedback">
            	<%
            		int project_members = ((int)request.getAttribute("project_members"));
            		List<CheckDTO> checked_members = ((List<CheckDTO>)request.getAttribute("checked_members"));
            		int checked_members_number = checked_members.size();
            				
            		
            		for ( int i = 1; i <= project_members; i++) {
            			if (i <= checked_members_number) {
            	%>
            			<i class="fas fa-check-circle checked"></i>
            	<%
            			} else {
            				
            	%>
            			<i class="fas fa-check-circle unchecked"></i>
            	<%
            			}
            	%>
            		
            	<%	
            		}
            	%>
                <!-- 
                <i class="fas fa-check-circle checked"></i>
                <i class="fas fa-check-circle checked"></i>
                <i class="fas fa-check-circle checked"></i>
                <i class="fas fa-check-circle checked"></i>
                <i class="fas fa-check-circle unchecked"></i>
                <i class="fas fa-check-circle unchecked"></i> -->
            </div>
			<div class="task-reply">
                <form class="input-reply">
                    <input type="text" name="reply_content" class="task-reply-write" placeholder="write feedback..." required/>
                    <input type="button" class="task-reply-submit"value="Feedback" />
                    <%
                    	List<CheckDTO> is_checked_user = (List<CheckDTO>)request.getAttribute("is_checked_user");
                    
                    	if (is_checked_user.size() == 0) {
                    		
                    %>
                    <input type="button" class="task-reply-check" value="확인"/>
                    <%
                    	} else {
                    		
                    %>
                    <input type="button" class="task-reply-uncheck" value="취소"/>
                    <%		
                    	}
                    %>
                    
                    
                    
                </form>
                <div class="clear"></div>
                
            </div>
    </div>

</section>
<!--project-->
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.1.0/jquery-migrate.min.js"></script>
<script>
    jQuery(document).ready(function($) {
    	
    	$('.feedback-append').on('click', function() { 
    		var last_reply_id = $(".task-bottom-reply:last").attr("data-id");
			console.log(last_reply_id);
			var task_id = <%=taskVO.getTask_id()%>;
			var logined_user = <%=session.getAttribute("user_id")%>;
			
			console.log(task_id);
			
			$.ajax({
				type:'POST',
				url: '/task/infinitescroll',
				data:{
					reply_task : task_id,
					reply_id : last_reply_id
				},
				success: function(res) {
					console.log(res)
					var tagList = "";
					
					/* console.log(date.getFullYear()); */
					
					if (res != "") {
						$(res).each( function() {
							console.log(this);
							var now_date = new Date();
							var created_date = now_date - this.created_at;
							
							/* console.log(created_date); */
							/* long gap = now - createdDate;
							gap = (long)(gap/1000);
							long hour = gap/3600;
							gap = gap%3600;
							long min = gap/60;
							long sec = gap%60; */
							created_date = parseInt(created_date/1000);
							var hour = parseInt(created_date/3600);
							created_date = parseInt(created_date % 3600);
							var min = parseInt(created_date / 60);
							var sec = parseInt(created_date % 60);
							/* console.log("hour:"+ hour);
							console.log("min:"+ min);
							console.log("sec:"+ sec); */
							
							if ( hour > 24) {
								created_date = "몇일 전";
								
							} else if(hour > 0) {
								created_date = hour + "시간 전";
							} else if(min > 0) {
								created_date = min + "분 전";
							} else if (sec > 0) {
								created_date = sec+"초 전";
							} else {
								
							}
							
							console.log("logined_user: " ,logined_user);
							console.log("createuser: ", this.reply_user)
							
							tagList += `<div class="task-bottom-reply" id="reply-content-wrap-`+this.reply_id+`" data-id="`+this.reply_id+`">`;
        					tagList += `<div class="reply-content-head">`;
                        	tagList += `<h3 class="reply-content-usernickname">`+this.reply_user_name+`</h3>`;
                        	if (this.reply_user == logined_user) {
                        		tagList += `<i class="fas fa-edit reply-content-edit-button" id="reply-edit-button" data-id="` + this.reply_id + `"></i>`;
                            	tagList += `<i class="fas fa-trash-alt reply-content-delete-button" id="reply-delete-button" data-id="`+ this.reply_id+`"></i>`;
                        	}
                        	
                        	tagList += `<p class="reply-content-write-date">`+created_date+`</p>`;
                        	tagList += `</div>`
                        	tagList += `<div class="clear"></div>`;
                        	tagList += `<p class="reply-content-bottom">`;
                        	tagList += `<div class="reply-text" id="text-` + this.reply_id + `">`;
                        	tagList += this.reply_text;
                        	tagList += `</div>`;
                        	tagList += `<form class="input-update-reply" id="reply-update-`+ this.reply_id +`">`;
                        	tagList += `<input type="text" name="reply_content" id="reply-update-form-`+ this.reply_id +`" class="task-reply-update" placeholder="write feedback..." />`;
                        	tagList += `<input type="button" class="task-reply-update-submit" id="reply-update-submit" data-id="`+this.reply_id+`" value="Update"/ >`;
                        	tagList += `</form>`;
                        	tagList += `</p>`;
                        	tagList += `</div>`;
						}
						);
						$('.append').append(tagList);
					} else {
						console.log("더이상 불러올 데이터가 없습니다.");
					}
				}
			})
    		
    	})
    	
    	<%-- var lastScrollTop = 0;
    	/* var start = $(".task-bottom:last").data("id");
    	console.log(start); */
    	$(window).scroll(function() {
    		var currentScrollTop = $(window).scrollTop();
    		
    		
    		if ( currentScrollTop - lastScrollTop > 0) {
    			/* 다운 스크롤 */
    			
    			/* 현재 스크롤의 top좌표가 > (게시글 불러온 화면높이 - 윈도우창의 높이)*/
    			if( $(window).scrollTop() >= ($(document).height()- $(window).height())) {
    			
    				var last_reply_id = $(".task-bottom-reply:last").attr("data-id");
    				console.log(last_reply_id);
    				var task_id = <%=taskVO.getTask_id()%>;
    				
    				console.log(task_id);
    				
    				$.ajax({
    					type:'POST',
    					url: '/task/infinitescroll',
    					data:{
    						reply_task : task_id,
    						reply_id : last_reply_id
    					},
    					success: function(res) {
    						console.log(res)
    						var tagList = "";
							
							/* console.log(date.getFullYear()); */
							
    						if (res != "") {
    							$(res).each( function() {
    								console.log(this);
    								var now_date = new Date();
    								var created_date = now_date - this.created_at;
    								
    								/* console.log(created_date); */
    								/* long gap = now - createdDate;
    								gap = (long)(gap/1000);
    								long hour = gap/3600;
    								gap = gap%3600;
    								long min = gap/60;
    								long sec = gap%60; */
    								created_date = parseInt(created_date/1000);
    								var hour = parseInt(created_date/3600);
    								created_date = parseInt(created_date % 3600);
    								var min = parseInt(created_date / 60);
    								var sec = parseInt(created_date % 60);
    								/* console.log("hour:"+ hour);
    								console.log("min:"+ min);
    								console.log("sec:"+ sec); */
    								
    								if ( hour > 24) {
    									created_date = "몇일 전";
    									
    								} else if(hour > 0) {
    									created_date = hour + "시간 전";
    								} else if(min > 0) {
    									created_date = min + "분 전";
    								} else if (sec > 0) {
    									created_date = sec+"초 전";
    								} else {
    									
    								}
    								
    								
    								
    								tagList += `<div class="task-bottom-reply" id="reply-content-wrap-`+this.reply_id+`" data-id="`+this.reply_id+`">`;
    	        					tagList += `<div class="reply-content-head">`;
    	                        	tagList += `<h3 class="reply-content-usernickname"><%=userNicknameMap.get((int) session.getAttribute("user_id"))%></h3>`;
    	                        	tagList += `<i class="fas fa-edit reply-content-edit-button" id="reply-edit-button" data-id="` + this.reply_id + `"></i>`;
    	                        	tagList += `<i class="fas fa-trash-alt reply-content-delete-button" id="reply-delete-button" data-id="`+ this.reply_id+`"></i>`;
    	                        	tagList += `<p class="reply-content-write-date">`+created_date+`</p>`;
    	                        	tagList += `</div>`
    	                        	tagList += `<div class="clear"></div>`;
    	                        	tagList += `<p class="reply-content-bottom">`;
    	                        	tagList += `<div class="reply-text" id="text-` + this.reply_id + `">`;
    	                        	tagList += this.reply_text;
    	                        	tagList += `</div>`;
    	                        	tagList += `<form class="input-update-reply" id="reply-update-`+ this.reply_id +`">`;
    	                        	tagList += `<input type="text" name="reply_content" id="reply-update-form-`+ this.reply_id +`" class="task-reply-update" placeholder="write feedback..." />`;
    	                        	tagList += `<input type="button" class="task-reply-update-submit" id="reply-update-submit" data-id="`+this.reply_id+`" value="Update"/ >`;
    	                        	tagList += `</form>`;
    	                        	tagList += `</p>`;
    	                        	tagList += `</div>`;
    							}
    							);
    							$('.append').append(tagList);
    						} else {
    							console.log("더이상 불러올 데이터가 없습니다.");
    						}
    					}
    				})
    			}
    		}
    	}) --%>

    		
        $('.task-reply-submit').on('click', function() {
            var user_id = <%=session.getAttribute("user_id")%>;
            var task_id = <%=taskVO.getTask_id()%>;
            
            var reply_content = $('.task-reply-write').val();
            console.log(reply_content)
            if (reply_content==null || reply_content=="" || reply_content==" ") {
            	alert("한 글자 이상 작성해주세요")
            } else {
            	$.ajax({
                    type:'POST',
                    url: '/task/addreply',
                    data: {
                        reply_task : task_id,
                        reply_user : user_id,
                        reply_text: reply_content
                    },
                    success: function(res) {
                    	console.log(res)
                        var tagList = "";

    					tagList += `<div class="task-bottom-reply" id="reply-content-wrap-`+res.reply_id+`">`;
    					tagList += `<div class="reply-content-head">`;
                    	tagList += `<h3 class="reply-content-usernickname">`+res.reply_user_name+`</h3>`;
                    	tagList += `<i class="fas fa-edit reply-content-edit-button" id="reply-edit-button" data-id="` + res.reply_id + `"></i>`;
                    	tagList += `<i class="fas fa-trash-alt reply-content-delete-button" id="reply-delete-button" data-id="`+ res.reply_id+`"></i>`;
                    	tagList += `<p class="reply-content-write-date">0 초전</p>`;
                    	tagList += `</div>`
                    	tagList += `<div class="clear"></div>`;
                    	tagList += `<p class="reply-content-bottom">`;
                    	tagList += `<div class="reply-text" id="text-` + res.reply_id + `">`;
                    	tagList += res.reply_text;
                    	tagList += `</div>`;
                    	tagList += `<form class="input-update-reply" id="reply-update-`+ res.reply_id +`">`;
                    	tagList += `<input type="text" name="reply_content" id="reply-update-form-`+ res.reply_id +`" class="task-reply-update" placeholder="write feedback..." />`;
                    	tagList += `<input type="button" class="task-reply-update-submit" id="reply-update-submit" data-id="`+res.reply_id+`" value="Update"/ >`;
                    	tagList += `</form>`;
                    	tagList += `</p>`;
                    	tagList += `</div>`;
                    	
                    	console.log(tagList);
    	
                    	$('.append').append(tagList);
                        $('.task-reply-write').val('');
    	
                        
                    },
                    error: function() {
                        console.log("error");
                    }
                })
            }
            
        })
        
        $(document).on('click','.task-reply-update-submit', function() {
        	var pk =  $(this).data("id")
            var reply_content = $('#reply-update-form-'+pk).val();
           
        	if (reply_content==null || reply_content=="" || reply_content==" ") {
        		alert("한 글자 이상 입력해주세요.")
        	} else {
        		$.ajax({
                	type: 'POST',
                	url: '/task/updatereply',
                	data: {
                		reply_id : pk,
                		reply_text : reply_content
                	},
                	
                	success: function(res) {
                		console.log('success');
                		$("#reply-update-"+pk).hide();
                		$("#text-"+pk).show();
                		$("#text-"+pk).text(reply_content);
                		
                	}
                })
        	}
        })
        
        $(document).on('click','.task-reply-check', function() {
        	var task_id = <%=taskVO.getTask_id()%>;
        	var user_id = <%=session.getAttribute("user_id")%>;
        	var is_checked = 1;
			
        	$.ajax({
            	type: 'POST',
            	url: '/task/check',
            	data: {
            		confirm_task : task_id,
            		confirm_user : user_id,
            		isChecked : is_checked
            	},
            	
            	success: function(res) {
            		console.log('success');
            		
            		if ($(".checked").length != 0) {
            			console.log( "checked if invoked")
            			var tagList = "";
            			
            			tagList += `<input type="button" class="task-reply-uncheck" value="취소"/>`;
            			$(".task-reply-check").hide()
                    	$(".input-reply").append(tagList);
            			var tagList = "";
            			tagList += `<i class="fas fa-check-circle checked"></i>`;
            			
            			$(".unchecked:first").before(tagList);
            			
            			$(".unchecked:first").hide();

            		} else {
            			$(".task-reply-check").hide();
						var tagList = "";
            			
            			tagList += `<input type="button" class="task-reply-uncheck" value="취소"/>`;
            			$(".task-reply-check").hide()
                    	$(".input-reply").append(tagList);
            			
            			var tagList = "";
            			tagList += `<i class="fas fa-check-circle checked"></i>`;
            			
            			$(".unchecked:first").before(tagList);
            			
            			$(".unchecked:first").hide();
            			
                    	console.log("checked else invoked")
            		}

                	
            		
            	}
            })


        })
        
        $(document).on('click','.task-reply-uncheck', function() {
        	var task_id = <%=taskVO.getTask_id()%>;
        	var user_id = <%=session.getAttribute("user_id")%>;
        	console.log(task_id);
        	var is_unChecked = 0;
			
        	$.ajax({
            	type: 'POST',
            	url: '/task/uncheck',
            	data: {
            		confirm_task : task_id,
            		confirm_user : user_id
            	},
            	
            	success: function(res) {
            		console.log('success');
            		
            		if ($(".unchecked").length != 0) {
            			console.log( "unchecked if invoked")
            			$(".task-reply-uncheck").hide();
						var tagList = "";
            			
            			tagList += `<input type="button" class="task-reply-check" value="확인"/>`;
            			$(".task-reply-uncheck").hide()
                    	$(".input-reply").append(tagList);
                    	var tagList = "";
            			tagList += `<i class="fas fa-check-circle unchecked"></i>`;
            			
            			$(".checked:last").after(tagList);
                    	$(".checked:last").remove()
                    	
            		} else {
                    	console.log("unchecked else invoked")
                    	$(".task-reply-uncheck").hide();
						var tagList = "";
            			
            			tagList += `<input type="button" class="task-reply-check" value="확인"/>`;
            			$(".task-reply-uncheck").hide()
                    	$(".input-reply").append(tagList);
                    	var tagList = "";
            			tagList += `<i class="fas fa-check-circle unchecked"></i>`;
            			$(".checked:last").after(tagList);
                    	$(".checked:last").remove();
                    	
            		}

                	/* $(".task-reply-check").hide();
                	$(".task-reply-uncheck").show();
                	$(".unchecked:first").hide()
                	$(".checked").show() */
            		
            	}
            })

        })
        
        $(document).on('click','.reply-content-edit-button', function() {
        	var pk =  $(this).data("id");
        	var text = $("#text-"+pk).text().trim();

        	
        	if ($('.input-update-reply').is(':visible')) {
        		$("#reply-update-"+pk).hide();
        		$("#text-"+pk).show();
        	} else {
        		$("#text-"+pk).hide();
        		$("#reply-update-"+pk).show();
        		$("#reply-update-form-"+pk).attr('value', text);
        	}
        })
        
       $(document).on("change", ".fileUpload", function(){
       	var fu = $(this).data("upload");
       
       	$("#file-submit-"+fu).show();  
  
    });
        
        $(document).on('click','.reply-content-delete-button', function() {
        	var pk =  $(this).data("id");
        	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
        		$.ajax({
                	type: 'POST',
                	url: '/task/deleteReply',
                	data: {
                		reply_id : pk
                	},
                	
                	success: function(res) {
                		console.log('success');
                		$('#reply-content-wrap-'+pk).remove();
                	}
                })
        	     

        	 }else{   //취소

        	     return false;

        	 }
            
        	
        })
    });
    
    	$(document).ready(function() {
				
				var duedate = $(".task-deadline").attr("data-duedate");
				var date = Date.now();
				var day = 1000 * 60 * 60 * 24 ;
				var gap = (duedate - date)/day; 

				if(gap <= 7){ // ~7
					$(".task-deadline").addClass("task-deadline-warn");
				}else if(gap >= 8 && gap <= 30){ // 8 ~ 30
					$(".task-deadline").addClass("task-deadline-common");
				}else{
					$(".task-deadline").addClass("task-deadline-relax");
				}//if(gap)
				
	});
    
</script>

<%! public String timeCalculate(ReplyVO vo) {

	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
	
	
	long createdDate = vo.getCreated_at().getTime();
	Date date = new Date();
		
	long now = date.getTime();
	long gap = now - createdDate;
	
	String gapTime = "";
		
	gap = (long)(gap/1000);
	long hour = gap/3600;
	gap = gap%3600;
	long min = gap/60;
	long sec = gap%60;
		
	if ( hour > 24) {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
		gapTime = format.format(vo.getCreated_at());
		
	} else if(hour > 0) {
		gapTime = hour + "시간 전";
	} else if(min > 0) {
		gapTime = min + "분 전";
	} else if (sec > 0) {
		gapTime = sec+"초 전";
	} else {
		gapTime = new SimpleDateFormat("HH:mm").format(date);
	}
	
	return gapTime;
};

%>


<script type="text/javascript" src="/resources/js/userCircle.js"></script>

</html>