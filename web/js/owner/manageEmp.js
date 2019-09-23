class MngEmp {
  constructor() {
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body div");
    const empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];

    const btnApproval = selectElements(".btn_Approval");
    const btnReject = selectElements(".btn_Reject");

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => e.classList.remove("focus"));
        mngBody[index].classList.add("focus");
      });
    });

    btnApproval.map(e => e.addEventListener("click", ({target}) => {
      mngBody.map(e => e.classList.remove("focus"));
      empInfo.classList.add("focus")
    }));
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
