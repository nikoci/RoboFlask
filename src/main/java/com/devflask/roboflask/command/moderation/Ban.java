package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Ban implements Command {


    @NotNull
    @Override
    public String getName() {
        return "ban";
    }

    private void execute(GuildMessageReceivedEvent event){
        // this is going to be so ugly, you ready
        final TextChannel channel = event.getChannel();
        final Message message = event.getMessage();
        final Member member = event.getMember();
        final List<String> args = new ArrayList<>();

        // for now I am going to have it so it has to be a mention, I'll change it later alrighty sec lemme try something

    }

    @Override
    public void execute(CommandInformation info){
        if (info.isGuild()) execute(info.getGuildEvent());
    }
}