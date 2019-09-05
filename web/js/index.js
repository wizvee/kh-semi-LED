var path = location.host;

// 폼 모달에서 header 버튼 active
$("#pop_header span").on("click", function() {
  $(this)
    .siblings()
    .removeClass("active");
  $("input").removeClass("focus");
  $(this).addClass("active");

  var types = ["email", "text", "password"];
  $.each(types, function(i, v) {
    $("input[type='" + v + "']").val("");
  });
  $(".error").remove();

  $(".msg_area")
    .children()
    .remove();

  var target = $(this).attr("id");
  $("." + target)
    .siblings()
    .removeClass("active");
  $("." + target).addClass("active");
});

// input에 대한 focus
$("input").on("focus", function() {
  $(this).addClass("focus");
  if ($(this).attr("id") == "pw1") {
    $(this).attr("placeholder", "8~16자 영문 대·소문자 포함");
  }
});

// input이 비었을 때
$("input").on("blur", function() {
  $("#pw1").removeAttr("placeholder");
  if ($(this).val() == "") {
    $(this).removeClass("focus");
  }
});

// 폼 모달 닫을 시 초기화
$("#pop_header label").on("click", function() {
  var types = ["email", "text", "password"];
  $.each(types, function(i, v) {
    $("input[type='" + v + "']").val("");
    $("input[type='" + v + "']").removeClass("focus");
  });
  $(".error").remove();

  $(".msg_area")
    .children()
    .remove();
});

var check_mail = false;
var check_pw = false;
var check_cpw = false;

// 이메일 체크
$("#uMail").on("blur", function() {
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/checkEmail.do",
    dataType: "text",
    data: { mail: $(this).val() },
    success: function(data) {
      if (
        data == "unable" &&
        !$("#uMail")
          .siblings()
          .is(".error")
      ) {
        $("#uMail")
          .parent()
          .append($("<span class='error'>사용불가</span>"));
      } else if (data == "able") {
        check_mail = true;
        $("#uMail")
          .siblings(".error")
          .remove();
      }
    },
    error: function() {
      alert("연결실패");
    }
  });
});

// 비밀번호 체크
$("#pw1").on("blur", function() {
  var check = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,16}$/.test($(this).value);
  if (
    $(this).val() != "" &&
    !check &&
    !$(this)
      .siblings()
      .is(".error")
  ) {
    $(this)
      .parent()
      .append($("<span class='error'>사용불가</span>"));
  } else {
    $(this)
      .siblings(".error")
      .remove();
    check_pw = true;
  }
});

// 비밀번호 확인
$("#pw2").on("blur", function() {
  var check = $(this).val() == $("#pw1").val();
  if (
    $(this).val() != "" &&
    !check &&
    !$(this)
      .siblings()
      .is(".error")
  ) {
    $(this)
      .parent()
      .append($("<span class='error'>불일치</span>"));
  } else {
    $(this)
      .siblings(".error")
      .remove();
    check_cpw = true;
  }
});

function sign_validate() {
  var check3 = $(".frm_register input").val() != "";

  var mail = $(".frm_register input")[0].value;
  var pwd = $(".frm_register input")[1].value;
  var uname = $(".frm_register input")[3].value;
  var uphone = $(".frm_register input")[4].value;
  var register = { email: mail, password: pwd, name: uname, phone: uphone };
  if (check_mail && check_pw && check_cpw && check3) {
    $.ajax({
      type: "post",
      async: false,
      url: "/p_190826_semi/register.do",
      dataType: "text",
      data: { data: JSON.stringify(register) },
      success: function(data) {
        if (data == "success") {
          location.href = "/p_190826_semi/emailSend.do?email=" + mail;
        } else {
          $(".frm_register .msg_area").html("<p>회원가입 실패</p>");
        }
      },
      error: function(data) {
        alert("연결 실패");
      }
    });
  } else {
    $(".frm_register .msg_area").html("<p>회원가입 실패</p>");
  }
}

function login_validate() {
  var mail = $(".frm_login input")[0].value;
  var pwd = $(".frm_login input")[1].value;
  var login = { email: mail, password: pwd };
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/login.do",
    dataType: "text",
    data: { data: JSON.stringify(login) },
    success: function(data) {
      if (data == "success") {
        location.href = "/p_190826_semi/main.do";
      } else if (data == "fail") {
        $(".frm_login .msg_area").html("<p>로그인 실패</p>");
      }
    },
    error: function() {
      alert("연결 실패");
    }
  });
}
