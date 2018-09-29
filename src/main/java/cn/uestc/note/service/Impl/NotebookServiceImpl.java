package cn.uestc.note.service.Impl;

import cn.uestc.note.dao.NotebookDao;
import cn.uestc.note.dao.UserDao;
import cn.uestc.note.entity.Notebook;
import cn.uestc.note.entity.User;
import cn.uestc.note.service.NotebookNotFoundException;
import cn.uestc.note.service.NotebookService;
import cn.uestc.note.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 业务层抛出异常，持久层层别抛出异常！！！！
 */
@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("notebookDao")
    private NotebookDao notebookDao;

    //@Value("#{jdbc.pageSize}")
    //private Integer pageSize;

    @Override
    public List<Map<String, Object>> findNotebooksByUserId(String userId) throws UserNotFoundException{
        if (userId==null||userId.trim().isEmpty()) {
            throw new UserNotFoundException("用户Id为空");
        }
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new UserNotFoundException(("用户不存在"));
        }
        List<Map<String,Object>> list = notebookDao.findNotebooksByUserId(userId);

        return list;
    }

    @Override
    public int addNotebook(String name,String type,String userId)
            throws UserNotFoundException,NotebookNotFoundException {
        if (name==null||name.trim().isEmpty()) {
            throw new NotebookNotFoundException("笔记本名为空");
        }
        User user = userDao.findUserById(userId);
        if (user==null) {
            throw new UserNotFoundException("用户不存在");
        }
        if(type==null||type.trim().isEmpty()){
            throw new NotebookNotFoundException("请指定笔记本类型");
        }
        String id = UUID.randomUUID().toString();
        String notebookname = name;
        String user_id = userId;
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        String desc = "123";
        String Type = type;
        Notebook notebook = new Notebook(id,user_id,notebookname,Type,desc,createtime);

        int n = notebookDao.addNotebook(notebook);

        if (n!=1) {
            throw new RuntimeException("添加失败");
        }
        return n;
    }

    public int deleteNotebook(String notebookId) throws NotebookNotFoundException {

        if (notebookId == null || notebookId.trim().isEmpty()) {
            throw  new NotebookNotFoundException("笔记本id为空");
        }
        Notebook notebook = notebookDao.findNotebookById(notebookId);

        if (notebook == null) {
            throw new NotebookNotFoundException("笔记本不存在！");
        }

        int n = notebookDao.deleteNotebook(notebookId);
        return n;
    }

    /**
     * 分页查询
     * @param userId
     * @param page
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public List<Map<String, Object>> findNotebooks(String userId, Integer page) throws UserNotFoundException {
        if (userId==null||userId.trim().isEmpty()) {
            throw new UserNotFoundException("用户Id为空");
        }
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new UserNotFoundException(("用户不存在"));
        }
        if(page==null){
            page=0;//没事儿别乱抛出异常。
        }
        int pageSize = 4;
        int start = page*pageSize;
        String table = "cn_notebook";

        List<Map<String,Object>> list = notebookDao.findNotebooksByPage(userId,start,pageSize,table);

        return list;

    }
}
