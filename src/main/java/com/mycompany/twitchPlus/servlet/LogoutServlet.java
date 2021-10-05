package com.mycompany.twitchPlus.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Destroy the session since the user is logged out.
        HttpSession session = request.getSession(false); // false means 有就有，没有也不创建新的
        if (session != null) {
            session.invalidate();
        }

    }
}
