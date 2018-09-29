package cn.uestc.note.dao;

import cn.uestc.note.entity.Notebook;
import cn.uestc.note.service.NotebookNotFoundException;
import cn.uestc.note.service.UserNotFoundException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 持久层的方法，不能够重载，因为和命名空间挂钩
 */
public interface NotebookDao {

    //1.我们用map来设置mapper.xml的返回结果类型，虽然方法返回的是一个list，但是我们可以用
    //map来设置xml中的返回类型，其中key为字段名称，value为字段的值。如果我们如果要查询
    //的是一个对象中的非全部字段集合，我们可以用map来封装每条记录，然后放入list中，这个
    //是一种数据的设计模式，方便前端显示！
    //2.关于map的泛型，由于key是字段，字段永远是String类型，所以第一个泛型为String，第二个
    //为字段值，字段值有多种类型，所以用Object
    List<Map<String,Object>> findNotebooksByUserId (String userId) ;
    Notebook findNotebookById(String notebookId);
    int addNotebook(Notebook notebook);
    int deleteNotebook(String notebookId);

    /**
     * 这个dao方法为何要用@Param注解：
     * 如果我们不用，那么我们在mapper.xml中要写的sql语句标签中需要添加
     * ParametarType="数据类型"属性，而我们的分页查询中，属性类型有int和String两个
     * 而ParametarType只能写一个，所以这里用@Param标签
     * @param userId
     * @param start
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> findNotebooksByPage(@Param("userId") String userId,
                                                 @Param("start") int start,
                                                 @Param("pageSize") int pageSize,
                                                 @Param("table") String table);
}
