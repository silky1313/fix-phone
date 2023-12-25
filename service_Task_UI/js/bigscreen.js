const { createApp } = Vue;
createApp({
  data() {
    return {
      tasks: [], //任务数据
    };
  },
  mounted() {
    setInterval(this.loadTaskData, 5000);
    this.loadTaskData();
  },
  methods: {
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
    calculateWidth: function (state) {
      switch (state) {
        case 0:
          return "30%";
        case 1:
          return "50%";
        case 2:
          return "100%";
        default:
          return "0%";
      }
    },
    calculateState: function (state) {
      switch (state) {
        case 0:
          return "维修中";
        case 1:
          return "维修中";
        case 2:
          return "取机";
      }
    },
    calculateTime: function (time) {
      let { year, month, day } = time.date;
      let { hour, minute, second } = time.time;
      return `${year}/${month}/${day} ${hour}-${minute}-${second}`;
    },
  },
}).mount("#app");
