package cn.uestc.note.service;

import cn.uestc.note.entity.User;
import sun.security.util.Password;

/**
 * 业务层接口
 */
public interface UserService {

    /**
     * 登录功能 登录成功返回用户信息，失败则抛出异常
     * @param name 用户名
     * @param password 密码
     * @return 如果登录成功就返回登录用户信息
     * @throws UserNotFoundException 用户不存在
     * @throws PasswordException 密码错误
     */
    User login(String name,String password)
            throws UserNotFoundException,PasswordException;
    User regist(String name,String nick,String password,String confirm)
            throws UserExistException,UserNotFoundException,PasswordException;
}
