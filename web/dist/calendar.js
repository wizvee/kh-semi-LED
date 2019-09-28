"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var Calendar =
/*#__PURE__*/
function () {
  function Calendar() {
    var _this = this;

    _classCallCheck(this, Calendar);

    _defineProperty(this, "previous", function () {
      _this.target = _this.getMyDate(-1, 1);

      _this.createCal();
    });

    _defineProperty(this, "next", function () {
      _this.target = _this.getMyDate(1, 1);

      _this.createCal();
    });

    _defineProperty(this, "setCal", function () {
      var date = document.getElementsByName("date")[0].value;
      var sftId = document.getElementsByName("sftId")[0].value;
      var title = document.getElementsByName("title")[0].value;
      var content = document.getElementsByName("content")[0].value;
      var data = "calDate=".concat(date, "&sftId=").concat(sftId, "&calTitle=").concat(title, "&calDetail=").concat(content);

      _this.getResult("owner/insertCal.do", data, _this.insertCal); // console.log(data);

    });

    _defineProperty(this, "insertCal", function (respText) {
      if (respText != "fail") {
        document.getElementsByName("title")[0].value = "";
        document.getElementsByName("content")[0].value = "";
        location.href = contextPath + "/owner/calendar.do";
      } else console.log("실패");
    });

    _defineProperty(this, "setCalList", function (respText) {
      console.log(JSON.parse(respText));
      _this.calList = JSON.parse(respText);
    });

    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;
    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];
    this.calList;
    this.setInit();
  }

  _createClass(Calendar, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      // get calendar list
      this.getResult("getCalList.do", "", this.setCalList); // create week cells

      var week = ["일", "월", "화", "수", "목", "금", "토"];
      week.map(function (w) {
        var cell = document.createElement("div");
        cell.setAttribute("class", "week");
        cell.textContent = w;

        _this2.body.appendChild(cell);
      });
      this.createCal(); // button event

      var pre = document.querySelector("#btn_calPrv");
      var nxt = document.querySelector("#btn_calNxt");
      pre.addEventListener("click", this.previous);
      nxt.addEventListener("click", this.next);
      var istCal = document.querySelector("#btn_insertCal");
      istCal.addEventListener("click", this.setCal);
    }
  }, {
    key: "createCal",
    value: function createCal() {
      var _this3 = this;

      var firstDay = this.target.getDay();
      var lastDate = this.getMyDate(1, 0).getDate();
      selectElements(".calendar_body .date").map(function (v) {
        return v.remove();
      });

      for (var i = 0; i < lastDate + firstDay; i++) {
        var cell = document.createElement("div");
        cell.setAttribute("class", "date");

        if (firstDay <= i && this.countDate <= lastDate) {
          cell.setAttribute("id", this.setDateId(this.countDate));
          cell.textContent = this.countDate;
          var compareY = this.target.getFullYear() == this.now.getFullYear();
          var compareM = this.target.getMonth() == this.now.getMonth();
          var compareD = this.countDate == this.now.getDate();
          if (compareY && compareM && compareD) cell.setAttribute("class", "date today");
          this.countDate++;
        }

        this.body.appendChild(cell);
        cell.addEventListener("click", function (_ref) {
          var target = _ref.target;
          var targetDate = target.getAttribute("id"); // 일정 추가 event

          var date = document.getElementsByName("date")[0];
          var title = document.getElementsByName("title")[0];
          var content = document.getElementsByName("content")[0];
          date.value = targetDate;
          title.value = "";
          content.value = ""; // 일정 보기 event

          var view = document.querySelectorAll(".viewCal_area")[0];

          _this3.calList.map(function (c) {
            console.log("ㅠㅠ");
            if (c.calDate == targetDate) view.textContent = c.calTitle;
          });
        });
      }

      this.header.innerHTML = "".concat(this.target.getFullYear(), "\uB144 <b>").concat(this.target.getMonth() + 1, "\uC6D4</b>");
      this.countDate = 1;
    }
  }, {
    key: "getResult",
    value: function getResult(servletURL, data, fn) {
      var xhr = new XMLHttpRequest();
      xhr.addEventListener("load", function () {
        fn(xhr.responseText);
      });
      xhr.open("post", contextPath + servletURL);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(data);
    }
  }, {
    key: "getMyDate",
    value: function getMyDate(month, date) {
      return new Date(this.target.getFullYear(), this.target.getMonth() + month, date);
    }
  }, {
    key: "setDateId",
    value: function setDateId(date) {
      var month = this.target.getMonth() + 1 < 10 ? "0".concat(this.target.getMonth() + 1) : this.target.getMonth() + 1;
      date = date < 10 ? "0".concat(date) : date;
      return "".concat(this.target.getFullYear(), "-").concat(month, "-").concat(date);
    }
  }]);

  return Calendar;
}();

var calendar = new Calendar();
var sfts = selectElements(".sftList .sft");
sfts.map(function (s) {
  return s.addEventListener("click", function (_ref2) {
    var target = _ref2.target;
    var id = document.getElementsByName("sftId")[0];
    var name = selectElements(".sftList .selectSft")[0];
    sfts.map(function (e) {
      return e.classList.remove("select");
    });
    target.classList.add("select");
    name.textContent = target.textContent;
    id.value = target.getAttribute("id");
  });
});
document.querySelector("#btn_addTask").addEventListener("click", function () {
  var area = document.querySelectorAll(".subCal_area .taskList")[0];
  var sftId = document.getElementsByName("sftId")[0].value;
  var emps = empList.find(function (e) {
    return e.sftId == sftId;
  });
  var targetEmp = document.createElement("span");
  targetEmp.setAttribute("class", "inpt-outline");
  var task = document.createElement("input");
  task.setAttribute("class", "inpt-outline");
  if (emps == undefined) console.log("modal! alert!");else {
    area.appendChild(targetEmp);
    area.appendChild(task);
  }
});