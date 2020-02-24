<%@ 
page language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="com.collabus.model.UserVO , java.util.List , com.collabus.model.InviteDTO"
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="/resources/css/mypage.css" />
    <link rel="stylesheet" href="/resources/css/userDropmenu.css" />
    <!-- <link rel="stylesheet" href="/resources/css/mypage.css" />
    <link rel="stylesheet" href="/resources/css/userDropmenu.css" /> -->
    <link
      href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
      rel="stylesheet"
    />

    <!-- modal Popup JQuery-->
    <link
      rel="stylesheet"
      href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"
    />

    <script
      type="text/javascript"
      src="http://code.jquery.com/jquery-1.9.1.min.js"
    ></script>
    <script
      type="text/javascript"
      src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"
    ></script>

    <script
      type="text/javascript"
      src="/resources/js/jquery.ui.datepicker-ko.js"
    ></script>
    <!-- modal Popup JQuery-->

    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <link
      href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
      rel="stylesheet"
    />

    <title>Collabus</title>
  </head>

  <body id="home">
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
      <div class="logined-user"></div>
      <div>
        <i id="user-circle" class="fas fa-user-circle"></i>

        <ul class="dropmenu">
          <li><a id="mypage"> 마이페이지</a></li>
          <li><a id="logout"> 로그아웃 </a></li>
        </ul>
      </div>
    </nav>

   <%
      UserVO userVO = (UserVO) request.getAttribute("userVo");
   %>
   
    <div id="mypage-container">
      <div class="mypage-header">
        <div class="mypage-user-circle">
          <i id="user-circle" class="fas fa-user-circle"></i>
        </div>
        <div class="user-name"><%= userVO.getUser_nickname() %></div>
        <div class="user-email"><%= userVO.getUser_email() %></div>
        <div class="mypage-tab">
   
          <div class="alarm">초대 알람</div>
          <div class="profile-edit">비밀번호 변경</div>
          <div class="secession"> 회원 탈퇴</div>
          
        </div>
      </div>
      <div class="mypage-contents">
        <div class="profile-info">
          <form >

            <div class="profile-form-group">
              <label for="nickname">현재 비밀번호</label>
              <input type="password" name="password" id="password" />
           
            </div>
            <div class="pw-message"></div>
            <div class="profile-form-group">
              <label for="password">변경 비밀번호</label>
              <input type="password" name="changed_password" id="changed_password" />
             
            </div>
          <div class="ch-pw-message"></div>
            <div class="profile-form-group">
              <label for="password-confirm">변경 비밀번호 확인</label>
              <input
                type="password"
                name="changed_password-confirm"
                id="changed_password-confirm"
              />
           
            </div>
           <div class="pw-confirm-message"></div>
            <button type="submit" class="profile-btn">비밀번호 변경</button>
          </form>
        </div>
           <div class="alarm-list">
           
        <%
          List<InviteDTO> invites = (List<InviteDTO>) request.getAttribute("inviteDTO");
        %>
        <%for(InviteDTO invite : invites){ %>
           <%if(invite.getProject_invite() ==0){  %>
        
             <div class="alarms">
               <div class="alarm-user-name">
          
                  <%=invite.getSend_name() %> 님이  프로젝트에 초대를 하였습니다.
               </div>
              <!--  <div class="before-invite"> -->
               
                 
                 <button type="button" class="after-invite-button" value="<%=invite.getProject_id()%>">
                          거절
                 </button>
                 <button type="button" class="before-invite-button" value="<%=invite.getProject_id()%>">
                           수락 
                 </button>
               
               <div class="clear" style="clear:both;"></div>
               <div class="mypage-bottom-line"></div>
             </div>
        
          <%} %>
         <%} %>
         
           </div>
           <div class="secession-info">
                 <h2>탈퇴 사유</h2>
                 <select class="secession-select">
                     <option value="0">사유선택</option>
	                <option value="1">필요가 없음</option>
	                <option value="2">유용하지 않음</option>
	                <option value="3">기타</option>
                 </select>
                 <textarea  rows="10" cols="60" style="resize:none;" placeholder="탈퇴하는 이유" required  id="reason"></textarea>
               <!--  radonly="readonly" --> 
                 <button type="submit" class="secession-info-button">탈퇴하기</button>
     
           </div>
            
         <!--  -->
      </div>
    </div>
  </body>
  <!-- <script type="text/javascript" src="/resources/js/userCircle.js"></script> -->
  <script type="text/javascript" src="/resources/js/userCircle.js"></script>
<script>

var pwPattern = RegExp(/^[a-zA-Z0-9]{6,25}$/);

var password="<%=userVO.getUser_pw()%>";

var email="<%=userVO.getUser_email()%>";
 //현재 비밀번호 
