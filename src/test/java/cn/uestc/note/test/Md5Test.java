package cn.uestc.note.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.web.servlet.DispatcherServlet;

public class Md5Test {
    /**
     * 测试Md5Hex加密
     */
    @Test
    public void testMd5(){
        String password = "123";
        String salt = "今天你吃了吗？";
        String pwd = DigestUtils.md5Hex(password+salt);
        System.out.println(pwd);
    }
}
