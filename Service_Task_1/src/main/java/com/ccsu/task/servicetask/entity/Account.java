package com.ccsu.task.servicetask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer aid;
    private String admin_name;
    private String admin_pwd;
    private Integer admin_role;
    private Integer admin_state;

    public Account(String admin_name, String admin_pwd, Integer admin_role, Integer admin_state) {
        this.admin_name = admin_name;
        this.admin_pwd = admin_pwd;
        this.admin_role = admin_role;
        this.admin_state = admin_state;
    }
}
