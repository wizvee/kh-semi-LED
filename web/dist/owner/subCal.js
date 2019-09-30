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

      $.ajax({
        url: contextPath + "ajaxPrevCal.do",
        data: {
          "date": _this.target.getMonth() + 1
        },
        method: "post",
        dataType: "json",
        success: function success(data) {
          //setAjaxCalendar(data);
          var date = $(".date");

          for (var i = 0; i < data.length; i++) {
            if (data[i][1] == 0) {
              var flagDate = data[i][0];
              $.each(date, function (i, v) {
                var dateId = $(v).attr("id");

                if (dateId != undefined) {
                  var temp = flagDate.substring(flagDate.length - 2);
                  var temp2 = dateId.substring(dateId.length - 2);

                  if (temp == temp2) {
                    $(v).css("borderBottom", "5px solid red");
                  }
                }
              });
            }
          }
        }
      });
    });

    _defineProperty(this, "next", function () {
      _this.target = _this.getMyDate(1, 1);

      _this.createCal();

      if (_this.now.getFullYear() >= _this.target.getFullYear() && _this.target.getMonth() + 1 < _this.now.getMonth() + 1) {
        $.ajax({
          url: contextPath + "ajaxNexCal.do",
          data: {
            "date": _this.target.getMonth() + 1
          },
          method: "post",
          dataType: "json",
          success: function success(data) {
            var date = $(".date");

            for (var i = 0; i < data.length; i++) {
              if (data[i][1] == 0) {
                var flagDate = data[i][0];
                $.each(date, function (i, v) {
                  var dateId = $(v).attr("id");

                  if (dateId != undefined) {
                    var temp = flagDate.substring(flagDate.length - 2);
                    var temp2 = dateId.substring(dateId.length - 2);

                    if (temp == temp2) {
                      $(v).css("borderBottom", "5px solid red");
                    }
                  }
                });
              }
            }
          }
        });
      }
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
          cell.setAttribute("id", this.setDateId(this.countDate)); //cell.setAttribute("style","border-bottom:3px solid red;");

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
          // 날짜 클릭 이벤트
          $.ajax({
            url: contextPath + "ajaxAtd.do",
            data: {
              "date": target.getAttribute("id")
            },
            method: "post",
            dataType: "json",
            success: function success(data) {
              var cardSet = "";

              for (var j = 0; j < data.length; j++) {
                var card = "";
                var widthLate = 0;
                var widthEarly = 0;
                var widthNomal = 0;
                var stAtd = data[j]['atdOn'];
                var enAtd = data[j]['atdOff'];
                var stSft = data[j]['sftOn'];
                var enSft = data[j]['sftOff'];

                if (enSft > enAtd) {
                  widthEarly = 100 - (enSft - stSft - (enSft - enAtd)) / (enSft - stSft) * 100;
                }

                if (stSft < stAtd) {
                  widthLate = 100 - (enSft - stSft - (stAtd - stSft)) / (enSft - stSft) * 100;
                }

                widthNomal = 100 - (widthLate + widthEarly);

                if (data[j]["atdType"] != null) {
                  var card1 = "<div class='makeCard'><div class='card'><div class='additional'><div class='user-card'><div class='level center'>LEVEL " + (data[j]['level'] != null ? data[j]['level'] : '미설정') + "</div>";
                  var card2 = "<div class='points center'>" + data[j]['sftName'] + "</div><img alt='' src=''" + (data[j]['pic'] != null ? data[j]['pic'] : '') + "></div>";
                  var card3 = "<div class='more-info'><h1>" + data[j]['name'] + "</h1><div class='coords'><span>지정 근무 시간</span> <span>" + data[j]['sSft'] + " ~ " + data[j]['nSft'] + "</span>";
                  var card4 = "</div><div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>" + data[j]['name'] + "</h1><hr><p style='margin:10%'>";
                  var card5 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(0, 4) : '') + " 년" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(4, 6) : '') + " 월";
                  var card6 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(6, 8) : '') + " 일의<br> 근무 기록 입니다.</p>";
                  var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>" + data[j]['sSft'] + "~" + data[j]['nSft'] + "</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                  var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                  var card9 = "<div style='float:left; height:100%;  background-color: red; width:" + widthLate + "%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :" + widthNomal + "%;'></div>";
                  var card10 = "<div style='float:left; height:100%; background-color: dodgerblue; width :" + widthEarly + "%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
                } else {
                  var card1 = "<div class='makeCard'><div class='card green'><div class='additional'><div class='user-card'><div class='level center'>LEVEL " + (data[j]['level'] != null ? data[j]['level'] : '미설정') + "</div>";
                  var card2 = "<div class='points center'>" + data[j]['sftName'] + "</div><img alt='' src=''" + (data[j]['pic'] != null ? data[j]['pic'] : '') + "></div>";
                  var card3 = "<div class='more-info'><h1>" + data[j]['name'] + "</h1><div class='coords'><span>지정 근무 시간</span> <span>" + data[j]['sSft'] + " ~ " + data[j]['nSft'] + "</span>";
                  var card4 = "</div><div class='coords'><span>Position/Role</span> <span>City, Country</span></div></div></div><div class='general'><h1>" + data[j]['name'] + "</h1><hr><p style='margin:10%'>";
                  var card5 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(0, 4) : '') + " 년" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(4, 6) : '') + " 월";
                  var card6 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(6, 8) : '') + " 일의<br> 근무 기록 입니다.</p>";
                  var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>" + data[j]['sSft'] + "~" + data[j]['nSft'] + "</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                  var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                  var card9 = "<div style='float:left; height:100%;  background-color: red; width:" + widthLate + "%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :" + widthNomal + "%;'></div>";
                  var card10 = "<div style='float:left; height:100%; background-color: dodgerblue; width :" + widthEarly + "%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
                }

                card = card1 + card2;
                card = card + card3;
                card = card + card4;
                card = card + card5;
                card = card + card6;
                card = card + card7;
                card = card + card8;
                card = card + card9;
                card = card + card10;
                cardSet = cardSet + card;
              }

              $(".item_atd").html(cardSet);
            }
          });
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
  }, {
    key: "setAjaxCalendar",
    value: function setAjaxCalendar(data) {
      var date = $(".date");

      for (var i = 0; i < data.length; i++) {
        if (data[i][1] == 0) {
          var flagDate = data[i][0];
          $.each(date, function (i, v) {
            var dateId = $(v).attr("id");

            if (dateId != undefined) {
              var temp = flagDate.substring(flagDate.length - 2);
              var temp2 = dateId.substring(dateId.length - 2);

              if (temp == temp2) {
                $(v).css("borderBottom", "5px solid red");
              }
            }
          });
        }
      }
    }
  }]);

  return SubCal;
}();

var subCal = new SubCal();