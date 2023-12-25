package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.dao.TaskDao;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Task;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
    TaskDao taskDao = new TaskDao();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxResult result = null;
        String choose = request.getParameter("choose");

        if (choose.equals("findAllTasks")) {
            List<Task> tasks = taskDao.findAllTask();
            if (tasks != null && !tasks.isEmpty()) {
                result = AjaxResult.success("任务数据查询成功", tasks);
            } else {
                result = AjaxResult.fail("任务数据查询失败");
            }
        } else if (choose.equals("delete")) {
            String id = request.getParameter("id");
            int success = taskDao.deleteTaskById(Integer.parseInt(id));

            if (success == 1) {
                result = AjaxResult.success("删除成功");
            } else {
                result = AjaxResult.fail("删除失败");
            }
        } else if (choose.equals("insertTask")) {
            String cus_name = request.getParameter("cus_name");
            String cus_phone = request.getParameter("cus_phone");
            String service_item = request.getParameter("service_item");

            Task task = new Task(cus_name, cus_phone, service_item);

            int row = taskDao.insertTask(task);
            if (row == 1) {
                result = AjaxResult.success("添加成功");
            } else {
                result = AjaxResult.fail("添加失败");
            }
        }

        String json = gson.toJson(result);
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
