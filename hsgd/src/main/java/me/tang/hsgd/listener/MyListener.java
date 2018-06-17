package me.tang.hsgd.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyListener implements ServletContextListener {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("我的监听器启动了 -- " + simpleDateFormat.format(new Date()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("我的监听器销毁了 -- " + simpleDateFormat.format(new Date()));
    }
}
