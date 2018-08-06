package com.xucc.javaweb.web.servlet.user;

import com.xucc.javaweb.web.servlet.ApplicationLoadServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户退出
 */
@WebServlet(name = "UserLogoutServlet", urlPatterns = {"/logout"})
public class UserLogoutServlet extends ApplicationLoadServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取Session
        HttpSession session = request.getSession();
        session.removeAttribute(CURRENT_USER);

        // 当前人数-1
        ServletContext servletContext = request.getServletContext();
        int count = (int) servletContext.getAttribute("onlineNumber");
        servletContext.setAttribute("onlineNumber", count - 1);


        // 客户端跳转
        response.sendRedirect("index.jsp");
    }
}
