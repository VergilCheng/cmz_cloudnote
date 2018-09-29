package cn.uestc.note.web;

import cn.uestc.note.entity.User;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器测试类,以及用来处理拦截的*.html请求
 */
@WebFilter(filterName = "Filter")
public class DemoFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    private String login = "/log_in.html";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //从请求中获取session
        HttpSession session = request.getSession();
        //从请求中获取URL
        String url = request.getRequestURI();
        System.out.println("Filter:"+url);//打桩
        //调用后续的web请求：*.html、*.do、*.js等等
        //1.放过log_in.html，在web.xml文件中配置url已经拦截了所有*.html的url
        //这里是对这些html进行进一步筛选
        if (url.endsWith("log_in.html")) {
            chain.doFilter(req, resp);
            //放过请求后一定要写return，不然会发生二次相应问题。
            return;
        }
        if(url.endsWith("DemoHtml.html")){
            chain.doFilter(req,resp);
            return;
        }
        //2.检查用户是否登录，如果登录就放过，没有就重定向到log_in.html
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {//这里不能用session.isNew()判断，如果攻击者两次没有登录访问edit.html则会进入edit.html
            //重定向一定要写绝对路径防止访问异常
            //转发是java提供的，只在servlet与jsp之间的进行，html没有转发！！！
            response.sendRedirect(((HttpServletRequest) req).getContextPath() + login);
            return;//还是避免二次相应的异常
        }
        chain.doFilter(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
