package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.ThemeColor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Kick implements Command {
    @Override
    public @NotNull String getName() {
        return "kick";
    }

    @Override
    public @NotNull Set<String> getAlias() {
        return new HashSet<>();
    }

    @Override
    public @NotNull String getHelp() {
        return "Kicks the member speciefied - !kick <@Person> reason";
    }

    public void execute(PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = MessageUtil.getDefaultEmbed(ThemeColor.RED, event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        builder.setTitle("Silly goose, you can only kick people in a guild.");
        event.getChannel().sendMessage(new MessageBuilder(builder.build()).build()).queue();
    }

    public void execute(GuildMessageReceivedEvent event) {
        //
    }

    @Override
    public void execute(CommandInformation info){
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }

    @Override
    public @NotNull Set<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}
