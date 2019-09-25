let socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

socket.onmessage = e => {
  console.log(e.data);
};

const selectElements = src => Array.from(document.querySelectorAll(src));
