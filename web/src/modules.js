let socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

socket.onopen = () => {
  socket.send(JSON.stringify(userInfo));
}

socket.onmessage = e => {
  if(e.data == "N") {
    alert.viewCount();
    console.log("알림");
  } if(e.data == "T") {
    console.log("테스트트트");
  }
};

const selectElements = src => Array.from(document.querySelectorAll(src));
