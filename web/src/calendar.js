class Calendar {
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
    const lastDate = new Date(
      this.target.getFullYear(),
      this.target.getMonth() + 1,
      0
    ).getDate();

    selectElements(".calendar_body .date").map(v => v.remove());

    for (let i = 0; i < lastDate + firstDay; i++) {
      const cell = document.createElement("div");
      cell.setAttribute("class", "date");
      if (firstDay <= i && this.countDate <= lastDate) {
        cell.textContent = this.countDate;

        const compareY = this.target.getFullYear() == this.now.getFullYear();
        const compareM = this.target.getMonth() == this.now.getMonth();
        const compareD = this.countDate == this.now.getDate(); 
        if (compareY && compareM && compareD) cell.setAttribute("class", "date today");
        this.countDate++;
      }
      this.body.appendChild(cell);
    }
    this.header.textContent = `${this.target.getFullYear()}년 ${this.target.getMonth()}월`;
    this.countDate = 1;
  }

  previous = () => {
    this.target = new Date(this.target.getFullYear(), this.target.getMonth() - 1, 1);
    this.createCal();
  };

  next = () => {
    this.target = new Date(this.target.getFullYear(), this.target.getMonth() + 1, 1);
    this.createCal();
  };
}

const calendar = new Calendar();
