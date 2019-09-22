const btnChat = document.querySelector("#btn_chatting");
btnChat.addEventListener("click", () => {
  const chatArea = document.querySelector("#chat_area");
  if (chatArea.style.display == "none") chatArea.style.display = "block";
  else chatArea.style.display = "none";
});

const chatListItem = selectElements("#chat_area .chatListItem_area");
const cListarea = document.querySelectorAll(".chatList_area")[0];
const msgArea = document.querySelectorAll(".chatMsg_area")[0];
chatListItem.map(e => {
  e.addEventListener("click", ({ target }) => {
    cListarea.style.display = "none";
    msgArea.style.display = "block";
    const busName = document.querySelectorAll(".chatMsg_area p")[0];
    busName.textContent = target.innerText;
  });
});

const btnChatBack = document.querySelectorAll(".btn_chatBack")[0];
btnChatBack.addEventListener("click", () => {
  cListarea.style.display = "block";
  msgArea.style.display = "none";
});

const dropToggle = selectElements(".dropdown_toggle");
dropToggle.map(e => {
  e.addEventListener("click", ({ target }) => {
    const menu = target.nextElementSibling;
    if (menu.style.display == "none") menu.style.display = "block";
    else menu.style.display = "none";
  });
});

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

$(".fa-sign-out").on("click", function() {
  location.href = "/p_190826_semi/logout.do";
});
