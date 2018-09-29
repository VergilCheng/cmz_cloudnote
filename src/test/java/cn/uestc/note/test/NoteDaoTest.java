package cn.uestc.note.test;

import cn.uestc.note.dao.NoteDao;
import cn.uestc.note.entity.Note;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NoteDaoTest extends BaseTest{

    NoteDao noteDao;

    @Before
    public void initDao() {
        noteDao = ctx.getBean("noteDao",NoteDao.class);
    }
    @Test
    public void testFindNotesByNotebookId() {
        String notebookId = "64639e62-9241-4efb-874b-01e97257cb6e";
        List<Map<String,Object>> noteList = noteDao.findNotesByNotebookId(notebookId);
        for (Map m:noteList) {
            System.out.println(m);
        }
    }
    @Test
    public void testFindNoteByNoteId() {
        String noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
        Note note = noteDao.findNoteByNoteId(noteId);
        System.out.println(note);
    }
    @Test
    public void testAddNote() {
        String id = UUID.randomUUID().toString();
		String notebookId = "";
		String userId = "";
		String status_id = "1";
		String type_id ="123123";
		String title = "我日死你的瘟";
		String body = "";
		long createtime = System.currentTimeMillis();
		long last_modify = System.currentTimeMillis();

		Note note = new Note(id,notebookId,userId,status_id,
                type_id,title,body,createtime,last_modify);
		int n = noteDao.addNote(note);
        System.out.println(n);
    }
    @Test
    public void testUpdateNote() {
        Note note = new Note();
        String title = "junit";
        String body = "123456";
        String noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
        note.setId(noteId);
        note.setBody(body);
        note.setTitle(title);
        note.setLast_modify(System.currentTimeMillis());
        int n = noteDao.updateNote(note);
        System.out.println(n);

    }
    @Test
    public void testDeleteNotes() {
        String id1 = "5d9587d3-b15a-486a-970d-9964c5b2410e";
        String id2 = "737a9a27-b02f-4cc2-b447-eba0f047cd0d";
        String id3 = "b5fc7da0-b5e8-4792-8f1f-9fd4b347a64f";
        int n = noteDao.deleteNotes(id1,id2,id3);
        System.out.println(n);
    }

}
