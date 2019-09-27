<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page
	import="com.semi.prm.model.vo.PayRollManagement,java.util.List"%>
<%
	int totalPay = 0;
	List<PayRollManagement> list = (List) request.getAttribute("list");
	for(PayRollManagement p : list) {
		totalPay += p.getPayRoll();
	}
%>
<script>
console.log(<%=totalPay%>);
var test = 100 - (((510300 - 72900)/510300)*100;
		  console.log(test);
</script>
<style>

@import url(https://fonts.googleapis.com/css?family=Ubuntu:300,500);
@import url(//weloveiconfonts.com/api/?family=entypo);

[class*="entypo-"]:before {
  font-family: 'entypo', sans-serif;
  padding-right: .5rem;
}
*, *:after, *:before {
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}
* {padding:0;margin:0;border: 0 none;position: relative;}
html {
  background: #8B9EB1;
  width: 100%;
  min-height: 100%;
  font-family: ubuntu, sans serif;
  font-weight: 300;
  font-size: 1rem;
  color: #000;
}
body {min-height: 100%;}
input {display: none;}
h1 {
  color: #98A2BA;
  font-size: inherit;
  font-weight: 300;
  line-height: 2rem;
  left: 1rem;
  position: absolute;
  z-index: 2;
}
body > p {
  color: #fff;
  text-align: center;
  font-size: 1.1rem;
  margin: 1rem 0;
}
nav {z-index: 2;}
label {
  color: #98A2BA;
  display: inline-block;
  cursor: pointer;
  padding: 0 1rem;
  line-height: 2rem;
}
label:hover {background: #2A394F;}
.continente {
  position: absolute;
  top: 0;
  right: 2rem;
  color: #fff;
}
.paises {
  position: absolute;
  left: 0;
  top: 3rem;
  width: 20%;
}
.paises label {
  display: block;
  width: 100%;
  line-height: 2.5rem;
  border-left: 3px solid transparent;
}
.section {
  background: #D6DBE7;
  margin: 2rem auto;
  width: 90%;
  min-width: 400px;
  height: 30rem;
  overflow: hidden;
  border-radius: 2px;
  box-shadow: 0 0 4px rgba(0,0,0,.4);
}

.section > div:before {
  content:'급여관리';
  position: absolute;
  left:0;top:0;right:0;
  text-align: center;
  font-size: 4rem;
  line-height: 16rem;
  color: rgba(0,0,0,.2);
}
article {
  margin: 1rem;
  height: 85%;
  overflow: auto;
}
article > div { 
  background: #fff;
  position: absolute;  
  height: 25rem;
  width: 100%;
}

table {
  table-layout: fixed;
  font-size: 1rem;
  width: 100%;
  color: #98A2BA;
}
caption {
  background: #EFF2F8;
  color: #717F93;
  font-weight: 500;
  padding: .75rem 0;
}
tr {
}
td {
  border-bottom: 1px solid #ddd;
  padding: .5rem 1rem;
}
tr td:nth-child(1) {
  width: 20%;
}
tr td:nth-child(2) {
  width: 40%;
  border-left: 1px solid #ddd;
  border-right: 1px solid #ddd;
}
tr td:nth-child(3) {
  width: 17%;
  font-weight: 500;
  text-align: right;
}
tr td:nth-child(4) {
  width: 12%;
  font-weight: 500;
  text-align: center;
  border-left: 1px solid #ddd;
}
tr td:nth-child(5) {
  width: 12%;
  font-weight: 500;
  text-align: center;
    border-left: 1px solid #ddd;
   border-right: 1px solid #ddd;
}
td span {
  background: #e5e5e5;
  display: inline-block;
  width: 100%;
  height: .7rem;
  border-radius: .3rem;
}
td span:before {
  content:'';
  display: inline-block;
  height: .7rem;
  vertical-align: top;
  border-radius: 2.5rem;
}
td span:after {
  position: absolute;
  right: -30%;
  top: 0;
  font-weight: 500;
}
.uno:before {
  background: #D87A80;
  width: 65%;
}
.dos:before {
  background: #F5994E;
  width: 73%;
}
.tres:before {
  background: #5AB1EF;
  width: 92%;
}
.cuatro:before {
  background: #2EC7C9;
  width: 53%;
}

td span p {
  content:'';
  display: inline-block;
  height: .7rem;
  vertical-align: top;
  border-radius: 2.5rem;
  background: #D87A80;
  width: 65%;
}

.italia span:before{opacity: .4}
.grecia span:before{opacity: .7}
.alemania span:before{opacity: .2}

caption:before {
  content: '';
  background-repeat: no-repeat;
  position: absolute;
  top:0; left:0;
  width: 50px;
  height: 50px;
  opacity: .5;  
}

.eu, .am, .as, .europa, .america, .asia, .pais {
  display: none
}

#europa:checked ~ .section .europa,
#europa:checked ~ .section .eu,
#america:checked ~ .section .america,
#america:checked ~ .section .am,
#asia:checked ~ .section .asia,
#asia:checked ~ .section .as {
  display: block;
}

#europa:checked ~ #espana:checked ~ .section .espana,
#europa:checked ~ #italia:checked ~ .section .italia,
#europa:checked ~ #grecia:checked ~ .section .grecia,
#europa:checked ~ #alemania:checked ~ .section .alemania,
#america:checked ~ #argentina:checked ~ .section .argentina,
#america:checked ~ #peru:checked ~ .section .peru,
#america:checked ~ #mexico:checked ~ .section .mexico,
#america:checked ~ #eeuu:checked ~ .section .eeuu,
#asia:checked ~ #japon:checked ~ .section .japon,
#asia:checked ~ #filipinas:checked ~ .section .filipinas,
#asia:checked ~ #china:checked ~ .section .china,
#asia:checked ~ #malasia:checked ~ .section .malasia {
  display: block;
  animation: .7s crece linear;
  transform-origin: 0 50%;
  animation-fill-mode: forwards;
}

#europa:checked ~ * [for='europa'],
#america:checked ~ * [for='america'],
#asia:checked ~ * [for='asia'],
#espana:checked ~ * [for='espana'],
#italia:checked ~ * [for='italia'],
#grecia:checked ~ * [for='grecia'],
#alemania:checked ~ * [for='alemania'],
#argentina:checked ~ * [for='argentina'],
#peru:checked ~ * [for='peru'],
#mexico:checked ~ * [for='mexico'],
#eeuu:checked ~ * [for='eeuu'],
#japon:checked ~ * [for='japon'],
#filipinas:checked ~ * [for='filipinas'],
#china:checked ~ * [for='china'],
#malasia:checked ~ * [for='malasia'] {
  background: #2A394F;
  border-left-color: #15A5FB;
  color: #15A5FB;
}

@keyframes crece {
  0% {transform: scalex(0)}
  100% {transform: scalex(1)}

}

.chart_content{
	width: 80%;
	float: right;
	margin-top: 4%;
	background-color: #e5e5e5;
	height: 100%;
}

table>caption>tbody>tr td{
	background-color: #e5e5e5;

}
</style>
<div id="content">

	<section class="item">
		<div class="item_header">
			<h2>급여관리</h2>
		</div>
		<div class="item_body">

				<input type="radio" id="europa" name="continente" checked='checked' />
				<input type="radio" id="america" name="continente" />
				<input type="radio" id="asia" name="continente" />
				
				<!-- Europa -->
				<input type="radio" id="espana" name="pais" checked='checked' />
				<input type="radio" id="italia" name="pais" />
				<input type="radio" id="grecia" name="pais" />
				<input type="radio" id="alemania" name="pais" />
				
				<!-- America -->
				<input type="radio" id="argentina" name="pais" />
				<input type="radio" id="peru" name="pais" />
				<input type="radio" id="mexico" name="pais" />
				<input type="radio" id="eeuu" name="pais" />
				
				<!-- Asia -->
				<input type="radio" id="japon" name="pais" />
				<input type="radio" id="filipinas" name="pais" />
				<input type="radio" id="china" name="pais" />
				<input type="radio" id="malasia" name="pais" />
				
				<div class="section">
				  <h1 class="entypo-address">e.get bus_name</h1>
				  <!-- labels continentes y grupos paises -->
				  <nav class='continente'>
					 <label for="europa" class="entypo-air">Monthly</label>
					 <label for="america" class="entypo-calendar">Daily</label>
					 <label for="asia" class="entypo-instagrem">Hourly</label>
				  </nav>
					<nav class='paises eu'>
					 <label for="espana" class="entypo-chart-bar">1개월</label>
					 <label for="italia" class="entypo-chart-line">3개월</label>
					 <label for="grecia" class="entypo-chart-area">6개월</label>
					 <label for="alemania" class="entypo-chart-pie">1년</label>
					</nav>
				  
					<nav class='paises am'>
					 <label for="argentina" class="entypo-rss">1개월</label>
					 <label for="peru" class="entypo-shareable">3개월</label>
					 <label for="mexico" class="entypo-shuffle">6개월</label>
					 <label for="eeuu" class="entypo-signal">1년</label>
					</nav>
				  
					<nav class='paises as'>
					 <label for="japon" class="entypo-flow-branch">1개월</label>
					 <label for="filipinas" class="entypo-flow-cascade">3개월</label>
					 <label for="china" class="entypo-flow-tree">6개월</label>
					 <label for="malasia" class="entypo-flow-parallel">1년</label>
					</nav>










				 <div class="chart_content">  
				  <article class='europa'>
					<div class='pais espana'>
				<table>
				   <caption>Monthly_Wage For One-Month</caption>
				   <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='uno' data-valor='100'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>      
					</div>
					
					<div class='pais italia'>
				<table>
				   <caption>Monthly_Wage For Three-Month</caption>
				   	     <tbody>
				   	     <%for(PayRollManagement p : list) { %>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <%
					  int forWidth = 100 - (((totalPay - p.getPayRoll()) / totalPay) * 100);
					  %>
					  <tr> 
					  <script>
					  console.log(<%=100 - (((totalPay - p.getPayRoll()) / totalPay) * 100)%>);
					
					  </script>
						 <td><%=p.getEmpName() %></td>
						<td><%=p.getPayRoll() %> <p style="float:right"><%=forWidth %> %</p>
						<span class='dos' ></span><p style="width:<%=forWidth%>%"></p></td>
						 <td><%=p.getWorkTime() %></td>
						  <td><%=p.getLateCount() %></td>
						  <td><%=p.getEarlyCount() %></td>
					  </tr>
					  
				<%} %>
				   </tbody>
				</table>      
					</div>
					
					<div class='pais grecia'>
				<table>
				   <caption>Monthly_Wage For Six-Month</caption>
				    <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='tres' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
			
				   </tbody>
				</table>      
					</div>
					
					<div class='pais alemania'>
				<table>
				   <caption>Monthly_Wage For One-Year</caption>
				     <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='cuatro' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>      
					</div>
				  </article>
				  
				  <article class='america'>
					  <div class='pais argentina'>
					  	<table>
				   <caption>Daily_Wage For One-Month</caption>
				      <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='uno' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais peru'>
					  	<table>
				   <caption>Daily_Wage For Three-Month</caption>
				     <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='dos' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais mexico'>
					 	<table>
				   <caption>Daily_Wage For Six-Month</caption>
				     <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='tres' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais eeuu'>
					 	<table>
				   <caption>Daily_Wage For One-Year</caption>
				     <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='cuatro' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
				  </article>
				  
				  <article class='asia'>
					  <div class='pais japon'>
					  	<table>
				   <caption>Hourly_Wage For One-Month</caption>
				      <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='uno' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais filipinas'>
						<table>
				   <caption>Hourly_Wage For Three-Month</caption>
				      <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='dos' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais china'>
						<table>
				   <caption>Hourly_Wage For Six-Month</caption>
				    <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='tres' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
					  <div class='pais malasia'>
					 	<table>
				   <caption>Hourly_Wage For One-Year</caption>
				     <tbody>
					  <tr> 
						 <td>이름</td>
						<td>급여 지급액</td>
						 <td>근무 시간</td>
						  <td>지각</td>
						  <td>조퇴</td>
					  </tr>
					  
					 <!-- 여기 부터 for 문 돌려서 계속 생성 -->
					  <tr> 
						 <td>신지섭</td>
						<td> 여기에 수령금액 적용하고 %값 밑에 width
						<span class='cuatro' data-valor='65'></span></td>
						 <td>345</td>
						   <td>지각</td>
						  <td>조퇴</td>
					  </tr>
				
				   </tbody>
				</table>   
					  </div>
					  
				  </article>
				 </div> 
				</div>
				
				<p>전체 직원 지급 예정 금액 : 여기에 더한값 넣기</p>
				<p></p>
		</div>
	</section>

</div>
<%@ include file="footer.jsp"%>