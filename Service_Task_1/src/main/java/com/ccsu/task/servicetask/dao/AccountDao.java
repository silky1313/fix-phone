package com.ccsu.task.servicetask.dao;

import com.ccsu.task.servicetask.entity.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountDao {
    public Account findAccountByNameAndPassword(String username, String password) {
        Connection conn = null;
        Account account = null;
        try {
            conn = DBCommon.getConnection();
            QueryRunner query = new QueryRunner();
            String sql = "select * from tb_account where admin_name=? and admin_pwd=?";
            account = query.query(conn, sql, new BeanHandler<Account>(Account.class), username, password);
        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return account;
    }

    public static void main(String[] args) throws SQLException {
        AccountDao accountDao = new AccountDao();
        Account silky = accountDao.findAccountByNameAndPassword("silky", "123456");
        System.out.println(silky);
    }
}
