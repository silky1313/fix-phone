package com.ccsu.task.servicetask.dao;

import com.ccsu.task.servicetask.entity.Account;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    //查询全部账号
    public List<Account> findAllAccount() {
        Connection conn = null;
        List<Account> list = null;
        try {
            conn = DBCommon.getConnection();
                String sql = "Select * from tb_account order by admin_state desc";
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<Account>(Account.class));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCommon.closeConnection(conn);
        }
        return list;
    }

    //修改账号信息
    public int updateAccountById(Account acc) {
        int row = 0;
        Connection conn = null;
        try {
            conn = DBCommon.getConnection();
            String sql = "update tb_account t set t.admin_name=?,t.admin_pwd=?,t.admin_role=?, t.admin_state=? where t.aid = ? ";
            QueryRunner qr = new QueryRunner();

            row = qr.execute(conn, sql, acc.getAdmin_name(), acc.getAdmin_pwd(), acc.getAdmin_role(), acc.getAdmin_state(), acc.getAid());

        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }

    //添加账号
    public int AddAccount(Account acc) {
        int row = 0;
        Connection conn = null;
        try {
            conn = DBCommon.getConnection();
            String sql = "insert into tb_account(admin_name,admin_pwd,admin_role,admin_state) values (?,?,?,?)";
            QueryRunner qr = new QueryRunner();

            row = qr.execute(conn, sql, acc.getAdmin_name(), acc.getAdmin_pwd(), acc.getAdmin_role(), acc.getAdmin_state());

        } catch (Exception ignored) {
        } finally {
            DBCommon.closeConnection(conn);
        }
        return row;
    }

    public static void main(String[] args) {
        AccountDao accountDao = new AccountDao();
        List<Account> allAccount = accountDao.findAllAccount();
        System.out.println(allAccount);
    }
}
