package com.devflask.roboflask.database;

import com.devflask.roboflask.command.Command;
import net.dv8tion.jda.api.entities.Guild;

public class DatabaseGuild {

    private Guild guild;
    private String prefix;
    private Command[] customCommands;

    //stats
    private int totalCommands;
    private int totalMessages;

    //statics
    private String[] rankIcons;
    private String[] rankNames;

    public DatabaseGuild(Guild guild, String prefix, Command[] customCommands, int totalCommands, int totalMessages, String[] rankIcons, String[] rankNames) {
        this.guild = guild;
        this.prefix = prefix;
        this.customCommands = customCommands;
        this.totalCommands = totalCommands;
        this.totalMessages = totalMessages;
        this.rankIcons = rankIcons;
        this.rankNames = rankNames;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Command[] getCustomCommands() {
        return customCommands;
    }

    public void setCustomCommands(Command[] customCommands) {
        this.customCommands = customCommands;
    }

    public int getTotalCommands() {
        return totalCommands;
    }

    public void setTotalCommands(int totalCommands) {
        this.totalCommands = totalCommands;
    }

    public int getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public String[] getRankIcons() {
        return rankIcons;
    }

    public void setRankIcons(String[] rankIcons) {
        this.rankIcons = rankIcons;
    }

    public String[] getRankNames() {
        return rankNames;
    }

    public void setRankNames(String[] rankNames) {
        this.rankNames = rankNames;
    }
}
