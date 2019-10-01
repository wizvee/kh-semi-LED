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
        url: contextPath + "ajaxCal.do",
        data: {
          "month": _this.target.getMonth() + 1,
          "year": _this.target.getFullYear()
        },
        method: "post",
        dataType: "json",
        success: function success(data) {
          //setAjaxCalendar(data);
          var date = $(".date");

          for (var i = 0; i < data.length; i++) {
            if (data[i][1] != 0) {
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

      if (!(_this.target.getFullYear() > _this.now.getFullYear()) && !(_this.target.getMonth() + 1 > _this.now.getMonth() + 1)) {
        $.ajax({
          url: contextPath + "ajaxCal.do",
          data: {
            "month": _this.target.getMonth() + 1,
            "year": _this.target.getFullYear()
          },
          method: "post",
          dataType: "json",
          success: function success(data) {
            //setAjaxCalendar(data);
            var date = $(".date");

            for (var i = 0; i < data.length; i++) {
              if (data[i][1] != 0) {
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
      $.ajax({
        url: contextPath + "ajaxCal.do",
        data: {
          "month": this.target.getMonth() + 1,
          "year": this.target.getFullYear()
        },
        method: "post",
        dataType: "json",
        success: function success(data) {
          //setAjaxCalendar(data);
          var date = $(".date");

          for (var i = 0; i < data.length; i++) {
            if (data[i][1] != 0) {
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
          $('.checkAttendance').show();
          $.ajax({
            url: contextPath + "ajaxAtd.do",
            data: {
              "date": target.getAttribute("id")
            },
            method: "post",
            dataType: "json",
            success: function success(data) {
              console.log(data);
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
                var check = data[j]['atdType'].split(",");
                var type = "";

                if (data[j]['atdType'] != "") {
                  for (var i = 0; i < check.length; i++) {
                    console.log(check[i]);

                    if (check[i] === "L") {
                      type += '<div style="text-algin:left"><h2 style ="color:red">지각</h2></div>';
                    } else if (check[i] === "E") {
                      type += '<div style="text-algin:left"><h2 style ="color:red">조퇴</h2></div>';
                    } else if (check[i] === "O") {
                      type += '<div style="text-algin:left"><h2 style ="color:red">추가근무</h2></div>';
                    }
                  }

                  ;
                } else {
                  type = "<div><h1 style='color:green'>정상 근무</h1></div>";
                }

                if (enSft > enAtd) {
                  widthEarly = 100 - (enSft - stSft - (enSft - enAtd)) / (enSft - stSft) * 100;
                }

                if (stSft < stAtd) {
                  widthLate = 100 - (enSft - stSft - (stAtd - stSft)) / (enSft - stSft) * 100;
                }

                widthNomal = 100 - (widthLate + widthEarly);

                if (check[i] != "") {
                  var card1 = "<div class='makeCard'><div class='card'><div class='additional'><div class='user-card'><div class='level center'>LEVEL " + (data[j]['level'] != null ? data[j]['level'] : '미설정') + "</div>";
                  var card2 = "<div class='points center'>" + data[j]['sftName'] + "</div><img class='svg' alt='' src='" + (data[j]['pic'] != null ? contextPath + 'upload/profile/' + data[j]['pic'] : contextPath + 'upload/profile/emp_default.png') + "'></div>";
                  var card3 = "<div class='more-info'><h1>" + data[j]['name'] + "</h1><div class='coords'><span>지정 근무 시간</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>" + data[j]['sSft'] + " ~ " + data[j]['nSft'] + "</span><br><span>실제 근무 시간</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>" + (data[j]['sAtd'].substring(8, 10) != null ? data[j]['sAtd'].substring(8, 10) + ':' : '근무준비중') + "" + (data[j]['sAtd'].substring(10, 12) != null ? data[j]['sAtd'].substring(10, 12) : '') + " ~ " + (data[j]['nAtd'].substring(8, 10) != null ? data[j]['nAtd'].substring(8, 10) + ':' : '') + "" + (data[j]['nAtd'].substring(10, 12) != null ? data[j]['nAtd'].substring(10, 12) : '') + "</span>";
                  var card4 = "</div><br><br><div class='coords'><span><h2>" + data[j]['sftName'] + "</h2></span> <span>" + (data[j]['nAtd'] != null ? '근무 종료' : '근무 중') + "</span>" + type + "</div></div></div><div class='general'><h1>" + data[j]['name'] + "</h1><hr><p style='margin:10%'>";
                  var card5 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(0, 4) : '') + " 년" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(4, 6) : '') + " 월";
                  var card6 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(6, 8) : '') + " 일의<br> 근무 기록 입니다.</p>";
                  var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>" + data[j]['sSft'] + "~" + data[j]['nSft'] + "</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                  var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                  var card9 = "<div style='float:left; height:100%;  background-color: red; width:" + widthLate + "%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :" + widthNomal + "%;'></div>";
                  var card10 = "<div style='float:left; height:100%; background-color: dodgerblue; width :" + widthEarly + "%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
                } else {
                  var card1 = "<div class='makeCard'><div class='card green'><div class='additional'><div class='user-card'><div class='level center'>LEVEL " + (data[j]['level'] != null ? data[j]['level'] : '미설정') + "</div>";
                  var card2 = "<div class='points center'>" + data[j]['sftName'] + "</div><img class='svg' alt='' src='" + (data[j]['pic'] != null ? contextPath + 'upload/profile/' + data[j]['pic'] : contextPath + 'upload/profile/emp_default.png') + "'></div>";
                  var card3 = "<div class='more-info'><h1>" + data[j]['name'] + "</h1><div class='coords'><span>지정 근무 시간</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>" + data[j]['sSft'] + " ~ " + data[j]['nSft'] + "</span><br><span>실제 근무 시간</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>" + (data[j]['sAtd'].substring(8, 10) != null ? data[j]['sAtd'].substring(8, 10) + ':' : '근무준비중') + "" + (data[j]['sAtd'].substring(10, 12) != null ? data[j]['sAtd'].substring(10, 12) : '') + " ~ " + (data[j]['nAtd'].substring(8, 10) != null ? data[j]['nAtd'].substring(8, 10) + ':' : '') + "" + (data[j]['nAtd'].substring(10, 12) != null ? data[j]['nAtd'].substring(10, 12) : '') + "</span>";
                  var card4 = "</div><br><br><div class='coords'><span><h2>" + data[j]['sftName'] + "</h2></span> <span>" + (data[j]['nAtd'] != null ? '근무 종료' : '근무 중') + "</span>" + type + "</div></div></div><div class='general'><h1>" + data[j]['name'] + "</h1><hr><p style='margin:10%'>";
                  var card5 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(0, 4) : '') + " 년" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(4, 6) : '') + " 월";
                  var card6 = "" + (data[j]['sAtd'] != null ? data[j]['sAtd'].substring(6, 8) : '') + " 일의<br> 근무 기록 입니다.</p>";
                  var card7 = "<div class='atd_check_late'><p>근무 시간</p><p>" + data[j]['sSft'] + "~" + data[j]['nSft'] + "</p><div class='atd_progress_color'><div style='background-color: red;'></div>";
                  var card8 = ":지각<div style='background-color: chartreuse;'></div>: 근무 시간<div style='background-color: dodgerblue;'></div>: 조퇴</div><div class='atd_progress_wraper'>";
                  var card9 = "<div style='float:left; height:100%;  background-color: red; width:" + widthLate + "%;'></div><div style='float:left; height:100%; background-color: chartreuse; width :" + widthNomal + "%;'></div>";
                  var card10 = "<div style='float:left; height:100%; background-color: dodgerblue; width :" + widthEarly + "%;''></div></div></div><span class='more'>Mouse over the card for more info</span></div></div></div>";
                }

                card = card + card1;
                card = card + card2;
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
            },
            error: function error(r, e, m) {
              console.log(r);
              console.log(e);
              console.log(m);
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