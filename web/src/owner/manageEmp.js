class MngEmp {
  constructor() {
    this.aprEmp = "";
    this.now = new Date();
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body .mngDiv");
    const empEdit = document.querySelectorAll(".editEmp_area")[0];
    const empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];

    const btnEditEmp = selectElements(".btn_editEmp");

    const sftItem = selectElements(".busShift_area .sftItem");

    const btnApproval = selectElements(".btn_approval");
    const btnReject = selectElements(".btn_reject");

    const btnApvEmp = document.querySelectorAll(".btn_arvEmp")[0];

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({ target }) => {
        mngHeader.map(e => e.classList.remove("focus"));
        target.classList.add("focus");
        empEdit.classList.remove("focus");
        empInfo.classList.remove("focus");
        mngBody.map(e => e.classList.remove("focus"));
        mngBody[index].classList.add("focus");
      });
    });

    btnEditEmp.map(b => {
      b.addEventListener("click", ({target}) => {
        mngBody.map(e => e.classList.remove("focus"));
        empEdit.classList.add("focus");
        empInfo.classList.remove("focus");
      })
    })

    btnApproval.map(e =>
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => e.classList.remove("focus"));
        empEdit.classList.remove("focus");
        empInfo.classList.add("focus");
        const id = target.nextElementSibling.value;
        this.aprEmp = id;
        const inptStart = selectElements("input[name='empStart']")[0];
        inptStart.value = this.getDate(this.now);
      })
    );

    btnReject.map(e =>
      e.addEventListener("click", ({ target }) => {
        const id = target.previousElementSibling.value;
        const data = `empId=${id}`;
        this.getResult("owner/rejectEmp.do", data, this.rejectEmp);
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
      const sftId = document.querySelectorAll(".sftItem")[0].getAttribute("id");
      const empStart = selectElements("input[name='empStart']")[0].value;

      const data = `empId=${this.aprEmp}&empType=${empType}&empWage=${empWage}&sftId=${sftId}&empStart=${empStart}`;
      this.getResult("owner/ApprovalEmp.do", data, this.approvalEmp);
    });
  }

  approvalEmp = respText => {
    if (respText != "fail") {
      this.aprEmp = "";
      socket.send(respText);
      location.href = contextPath + "owner/manageEmp.do";
    }
  };

  rejectEmp(respText) {
    if (respText != "fail") {
      socket.send(respText);
      location.href = contextPath + "owner/manageEmp.do";
    }
  }

  getResult(servletURL, data, fn) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(xhr.responseText);
    });
    xhr.open("post", contextPath + servletURL);
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

const dropShift = selectElements(".dropMenu .sftItem");
dropShift.map(s => {
  s.addEventListener("click", ({ currentTarget }) => {
    const target = document.querySelectorAll(".sftSelect")[0];
    const copy = currentTarget.cloneNode(true);
    target.firstElementChild.remove();
    target.appendChild(copy);
  });
});
