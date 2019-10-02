// Variables
const contextPath = "/albarang/";
const selectElements = src => Array.from(document.querySelectorAll(src));

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

let evetList = promiseGetDefault("getCalList.do").then(res =>
  getEventList(res)
);

// WebSocket
let socket = new WebSocket("ws://rclass.iptime.org:9999" + contextPath + "ws");
// let socket = new WebSocket("ws://localhost:9090" + contextPath + "ws");