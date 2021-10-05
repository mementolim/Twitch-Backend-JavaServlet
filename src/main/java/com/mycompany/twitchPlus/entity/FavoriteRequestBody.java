package com.mycompany.twitchPlus.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class FavoriteRequestBody {
    private final Item favoriteItem;

    @JsonCreator      // @JsonCreator is used to fine tune the constructor or factory method used in deserialization.
    public FavoriteRequestBody(@JsonProperty("favorite") Item favoriteItem) { // parse request from frontend which key is "favorite"
        this.favoriteItem = favoriteItem;                                     // 1) json to item class using @JsonProperty 2)
    }

    public Item getFavoriteItem() {
        return favoriteItem;
    }
}
