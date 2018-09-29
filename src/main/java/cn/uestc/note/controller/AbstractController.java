package cn.uestc.note.controller;

import cn.uestc.note.service.*;
import cn.uestc.note.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//泛化处理
public abstract class AbstractController {
    /**
     * 其他方法出现UserNotFoundException，执行这个处理异常方法。
     * @param e
     * @return
     */

    @ExceptionHandler(UserExistException.class)
    @ResponseBody
    public Object handleUserExistException(UserExistException e){
        e.printStackTrace();
        return new JsonResult(4,e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public Object handleUserNotFoundException(UserNotFoundException e){
        e.printStackTrace();
        return new JsonResult(2,e);
    }

    /**
     * 同上方法
     * @param e
     * @return
     */
    @ExceptionHandler(PasswordException.class)
    @ResponseBody
    public Object PasswordException(PasswordException e){
        e.printStackTrace();
        return new JsonResult(3,e);
    }

    /**
     * 在其他控制器方法出现异常的时候，执行异常处理方法handleException
     * 这个注解方法，能够简化Controller中对异常捕获的大量try-catch块。
     * 从而简化开发
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        e.printStackTrace();
        return new JsonResult(e);
    }
    @ExceptionHandler(NotebookNotFoundException.class)
    @ResponseBody
    public Object handlerNotebookNoteFoundException(NotebookNotFoundException e){
        e.printStackTrace();
        return new JsonResult(5,e);

    }

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseBody
    public Object handlerNoteNoteFoundException(NoteNotFoundException e){
        e.printStackTrace();
        return new JsonResult(6,e);

    }
}
