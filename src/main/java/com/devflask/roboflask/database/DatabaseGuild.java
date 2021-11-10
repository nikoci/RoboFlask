package com.devflask.roboflask.database;

import com.devflask.roboflask.interfaces.Command;
import com.devflask.roboflask.interfaces.Rank;
import net.dv8tion.jda.api.entities.Guild;

public class DatabaseGuild {

    private Guild guild;
    private String prefix;
    private DatabaseCommand[] customCommands;
    private DatabaseRank[] customRanks;

    //stats
    private int totalCommands;
    private int totalMessages;

    public DatabaseGuild(Guild guild, String prefix, DatabaseCommand[] customCommands, DatabaseRank[] customRanks, int totalCommands, int totalMessages) {
        this.guild = guild;
        this.prefix = prefix;
        this.customCommands = customCommands;
        this.totalCommands = totalCommands;
        this.totalMessages = totalMessages;
        this.customRanks = customRanks;
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

    public void setCustomCommands(DatabaseCommand[] customCommands) {
        this.customCommands = customCommands;
    }

    public Rank[] getCustomRanks() {
        return customRanks;
    }

    public void setCustomRanks(DatabaseRank[] customRanks) {
        this.customRanks = customRanks;
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
}
