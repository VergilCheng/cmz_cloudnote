package cn.uestc.note.test;

import cn.uestc.note.entity.User;
import cn.uestc.note.service.Impl.UserServiceImpl;
import cn.uestc.note.service.PasswordConfirmException;
import cn.uestc.note.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class UserServiceTest extends BaseTest {

    UserService service;


    @Before
    public void initService() {
        service = ctx.getBean("userService",UserService.class);
    }


    @Test
    public void testLogin(){
        String name = "Jean";
        String password = "123";
        String salt = "今天你吃了吗？";
        String pwd = DigestUtils.md5Hex(password+salt);
        User user = service.login(name,password);
        System.out.println(user);
    }

    @Test
    public void testRegist() throws PasswordConfirmException {

        String name = "Jordan";
        String nick="";
        String password = "123";
        String confirm = "123";
        User user = service.regist(name,nick,password,confirm);
        System.out.println(user);
    }
}
