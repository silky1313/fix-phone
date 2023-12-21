package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.dao.AccountDao;
import com.ccsu.task.servicetask.entity.Account;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.utils.JwtUtil;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    AccountDao accountDao = new AccountDao();
    Gson gson = new Gson();

    //doGet的时候解决get请求
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String admin_name = request.getParameter("admin_name");
        String admin_password = request.getParameter("admin_password");
        String json = gson.toJson(AjaxResult.fail("账号或者密码错误"));

        Account silky = accountDao.findAccountByNameAndPassword(admin_name, admin_password);

        PrintWriter out = response.getWriter();
        if (silky != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("admin_name", silky.getAdmin_name());
            String token = JwtUtil.createToken(map);
            map.put("token", token);

            json = gson.toJson(AjaxResult.success("查询成功", map));
        } else{
            json = gson.toJson(AjaxResult.fail("账号或密码错误", 400));
        }
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
