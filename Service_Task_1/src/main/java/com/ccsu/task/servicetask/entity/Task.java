package com.ccsu.task.servicetask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class Task {
    private int task_id;
    private String cus_name;
    private String cus_phone;
    private String service_item;
    private String task_no;
    private LocalDateTime task_time;
    private int task_state;

    public Task(String cus_name, String cus_phone,String service_item) {
        this.cus_name = cus_name;
        this.cus_phone = cus_phone;
        this.service_item = service_item;
    }
}
