
   var emailPattern = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*[.][a-zA-Z]{2,3}$/i); 
   var pwPattern = RegExp(/^[a-zA-Z0-9]{6,25}$/);
   var nickPattern = RegExp(/^[a-zA-Z0-9-ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}$/);
   
   var isEmailPass=false;
   var isPwdPass=false;
   var isPwd2Pass=false;
   var isNickPass=false;
   var isBtn =false;
   
   var nickname;
   
    $(function(){
       
       //처음부터
       if($('#nickname').val() == '' && $('#password').val() == '' && $('#email').val() == '' && $('#password-confirm').val() == ''){

          $('#signup-btn').attr('disabled',true);
          $('.signup-form-wrap button').css("background","#dfdfdfa1");
      
          $('#signup-btn').html("Please enter a value");
       }
       
    });
    
    
    $('#signup-btn').click(function(){
      alert(nickname+"님 회원가입을 환영합니다.");
    });
    
    //버튼 호버 이벤트 
    $('#signup-btn').hover(function(){
       
      if(isBtn == false){// 비활성화 
         $('.signup-form-wrap button').css("background","#dfdfdf");
          $('#signup-btn').html("Please enter a value");
      }else{
         $('.signup-form-wrap button').css("background","#ddbc19");
         $('#signup-btn').html("Sign Up");
      } 
      
    });
    
    //버튼의 색상과 입력 이벤트 변경 
    $('.signup-form-group').on("change keyup paste",function(){
       
       if(isEmailPass == true && isPwdPass == true && isNickPass == true && isPwd2Pass==true){
          // 모든 정보입력&유효성검사 완료 
          $('#signup-btn').attr('disabled',false);
          $('.signup-form-wrap button').css("background","var(--primary-color)");
          $('#signup-btn').html("Sign Up");
          
          isBtn= true;
      
       }else{
          $('#signup-btn').html("Please enter a value");
          $('.signup-form-wrap button').css("background","#dfdfdfa1");
          $('#signup-btn').attr('disabled',true);
      
          
          isBtn= false;
          
       }
       
    });
    
    //닉네임 유효성 검사 
    $('#nickname').keyup(function(){
       
       if(nickPattern.test($('#nickname').val())){
          
          $('.signup-nickname-massage').text(' ');
          nickname=$('#nickname').val();
          
       }else{

          $('.signup-nickname-massage').text('닉네임 최소 2자 ~ 최대 10자').css("color","red");
          nickname="";
       }
       
    });
    
    //닉네임 중복 체크
    $('#nickname').blur(function(){
       
       var query = { 
                 user_nickname : $("#nickname").val() 
              };
       

        $('.signup-nickname-massage').text(" 중복 검사중 ... ").css("color","red");
            
        if(nickPattern.test($('#nickname').val())){

           $.ajax({
                url : "/signup/checknickname",
                 type : "post",
                 data : query,
                 success : function(data){
                    
                    if(data == 1){
                        $('.signup-nickname-massage').text("이미 존재하는 닉네임 입니다.").css("color","red");
                        isNickPass = false;
                    }else if(data == 0 ) {
                       $('.signup-nickname-massage').text("사용 가능한 닉네임 입니다.").css("color","blue");
                        isNickPass = true;
                    }
                 }
           });//ajax end
           
        }else{
           
            $('.signup-nickname-massage').text('닉네임에 맞게 입력해 주세요').css("color","red");
            isNickPass = false;
        }
    });
    
    //이메일 입력란 
    $('#email').keyup(function(){
       //입력마다 이메일 유효성 검사를 함 
       
        if(!(emailPattern.test($('#email').val()))){
           
         $('.user_email').text('이메일 형식에 맞게 입력해 주세요').css("color","red");  
            isEmailPass=false;
            
        }else{
         $('.user_email').text(' ');  
        }
        
    });
    
    //이메일 db 검사 
    $('#email').blur(function(){
       //이메일 포커스를 벗어날때 발생함 
       
        var qeury = { 
                 user_email : $("#email").val() 
              };
       
        $('.user_email').text(" 중복 검사중 ... ").css("color","red");
      
        if(emailPattern.test($('#email').val())){
           
           $.ajax({
                url : "/signup/checkEmail",
                 type : "post",
                 data : qeury,
                 success : function(data){
                    
                    if(data == 1){
                       $('.user_email').text("이미 존재하는 이메일 주소입니다.").css("color","red");
   
                       isEmailPass=false;
                    }else if(data == 0 ) {
                       $('.user_email').text("사용 가능한 이메일 주소입니다.").css("color","blue");
                     isEmailPass=true;
                    }
                 }
           });//ajax end
           
        }else{
           
            $('.user_email').text('이메일 형식에 맞게 입력해 주세요').css("color","red");
              isEmailPass=false;
        }
     });
    
    //비밀번호 입력 
    $('#password').keyup(function(){
       
       if($('#password').val() != ''){
          
          if(pwPattern.test($('#password').val())){

             $('.signup-pw-message').text(' ');
             isPwd2Pass=true;
             
             if($('#password-confirm').val() != '' ){
                
                $('#password-confirm').trigger('keyup');
   
             }
             
          }else{
             
             $('.signup-pw-message').text('영어 와 숫자 6~25 자를 입럭하세요.').css("color","red");
               isPwd2Pass=false;
          }
          
       }else{
          isPwd2Pass=false;
          $('.signup-pw-message').text('영어 와 숫자 6~25 자를 입럭하세요.').css("color","red");
       }
       
       
       
    });
    
    //비밀번호 재입력 확인
    $('#password-confirm').keyup(function(){
       
       if(pwPattern.test($('#password-confirm').val())){
          //비밀번호 유효성 검사 성공후
          
          if($('#password').val()  == $('#password-confirm').val()){
             //비밀번호 일치 
             
             $('.signup-pw-confirm-message').text('비빌번호가 일치 합니다.').css("color","blue");
              isPwdPass=true;
              
          }else{
             //비밀번호 비일치 
             
             $('.signup-pw-confirm-message').text('비번이 일치 하지 않습니다.').css("color","red");
             isPwdPass=false;
             
             if($('#password').val() == '' || $('#password').val() == null){
                //비밀번호가 일치하지 않을시 비밀번호가 입력되었는지 확인
                 if(!(pwPattern.test($('#password').val()))){
                       
                        $('.signup-pw-message').text('영어 와 숫자 6~25 자를 입럭하세요.').css("color","red");
                     
                  
                 }
             }else{
                $('.signup-pw-confirm-message').text('비밀번호를 입력해 주세요.').css("color","red");
             }
          }
          
       }else{
          //비밀번호 유효성 검사 실패 후 
          
          $('.signup-pw-confirm-message').text('영어 와 숫자 6~25 자를 입럭하세요.').css("color","red");
          isPwdPass=false;
          
       } 
       
    });
    
    
    