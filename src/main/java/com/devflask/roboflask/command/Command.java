package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

public interface Command {
    @Nonnull
    String getName();
    @Nonnull
    default Collection<String> getAlias() {
        return new HashSet<>();
    }
    @Nonnull
    default String getHelp() {
        return "No help found for this command.";
    }

    void execute(PrivateMessageReceivedEvent event);
    void execute(GuildMessageReceivedEvent event);
    void execute(CommandInformation info);
    @Nonnull
    Collection<ChannelType> usableIn();
    @Nonnull
    default Collection<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}
