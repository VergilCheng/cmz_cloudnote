package cn.uestc.note.service;

import cn.uestc.note.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 业务层方法可以重载，因为不和持久层挂钩，业务层方法只是
 * 内部调用持久层方法。
 */
public interface NotebookService  {

    List<Map<String,Object>> findNotebooksByUserId(String userId) throws UserNotFoundException;
    int addNotebook(String name,String type,String userId)
            throws UserNotFoundException,NotebookNotFoundException;
    int deleteNotebook(String notebookId) throws NotebookNotFoundException;
    List<Map<String,Object>> findNotebooks(String userId,Integer page) throws UserNotFoundException;

}
