const dropToggle = selectElements(".dropdown_toggle");
dropToggle.map(e => {
  e.addEventListener("click", ({ target }) => {
    const menu = target.nextElementSibling;
    if (menu.style.display == "none") menu.style.display = "block";
    else menu.style.display = "none";
  });
});

$(".fa-sign-out").on("click", function() {
  location.href = "/p_190826_semi/logout.do";
});

class Alert {
  constructor() {
    this.list = userInfo.notiList;
    if (this.list != null) this.count = this.list.length;
    else this.count = 0;

    this.createItem = this.createItem;

    this.setInit();
  }

  setInit() {
    this.viewCount();
    this.viewNoti();
  }

  viewCount() {
    const area = document.querySelector("#gnb_alert");
    const badge = document.querySelector("#gnb_alertBadge");
    badge.textContent = this.count;

    if (this.count > 0) area.setAttribute("class", "news");
  }

  viewNoti() {
    const area = document.querySelector("#alert_body");
    selectElements("#alert_body .alert_item").map(e => e.remove());
    if (this.count > 0) this.list.map(n => this.createItem(area, n));
    else area.textContent = "알림이 없습니다.";
  }

  createItem(area, n) {
    const item = document.createElement("div");
    item.setAttribute("class", "alert_item");
    const img = document.createElement("img");
    img.setAttribute("class", "item_profile");
    img.setAttribute("src", `/p_190826_semi/upload/profile/${n.profilePic}`);
    const content = document.createElement("div");
    content.setAttribute("class", "alert_content");
    const spanUser = document.createElement("span");
    spanUser.setAttribute("class", "alert_user");
    spanUser.textContent = n.userName;
    const spanMsg = document.createElement("span");
    spanMsg.setAttribute("class", "alert_msg");
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
      location.href = `/p_190826_semi/${url}`;
    });
  }
}

const alert = new Alert();

document.querySelector("#btn_alert").addEventListener("click", () => {
  alert.viewNoti();
  const target = document.querySelector("#alert");
  target.classList.toggle("view");
});
