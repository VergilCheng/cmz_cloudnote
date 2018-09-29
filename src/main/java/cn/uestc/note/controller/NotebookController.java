package cn.uestc.note.controller;

import cn.uestc.note.service.NotebookNotFoundException;
import cn.uestc.note.service.NotebookService;
import cn.uestc.note.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notebooks")
public class NotebookController extends AbstractController{

    //面试问题：Controller类并没有set方法，那为什么可以用注解对私有属性注入值？
    //答：例用反射，调用setAccessible方法设置访问权限（true则可以访问）。
    @Resource//所有的注解都是用反射解析的。
    private NotebookService notebookService;

    @RequestMapping(value = "/showNotebooks.do",method = RequestMethod.GET)
    @ResponseBody
    public Object showNotebooks(String userId) {

        List<Map<String, Object>> list = notebookService.findNotebooksByUserId(userId);

        return new JsonResult(list);
    }
    @RequestMapping("/addNotebooks.do")
    @ResponseBody
    public Object addNotebook(String name,String type,String userId){
       int n =  notebookService.addNotebook(name,type,userId);

        return new JsonResult(n);
    }

    @RequestMapping("/deleteNotebook.do")
    @ResponseBody
    public Object deleteNotebook(String notebookId) {
        int n = notebookService.deleteNotebook(notebookId);
        return new JsonResult(n);
    }




}
