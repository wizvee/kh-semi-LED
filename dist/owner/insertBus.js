"use strict";

function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _nonIterableSpread(); }

function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance"); }

function _iterableToArray(iter) { if (Symbol.iterator in Object(iter) || Object.prototype.toString.call(iter) === "[object Arguments]") return Array.from(iter); }

function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = new Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Business = function Business(ownId, busName, busNum, busAddr, busPhone, busDate) {
  _classCallCheck(this, Business);

  this.ownId = ownId;
  this.busName = busName;
  this.busNum = busNum;
  this.busAddr = busAddr;
  this.busPhone = busPhone;
  this.busDate = busDate;
};

var Shift = function Shift(sftName, sftDay, sftOn, sftOff, sftTime) {
  _classCallCheck(this, Shift);

  this.sftName = sftName;
  this.sftDay = sftDay;
  this.sftOn = sftOn;
  this.sftOff = sftOff;
  this.sftTime = sftTime;
};

var InsertBus =
/*#__PURE__*/
function () {
  function InsertBus() {
    _classCallCheck(this, InsertBus);

    this.count = 0;
    this.setInit();
  }

  _createClass(InsertBus, [{
    key: "setInit",
    value: function setInit() {
      var _this = this;

      var btnAddSft = document.querySelector("#btn_addSft");
      var btnIstBus = document.querySelector("#btn_istBus");
      btnAddSft.addEventListener("click", function (_ref) {
        var target = _ref.target;
        var area = target.parentElement;
        var sftArea = document.createElement("div");
        var sftName = "\uADFC\uBB34\uC870".concat(++_this.count);
        sftArea.setAttribute("class", "sft_area");
        sftArea.innerHTML = "\n        <div><input type=\"text\" name=\"sftName\" value=\"".concat(sftName, "\" class=\"inpt-outline\">\n        <span><i class=\"fa fa-tags\" aria-hidden=\"true\"></i></span></div>\n        <div class=\"sftDay\">\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uC77C\" id=\"sft").concat(_this.count, "day1\">\n          <label for=\"sft").concat(_this.count, "day1\">\uC77C</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uC6D4\" id=\"sft").concat(_this.count, "day2\">\n          <label for=\"sft").concat(_this.count, "day2\">\uC6D4</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uD654\" id=\"sft").concat(_this.count, "day3\">\n          <label for=\"sft").concat(_this.count, "day3\">\uD654</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uC218\" id=\"sft").concat(_this.count, "day4\">\n          <label for=\"sft").concat(_this.count, "day4\">\uC218</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uBAA9\" id=\"sft").concat(_this.count, "day5\">\n          <label for=\"sft").concat(_this.count, "day5\">\uBAA9</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uAE08\" id=\"sft").concat(_this.count, "day6\">\n          <label for=\"sft").concat(_this.count, "day6\">\uAE08</label>\n          <input type=\"checkbox\" name=\"sftDay").concat(_this.count, "\" value=\"\uD1A0\" id=\"sft").concat(_this.count, "day7\">\n          <label for=\"sft").concat(_this.count, "day7\">\uD1A0</label>\n        </div>\n        <div><input type=\"text\" name=\"sftOn\" class=\"inpt-outline\">\n        <span><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i></span></div>\n        <div><input type=\"text\" name=\"sftOff\" class=\"inpt-outline\">\n        <span><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i></span></div>");
        area.append(sftArea);
      }, false);
      btnIstBus.addEventListener("click", function () {
        var ownId = userInfo.userId;
        var busName = document.getElementsByName("bName")[0].value;
        var busNum = document.getElementsByName("bNum")[0].value;
        var busAddr = document.getElementsByName("addr")[0].value;
        var busPhone = document.getElementsByName("phone")[0].value;
        var busDate = document.getElementsByName("bDate")[0].value;
        var business = new Business(ownId, busName, busNum, busAddr, busPhone, busDate);
        var sftName = document.getElementsByName("sftName");
        var sftOn = document.getElementsByName("sftOn");
        var sftOff = document.getElementsByName("sftOff");
        var sftArr = [];

        for (var i = 0; i < _this.count; i++) {
          var inpt = document.getElementsByName("sftDay".concat(i + 1));

          var sftDay = _toConsumableArray(inpt).filter(function (c) {
            return c.checked;
          }).map(function (v) {
            return v.value;
          });

          var sft = new Shift(sftName[i].value, sftDay.join(), sftOn[i].value, sftOff[i].value, _this.timeCal(sftOn[i].value, sftOff[i].value));
          sftArr.push(sft);
        }

        var data = _this.getData(JSON.stringify(business), JSON.stringify(sftArr));

        _this.getResult("/insertBus.do", data, _this.submitBusiness);
      }, false);
    }
  }, {
    key: "timeCal",
    value: function timeCal(on, off) {
      var start = on.split(":");
      var end = off.split(":");
      var onDate = new Date(0, 0, 0, start[0], start[1], 0);
      var offDate = new Date(0, 0, 0, end[0], end[1], 0);
      var diff = offDate.getTime() - onDate.getTime();
      return Math.floor(diff / 1000 / 60);
    }
  }, {
    key: "submitBusiness",
    value: function submitBusiness(respText) {
      if (respText != "fail") location.href = "/p_190826_semi/owner/main.do?selectBus=" + respText;
    }
  }, {
    key: "getResult",
    value: function getResult(servletURL, data, fn) {
      var xhr = new XMLHttpRequest();
      xhr.addEventListener("load", function () {
        fn(xhr.responseText);
      });
      xhr.open("post", "/p_190826_semi/" + servletURL);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(data);
    }
  }, {
    key: "getData",
    value: function getData(business, shift) {
      return "business=".concat(business, "&shift=").concat(shift);
    }
  }]);

  return InsertBus;
}();

var insertBus = new InsertBus(); // OLD!!!!!!!!!!!!!!!!!! *************

function fn_search() {
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/search.do",
    dataType: "text",
    data: {
      data: $("#input").val()
    },
    success: function success(data) {
      var json = JSON.parse(data);
      $(".addBus_area .result_area").remove();

      for (var i in json.items) {
        var area = $("<div class='result_area'>");
        area.append($("<p class='title'>").html(json.items[i].title));
        area.append($("<p class='address'>").html(json.items[i].roadAddress));
        area.append($("<p class='telephone'>").html(json.items[i].telephone));
        $(".addBus_area").append(area);
      }

      $(".addBus_area .result_area").on("click", function () {
        $("input[name='bName']").attr("value", $(this).find(".title").text());
        $("input[name='addr']").attr("value", $(this).find(".address").text());
        $("input[name='phone']").attr("value", $(this).find(".telephone").text());
        $(this).siblings().removeClass("selected");
        $(this).addClass("selected");
        $(".selected").on("click", function (e) {
          $(".addBus_area").fadeOut("fast");
          $(".addInfo_area").fadeIn("slow");
        });
      });
    }
  });
}

$("input[name='bNum']").on("blur", function () {
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/owner/checkBusNum.do",
    dataType: "text",
    data: {
      bNum: $(this).value
    },
    success: function success(data) {
      if (data == "unable" && $(this).siblings().is(".error")) {
        $(this).parent().append($("<span class='error'>사용불가</span>"));
      }
    },
    error: function error() {
      alert("연결 실패");
    }
  });
});