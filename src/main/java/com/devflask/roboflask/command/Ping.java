package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Ping implements Command{

    private static final Logger LOGGER = LogManager.getLogger(Ping.class);

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public Set<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("pong");
        return alias;
    }

    @Override
    public String getHelp() {
        return "Pings the server.";
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("pong!").queue();
        LOGGER.debug("PONG in guild channel with id: "+event.getChannel().getId());
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("pong!").queue();
        LOGGER.debug("PONG in private channel with id: "+event.getChannel().getId());
    }

    @Override
    public Set<Permission> getRequiredPermissions() {
        return null;
    }
}
