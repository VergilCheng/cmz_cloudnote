package cn.uestc.note.service;

import cn.uestc.note.entity.Note;

import java.util.List;
import java.util.Map;

public interface NoteService {

    List<Map<String,Object>> findNotesByNotebookId(String NotebookId) throws NotebookNotFoundException;

    Note findNoteByNoteId(String noteId) throws NoteNotFoundException;

    Note addNote(String userId,String notebookId,String title)
            throws UserNotFoundException,NotebookNotFoundException;
    boolean updateNote(String title,String body,String noteId) throws NoteNotFoundException;

    boolean moveNote(String noteId,String notebookId,String newNotebookId)
        throws UserNotFoundException,NotebookNotFoundException;
    boolean deleteNote(String noteId) throws NoteNotFoundException;

    boolean deleteNoteById(String ...notesId) throws NoteNotFoundException;
}
