package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface Command {

    String getName();
    Collection<String> getAlias();
    String getHelp();
    void execute(PrivateMessageReceivedEvent event);
    void execute(GuildMessageReceivedEvent event);
    Collection<ChannelType> usableIn();

    default Collection<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}
