package com.mycompany.twitchPlus.servlet;

import com.mycompany.twitchPlus.external.TwitchClient;
import com.mycompany.twitchPlus.external.TwitchException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameId = request.getParameter("game_id");
        if (gameId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        TwitchClient client = new TwitchClient();
        try {

//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchItems(gameId)));
            ServletUtil.writeItemMap(response, client.searchItems(gameId));
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
