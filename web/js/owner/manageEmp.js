class MngEmp {
  constructor() {
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body div");
    const empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];

    const sftItem = selectElements(".busShift_area .sftItem");

    const btnApproval = selectElements(".btn_Approval");
    const btnReject = selectElements(".btn_Reject");

    const btnEnrollEmp = document.querySelectorAll(".btn_enrollEmp")[0];

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => e.classList.remove("focus"));
        mngBody[index].classList.add("focus");
      });
    });

    btnApproval.map(e =>
      e.addEventListener("click", ({ target }) => {
        mngBody.map(e => e.classList.remove("focus"));
        empInfo.classList.add("focus");
        const id =target.nextElementSibling.value;
        selectElements("input[name='empId']")[0].value = id;
      })
    );

    sftItem.map(e =>
      e.addEventListener("click", ({ target }) => {
        sftItem.map(e => e.classList.remove("selected"));
        const targetDiv = target.parentElement;
        targetDiv.classList.add("selected");
        
      })
    );

    btnEnrollEmp.addEventListener("click", () => {
      const empId = selectElements("input[name='empId']")[0].value;
      const empType = selectElements("input[name='empType']").find(
        e => e.checked
      ).value;
      const empWage = selectElements("input[name='empWage']")[0].value;
      const sftId = selectElements(".selected input[name='sftId']")[0].value;

      const data = `empId=${empId}&empType=${empType}&empWage=${empWage}&sftId=${sftId}`;
      
      this.getResult("/owner/enrollEmp.do", data, this.approvalEmp);
    });
  }

  approvalEmp(respText) {
    if (respText != "fail") {
      console.log(respText);
    }
  }

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
}

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

const mngEmp = new MngEmp();
