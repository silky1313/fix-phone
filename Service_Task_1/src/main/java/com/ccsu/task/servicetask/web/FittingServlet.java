package com.ccsu.task.servicetask.web;

import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.service.FittingService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/FittingServlet")
public class FittingServlet extends HttpServlet {
    FittingService fittingService = new FittingService();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxResult result = null;
        String choose = request.getParameter("choose");
        result = switch (choose) {
            case "findAllFittings" -> fittingService.findAllFittings();
            case "updateFittingQuantity" -> fittingService.updateFittingQuantity(request);
            case "updateFittingQuantityDecrease" -> fittingService.updateFittingQuantityDecrease(request);
            case "insertFitting" -> fittingService.insertFitting(request);
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