class Business {
  constructor(ownId, busName, busNum, busAddr, busPhone) {
    this.ownId = ownId;
    this.busName = busName;
    this.busNum = busNum;
    this.busAddr = busAddr;
    this.busPhone = busPhone;
  }
}

class Shift {
  constructor(sftName, sftDay, sftOn, sftOff) {
    this.sftName = sftName;
    this.sftDay = sftDay;
    this.sftOn = sftOn;
    this.sftOff = sftOff;
  }
}

class InsertBus {
  constructor() {
    this.count = 0;
    this.setInit();
  }

  setInit() {
    const btnAddSft = document.querySelector("#btn_addSft");
    const btnIstBus = document.querySelector("#btn_istBus");

    btnAddSft.addEventListener(
      "click",
      ({ target }) => {
        const area = target.parentElement;
        const sftArea = document.createElement("div");
        const sftName = `근무조${++this.count}`;
        sftArea.setAttribute("class", "sft_area");
        sftArea.innerHTML = `
        <div><input type="text" name="sftName" value="${sftName}" class="inpt-outline">
        <span><i class="fa fa-tags" aria-hidden="true"></i></span></div>
        <div class="sftDay">
          <input type="checkbox" name="sftDay" value="1" id="day1">
          <label for="day1">일</label>
          <input type="checkbox" name="sftDay" value="2" id="day2">
          <label for="day2">월</label>
          <input type="checkbox" name="sftDay" value="3" id="day3">
          <label for="day3">화</label>
          <input type="checkbox" name="sftDay" value="4" id="day4">
          <label for="day4">수</label>
          <input type="checkbox" name="sftDay" value="5" id="day5">
          <label for="day5">목</label>
          <input type="checkbox" name="sftDay" value="6" id="day6">
          <label for="day6">금</label>
          <input type="checkbox" name="sftDay" value="7" id="day7">
          <label for="day7">토</label>
        </div>
        <div><input type="text" name="sftOn" class="inpt-outline">
        <span><i class="fa fa-clock-o" aria-hidden="true"></i></span></div>
        <input type="hidden" name="sftOff" value="18:00">`;
        area.append(sftArea);
      },
      false
    );

    btnIstBus.addEventListener(
      "click",
      () => {
        const ownId = userInfo.userId;
        const busName = document.getElementsByName("bName")[0].value;
        const busNum = document.getElementsByName("bNum")[0].value;
        const busAddr = document.getElementsByName("addr")[0].value;
        const busPhone = document.getElementsByName("phone")[0].value;
        const business = new Business(
          ownId,
          busName,
          busNum,
          busAddr,
          busPhone
        );

        const sftId = document.getElementsByName("sftName");
        const sftDay = document.getElementsByName("sftDay");
        const sftOn = document.getElementsByName("sftOn");
        const sftOff = document.getElementsByName("sftOff");
        const sftArr = [];
        for (let i = 0; i < this.count; i++) {
          const sft = new Shift(
            sftId[i].value,
            sftDay[i].value,
            sftOn[i].value,
            sftOff[i].value
          );
          sftArr.push(sft);
        }

        const data = this.getData(
          JSON.stringify(business),
          JSON.stringify(sftArr)
        );

        this.getResult("/insertBus.do", data, this.submitBusiness);
      },
      false
    );
  }

  submitBusiness(respText) {
    if (respText != "fail")
      location.href = "/p_190826_semi/owner/main.do?selectBus=" + respText;
  }

  getResult(servletURL, data, fn) {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load", () => {
      fn(xhr.responseText);
    });
    xhr.open("post", "/p_190826_semi/" + servletURL);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(data);
  }

  getData(business, shift) {
    return `business=${business}&shift=${shift}`;
  }
}

const insertBus = new InsertBus();

// OLD!!!!!!!!!!!!!!!!!! *************
function fn_search() {
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/search.do",
    dataType: "text",
    data: { data: $("#input").val() },
    success: function(data) {
      var json = JSON.parse(data);
      $(".addBus_area .result_area").remove();
      for (var i in json.items) {
        var area = $("<div class='result_area'>");
        area.append($("<p class='title'>").html(json.items[i].title));
        area.append($("<p class='address'>").html(json.items[i].roadAddress));
        area.append($("<p class='telephone'>").html(json.items[i].telephone));
        $(".addBus_area").append(area);
      }
      $(".addBus_area .result_area").on("click", function() {
        $("input[name='bName']").attr(
          "value",
          $(this)
            .find(".title")
            .text()
        );
        $("input[name='addr']").attr(
          "value",
          $(this)
            .find(".address")
            .text()
        );
        $("input[name='phone']").attr(
          "value",
          $(this)
            .find(".telephone")
            .text()
        );
        $(this)
          .siblings()
          .removeClass("selected");
        $(this).addClass("selected");

        $(".selected").on("click", function(e) {
          $(".addBus_area").fadeOut("fast");
          $(".addInfo_area").fadeIn("slow");
        });
      });
    }
  });
}

$("input[name='bNum']").on("blur", function() {
  $.ajax({
    type: "post",
    async: false,
    url: "/p_190826_semi/owner/checkBusNum.do",
    dataType: "text",
    data: { bNum: $(this).value },
    success: function(data) {
      if (
        data == "unable" &&
        $(this)
          .siblings()
          .is(".error")
      ) {
        $(this)
          .parent()
          .append($("<span class='error'>사용불가</span>"));
      }
    },
    error: function() {
      alert("연결 실패");
    }
  });
});
