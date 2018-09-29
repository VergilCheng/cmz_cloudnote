package cn.uestc.note.test;

import cn.uestc.note.dao.UserDao;
import cn.uestc.note.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class UserDaoTest extends BaseTest{

    UserDao dao;
    @Before
    public void initDao(){
        dao = ctx.getBean("userDao",UserDao.class);
    }

    @Test
    public void testFindUserByName(){
        String name = "程铭哲";
        //UserDao持久层接口的实现对象由spring-mybatis.xml文件的组件扫描实现，并给与其id为userDao，
        //虽然组件扫描没有搭配注解是不能创建对象的，但是spring-mybatis.xml中配置的mapperScannerConfiger
        //会帮我们创建对象。
        //配合1号注释查看。

        User user = dao.findUserByName(name);
        System.out.println(user);
    }
    @Test
    public void addUser(){
        String id = UUID.randomUUID().toString();
        String name = "程铭哲";
        String salt = "今天你吃了吗？";
        String password = DigestUtils.md5Hex("123456"+salt);
        String token = "";
        String nick = "";
        User user = new User(id,name,password,token,nick);
        UserDao dao = ctx.getBean("userDao",UserDao.class);
        int success = dao.addUser(user);
        System.out.println(success);
    }
    @Test
    public void testFindUserById() {
        String id = "ea09d9b1-ede7-4bd8-b43d-a546680df00b";
        User user = dao.findUserById(id);
        System.out.println(user);
    }
}
