package cn.uestc.note.dao;

import cn.uestc.note.entity.Post;

public interface PostDao {

    Post findPostById(Integer id);

}
