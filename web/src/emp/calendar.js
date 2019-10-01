class Task {
  constructor(taskDate, userId, taskMsg) {
    this.taskDate = taskDate;
    this.userId = userId;
    this.taskMsg = taskMsg;
  }
}

class Calendar {
  constructor(calList) {
    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;

    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];

    this.calList = calList;
    this.taskCount = 0;

    this.setInit();
  }

  setInit() {
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
  }

  // create date cells
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
        const calItem = document.querySelectorAll(".cal_item")[0];
        const view = document.querySelectorAll(".viewCalendar_area")[0];
        const add = document.querySelectorAll(".addCal_area")[0];
        calItem.classList.add("focus");
        view.classList.remove("focus");
        add.classList.add("focus");
      });
    }
    this.header.innerHTML = `${this.target.getFullYear()}년 <b>${this.target.getMonth() +
      1}월</b>`;
    this.countDate = 1;
    this.markEvent();
  }

  markEvent = () => {
    selectElements(".calendar_body .date").map(cell => {
      const targetDate = cell.getAttribute("id");
      const contianCals = this.calList.filter(c => c.calDate == targetDate);
      contianCals.map(e => {
        const div = document.createElement("div");
        div.setAttribute("id", e.calId);
        div.textContent = e.calTitle;
        cell.appendChild(div);
        div.addEventListener("click", event => {
          event.stopPropagation();
          const view = document.querySelectorAll(".viewCalendar_area")[0];
          const add = document.querySelectorAll(".addCal_area")[0];
          view.classList.add("focus");
          add.classList.remove("focus");
          // 일정 보기 event
          const calItem = document.querySelectorAll(".cal_item")[0];
          const calTitle = document.querySelectorAll(".calTitle")[0];
          const calSft = document.querySelectorAll(".calSft")[0];
          const calDetail = document.querySelectorAll(".calDetail")[0];
          const calTask = document.querySelectorAll(".calTask")[0];
          calItem.classList.add("focus");
          [...calTask.children].map(e => e.remove());

          const calId = event.target.getAttribute("id");
          const thisEvent = this.calList.find(e => e.calId == calId);
          calTitle.textContent = thisEvent.calTitle;
          calSft.textContent = thisEvent.sftName;
          calDetail.textContent = thisEvent.calDetail;
          thisEvent.taskList.map(t => {
            const ck = document.createElement("input");
            ck.setAttribute("type", "checkbox");
            ck.setAttribute("id", t.taskId);
            const lb = document.createElement("label");
            lb.setAttribute("for", t.taskId);
            if (t.userId != userInfo.userId) ck.disabled = true;
            if (t.done == true) ck.checked = true;
            lb.innerHTML = `<b>${t.userName} 종업원 : </b> ${t.taskMsg}`;
            calTask.appendChild(ck);
            calTask.appendChild(lb);
          });
        });
      });
    });
  };

  previous = () => {
    this.target = this.getMyDate(-1, 1);
    this.createCal();
  };

  next = () => {
    this.target = this.getMyDate(1, 1);
    this.createCal();
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

// Calendar 객체 생성!
promiseGetDefault("getCalList.do").then(res => new Calendar(JSON.parse(res)));
