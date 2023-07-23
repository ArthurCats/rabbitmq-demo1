package org.example.controller;

import org.example.annotation.TaskLog;
import org.example.service.impl.AsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/22 18:51
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    AsyncServiceImpl asyncService;

    @GetMapping("/test")
    public String testAsync(@RequestParam String message){
        asyncService.delay();
        return message + "async!";
    }

    @GetMapping("/test2")
    public String testAsync2(@RequestParam String message){
        CompletableFuture<String> stringCompletableFuture = asyncService.testReturnData();
        String res = "";
        try {
            res = stringCompletableFuture.get();
            System.out.println("res = " + res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return "响应成功";
    }

    @GetMapping("test3")
    public String test3(@RequestParam String msg){
        long start = System.currentTimeMillis();
        try {
            asyncService.testMethod().get(4000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e.toString());
        }
        // 耗时时间
        float totalTime = (float)(System.currentTimeMillis() - start) / 1000;
        System.out.println("total time: " + totalTime + " seconds");
        return msg+"异步无返回值!";
    }

    @GetMapping("test4")
    public String test4(@RequestParam String msg){
        long start = System.currentTimeMillis();
        CompletableFuture<String> future = asyncService.testMethod2();
        // 计算结果完成时的回调方法
        try {
            future.whenComplete((k, v)->{
                System.out.println("返回k= "+ k);
                System.out.println("异常v= "+ v);
            }).exceptionally(e -> {
                System.out.println("捕获异常："+e.toString());
                return "okkkkkkkkkkkkkkkkkkkkkkkkk";
            }).get(4000,TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e.toString());
        }
        // 耗时时间
        float totalTime = (float)(System.currentTimeMillis() - start) / 1000;
        System.out.println("total time: " + totalTime + " seconds");
        return msg+"异步有返回值!";
    }

    @GetMapping("/return")
    @TaskLog
    public String testReturn(@RequestParam Integer id){
        asyncService.futureTask2(id);
        return "ok";
    }
}
