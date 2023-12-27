package com.ccsu.task.servicetask.service;

import com.ccsu.task.servicetask.dao.AccountDao;
import com.ccsu.task.servicetask.entity.Account;
import com.ccsu.task.servicetask.entity.AjaxResult;
import com.ccsu.task.servicetask.entity.Task;
import com.ccsu.task.servicetask.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public class AccountService {
    AccountDao accountDao = new AccountDao();

    public AjaxResult findAllTasks() {
        List<Account> list = accountDao.findAllAccount();
        AjaxResult result = null;
        if (list != null && !list.isEmpty()) {
            result = AjaxResult.success("查询成功", list, 1);
        } else {
            result = AjaxResult.fail("无账户数据", 0);
        }
        return result;
    }

    public AjaxResult findAccountByNameAndPassword(HttpServletRequest request) {
        AjaxResult result = null;
        String admin_name = request.getParameter("admin_name");
        String admin_pwd = request.getParameter("admin_pwd");

        Account account = accountDao.findAccountByNameAndPassword(admin_name, admin_pwd);
        if (account != null) {
            HashMap map = new HashMap();
            map.put("admin_name", account.getAdmin_name());
            String token = JwtUtil.createToken(map);
            map.put("token", token);
            result = AjaxResult.success("查询成功", map, 1);
        } else {
            result = AjaxResult.fail("账户名或密码错误", 0);
        }
        return result;
    }

    public AjaxResult insertAccount(HttpServletRequest request) {
        AjaxResult result = null;
        String admin_name = request.getParameter("admin_name");
        String admin_pwd = request.getParameter("admin_pwd");
        String admin_role = request.getParameter("admin_role");
        String admin_state = request.getParameter("admin_state");
        Account account = new Account(admin_name, admin_pwd, Integer.parseInt(admin_role), Integer.parseInt(admin_state));
        int row = accountDao.AddAccount(account);

        if (row == 1) {
            result = AjaxResult.success("添加成功", 1);
        } else {
            result = AjaxResult.fail("添加失败", 0);
        }
        return result;
    }

    public AjaxResult updateAccount(HttpServletRequest request) {
        AjaxResult result = null;
        String aid = request.getParameter("aid");
        String admin_name = request.getParameter("admin_name");
        String admin_pwd = request.getParameter("admin_pwd");
        String admin_role = request.getParameter("admin_role");
        String admin_state = request.getParameter("admin_state");

        Account account =
                new Account(Integer.parseInt(aid), admin_name, admin_pwd, Integer.parseInt(admin_role), Integer.parseInt(admin_state));
        int row = accountDao.updateAccountById(account);

        if (row == 1) {
            result = AjaxResult.success("修改成功", 1);
        } else {
            result = AjaxResult.fail("修改失败", 0);
        }
        return result;
    }


}
