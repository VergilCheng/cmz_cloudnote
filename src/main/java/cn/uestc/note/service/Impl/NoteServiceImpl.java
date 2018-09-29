package cn.uestc.note.service.Impl;

import cn.uestc.note.dao.NoteDao;
import cn.uestc.note.dao.NotebookDao;
import cn.uestc.note.dao.UserDao;
import cn.uestc.note.entity.Note;
import cn.uestc.note.entity.Notebook;
import cn.uestc.note.entity.User;
import cn.uestc.note.service.NoteNotFoundException;
import cn.uestc.note.service.NoteService;
import cn.uestc.note.service.NotebookNotFoundException;
import cn.uestc.note.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    @Qualifier("noteDao")
    private NoteDao noteDao;

    @Autowired
    @Qualifier("notebookDao")
    private NotebookDao notebookDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> findNotesByNotebookId(String notebookId) throws NotebookNotFoundException {

        if (notebookId==null||notebookId.trim().isEmpty()) {
            throw new NotebookNotFoundException("笔记本Id为空");
        }
        Notebook notebook = notebookDao.findNotebookById(notebookId);
        if (notebook == null) {
            throw new NotebookNotFoundException("笔记本不存在");
        }

        List<Map<String,Object>> notes = noteDao.findNotesByNotebookId(notebookId);

        return notes;
    }

    @Override
    public Note findNoteByNoteId(String noteId) throws NoteNotFoundException {
        if (noteId==null||noteId.trim().isEmpty()) {
            throw new NoteNotFoundException("笔记本ID为空");
        }
        Note note = noteDao.findNoteByNoteId(noteId);
        if (note==null) {

            throw new NoteNotFoundException("笔记不存在");

        }
        return note;
    }

    @Override
    public Note addNote(String userId, String notebookId, String title)
            throws UserNotFoundException, NotebookNotFoundException {
        if (userId == null || userId.trim().isEmpty()) {

            throw new UserNotFoundException("用户未登录");
        }
        User user = userDao.findUserById(userId);
        if (user==null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (notebookId == null || notebookId.trim().isEmpty()) {
            throw new NotebookNotFoundException("笔记本未创建");
        }
        Notebook notebook = notebookDao.findNotebookById(notebookId);
        if (notebook==null) {
            throw new NotebookNotFoundException("笔记本未创建");
        }

        if (title==null||title.trim().isEmpty()) {
            throw new RuntimeException("请添加笔记标题！");
        }
        String noteId = UUID.randomUUID().toString();
        String statusId = "1";
        String typeId = "123123123";
        String body = "";
        long createtime = System.currentTimeMillis();
        long lastModify = System.currentTimeMillis();
        Note newNote = new Note(noteId,notebookId,userId,statusId,typeId,title,body,createtime,lastModify);
        int n = noteDao.addNote(newNote);
        if (n!=1) {
            throw new RuntimeException("添加失败");
        }
        return newNote;
    }

    @Override
    public boolean updateNote(String title, String body, String noteId)
            throws NoteNotFoundException {
        if(noteId==null||noteId.trim().isEmpty()){
            throw new NoteNotFoundException("笔记id为空");
        }
        Note note = noteDao.findNoteByNoteId(noteId);
        if (note == null) {
            throw new NoteNotFoundException("笔记不存在");
        }
        Note newNote = new Note();
        if((body!=null)&&!body.equals(note.getBody())){
            newNote.setBody(body);
            newNote.setLast_modify(System.currentTimeMillis());
            newNote.setId(noteId);
        }
        if((title!=null)&&!title.equals(note.getTitle())){
            newNote.setTitle(title);
            newNote.setLast_modify(System.currentTimeMillis());
            newNote.setId(noteId);
        }
        int n = noteDao.updateNote(newNote);
        return n==1;
    }

    @Override
    public boolean moveNote(String noteId,String notebookId, String newNotebookId) throws UserNotFoundException, NotebookNotFoundException {
        if (notebookId==null||notebookId.trim().isEmpty()) {
            throw new NotebookNotFoundException("笔记本Id为空" );
        }
        Notebook notebook = notebookDao.findNotebookById(notebookId);
        if (notebook == null) {
            throw new NotebookNotFoundException("笔记本不存在");
        }
        if (newNotebookId==null||newNotebookId.trim().isEmpty()) {
            throw new NotebookNotFoundException("请选择正确的笔记本");
        }
        Notebook newNotebook = notebookDao.findNotebookById(notebookId);
        if (newNotebook == null) {
            throw new NotebookNotFoundException("新笔记本不存在");
        }

        Note newNote = new Note();
        if (!newNotebookId.equals(notebookId)) {
            newNote.setId(noteId);
            newNote.setNotebookId(newNotebookId);
        }

        int n = noteDao.updateNote(newNote);

        return n == 1;
    }

    @Override
    public boolean deleteNote(String noteId) throws NoteNotFoundException {
        if (noteId ==null){
            throw new NoteNotFoundException("笔记id不存在");
        }
        Note note = noteDao.findNoteByNoteId(noteId);
        if (note == null) {
            throw new NoteNotFoundException("笔记不存在");
        }
        Note newNote = new Note();
        String statusId = "0";
        newNote.setStatus_id(statusId);
        int n = noteDao.updateNote(newNote);
        return n==1;
    }

   // @Transactional
    public boolean deleteNoteById(String ...notesId) throws NoteNotFoundException{

        boolean a = true;

        for (String id:notesId) {
            if (id == null || !id.trim().isEmpty()) {
                if (id == null || id.trim().isEmpty()) {
                    throw new NoteNotFoundException("笔记Id为空");
                }
                int n =noteDao.deleteNoteById(id);
                System.out.println(n);
                a = a&&(n==1);
            }
        }
        return a;
    }
}
