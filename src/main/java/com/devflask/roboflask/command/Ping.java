package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Ping implements Command {

    private static final Logger LOGGER = LogManager.getLogger(Ping.class);

    @Override
    public @NotNull String getName() {
        return "ping";
    }

    @Override
    public @NotNull Set<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("pong");
        return alias;
    }

    @Override
    public @NotNull String getHelp() {
        return "Pings the server.";
    }

    public void execute(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("pong!").queue();
        LOGGER.debug("PONG in guild channel with id: " + event.getChannel().getId());
    }

    public void execute(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("pong!").queue();
        LOGGER.debug("PONG in private channel with id: " + event.getChannel().getId());
    }

    @Override
    public void execute(CommandInformation info) {
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }

}
