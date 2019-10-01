"use strict";

function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _nonIterableSpread(); }

function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance"); }

function _iterableToArray(iter) { if (Symbol.iterator in Object(iter) || Object.prototype.toString.call(iter) === "[object Arguments]") return Array.from(iter); }

function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = new Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Task = function Task(taskDate, userId, taskMsg) {
  _classCallCheck(this, Task);

  this.taskDate = taskDate;
  this.userId = userId;
  this.taskMsg = taskMsg;
};

var Calendar =
/*#__PURE__*/
function () {
  function Calendar(calList) {
    var _this = this;

    _classCallCheck(this, Calendar);

    _defineProperty(this, "markEvent", function () {
      selectElements(".calendar_body .date").map(function (cell) {
        var targetDate = cell.getAttribute("id");

        var contianCals = _this.calList.filter(function (c) {
          return c.calDate == targetDate;
        });

        contianCals.map(function (e) {
          var div = document.createElement("div");
          div.setAttribute("id", e.calId);
          div.textContent = e.calTitle;
          cell.appendChild(div);
          div.addEventListener("click", function (event) {
            event.stopPropagation();
            var view = document.querySelectorAll(".viewCalendar_area")[0];
            var add = document.querySelectorAll(".addCal_area")[0];
            view.classList.add("focus");
            add.classList.remove("focus"); // 일정 보기 event

            var calItem = document.querySelectorAll(".cal_item")[0];
            var calTitle = document.querySelectorAll(".calTitle")[0];
            var calSft = document.querySelectorAll(".calSft")[0];
            var calDetail = document.querySelectorAll(".calDetail")[0];
            var calTask = document.querySelectorAll(".calTask")[0];
            calItem.classList.add("focus");

            _toConsumableArray(calTask.children).map(function (e) {
              return e.remove();
            });

            var calId = event.target.getAttribute("id");

            var thisEvent = _this.calList.find(function (e) {
              return e.calId == calId;
            });

            calTitle.textContent = thisEvent.calTitle;
            calSft.textContent = thisEvent.sftName;
            calDetail.textContent = thisEvent.calDetail;
            thisEvent.taskList.map(function (t) {
              var ck = document.createElement("input");
              ck.setAttribute("type", "checkbox");
              ck.setAttribute("id", t.taskId);
              var lb = document.createElement("label");
              lb.setAttribute("for", t.taskId);
              if (t.userId != userInfo.userId) ck.disabled = true;
              if (t.done == true) ck.checked = true;
              lb.innerHTML = "<b>".concat(t.userName, " \uC885\uC5C5\uC6D0 : </b> ").concat(t.taskMsg);
              calTask.appendChild(ck);
              calTask.appendChild(lb);
            });
          });
        });
      });
    });

    _defineProperty(this, "previous", function () {
      _this.target = _this.getMyDate(-1, 1);

      _this.createCal();
    });

    _defineProperty(this, "next", function () {
      _this.target = _this.getMyDate(1, 1);

      _this.createCal();
    });

    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;
    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];
    this.calList = calList;
    this.taskCount = 0;
    this.setInit();
  }

  _createClass(Calendar, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      // create week cells
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
    } // create date cells

  }, {
    key: "createCal",
    value: function createCal() {
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
          var calItem = document.querySelectorAll(".cal_item")[0];
          var view = document.querySelectorAll(".viewCalendar_area")[0];
          var add = document.querySelectorAll(".addCal_area")[0];
          calItem.classList.add("focus");
          view.classList.remove("focus");
          add.classList.add("focus");
        });
      }

      this.header.innerHTML = "".concat(this.target.getFullYear(), "\uB144 <b>").concat(this.target.getMonth() + 1, "\uC6D4</b>");
      this.countDate = 1;
      this.markEvent();
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
}(); // Calendar 객체 생성!


promiseGetDefault("getCalList.do").then(function (res) {
  return new Calendar(JSON.parse(res));
});