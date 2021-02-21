package com.devflask.roboflask.command;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public class CommandInformation {

    private GuildMessageReceivedEvent guildEvent;
    private PrivateMessageReceivedEvent privateEvent;
    private final boolean isGuild;
    private final String messageRaw;
    private List<String> args;

    public CommandInformation(GuildMessageReceivedEvent guildEvent, String messageRaw) {
        this.guildEvent = guildEvent;
        this.messageRaw = messageRaw;
        isGuild = true;
    }

    public CommandInformation( PrivateMessageReceivedEvent privateEvent, String messageRaw){
        this.privateEvent = privateEvent;
        this.messageRaw = messageRaw;
        isGuild = false;
    }

    public boolean isGuild() {
        return isGuild;
    }

    public GuildMessageReceivedEvent getGuildEvent() {
        return guildEvent;
    }

    public PrivateMessageReceivedEvent getPrivateEvent(){
        return privateEvent;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getMessageRaw() {
        return messageRaw;
    }
}
