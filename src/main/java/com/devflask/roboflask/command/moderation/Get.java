package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.configuration.Config;
import com.devflask.roboflask.util.EmbedColor;
import com.devflask.roboflask.util.Lists;
import com.devflask.roboflask.util.Maps;
import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class Get implements Command {

    @Override
    public @NotNull String getName() {
        return "Get";
    }

    @Override
    public Collection<Permission> getRequiredPermissions(){
        HashSet<Permission> set = new HashSet<>();
        set.add(Permission.ADMINISTRATOR);
        return set;
    }

    @Override
    public void execute(CommandInformation info) {
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }

    public void execute(PrivateMessageReceivedEvent event) {
        call(event.getMessage());
    }

    public void execute(GuildMessageReceivedEvent event) {
        call(event.getMessage());
    }

    private void call(Message message){
        final String[] args = message.getContentRaw().split(" ");
        String[] types = {"Map", "List"};

        if (args.length < 3){
            message.getChannel().sendMessage(
                    MessageUtil.getCommandError(
                            MessageUtil.Messages.COMMAND_ERROR_FEW_ARGUMENTS,
                            message.getAuthor().getName(), message.getAuthor().getAvatarUrl(),
                            "Usage: `"+Config.DEFAULT_PREFIX+"get <type> <attribute>`",
                            "\nTypes: Map, List"
                    ).build()
            ).queue();
            return;
        }

        if (Stream.of(types).anyMatch(type -> type.equalsIgnoreCase(args[1]))){
            EmbedBuilder builder = MessageUtil.getDefaultEmbed(
                    EmbedColor.PURPLE,
                    message.getAuthor().getName(),
                    message.getAuthor().getAvatarUrl()
            );

            if (
                    Stream.of(Maps.names).anyMatch(attr -> attr.equalsIgnoreCase(args[2]))
                            || Stream.of(Lists.names).anyMatch(attr -> attr.equalsIgnoreCase(args[2]))
            ){

                if (args[1].equalsIgnoreCase("map")){
                    Map map = Maps.getMapByString(args[2]);
                    message.getChannel().sendMessage(
                            MessageUtil.insertMapToEmbed(builder, map, map.getClass().getName(), true).build()
                    ).queue();

                }else if (args[1].equalsIgnoreCase("list")) {
                    List list = Lists.getListByString(args[2]);
                    message.getChannel().sendMessage(
                            MessageUtil.insertListToEmbed(builder, list, list.getClass().getName(), true).build()
                    ).queue();
                }

            } else {
                //error no attribute
                message.getChannel().sendMessage(
                        MessageUtil.getCommandError(
                                MessageUtil.Messages.EMPTY,
                                message.getAuthor().getName(), message.getAuthor().getAvatarUrl(),
                                "Attribute `"+args[2]+"` not found!"
                        ).build()
                ).queue();
            }
        } else {
            //error no type
            message.getChannel().sendMessage(
                    MessageUtil.getCommandError(
                            MessageUtil.Messages.EMPTY,
                            message.getAuthor().getName(), message.getAuthor().getAvatarUrl(),
                            "Type `"+args[1]+"` not valid!"
                    ).build()
            ).queue();
        }
    }
}
