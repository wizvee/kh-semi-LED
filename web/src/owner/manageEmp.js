class MngEmp {
  constructor() {
    this.aprEmp = "";
    this.now = new Date();
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body .mngDiv");
    const empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];

    const sftItem = selectElements(".busShift_area .sftItem");

    const btnApproval = selectElements(".btn_Approval");
    const btnReject = selectElements(".btn_Reject");

    const btnApvEmp = document.querySelectorAll(".btn_arvEmp")[0];

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({ target }) => {
        mngHeader.map(e => e.classList.remove("focus"));
        target.classList.add("focus");
        mngBody.map(e => e.classList.remove("focus"));
        mngBody[index].classList.add("focus");
      });
    });

    btnApproval.map(e =>
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => e.classList.remove("focus"));
        empInfo.classList.add("focus");
        const id = target.nextElementSibling.value;
        this.aprEmp = id;
        const inptStart = selectElements("input[name='empStart']")[0];
        inptStart.value = this.getDate(this.now);
      })
    );

    sftItem.map(e =>
      e.addEventListener("click", ({ target }) => {
        sftItem.map(e => e.classList.remove("selected"));
        const targetDiv = target.parentElement;
        targetDiv.classList.add("selected");
      })
    );

    btnApvEmp.addEventListener("click", () => {
      const empType = selectElements("input[name='empType']").find(
        e => e.checked
      ).value;
      const empWage = selectElements("input[name='empWage']")[0].value;
      const sftId = selectElements(".select input[name='sftId']")[0].value;
      const empStart = selectElements("input[name='empStart']")[0].value;

      const data = `empId=${this.aprEmp}&empType=${empType}&empWage=${empWage}&sftId=${sftId}&empStart=${empStart}`;
      this.getResult("owner/ApprovalEmp.do", data, this.approvalEmp);
    });
  }

  approvalEmp = respText => {
    if (respText != "fail") {
      this.aprEmp = "";
      socket.send(respText);
      location.href = "/p_190826_semi/owner/manageEmp.do";
    }
  };

  rejectEmp(respText) {}

  getResult(servletURL, data, fn) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(xhr.responseText);
    });
    xhr.open("post", "/p_190826_semi/" + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }

  getDate(time) {
    const year = time.getFullYear();
    const month =
      time.getMonth() + 1 < 10
        ? `0${time.getMonth() + 1}`
        : time.getMonth() + 1;
    const date = time.getDate() < 10 ? `0${time.getDate()}` : time.getDate();
    return `${year}-${month}-${date}`;
  }
}

const mngEmp = new MngEmp();
