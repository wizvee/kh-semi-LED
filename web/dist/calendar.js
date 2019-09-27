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
      _this.target = new Date(_this.target.getFullYear(), _this.target.getMonth() - 1, 1);

      _this.createCal();
    });

    _defineProperty(this, "next", function () {
      _this.target = new Date(_this.target.getFullYear(), _this.target.getMonth() + 1, 1);

      _this.createCal();
    });

    this.now = new Date();
    this.target = new Date(this.now.getFullYear(), this.now.getMonth(), 1);
    this.countDate = 1;
    this.body = selectElements(".calendar_body")[0];
    this.header = selectElements(".calendar_header span")[0];
    this.setInit();
  }

  _createClass(Calendar, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      var week = ["일", "월", "화", "수", "목", "금", "토"];
      week.map(function (w) {
        var cell = document.createElement("div");
        cell.setAttribute("class", "week");
        cell.textContent = w;

        _this2.body.appendChild(cell);
      });
      this.createCal();
      var pre = document.querySelector("#btn_calPrv");
      var nxt = document.querySelector("#btn_calNxt");
      pre.addEventListener("click", this.previous);
      nxt.addEventListener("click", this.next);
    }
  }, {
    key: "createCal",
    value: function createCal() {
      var firstDay = this.target.getDay();
      var lastDate = new Date(this.target.getFullYear(), this.target.getMonth() + 1, 0).getDate();
      selectElements(".calendar_body .date").map(function (v) {
        return v.remove();
      });

      for (var i = 0; i < lastDate + firstDay; i++) {
        var cell = document.createElement("div");
        cell.setAttribute("class", "date");

        if (firstDay <= i && this.countDate <= lastDate) {
          cell.textContent = this.countDate;
          var compareY = this.target.getFullYear() == this.now.getFullYear();
          var compareM = this.target.getMonth() == this.now.getMonth();
          var compareD = this.countDate == this.now.getDate();
          if (compareY && compareM && compareD) cell.setAttribute("class", "date today");
          this.countDate++;
        }

        this.body.appendChild(cell);
      }

      this.header.textContent = "".concat(this.target.getFullYear(), "\uB144 ").concat(this.target.getMonth(), "\uC6D4");
      this.countDate = 1;
    }
  }]);

  return Calendar;
}();

var calendar = new Calendar();