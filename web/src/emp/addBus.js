class AddBus {
  constructor() {
    this.setInit();
  }

  setInit() {
    const inptKey = document.querySelector("#inpt_key");

    inptKey.addEventListener("keypress", e => {
      const data = `data=${inptKey.value}`;
      selectElements(".addBus_area .result_area").map(e => e.remove());

      if (e.keyCode == 13)
        this.getResult("/emp/searchBus.do", data, this.viewResult);
    });
  }

  viewResult = respText => {
    const result = JSON.parse(respText);
    const target = document.querySelectorAll(".addBus_area")[0];

    result.map(v => {
      const area = document.createElement("div");
      area.setAttribute("class", "result_area");

      const busId = document.createElement("input");
      busId.setAttribute("type", "hidden");
      busId.setAttribute("name", "busId");
      busId.setAttribute("value", v.busId);
      const ownId = document.createElement("input");
      ownId.setAttribute("type", "hidden");
      ownId.setAttribute("name", "ownId");
      ownId.setAttribute("value", v.ownId);
      area.append(busId);
      area.append(ownId);

      const title = document.createElement("p");
      title.setAttribute("class", "title");
      title.textContent = v.busName;
      const addr = document.createElement("p");
      addr.textContent = v.busAddr;
      const phone = document.createElement("p");
      phone.textContent = v.busPhone;
      const owner = document.createElement("p");
      owner.innerHTML = `<b>대표자명:</b> ${v.ownName}`;
      area.append(title);
      area.append(addr);
      area.append(phone);
      area.append(owner);
      this.addEventAll(area);

      target.append(area);
    });
  };

  addEventAll(target) {
    target.addEventListener("click", () => {
      target.classList.add("selected");
      const btnEr = document.createElement("button");
      btnEr.setAttribute("class", "btn-primary");
      btnEr.textContent = "신청";

      btnEr.addEventListener("click", ({ target }) => {
        const result = target.parentElement;
        let busId;
        let ownId;

        [...result.children].map(e => {
          if (e.getAttribute("name") == "busId") busId = e.value;
          else if (e.getAttribute("name") == "ownId") ownId = e.value;
        });

        const data = `ownId=${ownId}&busId=${busId}`;
        this.getResult("/emp/requestBus.do", data, this.submitEnroll);
      });

      const checkBtn = selectElements(".result_area button").length > 0;
      if (!checkBtn) target.append(btnEr);
    });
  }

  submitEnroll(respText) {
    if (respText != "fail")
    location.href = contextPath+"emp/main.do";
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
}

const addBus = new AddBus();
