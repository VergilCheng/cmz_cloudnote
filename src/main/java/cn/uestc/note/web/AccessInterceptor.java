package cn.uestc.note.web;

import cn.uestc.note.entity.User;
import cn.uestc.note.util.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用于拦截*.do请求
 *
 * 与配置Filter不同，Filter的运行配置在web.xml,而
 * Interceptor的运行需要注册为javabean，也就是说
 * 在spring-mvc.xml中进行配置。
 *
 *
 * Filter和Interceptor的区别：
 * 1.Filter能做任何拦截功能，包括Interceptor的拦截功能
 * 2.Interceptor不能拦截html的页面请求。因为Interceptor工作在Spring-mvc层面，是不能
 * 拦截html的。而Filter工作在web层面，范围比Interceptor大，所以Interceptor只能拦截
 * 对servlet的请求。
 *
 *
 *
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        System.out.println("Interceptor:"+url);

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("loginUser");
        //如果没有登录则返回错误Json而不是JsonResult，这里不能直接返回JsonResult,
        // 需要用request来返回消息.因为没有@ResponseBody标签，所以只能用request来
        // 实现@ResponseBody标签的底层实现
        if (user == null) {
            JsonResult result = new JsonResult("需要重新登录");
            //设置返回的数据类型以及编码，保证不会乱码
            httpServletResponse.setContentType("application/json;charset=UTF-8");//这里是设置浏览器读取 的数据编码
            httpServletResponse.setCharacterEncoding("UTF-8");//这里是设置res打开的字符流的写入编码。
            //将JsonResult这个javabean转换成Json对象
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            //用res打开输出流，写入json
            httpServletResponse.getWriter().println(json);
            httpServletResponse.flushBuffer();
            return false;//不放过请求
        }
        //如果登录成功则请求放过
        return true;//放过请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
