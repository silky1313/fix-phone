package com.ccsu.task.servicetask.dao;

import com.ccsu.task.servicetask.entity.Task;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class TaskDao {
    public List<Task> findAllTask() {
        Connection conn = null;
        try{
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "select * from tb_task";
            return query.query(conn, sql, new BeanListHandler<Task>(Task.class));
        }catch(Exception ignored) {
        }finally {
            DBCommon.closeConnection(conn);
        }
        return null;
    }

    public int deleteTaskById(int id) {
        Connection conn = null;
        try{
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "delete from tb_task where task_id=?";
            return query.execute(conn, sql, id);
        }catch(Exception ignored) {
        }finally {
            DBCommon.closeConnection(conn);
        }
        return 0;
    }

    public static void main(String[] args) {
        TaskDao taskDao = new TaskDao();
        System.out.println(taskDao.findAllTask());
    }
}
