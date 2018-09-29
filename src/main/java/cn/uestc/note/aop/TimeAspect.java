package cn.uestc.note.aop;

import jdk.nashorn.internal.ir.JoinPredecessor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于记录业务方法执行时间的aop以及性能测试
 */

@Component
@Aspect
public class TimeAspect {
    @Around("bean(*Service)")
    public Object periodTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {



        long t1 = System.currentTimeMillis();

        //业务方法
        Object val = proceedingJoinPoint.proceed();
        //JoinPoint对象可以帮助我们获取业务方法的详细信息：方法签名，调用参数等
        //getSignature(),返回一个方法签名对象。
        Signature signature = proceedingJoinPoint.getSignature();


        long t2 = System.currentTimeMillis();
        //Signature重写了toString方法，输出方法名称与返回值类型
        System.out.println(signature+"消耗时间："+(t2-t1));
        return val;

    }

}
