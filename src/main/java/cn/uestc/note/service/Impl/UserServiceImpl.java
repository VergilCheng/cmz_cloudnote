package cn.uestc.note.service.Impl;

import cn.uestc.note.dao.UserDao;
import cn.uestc.note.entity.User;
import cn.uestc.note.service.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Properties;
import java.util.UUID;

//配合组建扫描
@Service("userService")
public class UserServiceImpl implements UserService {

    //@Resource这个注解有时候会bug，导致不能进行依赖注入，导致空指针异常，这时候换成如下注解。
    //spring容器在进行注入的时候，先会根据id是否和引用名称一致来进行呼入，如果找不到，则找到
    //类名称或者其子类来进行注入，如果扫描到两个类，则不能注入。这个就是autowired注入方式。
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Value("#{jdbc.salt}")
    private String salt;

    public User login(String name, String password) throws UserNotFoundException,PasswordException {

        //测试反射在aop以及整个ssm框架中的作用
        //String str = null;
        //str.endsWith("123");

        System.out.println("业务层方法login");//打桩


        if(password==null||password.trim().isEmpty()){
            throw new PasswordException("密码空");
        }

        if (name==null||name.trim().isEmpty()){
            throw new UserNotFoundException("用户为空");
        }
        //打桩
        //System.out.println(userDao);

        User user = userDao.findUserByName(name);
        if(user==null){
            throw new UserNotFoundException("用户不存在");
        }
        salt="今天你吃了吗？";
        String pwd = DigestUtils.md5Hex(password.trim()+salt);
        if(pwd.equals(user.getPassword())){

            return user;
        }
        throw new PasswordException("密码错误");
    }

    @Override
    public User regist(String name,String nick,String password, String confirm)
            throws UserExistException, UserNotFoundException, PasswordException {

        if (name == null || name.trim().isEmpty()) {

            throw new UserNotFoundException("用户名为空");
        }

        User user = userDao.findUserByName(name);

        if (user != null) {

            throw new UserExistException("用户名已经存在");
        }

        if (password == null || password.trim().isEmpty()) {

            throw new PasswordException("密码为空");
        }

        if(nick==null||nick.trim().isEmpty()){
            nick = name;
        }

        if (confirm == null || confirm.trim().isEmpty()) {

            throw new PasswordException("请填写确认密码");

        }
        if(password.equals(confirm)){
            String id = UUID.randomUUID().toString();
            salt="今天你吃了吗？";
            password = DigestUtils.md5Hex(password+salt);
            nick = "";
            String token = "";
            user = new User(id,name,password,token,nick);
            int n = userDao.addUser(user);
            if (n!=1) {
                throw new RuntimeException("添加失败");
            }
            return user;
        }
        throw new PasswordException("确认密码错误");
    }
}
