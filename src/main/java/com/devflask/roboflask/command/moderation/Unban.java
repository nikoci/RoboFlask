package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.Messages;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Unban implements Command {

    private static final Logger LOGGER = LogManager.getLogger(Unban.class);

    @NotNull
    public String getName() {
        return "unban";
    }

    @NotNull
    public Collection<Permission> getRequiredPermissions(){
        HashSet<Permission> set = new HashSet<>();
        set.add(Permission.BAN_MEMBERS);
        return set;
    }

    public @NotNull String getHelp() {
        return "Unbans a specified user.";
    }

    @Override
    public void execute(CommandInformation info){
        if (info.isGuild()) execute(info.getGuildEvent());
    }

    private void execute(GuildMessageReceivedEvent event){
        final TextChannel channel = event.getChannel();
        final Member member = event.getMember();
        final Message message = event.getMessage();
        final String[] args = message.getContentRaw().split(" ");

        if (args.length < 2){
            channel.sendMessage(MessageUtil.getCommandError(
                    Messages.COMMAND_ERROR_USAGE,
                    member.getEffectiveName(),
                    member.getUser().getAvatarUrl(),
                    "!unban <user id>"
                    ).build()
            ).queue();
            return;
        }
        final String strmember = args[1];


        if (!event.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            channel.sendMessage(MessageUtil.getPermissionError(
                    Messages.PERMISSION_ERROR_BOT,
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
                        Messages.COMMAND_ERROR_UNBAN,
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
                    Messages.COMMAND_SUCCESS_UNBAN,
                    targetName,
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
