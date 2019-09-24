let socket = new WebSocket("ws://localhost:9090/p_190826_semi/ws");

socket.onopen = e => {};

socket.onmessage = e => {
    console.log(e.data);
}