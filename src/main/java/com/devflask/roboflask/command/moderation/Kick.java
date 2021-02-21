package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.ThemeColour;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Kick implements Command {
    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public Set<String> getAlias() {
        return new HashSet<>();
    }

    @Override
    public String getHelp() {
        return "Kicks the member speciefied - !kick <@Person> reason";
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = MessageUtil.getDefaultEmbed(ThemeColour.RED, event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        builder.setTitle("Silly goose, you can only kick people in a guild.");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {

    }

    @Override
    public Collection<ChannelType> usableIn() {
        Set<ChannelType> channels = new HashSet<>();
        channels.add(ChannelType.TEXT);
        return channels;
    }

    @Override
    public Set<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}
