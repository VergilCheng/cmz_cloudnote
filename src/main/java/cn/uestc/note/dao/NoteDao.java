package cn.uestc.note.dao;

import cn.uestc.note.entity.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoteDao {

    List<Map<String,Object>> findNotesByNotebookId(String notebookId);
    Note findNoteByNoteId(String noteId);
    int addNote(Note note);
    int updateNote(Note note);
    int deleteNoteById(String noteId);

    //动态多字段查询,在参数名称前加@Param("name")注解,name的值为对应Mapper.xml中。
    //在Mapper.xml文件中sql标签的ParameterType属性只能注释一个参数。所以多参数用
    //@Param标签
    List<Map<String,Object>> findNotes(@Param("userId") String userId,
                                       @Param("notebookId") String notebookId,
                                       @Param("statusId") String statusId);
    int deleteNotes(@Param("ids") String...ids);
}
