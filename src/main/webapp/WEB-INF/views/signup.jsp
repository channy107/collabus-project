<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="/resources/css/signup.css" />
    <link
      href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
      integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
      crossorigin="anonymous"
    />
    
    <title>Sign Up</title>
  </head>
  <body>
    <!-- Navbar -->
    <nav id="navbar">
      <a href="/" class="logo-link">
        <h1 class="logo">
          <span class="text-primary">C</span>ollab
          <span class="text-primary">U</span>s
        </h1>
      </a>
    </nav>
    <!--Sign Up-->
    <div id="signup-container">
    
      <div class="signup-form-wrap">
      
        <h1 class="form-logo">
          <span class="text-primary">C</span>ollab
          <span class="text-primary">U</span>s
        </h1>
        
        <p>무료 회원가입 후 서비스를 이용해주세요.</p>
        
        <form method="POST" action="/signup">
          <div class="signup-form-group">
            <label for="email">Email</label>
            <input type="email" name="user_email" id="email" required/>
          
          </div>
          
          
          <div class="user_email"></div>

          <div class="signup-form-group">
            <label for="nickname">NickName</label>
            <input type="text" name="user_nickname" id="nickname" required/>
          </div>
          <div class="signup-nickname-massage"></div>

          <div class="signup-form-group">
            <label for="password">Password</label>
            <input type="password" name="user_pw" id="password" required/>
          </div>
          <div Class ="signup-pw-message"></div>

          <div class="signup-form-group" id="password-append">
            <label for="password-confirm">Confirm Password</label>
            <input
              type="password"
              name="password-confirm"
              id="password-confirm" 
               required
            />
            
            <div class="signup-pw-confirm-message"></div>
          </div>
          

          <button type="submit" class="signup-btn" id="signup-btn" >Sign Up</button>
        </form>
      </div>
      <div class="already-account">
        <p>Already have an account? <a href="/login">Login Here</a></p>
      </div>
    </div>

    <footer id="main-footer" class="bg-dark text-center py-1">
      <div class="contain">
        <p>Copyright &copy; 2019, CollabUs, All Rights Reserved</p>
      </div>
    </footer>
    
<script type="text/javascript" src ="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- Signup Jquery -->
<script type="text/javascript" src="/resources/js/signup.js"></script>
<script type="text/javascript" src="/resources/js/userCircle.js"></script>
</body>
</html>
