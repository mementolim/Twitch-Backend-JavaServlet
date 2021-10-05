package com.mycompany.twitchPlus.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseBody {
    @JsonProperty("user_id")
    private final String userId;  // 这里field加annotation是为了让Java Object ==> JSON

    @JsonProperty("name")
    private final String name;

    public LoginResponseBody(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
