package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.service.AccountService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {

    AccountService accountService = new AccountService();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("choose");
        AjaxResult result = switch (action) {
            case "list" -> accountService.findAllTasks();
            case "denlu" -> accountService.findAccountByNameAndPassword(request);
            case "add" -> accountService.insertAccount(request);
            case "update" -> accountService.updateAccount(request);
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
