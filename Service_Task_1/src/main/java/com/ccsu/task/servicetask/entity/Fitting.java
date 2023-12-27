package com.ccsu.task.servicetask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fitting {
    private int fit_id;
    private String fit_name;
    /*编号*/
    private String fit_no;
    /*数量*/
    private String fit_qty;
    /*原产地*/
    private String fit_factory;

    public Fitting(String fit_name, String fit_qty, String fit_factory) {
        this.fit_name = fit_name;
        this.fit_qty = fit_qty;
        this.fit_factory = fit_factory;
    }
}
