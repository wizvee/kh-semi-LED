"use strict";

var socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

socket.onopen = function () {
  socket.send(JSON.stringify(userInfo));
};

socket.onmessage = function (e) {
  if (e.data == "N") {
    alert.viewCount();
    console.log("알림");
  }
};

var selectElements = function selectElements(src) {
  return Array.from(document.querySelectorAll(src));
};