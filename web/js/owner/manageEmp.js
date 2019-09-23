class MngEmp {
  constructor() {
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body div");
    const empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];

    const btnApproval = document.querySelectorAll(".btn_Approval")[0];
    const btnReject = document.querySelectorAll(".btn_Reject")[0];

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => {
          e.classList.remove("focus", "slideIn");
          e.classList.add("slideOut");
        });
        mngBody[index].classList.add("focus", "slideIn");
      });
    });

    btnApproval.addEventListener("click", ({ target }) => {
      // const empId = target.nextElementSibling.value;
      // const data = `busId=${userInfo.selectBusId}&empId=${empId}`;
      // this.getResult("", target, data, this.approvalEmp);
      mngBody.map(e => {
        e.classList.remove("focus", "slideIn");
        e.classList.add("slideOut");
      });
      empInfo.classList.add("focus", "slideIn");
    });

    btnReject.addEventListener("click", ({ target }) => {
      const empId = target.previousElementSibling.value;
      console.log(empId);
    });
  }

  approvalEmp(target, respText) {
    if (respText != "fail") {
      console.log(empInfo);
    }
  }

  rejectEmp(target, respText) {}

  getResult(servletURL, target, data, fn) {
    const xhr = new XHLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(target, xhr.responseText);
    });
    xhr.open("post", "/p_190826_semi/" + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }
}

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

const mngEmp = new MngEmp();
