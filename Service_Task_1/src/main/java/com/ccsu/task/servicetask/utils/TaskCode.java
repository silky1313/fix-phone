package com.ccsu.task.servicetask.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskCode {
    public static String createTaskNo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd-HHmmssSSS");
        return "T-" + now.format(formatter);
    }
}
