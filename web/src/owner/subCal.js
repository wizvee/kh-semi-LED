class SubCal {
  constructor() {
    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;

    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];

    this.setInit();
  }

  setInit() {
    const week = ["일", "월", "화", "수", "목", "금", "토"];
    week.map(w => {
      const cell = document.createElement("div");
      cell.setAttribute("class", "week");
      cell.textContent = w;
      this.body.appendChild(cell);
    });
    this.createCal();

    const pre = document.querySelector("#btn_calPrv");
    const nxt = document.querySelector("#btn_calNxt");

    pre.addEventListener("click", this.previous);
    nxt.addEventListener("click", this.next);
  }

  createCal() {
    const firstDay = this.target.getDay();
    const lastDate = this.getMyDate(1, 0).getDate();

    selectElements(".calendar_body .date").map(v => v.remove());

    for (let i = 0; i < lastDate + firstDay; i++) {
      const cell = document.createElement("div");
      cell.setAttribute("class", "date");
      if (firstDay <= i && this.countDate <= lastDate) {
        cell.setAttribute("id", this.setDateId(this.countDate));
        cell.textContent = this.countDate;

        const compareY = this.target.getFullYear() == this.now.getFullYear();
        const compareM = this.target.getMonth() == this.now.getMonth();
        const compareD = this.countDate == this.now.getDate();
        if (compareY && compareM && compareD)
          cell.setAttribute("class", "date today");
        this.countDate++;
      }
      this.body.appendChild(cell);
      cell.addEventListener("click", ({ target }) => {
        // 날짜 클릭 이벤트
        console.log(target.getAttribute("id"));
          $.ajax({
            url: contextPath + "ajaxAtd.do",
            data : {"date" : target.getAttribute("id")},
              method : "post",
              dataType: "json",
              success: function(data) {
            	  console.log(data);
            	  for(var j = 0; j < data.length; j ++ ){
           		  var card = "";
                  var widthLate = 0;
                  var widthEarly = 0;
                  var widthNomal = 0; 
                  var stAtd = data[j]['atdOn'];
                  var enAtd = data[j]['atdOff'];
                  var  stSft = data[j]['sftOn'];
                  var  enSft = data[j]['sftOff'];
                  if (enSft > enAtd) {
                    widthEarly = ((100 - ((((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100))); }

                    if (stSft < stAtd) {
                      widthLate = ((100 - ((((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100)));}

                    widthNomal = 100 - (widthLate + widthEarly);


                // if(data[j]["atdType"].length > 0) {
                //   var card1 = "<div class='makeCard'><div class='card'><div class='additional'><div class='user-card'><div class='level center'>LEVEL "+data[j]['level']!=null?data[j]['level']:'미설정'+"</div>";
                //   var card2= "<div class='points center'>"+data[j]['sftName']+"</div><img alt='' src=''"+data[j]['pic']!=null?data[j]['pic']:''+">";
                //   var card3 = "</div><div class='more-info'><h1>"+data[j]['name']+"</h1><div class='coords'><span>지정 근무 시간</span> <span>"+data[j]['sSft']+" ~ "+data[j]['nSft']+"</span>";
                //   var card4 = "</div><div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>"+data[j]['name']+"</h1><hr><p style='margin:10%'>";
                //   var card5 = ""+data[j]['sAtd']!=null?data[j]['sAtd'].substring(0,4):''+" 년"+data[j]['sAtd']!=null?data[j]['sAtd'].substring(4,6):''+" 월";
                //   var card6 = ""+data[j]['sAtd']!=null?data[j]['sAtd'].substring(6,8):''+" 일의<br> 근무 기록 입니다.</p>";
                //   var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>"+data[j]['sSft']+"~"+data[j]['nSft']+"</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                //   var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                //   var card9 = "<div	style='float:left; height:100%;  background-color: red; width:"+widthLate+"%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :"+widthNomal+"%;'></div>";
                //   var card10 = "<div	style='float:left; height:100%; background-color: dodgerblue; width :"+widthEarly+"%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
               
                  // }else {
                    var card1 = "<div class='makeCard'><div class='card green'><div class='additional'><div class='user-card'><div class='level center'>LEVEL "+(data[j]['level']!=null?data[j]['level']:'미설정')+"</div>";
                    var card2= "<div class='points center'>"+data[j]['sftName']+"</div><img alt='' src=''"+(data[j]['pic']!=null?data[j]['pic']:'')+"></div>";
                    var card3 = "<div class='more-info'><h1>"+data[j]['name']+"</h1><div class='coords'><span>지정 근무 시간</span> <span>"+data[j]['sSft']+" ~ "+data[j]['nSft']+"</span>";
                    var card4 = "</div><div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>"+data[j]['name']+"</h1><hr><p style='margin:10%'>";
                    var card5 = ""+(data[j]['sAtd']!=null?data[j]['sAtd'].substring(0,4):'')+" 년"+(data[j]['sAtd']!=null?data[j]['sAtd'].substring(4,6):'')+" 월";
                    var card6 = ""+(data[j]['sAtd']!=null?data[j]['sAtd'].substring(6,8):'')+" 일의<br> 근무 기록 입니다.</p>";
                    var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>"+data[j]['sSft']+"~"+data[j]['nSft']+"</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                    var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                    var card9 = "<div style='float:left; height:100%;  background-color: red; width:"+widthLate+"%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :"+widthNomal+"%;'></div>";
                    var card10 = "<div style='float:left; height:100%; background-color: dodgerblue; width :"+widthEarly+"%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
                        // }
                    card = card1 +  card2;
                    card = card +  card3;
                    card = card +  card4;
                    card = card +  card5;
                    card = card +  card6;
                    card = card +  card7;
                    card = card +  card8;
                    card = card +  card9;
                    card = card +  card10; 
                    
                    console.log(card1);
                    console.log(card2);
                    console.log(card3);
                    console.log(card4);
                    console.log(card5);
                    console.log(card6);
                    console.log(card7);
                    console.log(card8);
                    console.log(card9);
                    console.log(card10);
                    console.log(card);
                    $(".item_atd").html(card);
                  }
                  
                  
                  

          
                
      
            	  
            	  
            	  
            	  
                
              }
            });
          
          
          

    });
    }
    this.header.innerHTML = `${this.target.getFullYear()}년 <b>${this.target.getMonth() +
      1}월</b>`;
    this.countDate = 1;
  }

  previous = () => {
    this.target = this.getMyDate(-1, 1);
    this.createCal();
  };

  next = () => {
    this.target = this.getMyDate(1, 1);
    this.createCal();
  };

  getMyDate(month, date) {
    return new Date(
      this.target.getFullYear(),
      this.target.getMonth() + month,
      date
    );
  }

  setDateId(date) {
    const month =
      this.target.getMonth() + 1 < 10
        ? `0${this.target.getMonth() + 1}`
        : this.target.getMonth() + 1;
    date = date < 10 ? `0${date}` : date;
    return `${this.target.getFullYear()}-${month}-${date}`;
  }
}

const subCal = new SubCal();
