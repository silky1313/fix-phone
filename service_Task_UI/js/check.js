let token = localStorage.getItem("token");

if (!token) {
  //有token去服务器检查
  $.post(base_url + "/checkServlet", { token: token }, function (data) {
    if (data.data === "false") {
      //token有效
      location.href = "login.html";
    }
  });
}
