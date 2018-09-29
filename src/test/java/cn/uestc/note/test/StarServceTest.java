package cn.uestc.note.test;

import cn.uestc.note.entity.Stars;
import cn.uestc.note.service.StarService;
import org.junit.Before;
import org.junit.Test;

public class StarServceTest extends BaseTest {

    StarService starService;
    @Before
    public void initService() {
        starService = ctx.getBean("starsService",StarService.class);
    }

    @Test
    public void testFindStarsByUserId() {
        String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        Stars stars = starService.findStarsByUserId(userId);
        System.out.println(stars);
    }
    @Test
    public void testaddStarsByUserId() {
        String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        int stars = 10;
        int n = starService.addStarsByUserId(userId,stars);
        System.out.println(n);
    }

}

