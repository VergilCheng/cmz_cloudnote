package cn.uestc.note.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest {

    protected ClassPathXmlApplicationContext ctx;

    @Before
    public void initCtx(){
        //初始化spring容器，让spring容器创建对象
        ctx = new ClassPathXmlApplicationContext
                ("conf/spring-mvc.xml",
                        "conf/spring-mybatis.xml",
                        "conf/spring-service.xml",
                        "conf/spring-aop.xml");
    }

    @After
    public void close(){
        ctx.close();
    }


}
