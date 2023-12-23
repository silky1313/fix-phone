package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.dao.FittingDao;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Fitting;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/FittingServlet")
public class FittingServlet extends HttpServlet {
    FittingDao fittingDao = new FittingDao();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Fitting> fittings = fittingDao.findAllFitting();
        AjaxResult result = null;
        if(fittings != null && !fittings.isEmpty()) {
            result =  AjaxResult.success("配件数据查询成功", fittings);
        } else{
            result = AjaxResult.fail("配件数据查询失败");
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