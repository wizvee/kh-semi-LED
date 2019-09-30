const selectElements = src => Array.from(document.querySelectorAll(src));
const contextPath="/p_190826_semi/";

class Register {
  constructor() {
    this.isUseMail = false;
    this.isCorrectPw = false;
    this.isCheckPw = false;
    this.insertError = this.insertError;
    this.setInit();
  }

  setInit() {
    const inptEmail = document.querySelector("#uMail");
    const inptPw1 = document.querySelector("#pw1");
    const inptPw2 = document.querySelector("#pw2");
    const btnRegister = document.querySelector("#btn_register");
    const btnLogin = document.querySelector("#btn_login");

    inptEmail.addEventListener("blur", ({ target }) => {
      this.isUseMail = false;
      const data = this.getData(target.value);
      this.getResult("checkEmail.do", data, this.checkEmail);
    });

    inptPw1.addEventListener("keyup", ({ target }) => {
      this.isCorrectPw = false;
      const area = target.parentElement;
      const reg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,16}$/;
      const pw = target.value.trim();
      this.deleteError(area);

      if (reg.test(pw)) this.isCorrectPw = true;
      else this.insertError(area, "사용 불가");
    });

    inptPw2.addEventListener("keyup", ({ target }) => {
      this.isCheckPw = false;
      const area = target.parentElement;
      const pw1 = inptPw1.value;
      this.deleteError(area);

      if (pw1 == target.value) this.isCheckPw = true;
      else this.insertError(area, "불일치");
    });

    btnRegister.addEventListener(
      "click",
      () => {
        const name = document.getElementsByName("userName")[0].value;
        const phone = document.getElementsByName("userPhone")[0].value;
        const isBlank = name != "" && phone != "";

        const area = document.querySelectorAll(".frm_register .msg_area")[0];
        this.deleteError(area);

        if (this.isUseMail && this.isCorrectPw && this.isCheckPw && isBlank) {
          const rawData = {
            email: inptEmail.value,
            password: inptPw1.value,
            name: name,
            phone: phone
          };
          const data = this.getData(JSON.stringify(rawData));
          this.getResult("register.do", data, this.submitRegister);
        } else {
          this.insertError(area, "회원가입 실패");
        }
      },
      false
    );

    btnLogin.addEventListener(
      "click",
      () => {
        const mail = document.getElementsByName("userEmail")[0].value;
        const pw = document.getElementsByName("userPw")[0].value;
        const isBlank = mail != null && pw != null;

        const area = document.querySelectorAll(".frm_login .msg_area")[0];
        this.deleteError(area);

        if (isBlank) {
          const rawData = {
            email: mail,
            password: pw
          };
          const data = this.getData(JSON.stringify(rawData));
          this.getResult("login.do", data, this.submitLogin);
        } else {
          this.insertError(area, "로그인 실패");
        }
      },
      false
    );
  }

  checkEmail = respText => {
    const area = document.querySelector("#uMail").parentElement;
    this.deleteError(area);

    if (respText == "success") this.isUseMail = true;
    else this.insertError(area, "사용 불가");
  };

  submitRegister = respText => {
    if (respText == "success") {
      const mail = document.querySelector("#uMail").value;
      location.href = contextPath+"emailSend.do?email=" + mail;
    } else {
      const area = document.querySelectorAll(".frm_register .msg_area")[0];
      this.insertError(area, "회원가입 실패");
    }
  };

  submitLogin = respText => {
    if (respText == "success") location.href = contextPath+"main.do";
    else {
      const area = document.querySelectorAll(".frm_login .msg_area")[0];
      this.insertError(area, "로그인 실패");
    }
  };

  deleteError(area) {
    const target = area.lastElementChild;
    if (target != null && target.getAttribute("class") == "error")
      target.remove();
  }

  insertError(area, msg) {
    const err = document.createElement("span");
    err.setAttribute("class", "error");
    err.textContent = msg;
    area.appendChild(err);
  }

  getResult(servletURL, data, fn) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(xhr.responseText);
    });
    xhr.open("post", contextPath + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }

  getData(data) {
    return `data=${data}`;
  }
}

const register = new Register();

// MODAL
// form간 switch event
selectElements("#pop_header span").map(target => {
  target.addEventListener("click", () => {
    selectElements("#pop_header span").map(v => v.classList.remove("active"));
    selectElements("#pop_container form").map(f => {
      const name = target.getAttribute("id");
      f.classList.remove("active");
      if (f.classList.contains(name)) f.classList.add("active");
    });
    fn_popInit();
    target.classList.add("active");
  });
});

// input 요소의 focus event
selectElements("#pop_container input").map(target => {
  target.addEventListener("focus", () => {
    target.classList.add("focus");
    if (target.getAttribute("id") == "pw1")
      target.setAttribute("placeholder", "8~16자 영문 대·소문자 포함");
  });
  target.addEventListener("blur", () => {
    if (target.getAttribute("id") == "pw1")
      target.setAttribute("placeholder", "");
    if (target.value == "") target.classList.remove("focus");
  });
});

// modal 닫을 시 초기화
document.querySelector("#btn_close").addEventListener("click", fn_popInit);

// 폼 초기화 함수, 1) input요소 비움 2) error요소 삭제 3) msg_area 비움
function fn_popInit() {
  selectElements("#pop_container input").map(v => {
    v.classList.remove("focus");
    v.value = "";
  });
  selectElements("#pop_container .error").map(v => v.remove());
  selectElements(".msg_area span").map(v => v.remove());
  register.isUseMail = false;
  register.isCorrectPw = false;
  register.isCheckPw = false;
}
