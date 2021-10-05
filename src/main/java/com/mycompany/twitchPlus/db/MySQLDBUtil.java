package com.mycompany.twitchPlus.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

public class MySQLDBUtil {

    public static String getMySQLAddress() throws IOException {

        Properties prop = new Properties();
        String propFileName = "config.properties";

//        read config file by using input stream
        InputStream inputStream = MySQLDBUtil.class.getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);

//        read username and password in config file
        String username = prop.getProperty("mysql_user");
        String password = prop.getProperty("mysql_password");
        String instance = prop.getProperty("mysql_instance");
        String port_num = prop.getProperty("mysql_port_num");
        String db_name = prop.getProperty("mysql_db_name");
        // Encode special characters in your password.
        try {
            password = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
                instance, port_num, db_name, username, password);
    }

}
