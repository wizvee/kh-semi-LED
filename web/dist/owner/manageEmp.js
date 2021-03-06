"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var MngEmp =
/*#__PURE__*/
function () {
  function MngEmp() {
    var _this = this;

    _classCallCheck(this, MngEmp);

    _defineProperty(this, "approvalEmp", function (respText) {
      if (respText != "fail") {
        _this.aprEmp = "";
        socket.send(JSON.stringify({
          flag: respText
        }));
        location.href = contextPath + "owner/manageEmp.do";
      }
    });

    this.aprEmp = "";
    this.now = new Date();
    this.setInit();
  }

  _createClass(MngEmp, [{
    key: "setInit",
    value: function setInit() {
      var _this2 = this;

      var mngHeader = selectElements(".mngEmp_header span");
      var mngBody = selectElements(".mngEmp_body .mngDiv");
      var empInfo = document.querySelectorAll(".approvalEmpInfo_area")[0];
      var sftItem = selectElements(".busShift_area .sftItem");
      var btnViewEdit = selectElements(".btn_viewEdit");
      var btnBackView = selectElements(".btn_backView");
      var btnApproval = selectElements(".btn_approval");
      var btnReject = selectElements(".btn_reject");
      var btnApvEmp = document.querySelectorAll(".btn_arvEmp")[0];
      var btnEditEmp = selectElements(".btn_editEmp");
      mngHeader.map(function (e, index) {
        e.addEventListener("click", function (_ref) {
          var target = _ref.target;
          mngHeader.map(function (e) {
            return e.classList.remove("focus");
          });
          target.classList.add("focus");
          empInfo.classList.remove("focus");
          mngBody.map(function (e) {
            return e.classList.remove("focus");
          });
          mngBody[index].classList.add("focus");
        });
      });
      btnViewEdit.map(function (e) {
        return e.addEventListener("click", function (_ref2) {
          var target = _ref2.target;
          var view = target.parentElement;
          var edit = view.nextElementSibling;
          view.classList.remove("focus");
          edit.classList.add("focus");
        });
      });
      btnBackView.map(function (e) {
        return e.addEventListener("click", function (_ref3) {
          var target = _ref3.target;
          var edit = target.parentElement.parentElement;
          var view = edit.previousElementSibling;
          view.classList.add("focus");
          edit.classList.remove("focus");
        });
      });
      btnApproval.map(function (e) {
        return e.addEventListener("click", function (_ref4) {
          var target = _ref4.target;
          mngBody.map(function (e) {
            return e.classList.remove("focus");
          });
          empInfo.classList.add("focus");
          var id = target.nextElementSibling.value;
          _this2.aprEmp = id;
          var inptStart = selectElements("input[name='empStart']")[0];
          inptStart.value = _this2.getDate(_this2.now);
        });
      });
      btnReject.map(function (e) {
        return e.addEventListener("click", function (_ref5) {
          var target = _ref5.target;
          var id = target.previousElementSibling.value;
          var data = "empId=".concat(id);

          _this2.getResult("owner/rejectEmp.do", data, _this2.rejectEmp);
        });
      });
      sftItem.map(function (e) {
        return e.addEventListener("click", function (_ref6) {
          var target = _ref6.target;
          sftItem.map(function (e) {
            return e.classList.remove("selected");
          });
          var targetDiv = target.parentElement;
          targetDiv.classList.add("selected");
        });
      });
      btnApvEmp.addEventListener("click", function () {
        var empType = selectElements("input[name='empType']").find(function (e) {
          return e.checked;
        }).value;
        var empWage = selectElements("input[name='empWage']")[0].value;
        var sftId = document.querySelectorAll(".sftItem")[0].getAttribute("id");
        var empStart = selectElements("input[name='empStart']")[0].value;
        var data = "empId=".concat(_this2.aprEmp, "&empType=").concat(empType, "&empWage=").concat(empWage, "&sftId=").concat(sftId, "&empStart=").concat(empStart);

        _this2.getResult("owner/ApprovalEmp.do", data, _this2.approvalEmp);
      });
      btnEditEmp.map(function (b, index) {
        b.addEventListener("click", function (_ref7) {
          var target = _ref7.target;
          var s1 = document.querySelectorAll(".editEmpType")[index];
          var empType = s1.options[s1.selectedIndex].value;
          var empWage = document.querySelectorAll(".editEmpWage")[index].value;
          var s2 = document.querySelectorAll(".editSftId")[index];
          var sftId = s2.options[s2.selectedIndex].value;
          var data = "empId=".concat(target.nextElementSibling.value, "&empType=").concat(empType, "&empWage=").concat(empWage, "&sftId=").concat(sftId);

          _this2.getResult("owner/editEmp.do", data, _this2.approvalEmp);
        });
      });
    }
  }, {
    key: "rejectEmp",
    value: function rejectEmp(respText) {
      if (respText != "fail") {
        socket.send(respText);
        location.href = contextPath + "owner/manageEmp.do";
      }
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
    key: "getDate",
    value: function getDate(time) {
      var year = time.getFullYear();
      var month = time.getMonth() + 1 < 10 ? "0".concat(time.getMonth() + 1) : time.getMonth() + 1;
      var date = time.getDate() < 10 ? "0".concat(time.getDate()) : time.getDate();
      return "".concat(year, "-").concat(month, "-").concat(date);
    }
  }]);

  return MngEmp;
}();

var mngEmp = new MngEmp();
var dropShift = selectElements(".dropMenu .sftItem");
dropShift.map(function (s) {
  s.addEventListener("click", function (_ref8) {
    var currentTarget = _ref8.currentTarget;
    var target = document.querySelectorAll(".sftSelect")[0];
    var copy = currentTarget.cloneNode(true);
    target.firstElementChild.remove();
    target.appendChild(copy);
  });
});