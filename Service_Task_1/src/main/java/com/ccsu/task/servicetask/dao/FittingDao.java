package com.ccsu.task.servicetask.dao;


import com.ccsu.task.servicetask.entity.Fitting;
import com.ccsu.task.servicetask.utils.TaskCode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class FittingDao {
    public List<Fitting> findAllFitting() {
        Connection conn = null;
        try{
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "select * from tb_fittings";
            return query.query(conn, sql, new BeanListHandler<Fitting>(Fitting.class));
        }catch(Exception ignored) {
        }finally {
            DBCommon.closeConnection(conn);
        }
        return null;
    }

    public int changeState(int fit_qty, int fit_id) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "update tb_fittings set fit_qty=? where fit_id=?";
            row = query.update(conn, sql, fit_qty, fit_id);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }

    public int insertFitting(Fitting fitting) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String fitting_no = TaskCode.createTaskNo();
            String sql = "insert into tb_fittings(fit_name, fit_qty, fit_factory, fit_no)" +
                    "value(?, ?, ?, ?)";
            row = query.execute(conn, sql, fitting.getFit_name(), fitting.getFit_qty(), fitting.getFit_factory(), fitting_no);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }


    public int updateFittingQuantityDecrease(String fit_name) {
        Connection conn = null;
        int row = 0;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "update tb_fittings set fit_qty=fit_qty-1 where fit_name=?";
            row = query.update(conn, sql, fit_name);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }
}
