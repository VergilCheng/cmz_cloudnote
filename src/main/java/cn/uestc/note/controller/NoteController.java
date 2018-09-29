package cn.uestc.note.controller;

import cn.uestc.note.entity.Note;
import cn.uestc.note.service.NoteService;
import cn.uestc.note.service.NotebookNotFoundException;
import cn.uestc.note.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {

    @Resource
    NoteService noteService;

    @RequestMapping(value="/showNotes.do",method = RequestMethod.GET)
    @ResponseBody
    public Object findNotes(String notebookId) {

        List<Map<String, Object>> notes = noteService.findNotesByNotebookId(notebookId);

        return new JsonResult(notes);
    }

    @RequestMapping("/showNote.do")
    @ResponseBody
    public Object findNote(String noteId) {
        Note note = noteService.findNoteByNoteId(noteId);
        return new JsonResult(note);
    }

    @RequestMapping("/addNote.do")
    @ResponseBody
    public Object addNote(String userId,String notebookId,String title) {
       Note newNote = noteService.addNote(userId,notebookId,title);

        return new JsonResult(newNote);
    }
    @RequestMapping("updateNote.do")
    @ResponseBody
    public Object updateNote(String title,String body,String noteId) {
        boolean a = noteService.updateNote(title,body,noteId);
        return new JsonResult(a);
    }
    @RequestMapping("/moveNote.do")
    @ResponseBody
    public Object moveNote(String noteId,String notebookId,String newNotebookId) {

        boolean a = noteService.moveNote(noteId,notebookId,newNotebookId);
        return new JsonResult(a);

    }
}
