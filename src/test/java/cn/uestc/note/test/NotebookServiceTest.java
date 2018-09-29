package cn.uestc.note.test;

import cn.uestc.note.service.Impl.NotebookServiceImpl;
import cn.uestc.note.service.NotebookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NotebookServiceTest extends BaseTest {

    NotebookService notebookService;

    @Before
    public void initService() {
        notebookService = ctx.getBean("notebookService", NotebookService.class);
    }

    @Test
    public void testFindNotebooksByName(){
        String userId="ea09d9b1-ede7-4bd8-b43d-a546680df00b";
        List<Map<String,Object>> list = notebookService.findNotebooksByUserId(userId);
        System.out.println(list);
    }
    @Test
    public void testAddNotebooks() {
        String userId = "ea09d9b1-ede7-4bd8-b43d-a546680df00b";
        String name = "面试心得";
        String type = "面试";
        int n = notebookService.addNotebook(name,type,userId);
        System.out.println(n);
    }
    @Test
    public void testDeleteNotebook() {
        String notebookId = "89ada77e-c930-4a73-b936-34e9b5bf333c";
        int n = notebookService.deleteNotebook(notebookId);
        System.out.println(n);

    }
    @Test
    public void testFindNotebooks(){
        String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        List<Map<String,Object>> list = notebookService.findNotebooks(userId,0);
        System.out.println(list);
    }

}
