package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.utils.JwtUtil;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {

    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取request中的token
        String token =  request.getParameter("token");
        boolean success = JwtUtil.verifyToken(token);
        AjaxResult result = AjaxResult.success("验证成功", success);
        String json = gson.toJson(result);

        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
