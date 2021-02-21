package com.devflask.roboflask.command;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AdminOverride implements Command {

    private final CommandManager commandManager;

    public AdminOverride(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "override";
    }

    @Override
    public Set<String> getAlias() {
        return new HashSet<>();
    }

    @Override
    public String getHelp() {
        return "Admin override for admins only.";
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event) {
        //Retrieve ids of admins from config. Then add them to the set of active admins in CommandManager.
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        //Should only override when in private message
    }

    @Override
    public Collection<ChannelType> usableIn() {
        return null;
    }
}
