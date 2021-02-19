package com.devflask.roboflask.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class MessageUtil {

    public static void getHelp(){

    }

    public static final String hello = "";

    public static EmbedBuilder getNoPermissionEmbed(Collection<Permission> permissionSet, String executor, String pfp){
        StringBuilder permissionString = new StringBuilder();
        for (Permission permission : permissionSet) {
            permissionString.append(permission.getName()).append(", ");
        }
        return getDefaultEmbed(ThemeColour.RED, executor, pfp)
                .addField("Not Allowed.", Messages.NO_PERMISSION.message +"You need `" + permissionString.toString() +  "` to perform this action", false);
    }

    public static EmbedBuilder getDefaultEmbed(ThemeColour colour, String executor, String pfp){
        return new EmbedBuilder().setColor(colour.colour).setFooter(executor, pfp).setTimestamp(new Date().toInstant());
    }

    enum Messages {

        NO_PERMISSION("Sorry but you do not have permission to do that. "),
        UNKNOWN_ERROR("An unknown error has occurred. Please report this to the developers https://github.com/devflask/roboflask")
        ;

        private final String message;

        Messages(String message) {
            this.message = message;
        }
    }
}
