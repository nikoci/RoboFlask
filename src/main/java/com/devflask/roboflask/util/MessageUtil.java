package com.devflask.roboflask.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.util.*;

public class MessageUtil {

    public enum Messages {
        EMPTY(""),

        COMMAND_ERROR_FEW_ARGUMENTS("Too few arguments! "),
        COMMAND_ERROR_MANY_ARGUMENTS("Too many arguments! "),

        PERMISSION_ERROR_BOT("I do not seem to have permission "),
        PERMISSION_ERROR_HIERARCHY("Can't modify a member with a higher or equal highest role "),
        PERMISSION_ERROR_USER("Sorry but you do not have permission to do that. "),
        UNKNOWN_ERROR("An unknown error has occurred. Please report this to the developers https://github.com/devflask/roboflask"),

        COMMAND_ERROR_USAGE("Wrong usage. Use: "),
        COMMAND_ERROR_UNBAN("This user is not banned! "),

        COMMAND_SUCCESS_BAN(" banned "),
        COMMAND_SUCCESS_UNBAN(" is unbanned. "),

        COMMAND_SUCCESS_KICK(" kicked "),

        COMMAND_SUCCESS_GET(" successfully fetched attribute: "),
        COMMAND_ERROR_GET(" could not get attribute: "),

        BLAME_DODO("Dodo is a fucking bitch"),
        BLAME_JOLLY("Jolly you didn't fucking tell us this was here and we wasted our time you cunt"),
        ;

        public final String message;

        Messages(String message) {
            this.message = message;
        }
    }

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

    public static EmbedBuilder insertListToEmbed(EmbedBuilder embedBuilder, List list, String listName, boolean inline){
        StringBuilder strb = new StringBuilder();

        int count = 0;
        for (Object o : list){
            strb
                    .append("[")
                    .append(count)
                    .append("]  `")
                    .append(o.toString())
                    .append("`\n");
            count++;
        }

        embedBuilder.addField(listName, strb.toString(), inline);

        return embedBuilder;
    }

    public static EmbedBuilder insertMapToEmbed(EmbedBuilder embedBuilder, Map map, String mapName, boolean inline){
        StringBuilder strb = new StringBuilder();

        int count = 0;
        for (Map.Entry<Object, Object> entry : ((Map<Object, Object>)map).entrySet()){
            strb
                    .append("[")
                    .append(count)
                    .append("]  `")
                    .append(entry.getKey().toString())
                    .append("` -> `")
                    .append(entry.getValue().toString())
                    .append("`\n");
            count++;
        }

        embedBuilder.addField(mapName+" [pos] <K, V>", strb.toString(), inline);

        return embedBuilder;
    }
}
