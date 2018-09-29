package cn.uestc.note.dao;

import cn.uestc.note.entity.Stars;

public interface StarsDao {

    Stars findStarsByUserId(String userId);

    int insertStarsByUserId(Stars stars);

    int updateStarsByUserId(Stars stars);


}
