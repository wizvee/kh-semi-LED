<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
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
}
.paises label {
  display: block;
  width: 9rem;
  line-height: 2.5rem;
  border-left: 3px solid transparent;
}
.section {
  background: #D6DBE7;
  margin: 2rem auto;
  width: 90%;
  max-width: 850px;
  min-width: 400px;
  height: 20rem;
  overflow: hidden;
  border-radius: 2px;
  box-shadow: 0 0 4px rgba(0,0,0,.4);
}

.section > div:before {
  content:'EsCss';
  position: absolute;
  left:0;top:0;right:0;
  text-align: center;
  font-size: 4rem;
  line-height: 16rem;
  color: rgba(0,0,0,.2);
}
article {
  margin: 1rem;
  height: 16rem;
  overflow: auto;
}
article > div { 
  background: #fff;
  position: absolute;  
  height: 16rem;
  width: 100%;
}
.pais img {
  max-height: 14rem;
  display: block;
  margin: 1rem auto;
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
  width: 45%;
}
tr td:nth-child(2) {
  width: 42%;
  border-left: 1px solid #ddd;
  border-right: 1px solid #ddd;
}
tr td:nth-child(3) {
  width: 13%;
  font-weight: 500;
  text-align: right;
}
td span {
  background: #e5e5e5;
  display: inline-block;
  width: 80%;
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
  content: attr(data-valor) '%';
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
.cinco:before {
  background: #CC324B;
  width: 37%;
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
.espana caption:before {background-image: url(http://www.institutovannghi.es/images/sp.gif)}
.italia caption:before {background-image: url(http://i01.i.aliimg.com/wsphoto/v0/659951967_1/ITALY-ITALIAN-Flag-150x90cm-5X3ft.jpg_50x50.jpg)}
.alemania caption:before {background-image: url(http://www.davidcalero.com/img/Banderas/Alemania.gif);}
.grecia caption:before {background-image: url(http://www.sobrefutbol.com/imagenes/banderas/grecia.gif);}
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
				  <h1 class="entypo-address">Double checked</h1>
				  <!-- labels continentes y grupos paises -->
				  <nav class='continente'>
					 <label for="europa" class="entypo-air">1rodnjf</label>
					 <label for="america" class="entypo-calendar">America</label>
					 <label for="asia" class="entypo-instagrem">Asia</label>
				  </nav>
					<nav class='paises eu'>
					 <label for="espana" class="entypo-chart-bar">1개월</label>
					 <label for="italia" class="entypo-chart-line">3개월</label>
					 <label for="grecia" class="entypo-chart-area">6개월</label>
					 <label for="alemania" class="entypo-chart-pie">1년</label>
					</nav>
				  
					<nav class='paises am'>
					 <label for="argentina" class="entypo-rss">Argentina</label>
					 <label for="peru" class="entypo-shareable">Peru</label>
					 <label for="mexico" class="entypo-shuffle">Mexico</label>
					 <label for="eeuu" class="entypo-signal">EE.UU.</label>
					</nav>
				  
					<nav class='paises as'>
					 <label for="japon" class="entypo-flow-branch">Japón</label>
					 <label for="filipinas" class="entypo-flow-cascade">Filipinas</label>
					 <label for="china" class="entypo-flow-tree">China</label>
					 <label for="malasia" class="entypo-flow-parallel">Malasia</label>
					</nav>










				 <div class="chart_content">  
				  <article class='europa'>
					<div class='pais espana'>
				<table>
				   <caption>Qué opinas sobre los aspectos siguientes:</caption>
				   <tbody>
					  <tr>
						 <td>La visitaré por su gente</td>
						<td><span class='uno' data-valor='65'></span></td>
						 <td>345</td>
					  </tr>
					  <tr>
						 <td>Iría por su comida</td>
						<td><span class='dos' data-valor='73'></span></td>
						 <td>456</td>
					  </tr>
					  <tr>
						 <td>La fiesta me llama</td>
						<td><span class='tres' data-valor='99'></span></td>
						 <td>980</td>
					  </tr>
					  <tr>
						 <td>Sol y playa me pierde</td>
						<td><span class='cuatro' data-valor='53'></span></td>
						 <td>666</td>
					  </tr>
					  <tr>
						 <td>Sus montañas son mi hogar</td>
						<td><span class='cinco' data-valor='37'></span></td>
						 <td>259</td>
					  </tr>
					  <tr>
							<td>Sus montañas son mi hogar</td>
						   <td><span class='cinqco' data-valor='37'></span></td>
							<td>259</td>
						 </tr>
						 <tr>
								<td>Sus montañas son mi hogar</td>
							   <td><span class='cinaco' data-valor='37'></span></td>
								<td>259</td>
							 </tr>
							 
							 <tr>
									<td>Sus montañas son mi hogar</td>
								   <td style="background-color: red;"><span class='cidnco' data-valor='37'></span></td>
									<td>259</td>
								 </tr>
								 <tr>
										<td>Sus montañas son mi hogar</td>
									   <td style="background-color: red;"><span class='cinco' data-valor='37'></span></td>
										<td>259</td>
									 </tr>
									 <tr>
											<td>Sus montañas son mi hogar</td>
										   <td style="background-color: red;"><span class='cinco' data-valor='37'></span></td>
											<td>259</td>
										 </tr>
										 <tr>
												<td>Sus montañas son mi hogar</td>
											   <td><span class='cinco' data-valor='37'></span></td>
												<td>259</td>
											 </tr>
				   </tbody>
				</table>      
					</div>
					
					<div class='pais italia'>
				<table>
				   <caption>Qué opinas sobre los aspectos siguientes:</caption>
				   <tbody>
					  <tr>
						 <td>La visitaré por su gente</td>
						<td><span class='uno' data-valor='65'></span></td>
						 <td>345</td>
					  </tr>
					  <tr>
						 <td>Iría por su comida</td>
						<td><span class='dos' data-valor='73'></span></td>
						 <td>456</td>
					  </tr>
					  <tr>
						 <td>La noche y la fiesta me llaman</td>
						<td><span class='tres' data-valor='99'></span></td>
						 <td>980</td>
					  </tr>
					  <tr>
						 <td>El sol y sus playas me pierden</td>
						<td><span class='cuatro' data-valor='53'></span></td>
						 <td>666</td>
					  </tr>
					  <tr>
						 <td>Sus montañas son mi hogar</td>
						<td><span class='cinco' data-valor='37'></span></td>
						 <td>259</td>
					  </tr>
				   </tbody>
				</table>      
					</div>
					
					<div class='pais grecia'>
				<table>
				   <caption>Qué opinas sobre los aspectos siguientes:</caption>
				   <tbody>
					  <tr>
						 <td>La visitaré por su gente</td>
						<td><span class='uno' data-valor='65'></span></td>
						 <td>345</td>
					  </tr>
					  <tr>
						 <td>Iría por su comida</td>
						<td><span class='dos' data-valor='73'></span></td>
						 <td>456</td>
					  </tr>
					  <tr>
						 <td>La noche y la fiesta me llaman</td>
						<td><span class='tres' data-valor='99'></span></td>
						 <td>980</td>
					  </tr>
					  <tr>
						 <td>El sol y sus playas me pierden</td>
						<td><span class='cuatro' data-valor='53'></span></td>
						 <td>666</td>
					  </tr>
					  <tr>
						 <td>Sus montañas son mi hogar</td>
						<td><span class='cinco' data-valor='37'></span></td>
						 <td>259</td>
					  </tr>
				   </tbody>
				</table>      
					</div>
					
					<div class='pais alemania'>
				<table>
				   <caption>Qué opinas sobre los aspectos siguientes:</caption>
				   <tbody>
					  <tr>
						 <td>La visitaré por su gente</td>
						<td><span class='uno' data-valor='65'></span></td>
						 <td>345</td>
					  </tr>
					  <tr>
						 <td>Iría por su comida</td>
						<td><span class='dos' data-valor='73'></span></td>
						 <td>456</td>
					  </tr>
					  <tr>
						 <td>La noche y la fiesta me llaman</td>
						<td><span class='tres' data-valor='99'></span></td>
						 <td>980</td>
					  </tr>
					  <tr>
						 <td>El sol y sus playas me pierden</td>
						<td><span class='cuatro' data-valor='53'></span></td>
						 <td>666</td>
					  </tr>
					  <tr>
						 <td>Sus montañas son mi hogar</td>
						<td><span class='cinco' data-valor='37'></span></td>
						 <td>259</td>
					  </tr>
				   </tbody>
				</table>      
					</div>
				  </article>
				  
				  <article class='america'>
					  <div class='pais argentina'>
					  <img alt='' src='https://4.bp.blogspot.com/-K5zec53PX2k/UrOmNoYza6I/AAAAAAAAAeU/Nc5i0G71N2A/s1600/bandera2.png'>
					  </div>
					  <div class='pais peru'>
					  <img alt='' src='http://www.mceconsultoresasociados.com/ps13/peru.jpg'>
					  </div>
					  <div class='pais mexico'>
					  <img alt='' src='http://fernandortizg.files.wordpress.com/2012/09/bandera-de-mexico.jpg'>
					  </div>
					  <div class='pais eeuu'>
					  <img alt='' src='http://www.computerworld.es/archivos/201211/bandera_eeuu_hi.png'>
					  </div>
				  </article>
				  
				  <article class='asia'>
					  <div class='pais japon'>
					  <img alt='' src='http://www.lovethesepics.com/wp-content/uploads/2012/09/Japan-Fujiyoshida-and-Mount-Fuji.jpg'>
					  </div>
					  <div class='pais filipinas'>
					  <img alt='' src='http://img01.lavanguardia.com/2010/12/07/Filipinas_54083687758_600_226.jpg'>
					  </div>
					  <div class='pais china'>
					  <img alt='' src='http://www.jaimelago.org/sites/default/files/u1/china.jpg'>
					  </div>
					  <div class='pais malasia'>
					  <img alt='' src='https://3.bp.blogspot.com/-wE_Jh3dv8jI/UzROlo7XU3I/AAAAAAAABJw/0Z_0YgOFmn4/s800/Shwedagon+Pagoda-007.jpg'>
					  </div>
				  </article>
				 </div> 
				</div>
				
				<p>Study of tabs by double checked. Pure Css. <a href='https://escss.blogspot.com/2014/01/the-Css-Mentalist.html'>The post and a simpler demo</a>.</p>
				<p>The base of this technique is the pen <a href='https://codepen.io/Kseso/full/IlwoG'>"The Css Mentalist"</a></p>

		</div>
	</section>

</div>
<%@ include file="footer.jsp"%>