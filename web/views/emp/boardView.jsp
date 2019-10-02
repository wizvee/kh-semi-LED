<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.semi.board.model.vo.Board" %>    
<%@ include file="header.jsp"%>
<% 
   request.setCharacterEncoding("UTF-8");
   Board b=(Board)request.getAttribute("board");
   
   
%>
<!-- jQuery library -->

<style>
/*table#tbl-board{border-collapse: separate; 표의 테두리와 셀의 테두리 사이의 간격을 처리할지 정하는 속성
    border-width: 2px; 테두리의 두께를 지정하기
    border:3 solid black; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    border-spacing: 1px; 표의 테두리와 셀의 테두리 사이의 간격을 정하는 속성
    text-align: center; 문단 정렬 방식
    line-height: 1.5; 줄의 높이를 정하는 속성
    border-top: 1px solid #ccc; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    border-left: 1px solid #ccc; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    border-right: 1px solid #ccc; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    margin : 20px 10px; box레벨이나 inline레벨의 요소들간의 간격을 조절하는 속성
    
    cellpadding:0; 테이블안에 삽입되는 내용물과 테이블 테두리 와의 간격
    cellspacing:0; 테이블 안에 삽입되는 내용물의 바깥 경계선과 테이블 테두리의 간격
    width:100%;} 전체 테이블의 높이 비율

table#tbl-board th {width: 150px; 높이
    padding: 10px; 테두리와 컨텐츠 사이의 여백
    font-weight: bold; 글자 굵기 정하기
    vertical-align: top; 인라인 요소의 수직 정렬
    border-bottom: 1px solid #ccc; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    text-align:center;}  문단 정렬 방식

table#tbl-board td { width: 350px;
    padding: 10px; 테두리와 컨텐츠 사이의 여백
    vertical-align: top; 인라인 요소의 수직 정렬
    border-bottom: 1px solid #ccc; 테두리 두께, 스타일, 색상을 한번에 지정할 수 있는 속성
    word-break: break-all;  단어의 분리를 어떻게 할지 정하는 것
    align: center;  텍스트의 정렬 방향 의미
    }*/
.title{padding-left: 10px;
margin-bottom: 12px;
padding: 32px 4px 0;
font-size: 25px;
}
.board-count{
display:none;
}
.contents{
font-size: 20px;
padding-left: 10px;
padding-top: 25px;
color: #222;
line-height: 1.8em;
text-align: center;
/*border-top:1px solid black;
border-bottom:1px solid black;
border-left:1px solid black;
border-right:1px solid black;*/
}

.add-files{
font-size:20px;
text-align:center;
}
    
#title {text-align: center;
float:center;}

div.button input
{padding: 13px;
font-size: 18px;
float:right;
font-weight:bold;
text-decoration:none; /*선으로 텍스트를 꾸미게 해주는 특성*/
font-family:굴림체; /*글꼴 정하는 속성*/
box-shadow:inset #ffffff 0px 5px 8px -1px,#d6d6d6 1px 3px 2px;
o-box-shadow:inset #ffffff 0px 5px 8px -1px,#d6d6d6 1px 3px 2px;
-moz-box-shadow:inset #ffffff 0px 5px 8px -1px,#d6d6d6 1px 3px 2px;
-webkit-box-shadow:inset #ffffff 0px 5px 8px -1px,#d6d6d6 1px 3px 2px;
background:#1f70a6;
background:-o-linear-gradient(90deg, #1f70a6, #3b9ceb);
background:-moz-linear-gradient( center top, #1f70a6 5%, #3b9ceb 100% );
background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #1f70a6), color-stop(1, #3b9ceb) );
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1f70a6', endColorstr='#3b9ceb');
background:-webkit-linear-gradient(#1f70a6, #3b9ceb);
background:-ms-linear-gradient(#1f70a6, #3b9ceb);
background:linear-gradient(#1f70a6, #3b9ceb);
text-indent:0px; /*들여쓰기와 내어쓰기 양수면 들여쓰기, 음수면 내어쓰기 0이면 기본값 */
line-height:30px; /* 줄 높이 정하는 속성*/
-moz-border-radius:25px;
-webkit-border-radius:25px;
border-radius:25px; /*테두리를 둥글게 만듬*/
text-align:center;
vertical-align:middle; 
display:inline-block;
color:#cfde28;
width:100px;
height:60px;
text-shadow:#6daac2 2px 2px 0px;
border-color:#659dab;
border-width:2px;
border-style:solid;

}
.btn{
background-color: gray;
border-bottom:5px solid gray;
color:red;}
.btn:active{
border-bottom:2px solid gray;
}
.boardNo{
display:none;}
.
</style>

		<div class="content">
		  <div class="content-area">
		    <div class="boardNo" name="boardNo">
		      <%=b.getBoardNo() %>
		    </div>
		    <div class="title">
		      <%=b.getBoardTitle() %>
		      <br>
		      <br>
		      <br>
		    </div>
		    <div class="board-count">조회수
		      <%=b.getBoardReadCount() %>
		        </div>
		    </div>
		    
		    <div class="contents" id="content">
		      <%=b.getBoardContent() %>
		      <br>
		      <br>
		      <br>
		     
		    </div>
		 
		</div>
		<div class="add-files">
		    <%if(b.getBoardOriginalFilename()!=null){ %>
				 	<a href="<%=request.getContextPath()%>/board/download?fileName=<%=b.getBoardRenamedFilename()%>">
				 	<img src="<%=request.getContextPath()%>/emp/images/file.png"
				 	width="6px"/><%=b.getBoardOriginalFilename()%> <!--이미지 파일 업로드시 미리보기 기능 구현 -->
				 	</a>
				 	
				 <%} %>
		    </div>
		    <div class="button">
		<input type="button" value="목록으로" id="button"
		 onclick="location.href='<%=request.getContextPath()%>/views/boardList'"/>
		
		<input type="button" value="삭제" id="button2" name="delete" 
		 onclick="location.href='<%=request.getContextPath()%>/board/delete?no=<%=b.getBoardNo()%>'"/>
            </div>        
        <!--  <input type="button" value="파일삭제" id="button" name="filedelete"
		onclick="location.href='<%=request.getContextPath() %>/board/filedelete?fileName=<%=b.getBoardRenamedFilename() %>'"/>-->
		
		
		<%@ include file="footer.jsp"%>



