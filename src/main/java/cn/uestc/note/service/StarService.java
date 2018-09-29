package cn.uestc.note.service;

import cn.uestc.note.entity.Stars;

public interface StarService {

    Stars findStarsByUserId(String userId) throws UserNotFoundException;

    int addStarsByUserId(String userId,int star) throws UserNotFoundException;

}
