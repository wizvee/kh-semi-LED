"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var SubCal =
/*#__PURE__*/
function () {
  function SubCal() {
    var _this = this;

    _classCallCheck(this, SubCal);

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
    this.setInit();
  }

  _createClass(SubCal, [{
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
        cell.addEventListener("click", function (_ref) {// 날짜 클릭 이벤트

          var target = _ref.target;
        });
      }

      this.header.innerHTML = "".concat(this.target.getFullYear(), "\uB144 <b>").concat(this.target.getMonth() + 1, "\uC6D4</b>");
      this.countDate = 1;
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

  return SubCal;
}();

var subCal = new SubCal();