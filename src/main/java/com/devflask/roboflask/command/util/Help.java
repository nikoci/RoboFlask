package com.devflask.roboflask.command.util;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Help implements Command {

    private final Logger LOGGER = LogManager.getLogger(Help.class);


    @Override
    public @NotNull String getName() {
        return "help";
    }

    @Override
    public @NotNull Collection<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("?");
        return alias;
    }

    @Override
    public @NotNull String getHelp() {
        return "Retrieves help from the bot.";
    }

    public void execute(PrivateMessageReceivedEvent event) {

    }

    public void execute(GuildMessageReceivedEvent event) {
        StringBuilder commands = new StringBuilder();
        HashMap<String, String> commandsMap = new HashMap<>();
        for (Command c : getManager().getCommands()){
            System.out.println(c.getName()+" - "+c.getHelp());
            commands
                    .append(c.getName())
                    .append(" - ")
                    .append(c.getHelp())
                    .append("\n")
            ;
        }

        commandsMap.put("Commands", commands.toString());

        event.getChannel().sendMessage(MessageUtil.getInformative(
                Objects.requireNonNull(event.getMember()).getEffectiveName(),
                event.getAuthor().getAvatarUrl(),
                "",
                "",
                "Help",
                commandsMap,
                ""
        ).build()).queue();
    }

    @Override
    public void execute(CommandInformation info) {
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }
}
