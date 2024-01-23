package com.lynx.uzz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author wubaocheng1
 * @date 2023/11/1 16:04
 */
public class ScheduleTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "延迟队列");
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("5s后执行:" + sdf.format(new Date()));
            }
        }, 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("3s后执行:" + sdf.format(new Date()));
            }
        }, 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        System.out.println("结束");
    }

}
