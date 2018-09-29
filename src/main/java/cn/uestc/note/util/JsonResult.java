package cn.uestc.note.util;

import cn.uestc.note.entity.User;
import cn.uestc.note.service.UserNotFoundException;
import com.sun.net.httpserver.Authenticator;

import java.io.Serializable;

//1.JsonResult类的设计出现正是设计模式思想的体现，它的出现方便前端的
// ajax对JsonResult的状态码进行业务逻辑判断，同时JsonResult也可以作为Json字符串返回。
//2.JsonResult将访问正常和异常封装在了一个JsonResult类中，在前端接收服务器返回的数据时
//不管时什么类型的数据，前端都可以进行一致性处理。
//3.如果将类似JsonResult的类作为json字符串进行传输，一定要记得重写toString方法。
public class JsonResult implements Serializable{

    private static final long serialVersionUID = 123412341234L;

    private static final int SUCCESS=0;
    private static final int ERROR = 1;

    //数据状态
    private int state;
    //正确时返回的数据
    private Object data;
    //错误时返回的错误消息
    private String message;
    public JsonResult(Object data) {

        this.state= SUCCESS;
        this.data = data;
    }

    //拦截器那里需要用到的重载构造器
    public JsonResult(String error) {

        this.state= ERROR;
        this.data = error;
    }

    public JsonResult(Throwable throwable){
        this.state = ERROR;
        this.message = throwable.getMessage();
    }

    public JsonResult(int i, Throwable e) {
        this.state = i;
        this.message = e.getMessage();
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getERROR() {
        return ERROR;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
