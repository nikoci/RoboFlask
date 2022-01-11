package com.devflask.roboflask.database.entry;

import com.devflask.roboflask.Robo;
import com.devflask.roboflask.database.DatabaseManager;
import com.devflask.roboflask.interfaces.Rank;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

public class RankEntry implements Rank, Entry{

    public String rankID;
    public String guildID;
    public String name;
    public String description;
    public String icon;
    public int ladderPosition;

    public RankEntry(String rankID, String guildID, String name, String description, String icon, int ladderPosition) {
        if (rankID == null) {
            this.rankID = Robo.generateId()+"";
        }else {
            this.rankID = rankID;
        }
        this.guildID = guildID;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.ladderPosition = ladderPosition;

        DatabaseManager.cache.put(EntryType.DatabaseRank, this);
    }

    public String getRankID() {
        return this.rankID;
    }

    @Override
    public @NotNull String getGuildId() {
        return this.guildID;
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
