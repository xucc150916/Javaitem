package com.xucc.javaweb.web.servlet.user;

import com.xucc.javaweb.entity.User;
import com.xucc.javaweb.service.UserService;
import com.xucc.javaweb.util.StringUtil;
import com.xucc.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登陆
 */
public class UserLoginServlet extends ApplicationLoadServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从请求中获取登陆信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // 将用户信息放到上下文中
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);

        // 非法输入
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            request.setAttribute("error", "用户名或密码为空！");
            // 跳转回主页
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);

        // 判断当前用户能否登陆成功
        boolean loginResult = userService.login(userName, password);
        if (loginResult) {
            // 获取Session
            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_USER, user);

            // 当前在线用户+1，将用户数放到上下文中
            if (request.getServletContext().getAttribute("onlineNumber") == null) {
                request.getServletContext().setAttribute("onlineNumber", 1);
            } else {
                ServletContext servletContext = request.getServletContext();
                int count = (int) servletContext.getAttribute("onlineNumber");
                servletContext.setAttribute("onlineNumber", count + 1);
            }

            // 客户端跳转
            response.sendRedirect("main.jsp");
        } else {
            request.setAttribute("error", "用户名或密码错误！");
            // 服务器跳转
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }


}
