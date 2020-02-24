<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="/resources/css/projects-style.css" />
    <link rel="stylesheet" href="/resources/css/userDropmenu.css" />
    <link
      href="https://fonts.googleapis.com/css?family=Shrikhand&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <title>404 - Page Not Found</title>
    <style>
      body {
        height: 100vh;
      }

      #projects_main {
        height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      .status-code {
        font-size: 5rem;
        font-weight: bold;
      }

      .status-message {
        font-size: 1.5rem;
        font-weight: normal;
      }

      .go-back {
        margin-top: 2rem;
        border-radius: 10px;
        background-color: var(--primary-color);
        padding: 1rem 2rem;
        color: black;
      }

      .go-back:hover {
        background-color: #ddbc19;
      }
    </style>
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

      <div class="logined-user"></div>
      <div>
        <i id="user-circle" class="fas fa-user-circle"></i>

        <ul class="dropmenu">
          <li><a id="mypage"> 마이페이지</a></li>
          <li><a id="logout"> 로그아웃 </a></li>
        </ul>
      </div>
    </nav>

    <section id="projects_main">
      <h1 class="status-code">
        404
        <span class="status-message"
          >The page you've requested could not be found :(</span
        >
      </h1>
      <p>
        죄송합니다. 요청하신 페이지가 존재하지 않습니다. 관리자에게
        문의해주십시오.
      </p>
      <a class="go-back" href="/">첫 페이지로 돌아가기</a>
    </section>
  </body>
</html>
