package cn.uestc.note.test;

import cn.uestc.note.entity.Note;
import cn.uestc.note.service.Impl.NoteServiceImpl;
import cn.uestc.note.service.NoteService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class NoteServiceTest extends BaseTest {

    NoteService noteService;


    //如果我们适用@Transactional，运用aop动态代理对业务层方法进行管理的时候
    //我们要让spring扫描接口。因为jdk的aop是基于接口对业务层进行实例化并调用
    //方法的，所以如果我们使用组件扫描加注解注入，并注入接口，是不会报错的
    //但是我们如果使用getBean，获取接口的实现类（这个情形下是NoteServiceImpl）
    //而不是NoteService，会报BeanNotRequriedException异常。
    @Before
    public void initService() {
        noteService = ctx.getBean("noteService", NoteService.class);
    }

    @Test
    public void TestfindNotesByNotebookId() {
        String notebookId = "64639e62-9241-4efb-874b-01e97257cb6e";
        List<Map<String, Object>> notes = noteService.findNotesByNotebookId(notebookId);
        for (Map m : notes) {
            System.out.println(m);
        }
    }

    @Test
    public void testFindNoteByNoteId() {
        String noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
        Note note = noteService.findNoteByNoteId(noteId);
        System.out.println(note);
    }
    @Test
    public void testAddNote() {
        String userId = "48595f52-b22c-4485-9244-f4004255b972";
        String notebookId = "12119052-874c-4341-b85d-6529e171ed83";
        String title = "实验添加笔记";
        Note newNote = noteService.addNote(userId,notebookId,title);
        System.out.println(newNote);
    }

    @Test
    public void testUpdataNote() {
        String title = "junit13";
        String body = "123456";
        String noteId = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
        boolean a = noteService.updateNote(title,body,noteId);
        System.out.println(a);
    }
    @Test
    public void testMoveNote() {
        String noteId = "2228527e-1cad-4318-94ee-3749a4dc25ce";
        String newNotebookId = "0cd94778-4d52-486d-a35d-263b3cfe6de9";
        String notebookId = "0b11444a-a6d6-45ff-8d46-282afaa6a655";
        boolean n = noteService.moveNote(noteId, notebookId, newNotebookId);
        System.out.println(n);
    }
    @Test
    public void testDeleteNote() {
        String[] notesId = {"051538a6-0f8e-472c-8765-251a795bc88f",
                            "91b7542e-3b63-4c9b-b212-70664e4a59bc"};
       boolean a = noteService.deleteNoteById(notesId);
        System.out.println(a);

    }
    @Test
    public void testAddNotes() {

    }
}
