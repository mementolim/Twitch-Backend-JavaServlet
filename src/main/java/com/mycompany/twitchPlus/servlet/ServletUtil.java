package com.mycompany.twitchPlus.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.twitchPlus.entity.Item;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
In SearchServlet.doGet() and FavoriteServlet.doGet(), we have similar code to add items to the response body.
We can consider having a utility function to avoid duplicated code.
Add a static function writeItemMap to ServletUtil to help write the ItemMap to the response body.
 */


public class ServletUtil {
    public static void writeItemMap(HttpServletResponse response, Map<String, List<Item>> itemMap) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));
    }

    // Help encrypt the user password before save to the database
    public static String encryptPassword(String userId, String password) throws IOException {
        return DigestUtils.md5Hex(userId + DigestUtils.md5Hex(password)).toLowerCase();
    }
}
