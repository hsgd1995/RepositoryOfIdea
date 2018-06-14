package com.entor.hrm.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("我的监听器启动了 -- "+simpleDateFormat.format(new Date()));
        System.out.println("牛逼呀！！！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("我的监听器销毁了 -- "+simpleDateFormat.format(new Date()));
    }
}
