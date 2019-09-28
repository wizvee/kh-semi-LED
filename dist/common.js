"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var Alert =
/*#__PURE__*/
function () {
  function Alert() {
    _classCallCheck(this, Alert);

    this.list = userInfo.notiList;
    if (this.list != null) this.count = this.list.length;else this.count = 0;
    this.viewNoti = this.viewNoti;
    this.createItem = this.createItem;
    this.setInit();
  }

  _createClass(Alert, [{
    key: "setInit",
    value: function setInit() {
      this.viewCount();
    }
  }, {
    key: "viewCount",
    value: function viewCount() {
      var area = document.querySelector("#gnb_alert");
      var badge = document.querySelector("#gnb_alertBadge");
      badge.textContent = this.count;

      if (this.count > 0) {
        area.setAttribute("class", "news");
        this.viewNoti();
      }
    }
  }, {
    key: "viewNoti",
    value: function viewNoti() {
      var _this = this;

      var area = document.querySelector("#alert_body");
      selectElements("#alert_body .alert_item").map(function (e) {
        return e.remove();
      });
      if (this.count > 0) this.list.map(function (n) {
        return _this.createItem(area, n);
      });else area.textContent = "알림이 없습니다.";
    }
  }, {
    key: "createItem",
    value: function createItem(area, n) {
      var item = document.createElement("div");
      item.setAttribute("id", "".concat(n.notiType, ",").concat(n.notiId));
      item.setAttribute("class", "alert_item");
      var img = document.createElement("img");
      img.setAttribute("class", "item_profile");
      img.setAttribute("src", "/p_190826_semi/upload/profile/".concat(n.profilePic));
      var content = document.createElement("div");
      content.setAttribute("class", "alert_content");
      var spanUser = document.createElement("span");
      spanUser.textContent = n.userName;
      var spanMsg = document.createElement("span");
      spanMsg.innerHTML = n.notiMsg;
      content.appendChild(spanUser);
      content.appendChild(spanMsg);
      item.appendChild(img);
      item.appendChild(content);
      this.itemLink(item, n.notiUrl);
      area.prepend(item);
    }
  }, {
    key: "itemLink",
    value: function itemLink(item, url) {
      var _this2 = this;

      item.addEventListener("click", function () {
        var id = item.getAttribute("id");

        if (!id.includes("static")) {
          var data = "notiId=".concat(id.split(",")[1]);

          _this2.getResult(data, url);
        }
      });
    }
  }, {
    key: "getResult",
    value: function getResult(data, url) {
      var _this3 = this;

      var xhr = new XMLHttpRequest();
      xhr.addEventListener("load", function () {
        if (xhr.responseText != "fail") {
          _this3.list = JSON.parse(xhr.responseText);

          _this3.viewCount();

          _this3.viewNoti();

          location.href = "/p_190826_semi/".concat(url);
        }
      });
      xhr.open("post", "/p_190826_semi/notiRead.do");
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(data);
    }
  }]);

  return Alert;
}();

var alert = new Alert();
document.querySelector("#btn_alert").addEventListener("click", function () {
  alert.viewNoti();
  var target = document.querySelector("#alert");
  target.classList.toggle("view");
}); // OLD????????????????!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

var dropToggle = selectElements(".dropdown_toggle");
dropToggle.map(function (e) {
  e.addEventListener("click", function (_ref) {
    var target = _ref.target;
    var menu = target.nextElementSibling;
    if (menu.style.display == "none") menu.style.display = "block";else menu.style.display = "none";
  });
});
$(".fa-sign-out").on("click", function () {
  location.href = "/p_190826_semi/logout.do";
});