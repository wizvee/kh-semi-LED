"use strict";

// Variables
var contextPath = "/p_190826_semi/";

var selectElements = function selectElements(src) {
  return Array.from(document.querySelectorAll(src));
}; // SPA - Basic Data


var eventList = null;

var promiseGetDefault = function promiseGetDefault(servletURL) {
  return new Promise(function (resolve) {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function () {
      resolve(xhr.responseText);
    });
    xhr.open("post", contextPath + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send();
  });
};

var getEventList = function getEventList(res) {
  eventList = JSON.parse(res);
};

var evetList = promiseGetDefault("getCalList.do").then(function (res) {
  return getEventList(res);
}); // WebSocket

var socket = new WebSocket("ws://rclass.iptime.org:9999" + contextPath + "ws");