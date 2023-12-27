package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    TaskService taskService = new TaskService();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String choose = request.getParameter("choose");
        AjaxResult result  = switch (choose) {
            case "findAllTasks" -> taskService.findAllTasks();
            case "insertTask" -> taskService.insertTask(request);
            case "updateTask" -> taskService.updateTask(request);
            case "select" -> taskService.findByPhone(request);
            default -> null;
        };

        String json = gson.toJson(result);
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
