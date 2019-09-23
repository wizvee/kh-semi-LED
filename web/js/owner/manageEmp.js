class MngEmp {
  constructor() {
    this.setInit();
  }

  setInit() {
      const mngHeader = selectElements(".mngEmp_header span");
      const mngBody = selectElements(".mngEmp_body")[0];

      const btnWork = mngHeader[0];
      const btnResign = mngHeader[1];
      const btnEnroll = mngHeader[2];

      btnWork.addEventListener("click", () => {
        mngBody.innerText = "재직 test";
      })

      btnResign.addEventListener("click", () => {
        mngBody.innerText = "퇴직";
      })

      btnEnroll.addEventListener("click", () => {
        mngBody.innerText = "진행";
      })
  }
}

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

const mngEmp = new MngEmp();