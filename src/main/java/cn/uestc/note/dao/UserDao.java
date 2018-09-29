package cn.uestc.note.dao;

import cn.uestc.note.entity.User;

public interface UserDao {

    User findUserByName(String name);
    User findUserById(String id);
    int addUser(User user);

}
