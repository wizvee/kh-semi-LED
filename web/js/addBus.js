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
        $("input[name='name']").attr(
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
    },
    error: function() {
      alert("연결실패");
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
        $(this).parent().append($("<span class='error'>사용불가</span>"));
      }
    },
    error: function() {
      alert("연결 실패");
    }
  });
});
