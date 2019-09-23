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
    
    const content = document.querySelectorAll(".chatMsg_area p")[0];
    content.textContent = target.innerText;

    const busId=$(target).find("input").val();
    console.log(busId);
    
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
    			content.textContent ='대화내용이 없습니다.';
    		}
    	},
    	error:function(e){
    		alert(e);
    	}
    })
    
  });
});

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

const btnChatBack = document.querySelectorAll(".btn_chatBack")[0];
btnChatBack.addEventListener("click", () => {
  cListarea.style.display = "block";
  msgArea.style.display = "none";
});

