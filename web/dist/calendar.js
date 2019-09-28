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
      var taskUserId = document.getElementsByName("taskUserId");
      var taskMsg = document.getElementsByName("taskMsg");
      var taskArr = [];

      for (var i = 0; i < _this.taskCount; i++) {
        var task = new Task(date, taskUserId[i].value, taskMsg[i].value);
        taskArr.push(task);
      }

      var data = "calDate=".concat(date, "&sftId=").concat(sftId, "&calTitle=").concat(title, "&calDetail=").concat(content, "&taskArr=").concat(JSON.stringify(taskArr));

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
      _this.calList = JSON.parse(respText);
    });

    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;
    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];
    this.calList;
    this.taskCount = 0;
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
      nxt.addEventListener("click", this.next); // add task div

      var addTask = document.querySelector("#btn_addTask");
      addTask.addEventListener("click", function () {
        var area = document.querySelectorAll(".subCal_area .taskList")[0];
        var sftId = document.getElementsByName("sftId")[0].value;
        var containEmp = empList.filter(function (e) {
          return e.sftId == sftId;
        });
        if (_toConsumableArray(containEmp).length == 0) console.log("modal! alert!");else {
          var targetEmpList = document.createElement("div");
          targetEmpList.setAttribute("class", "targetEmpList");
          var inpt = document.createElement("input");
          inpt.setAttribute("type", "hidden");
          inpt.setAttribute("name", "taskUserId");
          inpt.value = _toConsumableArray(containEmp)[0].userId;
          var targetEmp = document.createElement("span");
          targetEmp.setAttribute("class", "selectTargetUser");
          targetEmp.textContent = _toConsumableArray(containEmp)[0].userName;
          var ul = document.createElement("ul");
          ul.setAttribute("class", "dropMenu");

          _toConsumableArray(containEmp).map(function (e) {
            var li = document.createElement("li");
            li.setAttribute("id", e.userId);
            li.setAttribute("class", "taskUser");
            li.textContent = e.userName;
            ul.appendChild(li);
          });

          targetEmpList.appendChild(inpt);
          targetEmpList.appendChild(targetEmp);
          targetEmpList.appendChild(ul);
          var task = document.createElement("input");
          task.setAttribute("class", "inpt-outline");
          task.setAttribute("name", "taskMsg");
          area.appendChild(targetEmpList);
          area.appendChild(task);
          ++_this2.taskCount;
        }
      });
      var istCal = document.querySelector("#btn_insertCal");
      istCal.addEventListener("click", this.setCal);
    } // create date cells

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

          var calTitle = document.querySelectorAll(".viewCal_area .calTitle")[0];
          var calDetail = document.querySelectorAll(".viewCal_area .calDetail")[0];

          _this3.calList.map(function (c) {
            if (c.calDate == targetDate) {
              calTitle.textContent = c.calTitle;
              calDetail.textContent = c.calDetail;
            } else {
              calTitle.textContent = "";
              calDetail.textContent = "";
            }
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