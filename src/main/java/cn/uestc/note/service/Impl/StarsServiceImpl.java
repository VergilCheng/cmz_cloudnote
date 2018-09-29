package cn.uestc.note.service.Impl;


import cn.uestc.note.dao.StarsDao;
import cn.uestc.note.dao.UserDao;
import cn.uestc.note.entity.Stars;
import cn.uestc.note.entity.User;
import cn.uestc.note.service.StarService;
import cn.uestc.note.service.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service("starsService")
public class StarsServiceImpl implements StarService {

    @Resource
    StarsDao starsDao;

    @Resource
    UserDao userDao;


    @Override
    @Transactional(readOnly = true)//设置只读属性
    public Stars findStarsByUserId(String userId) throws UserNotFoundException {
        if (userId == null || userId.trim().isEmpty()) {
            throw new UserNotFoundException("用户Id为空");
        }
        Stars stars = starsDao.findStarsByUserId(userId);
        if (stars == null) {
            throw new RuntimeException("您没有参加星星活动");
        }
        return stars;
    }

    @Transactional
    public int addStarsByUserId(String userId, int newstars) throws UserNotFoundException {
        if (userId == null || userId.trim().isEmpty()) {
            throw new UserNotFoundException("用户Id为空");
        }

        User user = userDao.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        Stars oldStars = starsDao.findStarsByUserId(userId);
        if (oldStars != null) {
            Stars stars1 = new Stars();
            stars1.setStar(newstars);
            stars1.setUserId(userId);
            int n = starsDao.updateStarsByUserId(stars1);
            if (n != 1) {
                throw new RuntimeException("添加失败");
            }
            return n;
        } else {
            String id = UUID.randomUUID().toString();
            int stars = newstars;
            Stars Stars = new Stars(id, userId, stars);
            int n = starsDao.insertStarsByUserId(Stars);
            if (n != 1) {
                throw new RuntimeException("添加失败");


            }
            return n;
        }
    }
}