package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/23 10:31
 */
@Aspect
@Component
public class TaskLogAspect {

    @Pointcut("@annotation(org.example.annotation.TaskLog)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        // 获取参数
        Integer arg = (Integer) point.getArgs()[0];
        if(arg == 3){
            return "该任务下工具未启用";
        }
        // 方法执行前通知
        try {
            // 方法开始执行
            result = point.proceed();
        } catch (Throwable e) {
            // 方法异常通知
            throw new RuntimeException(e);
        }
        // 方法返回通知
        System.out.println("result1 = " + result);
        return result;
    }

    @Around("pointCut()")
    public Object around2(ProceedingJoinPoint point){
        Object result = null;
        // 获取参数
        Integer arg = (Integer) point.getArgs()[0];
        if(arg == 4){
            return "该任务下没有工具";
        }
        // 方法执行前通知
        try {
            // 方法开始执行
            result = point.proceed();
        } catch (Throwable e) {
            // 方法异常通知
            throw new RuntimeException(e);
        }
        // 方法返回通知
        System.out.println("result2 = " + result);
        return result;
    }
}
