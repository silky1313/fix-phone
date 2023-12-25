package com.ccsu.task.servicetask.dao;

import com.ccsu.task.servicetask.entity.Task;
import com.ccsu.task.servicetask.utils.TaskCode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class TaskDao {
    public List<Task> findAllTask() {
        Connection conn = null;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "select * from tb_task";
            return query.query(conn, sql, new BeanListHandler<Task>(Task.class));
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return null;
    }

    public int deleteTaskById(int id) {
        Connection conn = null;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "delete from tb_task where task_id=?";
            return query.execute(conn, sql, id);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return 0;
    }

    public int insertTask(Task task) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String task_no = TaskCode.createTaskNo();
            System.out.println(task);
            System.out.println(task_no);
            String sql = "insert into tb_task(cus_name, cus_phone, service_item, task_no, task_time, task_state)" +
                    "value(?, ?, ?, ?, now(), 0)";
            row = query.execute(conn, sql, task.getCus_name(), task.getCus_phone(), task.getService_item(), task_no);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }


    public static void main(String[] args) {
        TaskDao taskDao = new TaskDao();
        System.out.println(taskDao.findAllTask());
    }
}
