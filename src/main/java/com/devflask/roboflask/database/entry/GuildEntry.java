package com.devflask.roboflask.database.entry;

import com.devflask.roboflask.database.DatabaseManager;

public class GuildEntry implements Entry{

    public String guildID;
    public String roboID;
    public String prefix;
    public CommandEntry[] commands;
    public RankEntry[] ranks;
    public int totalCommands;
    public int totalMessages;

    public GuildEntry(String guildID, String roboID, String prefix, CommandEntry[] commands, RankEntry[] ranks, int totalCommands, int totalMessages) {
        this.guildID = guildID;
        this.prefix = prefix;
        this.commands = commands;
        this.ranks = ranks;
        this.totalCommands = totalCommands;
        this.totalMessages = totalMessages;

        DatabaseManager.cache.put(EntryType.DatabaseGuild, this);
    }

    public String getGuildID() {
        return guildID;
    }

    public String getRoboID() {
        return roboID;
    }

    public String getPrefix() {
        return prefix;
    }

    public CommandEntry[] getCommands() {
        return commands;
    }

    public RankEntry[] getRanks() {
        return ranks;
    }

    public int getTotalCommands() {
        return totalCommands;
    }

    public int getTotalMessages() {
        return totalMessages;
    }


    //SETTERS
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setCommands(CommandEntry[] commands) {
        this.commands = commands;
    }

    public void setRanks(RankEntry[] ranks) {
        this.ranks = ranks;
    }

    public void setTotalCommands(int totalCommands) {
        this.totalCommands = totalCommands;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }


}
