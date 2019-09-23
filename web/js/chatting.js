const btnChat = document.querySelector("#btn_chatting");
var busId="";
var userId="";

btnChat.addEventListener("click", () => {
  const chatArea = document.querySelector("#chat_area");
  if (chatArea.style.display == "none") chatArea.style.display = "block";
  else chatArea.style.display = "none";
});

const chatListItem = selectElements("#chat_area .chatListItem_area");
const cListarea = document.querySelectorAll(".chatList_area")[0];
const chatRoom = document.querySelectorAll(".chatRoom_area")[0];
chatListItem.map(e => {
  e.addEventListener("click", ({ target }) => {
    cListarea.style.display = "none";
    chatRoom.style.display = "block";
    
//    const content = document.querySelectorAll(".chatMsg_area p")[0];
//    content.textContent = target.innerText;

    busId=$(target).find("#hidden_busId").val();
    userId=$(target).find("#hidden_userId").val();
    console.log(busId);
    console.log(userId);
    
    $.ajax({
    	type:'post',
    	async: false,
    	url:'/p_190826_semi/chat.do',
    	dataType: 'text',
    	data: {"data": busId},
    	success:function(chatHistory){		
    		if(chatHistory!='none'){
    		const result=JSON.parse(chatHistory);
    		result.forEach(function(msg){
    			console.log(msg.profilePic);
    			addChat (msg.profilePic, msg.userName, msg.chatMsg, msg.chatDate, msg.chatType);
    		})
    		
    		}else{
    			$('.chatMsg_area').append('<p> 대화내용이 없습니다. </p>');
    		}
    	},
    	error:function(e){
    		alert(e);
    	}
    })
    
  });
});

// addChat 메소드
function addChat(profilePic, userName, chatMsg, chatDate, chatType){
	$('.chatMsg_area').append('<div class="row">'+
			'<div class="main-content">'+
			'<div class="media">'+
			'<a class="pull-left" href="#">'+
			'<img class="media-object img-circle" style="width:30px; height:30px;" src="/p_190826_semi/upload/profile/'+profilePic+'" alt="">'+
			'</a>'+
			'<div class="media-body">'+
			'<h5 class="media-heading">'+
			userName+
			'<span class="small pull-right">'+
			chatDate+
			'</span>' +
			'</h5>'+
			'<p>'+
			chatMsg +
			'</p>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<hr>');
}

$('.chatMsg_area').scrollTop($('.chatMsg_area')[0].scrollHeight);

// 뒤로가기 버튼 이벤트
const btnChatBack = document.querySelectorAll(".btn_chatBack")[0];
btnChatBack.addEventListener("click", () => {
	$('#content').val('');
	$('.chatMsg_area').empty();
	cListarea.style.display = "block";
	chatRoom.style.display = "none";
});

	
// socket 연결 
//	var socket=new WebSocket("ws://localhost:9000/p_190826_semi/")


//textArea 엔터키 이벤트
document.getElementById('content').addEventListener('keydown', function(event) {
	if (event.keyCode == 13) {
		const content=$("#content").val();
		const targetId=$()
		const chatType="msg";
		const send={
				"busId":busId, 
				"userId":userId, 
				"chatType":"msg", 
				"chatMsg":content
				};
		console.log(send);
//		socket.send(JSON.stringify(send));
		event.preventDefault();
		document.getElementById('content').value = "";
	}
});

