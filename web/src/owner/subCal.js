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
            	  console.log("asd");
            	  for(var j = 0; j < data.length; j ++ ){
                if(data.length < 0) {
                  console.log(data[j]["name"]);
                  var card = "<fieldset><legend> 사업장 근무자 리스트 </legend><div class='item_atd'>";
                  if(data[j]["atdType"].length > 1) {
                	  card += "<div><div class='card'><div class='additional'><div class='user-card'><div class='level center'>LEVEL "+data[j]['level']+"!=null?"+data[j]['level']+":'미설정'%></div><div class='points center'>"+data[j]['sftName']+"</div>";
                	  card += "<img alt='' src=ContextPath()+'/upload/profile/'"+data[j]['pic']+"></div><div class='more-info'><h1>"+data[j]['name']+"</h1><div class='coords'><span>지정 근무 시간</span><span>"+data[j]['sftOn']+" ~ "+data[j]['sftOff']+"</span></div>";
                	  card += "<div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>"+data[j]['name']+"</h1><hr><p style='margin:10%'>";
                	  card += ""+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(0, 4):''%> 년<%="+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(4, 6):''%> 월";
                	  card += ""+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(6, 8):''%> 일의<br> 근무 기록 입니다.</p>";
                	  card += "<div class='atd_check_late'><p>근무 시간</p><p>"+data[j]['sftOn']+"~"+data[j]['sftOff']+"</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                	  card += ": 지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div>";
                	  card += "<div class='atd_progress_wraper'><%double widthLate = 0;double widthEarly = 0;double widthNomal = 0;if("+data[j]['sftOn']+"!=null){double stAtd = "+data[j]['atdOn']+";double enAtd = "+data[j]['atdOff']+";";
                	  card += "double stSft = "+data[j]['sftOn']+";double enSft = "+data[j]['sftOff']+";if (stSft < stAtd) {widthLate = ((100 - ((((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100)));}";
                	  card += "if (enSft > enAtd) {widthEarly = ((100 - ((((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100)));}widthNomal = 100 - (widthLate + widthEarly);}%>";
                	  card += "<div style='float:left; height:100%;  background-color: red; width:<%=widthLate%>%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :<%=widthNomal%>%;'></div><div style='float:left; height:100%; background-color: dodgerblue; width :<%=widthEarly%>%;'></div>";
                	  card += "</div></div><span class='more'>Mouse over the card for more info</span></div></div>";
                  }else {
                    card += "<div><div class='card green'><div class='additional'><div class='user-card'><div class='level center'>LEVEL "+data[j]['level']+"!=null?"+data[j]['level']+":'미설정'%></div><div class='points center'>"+data[j]['sftName']+"</div>";
                	  card += "<img alt='' src=ContextPath()+'/upload/profile/'"+data[j]['pic']+"></div><div class='more-info'><h1>"+data[j]['name']+"</h1><div class='coords'><span>지정 근무 시간</span><span>"+data[j]['sftOn']+" ~ "+data[j]['sftOff']+"</span></div>";
                	  card += "<div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>"+data[j]['name']+"</h1><hr><p style='margin:10%'>";
                	  card += ""+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(0, 4):''%> 년<%="+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(4, 6):''%> 월";
                	  card += ""+data[j]['sAtd']+"!=null?"+data[j]['sAtd']+".substring(6, 8):''%> 일의<br> 근무 기록 입니다.</p>";
                	  card += "<div class='atd_check_late'><p>근무 시간</p><p>"+data[j]['sftOn']+"~"+data[j]['sftOff']+"</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                	  card += ": 지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div>";
                	  card += "<div class='atd_progress_wraper'><%double widthLate = 0;double widthEarly = 0;double widthNomal = 0;if("+data[j]['sftOn']+"!=null){double stAtd = "+data[j]['atdOn']+";double enAtd = "+data[j]['atdOff']+";";
                	  card += "double stSft = "+data[j]['sftOn']+";double enSft = "+data[j]['sftOff']+";if (stSft < stAtd) {widthLate = ((100 - ((((enSft - stSft) - (stAtd - stSft)) / (enSft - stSft)) * 100)));}";
                	  card += "if (enSft > enAtd) {widthEarly = ((100 - ((((enSft - stSft) - (enSft - enAtd)) / (enSft - stSft)) * 100)));}widthNomal = 100 - (widthLate + widthEarly);}%>";
                	  card += "<div style='float:left; height:100%;  background-color: red; width:<%=widthLate%>%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :<%=widthNomal%>%;'></div><div style='float:left; height:100%; background-color: dodgerblue; width :<%=widthEarly%>%;'></div>";
                	  card += "</div></div><span class='more'>Mouse over the card for more info</span></div></div>";
                  }
                  	  card += "</div></fieldset>";
            	  }
                  
            	  $(".makeCard").append(card);
                  
                  
                  

          
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
