const btnChat = document.querySelector("#btn_chatting");
var busId="";
var userId="";
var userName="";
var profilePic="";




const chatHeaderBtn = document.querySelectorAll(".chat .chat_header span");
const chatListArea = document.querySelectorAll(".chat .chat_body .chatList_area")[0];
const chatRoomArea = document.querySelectorAll(".chat .chat_body .chatRoom_area")[0];
const chatBusList = selectElements(".chat .chat_body .chatList_area .chatListItem_area");

//뒤로가기
chatHeaderBtn[0].addEventListener("click", () => {
	$('#content').val('');
	$('.chatMsg_area').empty();

	chatListArea.classList.add("focus");
	chatRoomArea.classList.remove("focus");
	chatHeaderBtn[0].classList.add("focus");
	chatHeaderBtn[1].classList.remove("focus");

});



// 방 입장 
chatBusList.map(l => l.addEventListener("click", () => {
	var whatChatDate="";
	busId=userInfo.selectBusId;
    userId=userInfo.userId;
    console.log(busId);
	console.log(userId);

    
    $.ajax({
    	type:'post',
    	async: false,
    	url: contextPath+'chat.do',
    	dataType: 'text',
    	data: {"data": busId},
    	success:function(data){
    		const wholeData=JSON.parse(data);
    		if(wholeData.chatHistory!='none'){
    		const result=JSON.parse(wholeData.chatHistory);
    		result.forEach(function(msg){
				var whatTime=new Date(msg.chatDate);
				var whatHours=whatTime.getHours();
				var whatMinutes=whatTime.getMinutes();
				var wampm="";
				if (whatHours >12){
					whatHours -=12;
					wampm="오후";
				}else{
					wampm="오전";
				}
				if(whatMinutes<10){
					whatMinutes="0"+whatMinutes;
				}
				whatChatDate= wampm+" "+whatHours+":"+whatMinutes;

    			addChat (msg.profilePic, msg.userName, msg.chatMsg, whatChatDate, msg.chatType);
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

	chatListArea.classList.remove("focus");
	chatRoomArea.classList.add("focus");
	chatHeaderBtn[0].classList.remove("focus");
	chatHeaderBtn[1].classList.add("focus");



	// 뒤로가기 버튼 이벤트
// const btnChatBack = document.querySelectorAll(".btn_chatBack")[0];
// btnChatBack.addEventListener("click", () => {

	// cListarea.style.display = "block";
	// chatRoom.style.display = "none";
// });
}));


// old css

// btnChat.addEventListener("click", () => {
//   const chatArea = document.querySelector("#chat_area");
//   if (chatArea.style.display == "none") chatArea.style.display = "block";
//   else chatArea.style.display = "none";
// });

// const chatListItem = selectElements("#chat_area .chatListItem_area");
// const cListarea = document.querySelectorAll(".chatList_area")[0];
// const chatRoom = document.querySelectorAll(".chatRoom_area")[0];
// chatListItem.map(e => {
//   e.addEventListener("click", ({ target }) => {
//     cListarea.style.display = "none";
//     chatRoom.style.display = "block";
    
//    const content = document.querySelectorAll(".chatMsg_area p")[0];
//    content.textContent = target.innerText;



//   });
// });

// addChat 메소드
function addChat(profilePic, userName, chatMsg, whatChatDate, chatType){
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
			whatChatDate+
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


//채팅 객체 생성 
var chatInfo=function(flag, busId, userId, chatType, chatMsg, readed) {
	this.flag = flag;
	this.busId = busId;
	this.userId = userId;
	this.chatType = chatType;
	this.chatMsg = chatMsg;
	this.readed= readed;
}

// 타임 인터벌 체크 
var timecheck="";
var dnow=new Date().getDate();

setInterval(function(){
	var tnow=new Date();
	var checkTimeMsg=tnow.getFullYear()+"-"+tnow.getMonth()+"-"+tnow.getDate()+","+tnow.getDay();

	switch(new Date().getMonth()){
		case 1 : case 3 : case 5 : case 7 : case 8: case 10 : case 12 :  if(new Date().getDate()==1&&dnow==31){
			timecheck=chatInfo("T",busId,userId,"timecheck",checkTimeMsg,"T");
			socket.send(JSON.stringify(timecheck));
		}
		case 4: case 6: case 9: case 11 : if(new Date().getDate()==1&&dnow==31){
			timecheck=chatInfo("T",busId,userId,"timecheck",checkTimeMsg,"T");
			socket.send(JSON.stringify(timecheck));
		}
		case 2: if(new Date().getDate()==1&&dnow==28){
			timecheck=chatInfo("T",busId,userId,"timecheck",checkTimeMsg,"T");
			socket.send(JSON.stringify(timecheck));
		}
	}
	if(dnow<new Date().getDate()){
		timecheck=chatInfo("T",busId,userId,"timecheck",checkTimeMsg,"T");
		socket.send(JSON.stringify(timecheck));
		dnow=new Date().getDate();
	}
},1000*60);



//textArea 엔터키 이벤트
document.getElementById('content').addEventListener('keydown', function(event) {
	if (event.keyCode == 13) {
		var chatMsg=$("#content").val();
		var websocket=new chatInfo("C",busId,userId,"MSG",chatMsg,"T");
		socket.send(JSON.stringify(websocket));
		
		event.preventDefault();
		document.getElementById('content').value = "";
	}
});


//userName 그리고 userProfile 불러와야함 
var Message;
profilePic=userInfo.profilePic;
userName=userInfo.userName;

socket.onmessage=function(e){
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

		if(e.data=="연결"){
		console.log("연결됨");
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






