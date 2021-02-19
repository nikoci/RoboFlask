package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Set;

public interface Command {

    String getName();
    Set<String> getAlias();
    String getHelp();
    void execute(PrivateMessageReceivedEvent event);
    void execute(GuildMessageReceivedEvent event);

    default Set<Permission> getRequiredPermissions() {
        return null;
    }
}
