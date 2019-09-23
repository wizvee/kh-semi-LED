class MngEmp {
  constructor() {
    this.setInit();
  }

  setInit() {
    const mngHeader = selectElements(".mngEmp_header span");
    const mngBody = selectElements(".mngEmp_body div");

    mngHeader.map((e, index) => {
      e.addEventListener("click", ({target}) => {
        mngBody.map(e => e.classList.remove("focus"));
        mngBody[index].classList.add("focus");
      })
    });
  }
}

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

const mngEmp = new MngEmp();
