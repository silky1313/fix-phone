package com.ccsu.task.servicetask.entity;

import lombok.Data;

@Data
public class Account {
    private Integer aid;
    private String admin_name;
    private String admin_pwd;
    private String admin_role;
    private Integer admin_state;
}
