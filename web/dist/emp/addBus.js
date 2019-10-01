"use strict";

function _toConsumableArray(arr) { return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _nonIterableSpread(); }

function _nonIterableSpread() { throw new TypeError("Invalid attempt to spread non-iterable instance"); }

function _iterableToArray(iter) { if (Symbol.iterator in Object(iter) || Object.prototype.toString.call(iter) === "[object Arguments]") return Array.from(iter); }

function _arrayWithoutHoles(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = new Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var AddBus =
/*#__PURE__*/
function () {
  function AddBus() {
    var _this = this;

    _classCallCheck(this, AddBus);

    _defineProperty(this, "viewResult", function (respText) {
      var result = JSON.parse(respText);
      var target = document.querySelectorAll(".addBus_area")[0];
      result.map(function (v) {
        var area = document.createElement("div");
        area.setAttribute("class", "result_area");
        var busId = document.createElement("input");
        busId.setAttribute("type", "hidden");
        busId.setAttribute("name", "busId");
        busId.setAttribute("value", v.busId);
        var ownId = document.createElement("input");
        ownId.setAttribute("type", "hidden");
        ownId.setAttribute("name", "ownId");
        ownId.setAttribute("value", v.ownId);
        area.append(busId);
        area.append(ownId);
        var title = document.createElement("p");
        title.setAttribute("class", "title");
        title.textContent = v.busName;
        var addr = document.createElement("p");
        addr.textContent = v.busAddr;
        var phone = document.createElement("p");
        phone.textContent = v.busPhone;
        var owner = document.createElement("p");
        owner.innerHTML = "<b>\uB300\uD45C\uC790\uBA85:</b> ".concat(v.ownName);
        area.append(title);
        area.append(addr);
        area.append(phone);
        area.append(owner);

        _this.addEventAll(area);

        target.append(area);
      });
    });

    this.setInit();
  }

  _createClass(AddBus, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      var inptKey = document.querySelector("#inpt_key");
      inptKey.addEventListener("keypress", function (e) {
        var data = "data=".concat(inptKey.value);
        selectElements(".addBus_area .result_area").map(function (e) {
          return e.remove();
        });
        if (e.keyCode == 13) _this2.getResult("/emp/searchBus.do", data, _this2.viewResult);
      });
    }
  }, {
    key: "addEventAll",
    value: function addEventAll(target) {
      var _this3 = this;

      target.addEventListener("click", function () {
        target.classList.add("selected");
        var btnEr = document.createElement("button");
        btnEr.setAttribute("class", "btn-primary");
        btnEr.textContent = "신청";
        btnEr.addEventListener("click", function (_ref) {
          var target = _ref.target;
          var result = target.parentElement;
          var busId;
          var ownId;

          _toConsumableArray(result.children).map(function (e) {
            if (e.getAttribute("name") == "busId") busId = e.value;else if (e.getAttribute("name") == "ownId") ownId = e.value;
          });

          var data = "ownId=".concat(ownId, "&busId=").concat(busId);

          _this3.getResult("/emp/requestBus.do", data, _this3.submitEnroll);
        });
        var checkBtn = selectElements(".result_area button").length > 0;
        if (!checkBtn) target.append(btnEr);
      });
    }
  }, {
    key: "submitEnroll",
    value: function submitEnroll(respText) {
      if (respText != "fail") location.href = contextPath + "emp/main.do";
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
  }]);

  return AddBus;
}();

var addBus = new AddBus();