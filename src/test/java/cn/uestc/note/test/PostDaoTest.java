package cn.uestc.note.test;

import cn.uestc.note.dao.PostDao;
import cn.uestc.note.entity.Post;
import org.junit.Before;
import org.junit.Test;

public class PostDaoTest extends BaseTest {

    PostDao dao;
    @Before
    public void initDao() {
        dao = ctx.getBean("postDao", PostDao.class);
    }
    @Test
    public void testFindPostById() {
        Post post = dao.findPostById(1);
        System.out.println(post);
    }

}
