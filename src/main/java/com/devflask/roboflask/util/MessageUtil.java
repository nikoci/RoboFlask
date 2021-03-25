package com.devflask.roboflask.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.util.Collection;
import java.util.Date;

public class MessageUtil {

    public enum Messages {
        PERMISSION_ERROR_BOT("I do not seem to have permission "),
        PERMISSION_ERROR_HIERARCHY("Can't modify a member with a higher or equal highest role "),
        PERMISSION_ERROR_USER("Sorry but you do not have permission to do that. "),
        UNKNOWN_ERROR("An unknown error has occurred. Please report this to the developers https://github.com/devflask/roboflask"),

        COMMAND_ERROR_USAGE("Wrong usage. Use: "),

        COMMAND_SUCCESS_BAN(" banned "),

        BLAME_DODO("Dodo is a fucking bitch"),
        BLAME_JOLLY("Jolly you didn't fucking tell us this was here and we wasted our time you cunt"),
        ;

        public final String message;

        Messages(String message) {
            this.message = message;
        }
    }

    public static EmbedBuilder getPermissionError(Messages messagesEnum, Collection<Permission> permissionSet,  String executor, String pfp, String ... s){
        StringBuilder permissionString = new StringBuilder();
        for (Permission permission : permissionSet) {
            permissionString
                    .append("`")
                    .append(permission.getName())
                    .append("`")
                    .append("\n");
        }

        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }

        return getDefaultEmbed(EmbedColor.RED, executor, pfp)
                .setDescription(messagesEnum.message+appendString)
                .addField("Required Permissions:", permissionString.toString(), false);
    }

    public static EmbedBuilder getCommandError(Messages messagesEnum, String executor, String pfp, String ... s){
        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }
        return getDefaultEmbed(EmbedColor.RED, executor, pfp)
                .setDescription(messagesEnum.message+appendString);
    }

    public static EmbedBuilder getCommandSuccess(Messages messagesEnum, String executor, String pfp, String ... s){
        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }

        return getDefaultEmbed(EmbedColor.GREEN, executor, pfp)
                .setDescription(executor+messagesEnum.message+appendString.toString());
    }

    public static EmbedBuilder getDefaultEmbed(EmbedColor color, String executor, String pfp){
        return new EmbedBuilder().setColor(color.color).setFooter(executor, pfp).setTimestamp(new Date().toInstant());
    }
}
