package com.phonemarket.interceptor;

import com.phonemarket.entity.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * 如何调用：
     * 按拦截器定义逆序调用
     * 何时调用：
     * 只有 preHandle 返回 true 才调用
     * 有什么用 ：
     * 在 DispatcherServlet 完全处理完请求后被调用，
     * 可以在该方法中进行一些资源清理的操作。
     */

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /** 后处理
     * 如何调用：
     * 按拦截器定义逆序调用
     * 何时调用：
     * 在拦截器链内所有拦截器返成功调用
     * 有什么用：
     * 在业务处理器处理完请求后，但是 DispatcherServlet 向客户端返回响应前被调用，在该方法中对用户请求 request 进行处理。
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /** 预处理
     * 如何调用：
     * 按拦截器定义顺序调用
     * 何时调用：
     * 只要配置了都会调用
     * 有什么用：
     * 预处理 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去
     * 进行处理，则返回 true。
     * 如果程序员决定不需要再调用其他的组件去处理请求，则返回 false。
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        HttpSession session = arg0.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return true;  //放行
        }
        arg1.sendRedirect(arg0.getContextPath() + "/view/login");  //页面通过重定向跳转到登录界面
        return false;
    }

}
