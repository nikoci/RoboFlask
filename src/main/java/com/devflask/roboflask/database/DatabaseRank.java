package com.devflask.roboflask.database;

import com.devflask.roboflask.interfaces.Rank;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

public class DatabaseRank implements Rank {

    public int id;
    public String guildId;
    public String name;
    public String description;
    public String icon;
    public int ladderPosition;

    public DatabaseRank(@NotNull int id, @NotNull String guildId, String data) {
        this.guildId = guildId;

        //TODO: implement to parse data correctly
        //new Gson().fromJson(data, this.getClass());
        /*
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.ladderPosition = ladderPosition;
         */
    }

    public int getId() {
        return this.id;
    }

    @Override
    public @NotNull String getGuildId() {
        return this.guildId;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull String getDescription() {
        return this.description;
    }

    @Override
    public @NotNull String getIcon() {
        return this.icon;
    }

    @Override
    public @NotNull int getLadderPosition() {
        return this.ladderPosition;
    }

    public String asJson() {
        return new Gson().toJson(this);
    }
}
