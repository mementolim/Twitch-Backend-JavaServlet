package com.mycompany.twitchPlus.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.twitchPlus.db.MySQLConnection;
import com.mycompany.twitchPlus.db.MySQLException;
import com.mycompany.twitchPlus.entity.LoginRequestBody;
import com.mycompany.twitchPlus.entity.LoginResponseBody;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read user data from the request body
        ObjectMapper mapper = new ObjectMapper();
        LoginRequestBody body = mapper.readValue(request.getReader(), LoginRequestBody.class);
        if (body == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String username;
        MySQLConnection connection = null;
        try {
            // Verify if the user ID and password are correct
            connection = new MySQLConnection();
            String userId = body.getUserId();
            String password = ServletUtil.encryptPassword(body.getUserId(), body.getPassword());
            username = connection.verifyLogin(userId, password);
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            connection.close();
        }

        // Create a new session for the user if user ID and password are correct, otherwise return Unauthorized error.
        if (!username.isEmpty()) {
            // Create a new session, put user ID as an attribute into the session object, and set the expiration time to 600 seconds.
            HttpSession session = request.getSession();  // create a session
            session.setAttribute("user_id", body.getUserId());
            session.setMaxInactiveInterval(600);
            // In tomcat, session will be banded to response automatically, we don't need to do it manually

            LoginResponseBody loginResponseBody = new LoginResponseBody(body.getUserId(), username);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponseBody));
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}