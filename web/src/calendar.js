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

    // add task div
    const addTask = document.querySelector("#btn_addTask");
    addTask.addEventListener("click", () => {
      const area = document.querySelectorAll(".subCal_area .taskList")[0];
      const sftId = document.getElementsByName("sftId")[0].value;
      const containEmp = empList.filter(e => e.sftId == sftId);

      if ([...containEmp].length == 0) console.log("modal! alert!");
      else {
        const targetEmpList = document.createElement("div");
        targetEmpList.setAttribute("class", "targetEmpList");
        const targetEmp = document.createElement("span");
        targetEmp.setAttribute("id", [...containEmp][0].userId);
        targetEmp.setAttribute("class", "selectTargetUser");
        targetEmp.textContent = [...containEmp][0].userName;
        const ul = document.createElement("ul");
        ul.setAttribute("class", "dropMenu");
        [...containEmp].map(e => {
          const li = document.createElement("li");
          li.setAttribute("id", e.userId);
          li.setAttribute("class", "taskUser");
          li.textContent = e.userName;
          ul.appendChild(li);
          li.addEventListener("click", ({ target }) => {
            const selectTaskEmp = target.parentElement.previousElementSibling;
            selectTaskEmp.textContent = target.textContent;
            selectTaskEmp.setAttribute("id", target.getAttribute("id"));
          });
        });

        targetEmpList.appendChild(targetEmp);
        targetEmpList.appendChild(ul);

        const task = document.createElement("input");
        task.setAttribute("class", "inpt-outline");
        task.setAttribute("name", "taskMsg");

        area.appendChild(targetEmpList);
        area.appendChild(task);

        ++this.taskCount;
      }
    });

    const istCal = document.querySelector("#btn_insertCal");
    istCal.addEventListener("click", this.setCalData);
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
        const view = document.querySelectorAll(".viewCalendar_area")[0];
        const add = document.querySelectorAll(".addCal_area")[0];
        view.classList.remove("focus");
        add.classList.add("focus");

        selectElements(".taskList div").map(e => e.remove());
        selectElements(".taskList input").map(e => e.remove());

        const targetDate = target.getAttribute("id");
        // 일정 추가 event
        const calItem = document.querySelectorAll(".cal_item")[0];
        const date = document.getElementsByName("date")[0];
        const title = document.getElementsByName("title")[0];
        const content = document.getElementsByName("content")[0];
        calItem.classList.add("focus");
        date.value = targetDate;
        title.value = "";
        content.value = "";
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

  setCalData = () => {
    const date = document.getElementsByName("date")[0].value;
    const sftId = document.getElementsByName("sftId")[0].value;
    const title = document.getElementsByName("title")[0].value;
    const content = document.getElementsByName("content")[0].value;

    const taskUserId = document.querySelectorAll(".selectTargetUser");
    const taskMsg = document.getElementsByName("taskMsg");
    const taskArr = [];

    for (let i = 0; i < this.taskCount; i++) {
      const task = new Task(date, taskUserId[i].getAttribute("id"), taskMsg[i].value);
      taskArr.push(task);
    }

    this.taskCount = 0;
    
    const data = `calDate=${date}&sftId=${sftId}&calTitle=${title}&calDetail=${content}&taskArr=${JSON.stringify(
      taskArr
    )}`;
    this.getResult("owner/insertCal.do", data, this.insertCal);
  };

  insertCal = respText => {
    if (respText != "fail") {
      document.getElementsByName("title")[0].value = "";
      document.getElementsByName("content")[0].value = "";
      location.href = contextPath + "/owner/calendar.do";
    } else console.log("실패");
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

const sfts = selectElements(".sftList .sft");
sfts.map(s =>
  s.addEventListener("click", ({ target }) => {
    selectElements(".taskList div").map(e => e.remove());
    selectElements(".taskList input").map(e => e.remove());

    const id = document.getElementsByName("sftId")[0];
    const name = selectElements(".sftList .selectSft")[0];
    sfts.map(e => e.classList.remove("select"));
    target.classList.add("select");
    name.textContent = target.textContent;
    id.value = target.getAttribute("id");
  })
);
