package org.example.service.impl;

import com.sun.xml.internal.ws.util.CompletedFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/22 20:43
 */
@Service
public class AsyncServiceImpl {

    @Async
    public void delay(){
        try {
            Thread.sleep(10000);
            System.out.println("我被打印了");
        } catch (InterruptedException e) {
            throw new RuntimeException("睡眠");
        }
    }

    @Async
    public CompletableFuture<String> testReturnData(){
        String msg = "";
        try {
            Thread.sleep(10000);
            msg = "未来10秒的数据";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(msg);
    }

    // 异步无返回值
    public CompletableFuture<Void> testMethod(){
        return CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(5000);
                System.out.println("等待5秒后的数据");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 有返回值的异步方法
    public CompletableFuture<String> testMethod2(){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
                System.out.println("异步有返回值的方法");
                return "5秒后的返回数据";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async
    public String futureTask(){
        String msg = "";
        try {
            Thread.sleep(10000);
            msg = "未来10秒的返回数据";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("任务处理完毕之后的数据："+msg);
        return msg;
    }

    /**
     * 未来task2
     *
     * @param id id
     * @return 执行结果
     */
    @Async
    public String futureTask2(Integer id){

        String msg = "";
        try {
            Thread.sleep(10000);
            msg = "未来10秒的返回数据";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("任务处理完毕之后的数据："+msg);
        return msg;
    }
}
