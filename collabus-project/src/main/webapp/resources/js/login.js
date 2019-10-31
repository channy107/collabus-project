$('#login-btn').click(function(){
			
			var pw = $('#password').val() == '' || $('#password').val() == null ;
			var email = $('#email').val() == '' || $('#email').val() == null ;
			
			if(pw == false && email == false){
				
				$('#login-btn').attr('disabled',false);
				
			}else{
				
				if(pw){

					$('#pw-message').text('비밀번호 를 입력하세요 .').css("color","red");
				}
				
				if(email){

					$('#email-message').text('이메일 을  입력하세요 .').css("color","red");
				}

				$('#login-btn').attr('disabled',true);
			}
	    			
	    			
});
		
$('#password').keyup(function(){

	$('#login-btn').attr('disabled',false);

	$('#pw-message').text('');
});
		
$('#email').keyup(function(){

	$('#login-btn').attr('disabled',false);

	$('#email-message').text('');
			
});

		
    	