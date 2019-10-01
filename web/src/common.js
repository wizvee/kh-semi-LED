class Alert {
  constructor() {
    this.list = userInfo.notiList;
    if (this.list != null) this.count = this.list.length;
    else this.count = 0;

    this.viewNoti = this.viewNoti;
    this.createItem = this.createItem;
    this.setInit();
  }

  setInit() {
    this.viewCount();
  }

  viewCount() {
    const area = document.querySelector("#gnb_alert");
    const badge = document.querySelector("#gnb_alertBadge");
    badge.textContent = this.count;
    console.log(this.count);

    if (this.count > 0) {
      area.setAttribute("class", "news");
      this.viewNoti();
    }
  }

  viewNoti() {
    const area = document.querySelector("#alert_body");
    selectElements("#alert_body .alert_item").map(e => e.remove());
    if (this.count > 0) this.list.map(n => this.createItem(area, n));
    else area.textContent = "알림이 없습니다.";
  }

  createItem(area, n) {
    const item = document.createElement("div");
    item.setAttribute("id", `${n.notiType},${n.notiId}`);
    item.setAttribute("class", "alert_item");
    const img = document.createElement("img");
    img.setAttribute("class", "item_profile");
    img.setAttribute("src", `/p_190826_semi/upload/profile/${n.profilePic}`);
    const content = document.createElement("div");
    content.setAttribute("class", "alert_content");
    const spanUser = document.createElement("span");
    spanUser.textContent = n.userName;
    const spanMsg = document.createElement("span");
    spanMsg.innerHTML = n.notiMsg;

    content.appendChild(spanUser);
    content.appendChild(spanMsg);

    item.appendChild(img);
    item.appendChild(content);
    this.itemLink(item, n.notiUrl);

    area.prepend(item);
  }

  itemLink(item, url) {
    item.addEventListener("click", () => {
      const id = item.getAttribute("id");
      if (!id.includes("static")) {
        const data = `notiId=${id.split(",")[1]}`;
        this.getResult(data, url);
      }
    });
  }

  getNotiList = res => {
    this.list = JSON.parse(res);
    this.count = this.list.length;
    console.log("get List");
    console.log(this.list);
  }

  getResult(data, url) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      if (xhr.responseText != "fail") {
        this.list = JSON.parse(xhr.responseText);
        this.viewCount();
        this.viewNoti();
        location.href = `/p_190826_semi/${url}`;
      }
    });
    xhr.open("post", contextPath + "notiRead.do");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }
}

const alert = new Alert();

document.querySelector("#btn_alert").addEventListener("click", ({target}) => {
  alert.viewNoti();
  const area = document.querySelector("#alert");
  target.classList.toggle("focus");
  area.classList.toggle("view");
});

// OLD????????????????!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

const dropToggle = selectElements(".dropdown_toggle");
dropToggle.map(e => {
  e.addEventListener("click", ({ target }) => {
    const menu = target.nextElementSibling;
    if (menu.style.display == "none") menu.style.display = "block";
    else menu.style.display = "none";
  });
});

$(".fa-sign-out").on("click", function() {
  location.href = contextPath + "logout.do";
});

document.querySelectorAll(".fa-cog")[0].addEventListener("click", () => {
  location.href = contextPath + "businfo.do";
});
