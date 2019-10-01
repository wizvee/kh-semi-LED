"use strict";

var btnChat = document.querySelector("#btn_chatting");
var busId = "";
var userId = "";
var userName = "";
var profilePic = "";
var chatHeaderBtn = document.querySelectorAll(".chat .chat_header span");
var chatListArea = document.querySelectorAll(".chat .chat_body .chatList_area")[0];
var chatRoomArea = document.querySelectorAll(".chat .chat_body .chatRoom_area")[0];
var chatBusList = selectElements(".chat .chat_body .chatList_area .chatListItem_area"); //뒤로가기

chatHeaderBtn[0].addEventListener("click", function () {
  $('#content').val('');
  $('.chatMsg_area').empty();
  $('.chatEmp_area').empty();
  chatListArea.classList.add("focus");
  chatRoomArea.classList.remove("focus");
  chatHeaderBtn[0].classList.add("focus");
  chatHeaderBtn[1].classList.remove("focus");
}); // 방 입장 

chatBusList.map(function (l) {
  return l.addEventListener("click", function () {
    var whatChatDate = "";
    busId = userInfo.selectBusId;
    userId = userInfo.userId;
    console.log(busId);
    console.log(userId);
    $.ajax({
      type: 'post',
      async: false,
      url: contextPath + 'chat.do',
      dataType: 'text',
      data: {
        "data": busId + "/" + userId
      },
      success: function success(data) {
        var wholeData = JSON.parse(data);

        if (wholeData.chatHistory != 'none') {
          var result = JSON.parse(wholeData.chatHistory);
          result.forEach(function (msg) {
            if (msg.chatType == 'timecheck') {
              var theTime = new Date(msg.chatDate);
              var theYear = theTime.getFullYear();
              var theMonth = theTime.getMonth();
              var theDate = theTime.getDate();
              var theDay = "";

              switch (theTime.getDay()) {
                case 0:
                  theDay = '월요일';

                case 1:
                  theDay = '화요일';

                case 2:
                  theDay = '수요일';

                case 3:
                  theDay = '목요일';

                case 4:
                  theDay = '금요일';

                case 5:
                  theDay = '토요일';

                case 6:
                  theDay = '일요일';
              }

              $('.chatMsg_area').append('<div><p>' + theYear + '년 ' + theMonth + '월 ' + theDate + '일 ' + theDay + '</p></div>');
            } else {
              var whatTime = new Date(msg.chatDate);
              var whatHours = whatTime.getHours();
              var whatMinutes = whatTime.getMinutes();
              var wampm = "";

              if (whatHours > 12) {
                whatHours -= 12;
                wampm = "오후";
              } else if (whatHours == 0) {
                whatHours = 12;
                wampm = "오전";
              } else {
                wampm = "오전";
              }

              if (whatMinutes < 10) {
                whatMinutes = "0" + whatMinutes;
              }

              whatChatDate = wampm + " " + whatHours + ":" + whatMinutes;
              addChat(msg.profilePic, msg.userName, msg.chatMsg, whatChatDate, msg.chatType);
            }
          });
        } else {
          $('.chatMsg_area').append('<p> 대화내용이 없습니다. </p>');
        }

        var profileP = "";

        if (wholeData.userList != 'none') {
          var userList = JSON.parse(wholeData.userList);
          userList.forEach(function (user) {
            if (user.profilePic == null) {
              profileP = 'emp_default.png';
            } else {
              profileP = user.profilePic;
            }

            $('.chatEmp_area').append('<div>' + user.userName + '<a class="pull-left" href="#">' + '<img class="media-object img-circle" style="width:10px; height:10px;" src="' + contextPath + 'upload/profile/' + profileP + '" alt="">' + '</a>' + '</div>');
          });
        } else {
          $('.chatEmp_area').append('<div>사업장에 등록된 인원이 없습니다</div>');
        }
      },
      error: function error(e) {
        alert(e);
      }
    });
    chatListArea.classList.remove("focus");
    chatRoomArea.classList.add("focus");
    chatHeaderBtn[0].classList.remove("focus");
    chatHeaderBtn[1].classList.add("focus");
  });
}); // addChat 메소드

