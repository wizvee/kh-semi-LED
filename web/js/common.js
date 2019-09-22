const dropToggle = selectElements(".dropdown_toggle");
dropToggle.map(e => {
  e.addEventListener("click", ({ target }) => {
    const menu = target.nextElementSibling;
    if (menu.style.display == "none") menu.style.display = "block";
    else menu.style.display = "none";
  });
});

function selectElements(name) {
  return Array.from(document.querySelectorAll(name));
}

$(".fa-sign-out").on("click", function() {
  location.href = "/p_190826_semi/logout.do";
});
