package com.ccsu.task.servicetask.service;

import com.ccsu.task.servicetask.dao.TaskDao;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TaskService {
    TaskDao taskDao = new TaskDao();

    public AjaxResult findAllTasks() {
        AjaxResult result = null;
        List<Task> tasks = taskDao.findAllTask();
        if (tasks != null && !tasks.isEmpty()) {
            result = AjaxResult.success("任务数据查询成功", tasks);
        } else {
            result = AjaxResult.fail("任务数据查询失败");
        }
        return result;
    }

    public AjaxResult changeTaskById(HttpServletRequest request) {
        AjaxResult result = null;
        String id = request.getParameter("id");
        int success = taskDao.deleteTaskById(Integer.parseInt(id));

        if (success == 1) {
            result = AjaxResult.success("删除成功");
        } else {
            result = AjaxResult.fail("删除失败");
        }
        return result;
    }

    public AjaxResult insertTask(HttpServletRequest request) {
        AjaxResult result = null;
        String cus_name = request.getParameter("cus_name");
        String cus_phone = request.getParameter("cus_phone");
        String service_item = request.getParameter("service_item");

        System.out.println(cus_name);
        System.out.println(cus_phone);
        Task task = new Task(cus_name, cus_phone, service_item);
        int success = taskDao.insertTask(task);

        if (success == 1) {
            result = AjaxResult.success("添加成功");
        } else {
            result = AjaxResult.fail("添加失败，电话号码不能重复");
        }
        return result;
    }

    public AjaxResult updateTask(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int state = Integer.parseInt(request.getParameter("state"));
        int success = taskDao.changeState(id, state);
        AjaxResult result = null;

        if (success == 1) {
            result = AjaxResult.success("更新状态成功");
        } else {
            result = AjaxResult.fail("更新状态失败");
        }
        return result;
    }

    public AjaxResult findByPhone(HttpServletRequest request) {
        String T_phone = request.getParameter("T_phone");
        AjaxResult result = null;
        //查询所有配件
        List<Task> list = taskDao.findByPhone(T_phone);
        if (list != null && !list.isEmpty()) {
            result = AjaxResult.success("查询成功", list, 1);
        } else {
            result = AjaxResult.fail("无任务数据", 0);
        }

        return result;
    }
}
