const btnChat = document.querySelector("#btn_chatting");
var busId="";
var userId="";
var userName="";
var profilePic="";
console.log(userInfo.userName);

//var userNow = <%=loginOwner %>;
//console.log(userNow);


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
    	success:function(data){
    		const wholeData=JSON.parse(data);
    		if(wholeData.chatHistory!='none'){
    		const result=JSON.parse(wholeData.chatHistory);
    		result.forEach(function(msg){
				console.log(msg.chatMsg.getHours);
    			addChat (msg.profilePic, msg.userName, msg.chatMsg, msg.chatDate, msg.chatType);
    		})
    		
    		}else{
    			$('.chatMsg_area').append('<p> 대화내용이 없습니다. </p>');
    		}
    		if(wholeData.userList!='none'){
    			// 리스트 뿌려줘야함 
    		}else{
    			
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
			'<img class="media-object img-circle" style="width:30px; height:30px;" src="'+contextPath+'upload/profile/'+profilePic+'" alt="">'+
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

	
//채팅 객체 생성 
var chatInfo=function(flag, busId, userId, chatType, chatMsg, readed) {
	this.flag = flag;
	this.busId = busId;
	this.userId = userId;
	this.chatType = chatType;
	this.chatMsg = chatMsg;
	this.readed= readed;
}

//textArea 엔터키 이벤트
document.getElementById('content').addEventListener('keydown', function(event) {
	if (event.keyCode == 13) {
		var chatMsg=$("#content").val();
		var websocket=new chatInfo("C",busId,userId,"MSG",chatMsg,"T");
		socket.send(JSON.stringify(websocket));
		
//		const content=$("#content").val();
//		const targetId=$()
//		const chatType="msg";
//		const send={
//				"busId":busId, 
//				"userId":userId, 
//				"chatType":"msg", 
//				"chatMsg":content
//				};
//		socket.send(JSON.stringify(send));
		
		event.preventDefault();
		document.getElementById('content').value = "";
	}
});


//userName 그리고 userProfile 불러와야함 
var Message;
profilePic=userInfo.profilePic;
userName=userInfo.userName;
var d = new Date();
var hours=d.getHours();
var minutes=d.getMinutes();
var ampm="";
if (hours >12){
	hours -=12;
	ampm="오후";
}else{
	ampm="오전";
}
if(minutes<10){
	minutes="0"+minutes;
}

var chatDate= ampm+" "+hours+":"+minutes;

socket.onmessage=function(e){
	console.log(e.data);
	console.log(typeof e.data);
		if(e.data=="연결"){
		var message="채팅방에 입장하셨습니다!";
	}else{
		message=e.data
	}
	$('.chatMsg_area').append('<div class="row">'+
			'<div class="main-content">'+
			'<div class="media">'+
			'<a class="pull-left" href="#">'+
			'<img class="media-object img-circle" style="width:30px; height:30px;" src="'+contextPath+'upload/profile/'+profilePic+'" alt="">'+
			'</a>'+
			'<div class="media-body">'+
			'<h5 class="media-heading">'+
			userName+
			'<span class="small pull-right">'+
			chatDate+
			'</span>' +
			'</h5>'+
			'<p>'+
			message +
			'</p>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'</div>'+
			'<hr>');
}






