"use strict";

var socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

socket.onmessage = function (e) {
  console.log(e.data);

  if (e.data == "N") {
    alert.viewCount();
    console.log("알림");
  }
};

var contextPath = "/p_190826_semi/";

var selectElements = function selectElements(src) {
  return Array.from(document.querySelectorAll(src));
};