package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Unban implements Command {
    @NotNull
    @Override
    public String getName() {
        return "unban";
    }

    @NotNull
    @Override
    public Collection<Permission> getRequiredPermissions(){
        HashSet<Permission> set = new HashSet<>();
        set.add(Permission.BAN_MEMBERS);
        return set;
    }

    @Override
    public void execute(CommandInformation info){
        if (info.isGuild()) execute(info.getGuildEvent());
    }

    private void execute(GuildMessageReceivedEvent event){
        final TextChannel channel = event.getChannel();
        final Member member = event.getMember();
        final Message message = event.getMessage();
        final List<String> args = null;
        final String strmember = String.join(" ", args);

        if (args.isEmpty()){
            channel.sendMessage(MessageUtil.getCommandError(
                    MessageUtil.Messages.COMMAND_ERROR_USAGE,
                    member.getEffectiveName(),
                    member.getUser().getAvatarUrl(),
                    "!unban <user>"
                    ).build()
            ).queue();
            return;
        }

        if (!event.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            channel.sendMessage(MessageUtil.getPermissionError(
                    MessageUtil.Messages.PERMISSION_ERROR_BOT,
                    this.getRequiredPermissions(),
                    member.getEffectiveName(),
                    member.getUser().getAvatarUrl()
                    ).clearFields()
                            .build()
            ).queue();
            return;
        }

        event.getGuild().retrieveBanList().queue((bans) ->{
            List<User> possibleTargets = bans.stream().filter((ban) -> isRightUser(ban, strmember)).map(Guild.Ban::getUser).collect(Collectors.toList());
            if (possibleTargets.isEmpty()){
                channel.sendMessage(MessageUtil.getCommandError(
                        MessageUtil.Messages.COMMAND_ERROR_UNBAN,
                        member.getEffectiveName(),
                        member.getUser().getAvatarUrl()
                        ).clearFields()
                                .build()
                ).queue();
                return;
            }

            User target = possibleTargets.get(0);
            String targetName = String.format("%#s", target);
            event.getGuild().unban(target).queue();
            channel.sendMessage(MessageUtil.getCommandSuccess(
                    MessageUtil.Messages.COMMAND_SUCCESS_UNBAN,
                    member.getEffectiveName(),
                    member.getUser().getAvatarUrl()
                ).build()
            ).queue();
        });


    }

    private static boolean isRightUser(Guild.Ban ban, String args) {
        User bannedUser = ban.getUser();
        return bannedUser.getName().equalsIgnoreCase(args) || bannedUser.getId().equals(args)
                || bannedUser.getAsTag().equalsIgnoreCase(args);
    }
}