var pw1=false;
var pw2=false;
var pw3=false;
// 탈퇴 ---------
var par = 0;

 $(document).ready(function(){
	$('#reason').on('keyup',function(){
		if($(this).val().length >= 100){
			$(this).val($(this).val().subString(0,100));
		}
	});
	
}); 

  $('.secession-info-button').click(function(){
	 console.log("+++++ :"+par);
	 console.log("+----+ : "+ $('#reason').val());
	 if(par != 0){
		 
		 
		 var query ={ number : par , message : $('#reason').val() };
		 
		 $.ajax({
		         url : "/mypage/userTaltoe",
		         type : "post",
		         data : query,
		         success : function(data){
		          //0 실패 1 성공
		              if(data == 1){
		            	  
		                 	alert("회원 탈퇴 성공");
							location.href ="/login/logout";
		              }else{
		            	  alert("회원 탈퇴 실패");
		              }
		         }
		   });//ajax end 
	 }else{
		 alert("사유를 선택하세요 ." + par);
	 }
 }); 
 
 $('.secession-select').click(function(){
	 par = $(this).val();
	 
	 if(par <= 1){
		 
		 $('#reason').attr('disabled','disabled');
		 console.log("변경");
		 
	 }else{

		 $('#reason').removeAttr('disabled');
		 console.log("변경")
	 }
	console.log(par +"()()()()"); 
 });
 
 //---------탈퇴 end -
 $('.profile-btn').click(function(){
    
    if(pwPattern.test($('#password').val())){
       $('.pw-message').text(' ');
       pw1=true;
    }else{ pw1=false; }
    
    if(pwPattern.test($('#changed_password').val())){
      $('.ch-pw-message').text(" ");
       pw2=true;
    }else{
       pw2=false; 
       $('.ch-pw-message').text("영어 와 숫자 6~25 자를 입럭하세요.").css("color","red");
    }
    
    if(pwPattern.test($('#changed_password-confirm').val())){
      if($('#changed_password-confirm').val() == $('#changed_password').val()){
         pw3=true;
         console.log("성공");

         $('.pw-confirm-message').text(' ');
      }else{
         $('.pw-confirm-message').text('위와 똑같이 입력해 주세요.').css("color","red");
         console.log("실패");
      }
       
    }else{ pw3=false; }
    
    if(pw1==true && pw2==true && pw3 ==true){
      
       var query ={
             userPw:password ,
             isPw:$('#password').val(),
             changePw:$('#changed_password').val(),
             user_email:email
       };
       
       $.ajax({
             url : "/mypage/password",
              type : "post",
              data : query,
              success : function(data){
            console.log("userPW: "+ password);
            console.log("isPw: "+ $('#password').val());
            console.log("changepw: "+ $('#changed_password').val());
            console.log("user_email: "+email);
               if(data == 1){
                  //성공
                  alert("성공");

                  location.href ="/login/logout";
               }else if(data == 2){
                  
                  //비번 틀림 
                   alert("비번 틀림 ")
                  return false;
                  
               }else{//완전 실패
                  alert("완전 실패");
                  return false;
               }
         
               
              }
        });//ajax end
    }else{
       return false;
      }
    
    
 });
  
 $('.after-invite-button').click(function(){
	//선 넘
	//1 삭제 성공 0 삭제 실패 
	
	var RejectProjectId = $(this).val();
	var button = $(this);
	console.log("Reject : "+ button +"Reject_id : "+ RejectProjectId);
	var query =
		{ project_id : RejectProjectId };
	
    $.ajax({
        url : "/mypage/isReject",
         type : "post",
         data : query,
         success : function(data){
          //0 실패 1 성공
              if(data == 1){
                 
                 alert("거절 성공");
                 $(button).parent().css("display", "none");
                 $(button).attr('disabled',true);
              }else{
                 alert("거절 실패  재접속 바람");
              }
    
          
         }
   });//ajax end
 });
 $('.before-invite-button').click(function(){
   //수락 버튼 누룰시 
    var inviteProjectId = $(this).val();
    var button = $(this);
    console.log("button"+button +"_____ "+inviteProjectId);
    var query = { project_id : inviteProjectId};
       
           $.ajax({
             url : "/mypage/isInvite",
              type : "post",
              data : query,
              success : function(data){
               //0 실패 1 성공
	               if(data == 1){
	                  
	                  alert("초대 받기 성공");
	                  $(button).html("초대 받음")
	                  $(button).attr('disabled',true);
	               }else{
	                  alert("다시 접속하시기 바랍니다.");
	               }
         
               
              }
        });//ajax end
 });
  
  
 $(document)
 $(document).on('click','.alarm', function() {

     $(".profile-edit").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     $(".secession").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     $(".alarm").css('background-color','var(--primary-color)').css('color', 'var(--black-color)');
       $(".profile-info").hide();
       $(".secession-info").hide();
       $(".alarm-list").show();

    })
    
    $(document).on('click','.profile-edit', function() {

     $(".alarm").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     
     $(".secession").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     
     $(".profile-edit").css('background-color','var(--primary-color)').css('color', 'var(--black-color)');
       $(".alarm-list").hide();
       $(".secession-info").hide();
       $(".profile-info").show();

    })
    
    $(document).on('click','.secession', function() {
  
     $(".profile-edit").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     $(".alarm").css('background-color','var(--dark-color)').css('color', 'var(--white-color)');
     $(".secession").css('background-color','var(--primary-color)').css('color', 'var(--black-color)');
       $(".alarm-list").hide();
       $(".profile-info").hide();
       $(".secession-info").show();


    })
  </script>
</html>