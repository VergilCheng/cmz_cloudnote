package cn.uestc.note.aop;

import cn.uestc.note.util.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * aop案例类
 *
 * 创建一个切面组件，其实就是一个javabean
 */
//@Component
//@Aspect//aop需要这两个注解,@Componet注解来进行组件扫描
public class DemoAspect {

    @Before("bean(userService)")//声明test方法将在userService的全部方法之前运行。
    public void test() {
        System.out.println("Hello World");
    }
    @After("bean(userService)")//after方法在userService的全部方法之后运行
    public void after(){
        System.out.println("After");
    }
    @AfterThrowing("bean(userService)")
    public void AfterThrowing() {
        System.out.println("AfterThrowing");
    }
    //excution切入点：excution(修饰词[一般写*表示都可以] 类名.方法名)
    //在指定类的方法下进行切入，login(..)说明什么参数都可以
    @AfterReturning("execution(* cn.uestc.note.service.UserService.login(..))")
    public void AfterReturning() {
        System.out.println("afterReturning");
    }

    /**
     * 环绕方法：@Aroud注解方法
     * 1.必须有返回值Object
     * 2.必须有参数 ProceedingJoinPoint
     * 3.必须抛出异常
     * 4.必须在方法中调用jp.proceed()
     * 5.返回业务方法的返回值
     */
    @Around("bean(userService)")
    public Object test5(ProceedingJoinPoint proceedingJoinPoint)
        throws Throwable{

        /*
         * 业务方法之前,记录时间
         */
        long time1 = System.currentTimeMillis();
        //业务方法：proceed()方法底层会调用业务层方法，我们这里是调用业务方法并返回User对象。如
        // 果不执行proceed，则跳过业务层方法。
        Object val = proceedingJoinPoint.proceed();
        //业务方法之后,将userService.login()返回值赋给Controller中的user
        //记录时间
        long time2 = System.currentTimeMillis();
        System.out.println((time2-time1)/1000);
        return val;

    }
}
