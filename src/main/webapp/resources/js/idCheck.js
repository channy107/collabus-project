const userEmail = document.getElementById("userEmail");
const emailResult = document.getElementById("emailResult");
const userPw = document.getElementById("userPw");
const pwResult = document.getElementById("pwResult");
const signupBtn = document.getElementById("signupBtn");
const xhr = new XMLHttpRequest();

let prevInput = '';

userEmail.addEventListener("keyup", () => {

  const email = userEmail.value;
  if(prevInput !== email) {
    
    prevInput = email;
    emailResult.innerText = '';
    
    if(varify(email)) {
      xhr.open("POST", '/signup/checkEmail', true);
      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      xhr.send('email=' + email);
    }
  }
})

xhr.onload = () => {
  if(xhr.status === 200) {
    const emailAvailable = JSON.parse(xhr.response).emailAvailable;

    if(emailAvailable === true) {
      emailResult.style.color = 'blue';
      emailResult.innerText = '사용 가능한 이메일 주소입니다.';
      signupBtn.removeAttribute("disabled");

    } else {
      emailResult.style.color = 'red';
      emailResult.innerText = '이미 존재하는 이메일 주소입니다.';
      signupBtn.setAttribute("disabled", "");
      
    }
  }
}


// 이메일 검증
function varify() {

  var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

  if (userEmail.value.match(regExp) != null) {
    return true;
  } else {
    return false;
  }
};