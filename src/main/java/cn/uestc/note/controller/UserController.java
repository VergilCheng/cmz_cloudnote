package cn.uestc.note.controller;

import cn.uestc.note.entity.User;
import cn.uestc.note.service.PasswordException;
import cn.uestc.note.service.UserExistException;
import cn.uestc.note.service.UserNotFoundException;
import cn.uestc.note.service.UserService;
import cn.uestc.note.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Resource
    private UserService service;

    //登录
    //这里方法改成get是方便调试，改成post可以禁止客户端用get请求登录，防止资料泄露
    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    @ResponseBody//加了注解，控制器返回的对象就被JacksonAPI来转换为JsonResult
    public Object login(String name,String password,HttpSession session){


        User user =  service.login(name,password);
        //登录成功，将用户状态保存在session中，用于拦截器以及过滤器检查
        //用户的登录情况。
        session.setAttribute("loginUser",user);//服务器赋予浏览器cookie中sessionId
        System.out.println(session.getId());
        return new JsonResult(user);

    }

    //注册
    @RequestMapping(value = "/regist.do")
    @ResponseBody
    public Object regist(String name,String nick,String password,String confirm){

        User user = service.regist(name, nick,password, confirm);


        return new JsonResult(user);
    }


    //分割线，如下方法为处理控制器抛出异常后的方法,现在放到了异常处理中
    //现在这些方法已经放到了一个抽象类中实现复用

}
