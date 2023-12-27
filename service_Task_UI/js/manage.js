const { createApp } = Vue;
createApp({
  data() {
    return {
      code: "",
      msg: "",
      task: {},
      fitting: {},
      cusView: true,
      fittingView: false,
      fixView: false,
      accountView: false,
      fittings: [],
      account_list: [],
      tasks: [], //任务数据
      accounts: {}, //v-model绑定
      aaccount: {}, //v-model绑定，用户添加账户
      options_role: [ //v-model绑定，用于admin_role下拉框的选择
        {
          value: 0,
          text: '管理员'
        },
        {
          value: 1,
          text: '前台接待'
        },
        {
          value: 2,
          text: '维修师傅'
        },
        {
          value: 3,
          text: '检测师傅'
        },
      ],
      options_state: [ //v-model绑定，用于admin_state下拉框的选择
        {
          value: 0,
          text: '正常'
        },
        {
          value: 1,
          text: '锁定'
        },
        {
          value: 2,
          text: '注销'
        },
      ],
    };
  },
  mounted() {
    $("#info").text("【 " + localStorage.getItem("admin_name") + " 】");
    this.loadTaskData();
    this.loadFittig();
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
        this.accountView = false,
          this.loadTaskData();
      } else if (panelName == "fitting") {
        this.loadFittig();
        this.fittingView = true;
        this.cusView = false;
        this.fixView = false;
        this.accountView = false;
      } else if (panelName == "fix") {
        this.loadTaskData();
        this.fixView = true;
        this.cusView = false;
        this.fittingView = false;
        this.accountView = false;
      } else if (panelName == "account") {
        this.loadAccountData();
        this.accountView = true,
          this.fixView = false;
        this.cusView = false;
        this.fittingView = false;
      }
    },
    loadFittig: function () {
      var that = this;
      var url = base_url + "/FittingServlet";
      $.post(url, { choose: "findAllFittings" }, function (data) {
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
        if (data.code >= 200 && data.code <= 299) {
          that.tasks = data.data;
        } else {
          that.msg = data.message;
        }
      });
    },
    deleteTask: function (id, name, state) {
      if (state == 0) {
        var result = confirm("确定要删除客户名为【" + name + "】的维修订单吗？");
      } else {
        var result = confirm("确定要完成客户名为【" + name + "】的维修订单吗？");
      }

      if (result) {
        var that = this;
        var url = base_url + "/TaskServlet";
        let myState = state == 0 ? -1 : 3;
        $.post(url, { choose: "updateTask", id: id, state: myState }, function (data) {
          console.log(data);
          if (data.code >= 200 && data.code <= 299) {
            alert("操作成功！");
            that.loadTaskData();
          } else {
            alert("操作失败， 请稍后再试！");
          }
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
      console.log(this.task);
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
    changeState: function (id, state, fit_name) {
      let url = base_url + "/TaskServlet";
      let that = this;
      this.task.choose = "updateTask";
      this.task.id = id;
      this.task.state = state;
      if (state == 2) {//维修中可以点击完成维修删除配件
        let url = base_url + "/FittingServlet";
        $.post(url, { choose: "updateFittingQuantityDecrease", fit_name: fit_name }, function (data) {
          console.log(data);
        });
      }

      $.post(url, this.task, function (data) {
        if (data.code >= 200 && data.code <= 299) {
          that.loadTaskData();
        }
        alert("状态修改成功");
      });
    },
    updatefittingQuantity: function (id) {
      let url = base_url + "/FittingServlet";
      let that = this;
      let inputId = "quantity-" + id;
      quantity = document.getElementById(inputId).value;
      document.getElementById(inputId).value = "";
      console.log(quantity);
      $.post(
        url,
        { choose: "updateFittingQuantity", fit_id: id, fit_qty: quantity },
        function (data) {
          if (data.code >= 200 && data.code <= 299) {
            that.loadFittig();
          }
          alert(data.message);
        }
      );
    },
    insertFitting: function () {
      if (!this.fitting.fit_name) {
        alert("配件名称不能为空！");
        return;
      }
      if (!this.fitting.fit_qty) {
        alert("配件数量不能为空！");
        return;
      }
      if (!this.fitting.fit_factory) {
        alert("配件产地不能为空！");
        return;
      }

      let that = this;
      let url = base_url + "/FittingServlet";
      this.fitting.choose = "insertFitting";
      console.log(this.fitting);
      $.post(url, this.fitting, function (data) {
        if (data.code >= 200 && data.code <= 299) {
          that.fitting = {};
          that.loadFittig();
        }
        alert(data.message);
      });
    },
    loadAccountData: function () {
      var that = this;
      $.post(base_url + "/AccountServlet", {
        "choose": "list"
      }, function (data) {
        that.account_list = data.data;
      });
    },
    memaccount: function (account) {
      this.accounts = {
        aid: account.aid,
        admin_name: account.admin_name,
        admin_pwd: account.admin_pwd,
        admin_role: account.admin_role,
        admin_state: account.admin_state
      };
    },
    updateaccount: function () {
      var that = this;
      this.accounts.choose = "update"; //json
      $.post(base_url + "/AccountServlet", this.accounts, function (data) {
        // console.log(this.accounts);
        // console.log(data);
        if (data.code == 1) {
          alert("修改账号信息成功");
          that.loadAccountData();
          // 删除完成后重新加载
        } else {
          alert("修改账号信息失败");
        }
      });
    },
    addaccount: function () {
      var that = this;
      this.aaccount.choose = "add"; //json
      console.log(this.addaccount);
      $.post(base_url + "/AccountServlet", this.aaccount, function (data) {
        if (data.code == 1) {
          alert("添加账号信息成功");
          that.loadAccountData();
          // 删除完成后重新加载
        } else {
          alert("添加账号信息失败");
        }
      });
    },
  },
}).mount("#app");
