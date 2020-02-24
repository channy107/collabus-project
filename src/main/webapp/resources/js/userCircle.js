$("#user-circle").hover(function(){
		$(this).find(" .dropmenu").stop().fadeToggle(300);
	});
	
	
	
	$(document).ready(function(){
	
		$("#user-circle ").click(function(){
			
			var submenu = $(this).next(".dropmenu");
			
			if(submenu.is(":visible")){
				submenu.slideUp();
			}else{
				submenu.slideDown();
			}
			
		});
	});
	
$('#logout').click(function(){
	
	location.href ="/login/logout";
		
});
	
$('#mypage').click(function(){
	
	location.href ="/mypage";
		
});