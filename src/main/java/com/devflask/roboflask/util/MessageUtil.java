package com.devflask.roboflask.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.util.*;

public class MessageUtil {

    public static EmbedBuilder getInformative(String executor, String pfp, String image, String thumbnail, String title, HashMap<String, String> fields, String ... s){
        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }

        EmbedBuilder embedBuilder = getDefaultEmbed(EmbedColor.PURPLE, executor, pfp);

        if (!image.isEmpty()) embedBuilder.setImage(image);
        if (!thumbnail.isEmpty()) embedBuilder.setThumbnail(thumbnail);

        embedBuilder.setTitle(title);

        if (!fields.isEmpty()){
            for (Map.Entry entry : fields.entrySet()){
                embedBuilder.addField(entry.getKey().toString(), entry.getValue().toString(), true);
            }
        }

        embedBuilder.setDescription(appendString);

        return embedBuilder;
    }

    //Permission Error
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

    //Command Error
    public static EmbedBuilder getCommandError(Messages messagesEnum, String executor, String pfp, String ... s){
        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }
        return getDefaultEmbed(EmbedColor.RED, executor, pfp)
                .setDescription(messagesEnum.message+appendString);
    }

    //Command Success
    public static EmbedBuilder getCommandSuccess(Messages messagesEnum, String executor, String pfp, String ... s){
        StringBuilder appendString = new StringBuilder();
        for (String str : s){
            appendString.append(str);
        }

        return getDefaultEmbed(EmbedColor.GREEN, executor, pfp)
                .setDescription(executor+messagesEnum.message+appendString.toString());
    }

    //Default Embed
    public static EmbedBuilder getDefaultEmbed(EmbedColor color, String executor, String pfp){
        return new EmbedBuilder().setColor(color.color).setFooter(executor, pfp).setTimestamp(new Date().toInstant());
    }
}
