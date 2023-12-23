let token = localStorage.getItem("token");

if (!token) {
  $.post(base_url + "/CheckServlet", { token: token }, function (data) {
    console.log(data);
    if (data.data === false) {
      location.href = "login.html";
    }
  });
}
