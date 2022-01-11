package com.devflask.roboflask.database.entry;

import com.google.gson.Gson;

public interface Entry {
    default String asJson() {
        return new Gson().toJson(this);
    }
}
