<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="resources/css/login.css" />
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
    <title>Login</title>
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
   
    <!-- Login -->
    <div id="login-container">
      <div class="login-form-wrap">
        <h1 class="form-logo">
          <span class="text-primary">C</span>ollab
          <span class="text-primary">U</span>s
        </h1>
        <p>계정이 있으시다면 로그인을 해주세요</p>
        <form action="login" method ="post" >
          <div class="login-form-group">
            <label for="email">Email</label>
            <input type="email" name="user_email" id="email" />
            
          </div>
          <div id="email-message"></div>

          <div class="login-form-group">
            <label for="password">Password</label>
            <input type="password" name="user_pw" id="password" />
          </div>
			<div id="pw-message"></div>
          <button type="submit" class="login-btn" id="login-btn">Login</button>
        </form>
      </div>
      <div class="already-account">
        <p>Already have an account? <a href="/signup">Sign Up Here</a></p>
      </div>
    </div>

    <footer id="main-footer" class="bg-dark text-center py-1">
      <div class="contain">
        <p>Copyright &copy; 2019, CollabUs, All Rights Reserved</p>
      </div>
    </footer>
    
	<script type="text/javascript" src ="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/login.js">
    </script>
  </body>
</html>
