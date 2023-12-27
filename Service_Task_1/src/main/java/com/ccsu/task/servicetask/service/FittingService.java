package com.ccsu.task.servicetask.service;

import com.ccsu.task.servicetask.dao.DBCommon;
import com.ccsu.task.servicetask.dao.FittingDao;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Fitting;
import org.apache.commons.dbutils.QueryRunner;


import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class FittingService {

    FittingDao fittingDao = new FittingDao();

    public AjaxResult findAllFittings() {
        AjaxResult result = null;
        List<Fitting> tasks = fittingDao.findAllFitting();
        if (tasks != null && !tasks.isEmpty()) {
            result = AjaxResult.success("配件数据查询成功", tasks);
        } else {
            result = AjaxResult.fail("配件数据查询失败");
        }
        return result;
    }

    public AjaxResult updateFittingQuantity(HttpServletRequest request) {
        int fit_qty = Integer.parseInt(request.getParameter("fit_qty"));
        int fit_id = Integer.parseInt(request.getParameter("fit_id"));
        int success = fittingDao.changeState(fit_qty, fit_id);
        AjaxResult result = null;

        if (success == 1) {
            result = AjaxResult.success("更新配件数量成功");
        } else {
            result = AjaxResult.fail("更新配件数量失败");
        }
        return result;
    }

    public AjaxResult insertFitting(HttpServletRequest request  ) {
        String fit_name = request.getParameter("fit_name");
        String fit_qty = request.getParameter("fit_qty");
        String fit_factory = request.getParameter("fit_factory");

        Fitting fitting = new Fitting(fit_name, fit_qty, fit_factory);
        int success = fittingDao.insertFitting(fitting);
        AjaxResult result = null;

        if (success == 1) {
            result = AjaxResult.success("插入配件成功");
        } else {
            result = AjaxResult.fail("插入配件失败");
        }
        return result;
    }

    public AjaxResult updateFittingQuantityDecrease(HttpServletRequest request) {
        String fit_name = request.getParameter("fit_name");

        int success = fittingDao.updateFittingQuantityDecrease(fit_name);
        AjaxResult result = null;

        if (success == 1) {
            result = AjaxResult.success("修改配件成功");
        } else {
            result = AjaxResult.fail("修改配件失败");
        }
        return result;
    }
}
