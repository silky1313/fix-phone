const { createApp } = Vue;
createApp({
  data() {
    return {
      code: "",
      msg: "",
      task: {},
      cusView: true,
      fittingView: false,
      fixView: false,
      fittings: [],
      tasks: [], //任务数据
    };
  },
  mounted() {
    $("#info").text("【 " + localStorage.getItem("admin_name") + " 】");
    this.loadTaskData();
  },
  methods: {
    exitLogin: function () {
      let result = confirm("确定要退出登录吗？");
      if (result) {
        localStorage.removeItem("admin_name");
        localStorage.removeItem("token");
        location.href = "login.html";
      }
    },
    login: function () {
      var that = this;
      var admin_name = $("#admin_name").val();
      var admin_password = $("#admin_password").val();
      var url = base_url + "AccountServlet";
      console.log(url);

      $.post(
        url,
        { admin_name: admin_name, admin_password: admin_password },
        function (data) {
          if (data.code >= 200 && data.code <= 299) {
            location.href = "manage.html";
            localStorage.setItem("admin_name", data.data.admin_name);
            localStorage.setItem("token", data.data.token);
          } else {
            that.msg = data.message;
          }
        }
      );
    },
    showPanel: function (panelName) {
      if (panelName == "cus") {
        this.cusView = true;
        this.fittingView = false;
        this.fixView = false;
        this.loadTaskData();
      } else if (panelName == "fitting") {
        this.loadFittig();
        this.fittingView = true;
        this.cusView = false;
        this.fixView = false;
      } else if (panelName == "fix") {
        this.loadTaskData();
        this.fixView = true;
        this.cusView = false;
        this.fittingView = false;
      }
    },
    loadFittig: function () {
      var that = this;
      var url = base_url + "/FittingServlet";
      $.post(url, function (data) {
        console.log(data);
        if (data.code >= 200 && data.code <= 299) {
          that.fittings = data.data;
        } else {
          that.msg = data.message;
        }
      });
    },
    loadTaskData: function () {
      var that = this;
      var url = base_url + "/TaskServlet";

      $.post(url, { choose: "findAllTasks" }, function (data) {
        console.log(data);
        if (data.code >= 200 && data.code <= 299) {
          that.tasks = data.data;
        } else {
          that.msg = data.message;
        }
      });
    },
    deleteTask: function (id, no) {
      var result = confirm("确定要删除任务编号为【" + no + "】的任务吗？");
      if (result) {
        var that = this;
        var url = base_url + "/TaskServlet";
        $.post(url, { choose: "delete", id: id }, function (data) {
          if (data.code >= 200 && data.code <= 299) {
            that.loadTaskData();
          }
          alert(data.message);
        });
      }
    },
    insertTask: function () {
      if (!this.task.cus_name) {
        alert("客户姓名不能为空！");
        return;
      }
      if (!this.task.cus_phone) {
        alert("客户电话不能为空！");
        return;
      }
      if (!/^[0-9]{11}$/.test(this.task.cus_phone)) {
        alert("电话号码应该是11位数字！");
        return;
      }
      if (!this.task.service_item) {
        alert("服务项目不能为空！");
        return;
      }

      let that = this;
      let url = base_url + "/TaskServlet";
      this.task.choose = "insertTask";
      $.post(url, this.task, function (data) {
        if (data.code >= 200 && data.code <= 299) {
          alert("添加成功！请收好服务小票 ！");
          that.task = {};
          that.loadTaskData();
        } else {
          alert(data.message);
        }
      });
    },
  },
}).mount("#app");
