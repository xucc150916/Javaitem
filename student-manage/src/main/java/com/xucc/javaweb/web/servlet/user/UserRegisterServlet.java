package com.xucc.javaweb.web.servlet.user;

import com.xucc.javaweb.entity.User;
import com.xucc.javaweb.service.UserService;
import com.xucc.javaweb.web.servlet.ApplicationLoadServlet;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户注册
 */
@WebServlet(name = "UserRegisterServlet", urlPatterns = {"/register"})
public class UserRegisterServlet extends ApplicationLoadServlet {
    
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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password_repeat");
        
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        
        
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordRepeat)) {
            request.setAttribute("error", "用户名或密码为空！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        if (!password.equals(passwordRepeat)) {
            request.setAttribute("error", "两次密码不一致！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        boolean loginResult = userService.register(userName, password);
        if (loginResult) {
            // 获取Session
            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_USER, user);
            // 客户端跳转
            response.sendRedirect("main.jsp");
        } else {
            request.setAttribute("error", "用户名已经存在！");
            // 服务器跳转
            request.getRequestDispatcher("register.jsp").forward(request, response);
            
        }
    }
}
