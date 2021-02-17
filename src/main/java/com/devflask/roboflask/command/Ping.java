package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashSet;
import java.util.Set;

public class Ping implements Command{

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
        event.getChannel().sendMessage("pong").queue();
    }

    @Override
    public Set<Permission> getRequiredPermissions() {
        return null;
    }
}
