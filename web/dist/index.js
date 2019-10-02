"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var selectElements = function selectElements(src) {
  return Array.from(document.querySelectorAll(src));
}; // const contextPath="/albarang/";


var contextPath = "/p_190826_semi/";

var Register =
/*#__PURE__*/
function () {
  function Register() {
    var _this = this;

    _classCallCheck(this, Register);

    _defineProperty(this, "checkEmail", function (respText) {
      var area = document.querySelector("#uMail").parentElement;

      _this.deleteError(area);

      if (respText == "success") _this.isUseMail = true;else _this.insertError(area, "사용 불가");
    });

    _defineProperty(this, "submitRegister", function (respText) {
      if (respText == "success") {
        var mail = document.querySelector("#uMail").value;
        location.href = contextPath + "emailSend.do?email=" + mail;
      } else {
        var area = document.querySelectorAll(".frm_register .msg_area")[0];

        _this.insertError(area, "회원가입 실패");
      }
    });

    _defineProperty(this, "submitLogin", function (respText) {
      if (respText == "success") location.href = contextPath + "main.do";else {
        var area = document.querySelectorAll(".frm_login .msg_area")[0];

        _this.insertError(area, "로그인 실패");
      }
    });

    this.isUseMail = false;
    this.isCorrectPw = false;
    this.isCheckPw = false;
    this.insertError = this.insertError;
    this.setInit();
  }

  _createClass(Register, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      var inptEmail = document.querySelector("#uMail");
      var inptPw1 = document.querySelector("#pw1");
      var inptPw2 = document.querySelector("#pw2");
      var btnRegister = document.querySelector("#btn_register");
      var btnLogin = document.querySelector("#btn_login");
      inptEmail.addEventListener("blur", function (_ref) {
        var target = _ref.target;
        _this2.isUseMail = false;

        var data = _this2.getData(target.value);

        _this2.getResult("checkEmail.do", data, _this2.checkEmail);
      });
      inptPw1.addEventListener("keyup", function (_ref2) {
        var target = _ref2.target;
        _this2.isCorrectPw = false;
        var area = target.parentElement;
        var reg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,16}$/;
        var pw = target.value.trim();

        _this2.deleteError(area);

        if (reg.test(pw)) _this2.isCorrectPw = true;else _this2.insertError(area, "사용 불가");
      });
      inptPw2.addEventListener("keyup", function (_ref3) {
        var target = _ref3.target;
        _this2.isCheckPw = false;
        var area = target.parentElement;
        var pw1 = inptPw1.value;

        _this2.deleteError(area);

        if (pw1 == target.value) _this2.isCheckPw = true;else _this2.insertError(area, "불일치");
      });
      btnRegister.addEventListener("click", function () {
        var name = document.getElementsByName("userName")[0].value;
        var phone = document.getElementsByName("userPhone")[0].value;
        var isBlank = name != "" && phone != "";
        var area = document.querySelectorAll(".frm_register .msg_area")[0];

        _this2.deleteError(area);

        if (_this2.isUseMail && _this2.isCorrectPw && _this2.isCheckPw && isBlank) {
          var rawData = {
            email: inptEmail.value,
            password: inptPw1.value,
            name: name,
            phone: phone
          };

          var data = _this2.getData(JSON.stringify(rawData));

          _this2.getResult("register.do", data, _this2.submitRegister);
        } else {
          _this2.insertError(area, "회원가입 실패");
        }
      }, false);
      btnLogin.addEventListener("click", function () {
        var mail = document.getElementsByName("userEmail")[0].value;
        var pw = document.getElementsByName("userPw")[0].value;
        var isBlank = mail != null && pw != null;
        var area = document.querySelectorAll(".frm_login .msg_area")[0];

        _this2.deleteError(area);

        if (isBlank) {
          var rawData = {
            email: mail,
            password: pw
          };

          var data = _this2.getData(JSON.stringify(rawData));

          _this2.getResult("login.do", data, _this2.submitLogin);
        } else {
          _this2.insertError(area, "로그인 실패");
        }
      }, false);
    }
  }, {
    key: "deleteError",
    value: function deleteError(area) {
      var target = area.lastElementChild;
      if (target != null && target.getAttribute("class") == "error") target.remove();
    }
  }, {
    key: "insertError",
    value: function insertError(area, msg) {
      var err = document.createElement("span");
      err.setAttribute("class", "error");
      err.textContent = msg;
      area.appendChild(err);
    }
  }, {
    key: "getResult",
    value: function getResult(servletURL, data, fn) {
      var xhr = new XMLHttpRequest();
      xhr.addEventListener("load", function () {
        fn(xhr.responseText);
      });
      xhr.open("post", contextPath + servletURL);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(data);
    }
  }, {
    key: "getData",
    value: function getData(data) {
      return "data=".concat(data);
    }
  }]);

  return Register;
}();

var register = new Register(); // MODAL
// form간 switch event

selectElements("#pop_header span").map(function (target) {
  target.addEventListener("click", function () {
    selectElements("#pop_header span").map(function (v) {
      return v.classList.remove("active");
    });
    selectElements("#pop_container form").map(function (f) {
      var name = target.getAttribute("id");
      f.classList.remove("active");
      if (f.classList.contains(name)) f.classList.add("active");
    });
    fn_popInit();
    target.classList.add("active");
  });
}); // input 요소의 focus event

selectElements("#pop_container input").map(function (target) {
  target.addEventListener("focus", function () {
    target.classList.add("focus");
    if (target.getAttribute("id") == "pw1") target.setAttribute("placeholder", "8~16자 영문 대·소문자 포함");
  });
  target.addEventListener("blur", function () {
    if (target.getAttribute("id") == "pw1") target.setAttribute("placeholder", "");
    if (target.value == "") target.classList.remove("focus");
  });
}); // modal 닫을 시 초기화

document.querySelector("#btn_close").addEventListener("click", fn_popInit); // 폼 초기화 함수, 1) input요소 비움 2) error요소 삭제 3) msg_area 비움

function fn_popInit() {
  selectElements("#pop_container input").map(function (v) {
    v.classList.remove("focus");
    v.value = "";
  });
  selectElements("#pop_container .error").map(function (v) {
    return v.remove();
  });
  selectElements(".msg_area span").map(function (v) {
    return v.remove();
  });
  register.isUseMail = false;
  register.isCorrectPw = false;
  register.isCheckPw = false;
}