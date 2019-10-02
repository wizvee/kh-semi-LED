<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List, com.semi.board.model.vo.Board" %>
    <%
    List<Board> list=(List)request.getAttribute("list"); %>

    <%@ include file="header.jsp"%>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style>
#title {text-align: center;}

div.preview{width:50%, height:50%;}
    
</style>

<h2 id="title"> 게시글 등록 </h2>

<div class="container">
<table class="table table-bordered">

    <tbody>
        <!--multiplart/form-data를 사용하지 않으면 파일 경로명만 넘어가고 파일 내용이 전송되지 않음-->
        <form action="<%=request.getContextPath() %>/board/writereal" method="post" enctype="multipart/form-data">
            <tr>
                <th>제목: </th>  <!--form-control은 부트스트랩 고유 방식-->
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" class="form-control"/></td>
            </tr>
            
            <tr>
                <th>내용: </th> <!-- textarea에는 이미지 삽입 불가 -->
                <td><textarea cols="10" rows="5" autofocus="autofocus" placeholder="내용을 입력하세요. " 
                name="content" class="form-control" required="required" style="resize:vertical;">
                </textarea></td> 
            </tr>
            <tr>
					<th>첨부파일</th>
					<td>
					    <input type="file" name="up_file" id="up_file"  multiple/>
					    <div id="preview"> <!-- 여기에 이미지 미리보기(img태그의 id값이  preview인 곳에들어감) -->
					    </div>	
					</td>
			</tr>
            
                <td colspan="2">
                    <input type="submit" value="등록" onclick="location.href='<%=request.getContextPath()%>/views/boardList?cPage=<%=request.getAttribute("cPage")%>'"/>
                    <input type="button" value="글 목록 " class="pull-right" onclick="location.href='<%=request.getContextPath()%>/views/boardList'"/>
                    <!-- <a class="btn btn-default" onclick="sendData()"> 등록 </a>
                    <a class="btn btn-default" type="reset"> reset </a>
                    <a class="btn btn-default" onclick="javascript:location.href='list.jsp'">글 목록으로...</a> -->
                </td>
            </tr>
        </form>
    </tbody>
</table>
</div>
<script>
		//사진 미리보기 기능~!
		$(function(){
			$("#up_file").change(function(){ //change() 메소드는 해당요소의 value에 변화가 생길경우 감지하여 등록된 함수 실행
				$("#preview").html(""); //선택된 요소의 첫번째 text노드 리턴
				$.each($(this)[0].files,function(i,item){//each() 메소드  for in 문과 비슷함 객체나 배열의 요소 검사 
					var reader=new FileReader(); //자바스크립트에 기본제공기능, inputStream 과 같음
					
					reader.onload=function(e){ //onload에 on은 이벤트뜻 load라는 이벤트가발생한다는 것
						var img=$("<img>").attr({"src":e.target.result}).css({"width":"200px","height":"200px"}); //attr은 요소의 속성값 확인
						$("#preview").append(img); //id값이 preview에 img태그를 삽입
					}
					reader.readAsDataURL(item); //item에 들어가있는 값을 URL로 바꿔주고 이게 실행되면 onload가 실행되서 e에 값이 들어감
				})
			
			});
		});
			</script>
			
<%@ include file="footer.jsp"%>
