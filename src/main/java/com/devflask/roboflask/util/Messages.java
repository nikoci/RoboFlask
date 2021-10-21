package com.devflask.roboflask.util;

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

    BLAME_DODO("Dodo is a fucking bitch"),
    BLAME_JOLLY("Jolly you didn't fucking tell us this was here and we wasted our time you cunt"),
    ;

    public final String message;

    Messages(String message) {
        this.message = message;
    }
}