function addChat(profilePic, userName, chatMsg, whatChatDate, chatType) {
  $('.chatMsg_area').append('<div class="row">' + '<div class="main-content">' + '<div class="media">' + '<a class="pull-left" href="#">' + '<img class="media-object img-circle" style="width:30px; height:30px;" src="' + contextPath + 'upload/profile/' + profilePic + '" alt="">' + '</a>' + '<div class="media-body">' + '<h5 class="media-heading">' + userName + '<span class="small pull-right">' + whatChatDate + '</span>' + '</h5>' + '<p>' + chatMsg + '</p>' + '</div>' + '</div>' + '</div>' + '</div>' + '<hr>');
}

$('.chatMsg_area').scrollTop($('.chatMsg_area')[0].scrollHeight); // 타임 인터벌 체크 

var timecheck = "";
var dnow = new Date().getDate();
setInterval(function () {
  var tnow = new Date();
  var checkTimeMsg = tnow.getFullYear() + "-" + tnow.getMonth() + "-" + tnow.getDate() + "," + tnow.getDay();

  switch (new Date().getMonth()) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      if (new Date().getDate() == 1 && dnow == 31) {
        timecheck = chatInfo("T", busId, userId, "timecheck", checkTimeMsg, "T");
        socket.send(JSON.stringify(timecheck));
      }

    case 4:
    case 6:
    case 9:
    case 11:
      if (new Date().getDate() == 1 && dnow == 31) {
        timecheck = chatInfo("T", busId, userId, "timecheck", checkTimeMsg, "T");
        socket.send(JSON.stringify(timecheck));
      }

    case 2:
      if (new Date().getDate() == 1 && dnow == 28) {
        timecheck = chatInfo("T", busId, userId, "timecheck", checkTimeMsg, "T");
        socket.send(JSON.stringify(timecheck));
      }

  }

  if (dnow < new Date().getDate()) {
    timecheck = chatInfo("T", busId, userId, "timecheck", checkTimeMsg, "T");
    socket.send(JSON.stringify(timecheck));
    dnow = new Date().getDate();
  }
}, 1000 * 60); //채팅 객체 생성 

var chatInfo = function chatInfo(flag, busId, userId, chatType, chatMsg, readed, userName, profilePic) {
  this.flag = flag;
  this.busId = busId;
  this.userId = userId;
  this.chatType = chatType;
  this.chatMsg = chatMsg;
  this.readed = readed;
  this.userName = userName;
  this.profilePic = profilePic;
}; //textArea 엔터키 이벤트


document.getElementById('content').addEventListener('keydown', function (event) {
  if (event.keyCode == 13) {
    var chatMsg = $("#content").val();
    var websocket = new chatInfo("C", busId, userId, "MSG", chatMsg, "T", userName, profilePic);
    socket.send(JSON.stringify(websocket));
    event.preventDefault();
    document.getElementById('content').value = "";
  }
}); //userName 그리고 userProfile 불러와야함 

var Message;
profilePic = userInfo.profilePic;
userName = userInfo.userName;

socket.onmessage = function (e) {
  var chatInfo = JSON.parse(e.data);
  var d = new Date();
  var hours = d.getHours();
  var minutes = d.getMinutes();
  var ampm = "";

  if (hours > 12) {
    hours -= 12;
    ampm = "오후";
  } else if (hours == 0) {
    hours = 12;
    ampm = "오전";
  } else {
    ampm = "오전";
  }

  if (minutes < 10) {
    minutes = "0" + minutes;
  }

  var chatDate = ampm + " " + hours + ":" + minutes;
  var userName = chatInfo.userName;
  var profilePic = chatInfo.profilePic;
  var message = chatInfo.chatMsg;
  $('.chatMsg_area').append('<div class="row">' + '<div class="main-content">' + '<div class="media">' + '<a class="pull-left" href="#">' + '<img class="media-object img-circle" style="width:30px; height:30px;" src="' + contextPath + 'upload/profile/' + profilePic + '" alt="">' + '</a>' + '<div class="media-body">' + '<h5 class="media-heading">' + userName + '<span class="small pull-right">' + chatDate + '</span>' + '</h5>' + '<p>' + message + '</p>' + '</div>' + '</div>' + '</div>' + '</div>' + '<hr>');
};