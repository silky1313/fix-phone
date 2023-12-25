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

    public AjaxResult deleteTaskById(HttpServletRequest request) {
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
        String id = request.getParameter("id");
        int success = taskDao.deleteTaskById(Integer.parseInt(id));

        if (success == 1) {
            result = AjaxResult.success("删除成功");
        } else {
            result = AjaxResult.fail("删除失败");
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
}
