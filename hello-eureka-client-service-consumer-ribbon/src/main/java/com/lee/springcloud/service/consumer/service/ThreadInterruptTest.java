package com.lee.springcloud.service.consumer.service;


import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020/4/14 14:01
 */
public class ThreadInterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

            while (true) {

            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // isInterrupted()方法是线程的实例方法,只是获取当前线程的中断状态
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());

        Thread main = Thread.currentThread();
        System.out.println("main: " + main.isInterrupted()); // false
        main.interrupt();
        System.out.println("main: " + main.isInterrupted()); // true
        boolean interrupted = Thread.interrupted();// 返回当前线程的中断状态,并且重置当前线程的中断状态为不中断
        System.out.println("main: " + interrupted); // true
        System.out.println("main: " + main.isInterrupted()); // false

    }
}
