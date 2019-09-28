class Calendar {
  constructor() {
    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;

    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];

    this.calList;

    this.setInit();
  }

  setInit() {
    // get calendar list
    this.getResult("getCalList.do", "", this.setCalList);

    // create week cells
    const week = ["일", "월", "화", "수", "목", "금", "토"];
    week.map(w => {
      const cell = document.createElement("div");
      cell.setAttribute("class", "week");
      cell.textContent = w;
      this.body.appendChild(cell);
    });
    this.createCal();

    // button event
    const pre = document.querySelector("#btn_calPrv");
    const nxt = document.querySelector("#btn_calNxt");

    pre.addEventListener("click", this.previous);
    nxt.addEventListener("click", this.next);

    const istCal = document.querySelector("#btn_insertCal");
    istCal.addEventListener("click", this.setCal);
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
        const targetDate = target.getAttribute("id");
        // 일정 추가 event
        const date = document.getElementsByName("date")[0];
        const title = document.getElementsByName("title")[0];
        const content = document.getElementsByName("content")[0];
        date.value = targetDate;
        title.value = "";
        content.value = "";
        // 일정 보기 event
        const view = document.querySelectorAll(".viewCal_area")[0];
        this.calList.map(c => {
          console.log("ㅠㅠ");
          if (c.calDate == targetDate) view.textContent = c.calTitle;
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

  setCal = () => {
    const date = document.getElementsByName("date")[0].value;
    const sftId = document.getElementsByName("sftId")[0].value;
    const title = document.getElementsByName("title")[0].value;
    const content = document.getElementsByName("content")[0].value;

    const data = `calDate=${date}&sftId=${sftId}&calTitle=${title}&calDetail=${content}`;
    this.getResult("owner/insertCal.do", data, this.insertCal);
    // console.log(data);
  };

  insertCal = respText => {
    if (respText != "fail") {
      document.getElementsByName("title")[0].value = "";
      document.getElementsByName("content")[0].value = "";
      location.href = contextPath + "/owner/calendar.do";
    } else console.log("실패");
  };

  setCalList = respText => {
    console.log(JSON.parse(respText));
    this.calList = JSON.parse(respText);
  };

  getResult(servletURL, data, fn) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(xhr.responseText);
    });
    xhr.open("post", contextPath + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }

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

const calendar = new Calendar();

const sfts = selectElements(".sftList .sft");
sfts.map(s =>
  s.addEventListener("click", ({ target }) => {
    const id = document.getElementsByName("sftId")[0];
    const name = selectElements(".sftList .selectSft")[0];
    sfts.map(e => e.classList.remove("select"));
    target.classList.add("select");
    name.textContent = target.textContent;
    id.value = target.getAttribute("id");
  })
);

document.querySelector("#btn_addTask").addEventListener("click", () => {
  const area = document.querySelectorAll(".subCal_area .taskList")[0];
  const sftId = document.getElementsByName("sftId")[0].value;
  const emps = empList.find(e => e.sftId == sftId);

  const targetEmp = document.createElement("span");
  targetEmp.setAttribute("class", "inpt-outline");
  const task = document.createElement("input");
  task.setAttribute("class", "inpt-outline");

  if (emps == undefined) console.log("modal! alert!");
  else {
    area.appendChild(targetEmp);
    area.appendChild(task);
  }
});
