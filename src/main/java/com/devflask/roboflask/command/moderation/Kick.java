package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.interfaces.Command;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.Messages;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
    public @NotNull String getDescription() {
        return "Kicks the member specified - !kick <@Person> [reason]";
    }

    public void execute(GuildMessageReceivedEvent event) {
            final TextChannel channel = event.getChannel();
            final Message message = event.getMessage();
            final Member member = event.getMember();
            final Member target = message.getMentionedMembers().isEmpty() ? null : message.getMentionedMembers().get(0);
            final String[] args = message.getContentRaw().split(" ");


            if (target == null || message.getMentionedMembers().isEmpty()){
                channel.sendMessage(MessageUtil.getCommandError(
                        Messages.COMMAND_ERROR_USAGE,
                        member.getEffectiveName(),
                        member.getUser().getAvatarUrl(),
                        "!kick <user> [reason]"
                        ).build()
                ).queue();
                return;
            }

            if (!member.canInteract(target) || !event.getGuild().getSelfMember().canInteract(target)){
                channel.sendMessage(MessageUtil.getPermissionError(
                        Messages.PERMISSION_ERROR_HIERARCHY,
                        this.getRequiredPermissions(),
                        member.getEffectiveName(),
                        member.getUser().getAvatarUrl()
                        ).clearFields()
                                .build()
                ).queue();
                return;
            }

            String reason = String.join(" ", Arrays.asList(args).subList(2, args.length));

            if (!reason.isEmpty()) {
                reason = " for "+reason;
            }

            target.kick(reason).queue();
            channel.sendMessage(MessageUtil.getCommandSuccess(
                    Messages.COMMAND_SUCCESS_KICK,
                    member.getEffectiveName(),
                    member.getUser().getAvatarUrl(),
                    target.getEffectiveName(),
                    reason
                    ).build()
            ).queue();
        }


    @Override
    public void execute(CommandInformation ci){
        if (ci.isGuild()) execute(ci.getGuildEvent());
    }

    @Override
    public @NotNull Set<Permission> getRequiredPermissions() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.KICK_MEMBERS);
        return permissions;
    }
}
