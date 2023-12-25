package com.ccsu.task.servicetask.dao;


import com.ccsu.task.servicetask.entity.Fitting;
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

    public static void main(String[] args) {
        FittingDao fittingDao = new FittingDao();
        List<Fitting> fittings = fittingDao.findAllFitting();
        System.out.println(fittings);
        //fittings.forEach(System.out::println);
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
}
