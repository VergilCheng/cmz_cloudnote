package cn.uestc.note.test;

import cn.uestc.note.dao.NotebookDao;
import cn.uestc.note.entity.Notebook;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NotebookDaoTest extends BaseTest {

    NotebookDao notebookDao;
    @Before
    public void initDao(){
       notebookDao= ctx.getBean("notebookDao",NotebookDao.class);
    }
    @Test
    public void testfindNotebooksByName() {
        String userId = "ea09d9b1-ede7-4bd8-b43d-a546680df00b";
        List<Map<String,Object>> list = notebookDao.findNotebooksByUserId(userId);
        System.out.println(list);
    }
    @Test
    public void testAddNotebooks() {
        String id = UUID.randomUUID().toString();
        String userId = "ea09d9b1-ede7-4bd8-b43d-a546680df00b";
        String name = "锂电池材料笔记";
        String type = "材料类型";
        String desc = "123123";
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        Notebook notebook = new Notebook(id,userId,name,type,desc,createtime);
        notebookDao.addNotebook(notebook);
    }
    @Test
    public void testDeleteNotebook() {
        String id = "0aad2603-f179-47af-9bfc-62530c02fa3a";
        int n = notebookDao.deleteNotebook(id);
        System.out.println(n);
    }
    @Test
    public void testFindNotebookByNotebookId() {
        String notebookId = "d58c3740-6d01-4302-8258-f8ba1cb8ffc0";
        Notebook notebook = notebookDao.findNotebookById(notebookId);
        System.out.println(notebook);
    }
}
