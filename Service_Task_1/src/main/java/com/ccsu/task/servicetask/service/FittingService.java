package com.ccsu.task.servicetask.service;

import com.ccsu.task.servicetask.dao.FittingDao;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Fitting;


import javax.servlet.http.HttpServletRequest;
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

    public AjaxResult updateFitting(HttpServletRequest request) {
        int fit_qty = Integer.parseInt(request.getParameter("fit_qty"));
        int fit_id = Integer.parseInt(request.getParameter("fit_id"));
        System.out.println(fit_qty);
        System.out.println(fit_id);
        int success = fittingDao.changeState(fit_qty, fit_id);
        AjaxResult result = null;

        if (success == 1) {
            result = AjaxResult.success("更新配件数量成功");
        } else {
            result = AjaxResult.fail("更新配件数量失败");
        }
        return result;
    }
}
