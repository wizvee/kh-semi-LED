// Variables
const contextPath = "/p_190826_semi/";
const selectElements = src => Array.from(document.querySelectorAll(src));

// WebSocket
let socket = new WebSocket("ws://localhost:9090"+contextPath+"ws");

socket.onmessage = e => {
  console.log(e.data);
  if (e.data == "N") {
    alert.viewCount();
    console.log("알림");
  }
};

// SPA - Basic Data
let eventList = null;

const promiseGetDefault = servletURL => {
  return new Promise(resolve => {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      resolve(xhr.responseText);
    });
    xhr.open("post", contextPath + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send();
  });
};

const getEventList = res => {
  eventList = JSON.parse(res);
};

let evetList = promiseGetDefault("getCalList.do").then(res => getEventList(res));