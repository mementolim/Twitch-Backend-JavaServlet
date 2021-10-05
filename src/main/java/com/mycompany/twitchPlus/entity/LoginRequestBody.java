package com.mycompany.twitchPlus.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestBody {
    private final String userId;   // 为什么这里不写@JsonProperty，因为这里写是为了让Java object ==> JSON 返回前端
    private final String password;

    @JsonCreator  //@JsonCreator的作用，用底下的constructor来解析JSON
    public LoginRequestBody(@JsonProperty("user_id") String userId, @JsonProperty("password") String password) {
        this.userId = userId;       // 这里写@JsonProperty是为了让JSON ==> Java Object
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
