package com.henry.Home;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Home {

    public String DateOfToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public long captcha() {
        return (long) (Math.random() * 1000000);
    }
}
