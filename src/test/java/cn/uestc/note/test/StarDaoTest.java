package cn.uestc.note.test;

import cn.uestc.note.dao.StarsDao;
import cn.uestc.note.entity.Stars;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class StarDaoTest extends BaseTest{

    StarsDao starsDao;

    @Before
    public void initDao() {
        starsDao = ctx.getBean("starsDao",StarsDao.class);
    }

    @Test
    public void testInsertStarsByUserId() {
        Stars stars= new Stars();
        stars.setId(UUID.randomUUID().toString());
        stars.setUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
        stars.setStar(5);
        int n = starsDao.insertStarsByUserId(stars);
        System.out.println(n+"以及"+stars);
    }

    @Test
    public void testupdateStarsByUserId() {

        String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        String id = null;
        int star = 10;
        Stars newStars = new Stars();
        newStars.setStar(star);
        newStars.setUserId(userId);
        int n = starsDao.updateStarsByUserId(newStars);
        System.out.println(n);

    }

    @Test
    public void testfindStarsByUserId() {
        String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";

        Stars stars = starsDao.findStarsByUserId(userId);
        System.out.println(stars);

    }

}
