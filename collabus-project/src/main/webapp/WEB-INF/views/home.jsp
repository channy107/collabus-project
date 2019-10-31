<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="/resources/css/index-style.css" />
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
    <title>Collabus</title>
  </head>
  <body id="home">
    <!-- Navbar -->
    <nav id="navbar">
      <h1 class="logo">
        <span class="text-primary">C</span>ollab
        <span class="text-primary">U</span>s
      </h1>

      <ul>
        <li><a href="#home">Home</a></li>
        <li><a href="#service">Service</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>
    </nav>

    <!-- Header introduce -->
    <header id="introduce">
      <div class="introduce-content">
        <h1 class="l-heading">
          We Are <span class="text-primary">C</span>ollab<span
            class="text-primary"
            >U</span
          >s
        </h1>
        <p class="lead">
          We work
          <span
            class="txt-type"
            data-wait="3000"
            data-words='["SMART", "EASY", "FAST"]'
          ></span>
        </p>
        <a href="/login" class="btn">시작하기</a>
      </div>
    </header>

    <!-- Section: Service -->
    <section id="service" class="bg-light py-1">
      <div class="service-container">
        <h2 class="m-heading text-center">
          <span class="text-primary">What</span> We Do
        </h2>
        <div class="service-items">
          <div class="service-item">
            <i class="fas fa-paste fa-2x"></i>
            <div>
              <h3>업무 등록</h3>
              <p>업무를 등록합니다</p>
            </div>
          </div>

          <div class="service-item">
            <i class="fas fa-book-reader fa-2x"></i>
            <div>
              <h3>업무 수행</h3>
              <p>업무를 수행합니다.</p>
            </div>
          </div>

          <div class="service-item">
            <i class="fas fa-clipboard-check fa-2x"></i>
            <div>
              <h3>업무 완료</h3>
              <p>
                팀원 모두가 확인하면 업무가 완료됩니다.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Section About-->
    <section id="about" class="bg-dark py-1">
      <div class="about-container">
        <h2 class="m-heading text-center">
          <span class="text-primary">Who</span> Are We ?
        </h2>
        <div class="about-items">
          <div class="about-item">
            <i class="fas fa-user fa-2x"></i>
            <div>
              <h3>이성범</h3>
              <ul class="about-we">
                <li>기획</li>
                <li>프론트 엔드</li>
              </ul>
            </div>
          </div>

          <div class="about-item">
            <i class="fas fa-user fa-2x"></i>
            <div>
              <h3>박상현</h3>
              <ul class="about-we">
                <li>기획</li>
                <li>백엔드</li>
              </ul>
            </div>
          </div>

          <div class="about-item">
            <i class="fas fa-user fa-2x"></i>
            <div>
              <h3>임헌찬</h3>
              <ul class="about-we">
                <li>디자인</li>
                <li>프론트 엔드</li>
              </ul>
            </div>
          </div>

          <div class="about-item">
            <i class="fas fa-user fa-2x"></i>
            <div>
              <h3>한창환</h3>
              <ul class="about-we">
                <li>디자인</li>
                <li>프론트 엔드</li>
              </ul>
            </div>
          </div>

          <div class="about-item">
            <i class="fas fa-user fa-2x"></i>
            <div>
              <h3>강정훈</h3>
              <ul class="about-we">
                <li>기획</li>
                <li>백 엔드</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Section: Contact -->
  <section id="contact">
      <div class="contact-form bg-primary p-2">
        <h2 class="m-heading">Contact Us</h2>
        <p>Please use the form below to contact us</p>
        <form>
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" name="name" id="name" placeholder="Enter Name">
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" id="email" placeholder="Enter Email">
          </div>
          <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="text" name="phone" id="phone" placeholder="Enter Phone Number">
          </div>
          <div class="form-group">
            <label for="message">Message</label>
            <textarea name="message" id="message" placeholder="Enter Message"></textarea>
          </div>
          <input type="submit" value="Send" class="btn btn-dark">
        </form>
      </div>
  </section>
    <script
      src="https://code.jquery.com/jquery-3.3.1.min.js"
      integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
      crossorigin="anonymous"
    ></script>
    <script src="/resources/js/typewriter.js"></script>
    <script src="/resources/js/main.js"></script>
  </body>
</html>