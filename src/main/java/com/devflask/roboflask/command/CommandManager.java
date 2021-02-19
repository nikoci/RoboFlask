package com.devflask.roboflask.command;

import com.devflask.roboflask.util.MessageUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager extends ListenerAdapter {

    private final Set<Command> commands = new HashSet<>();
    //implement this properly somehow. private String prefix = new ConfigManager().getPrefix();
    private final String prefix = "!";
    //private final String commandRegex = "^" + prefix + "(\\w+)\\s(.*)";
    private static final Logger LOGGER = LogManager.getLogger(CommandManager.class);

    private static void accept(Command command) {

    }

    public void addCommand(Command command){
        commands.add(command);
        LOGGER.info("yes");
    }

    public void removeCommand(String commandName){
        commands.forEach((command) -> {
            if(command.getName().equalsIgnoreCase(commandName)){
                commands.remove(command);
                return;
            }
        });
    }

    public HashSet<Command> getCommands(){
        return new HashSet<>(commands);
    }

    private Command getCommand(String name){
        name = name.toLowerCase();
        for (Command commandX : commands) {
            if(commandX.getName().equalsIgnoreCase(name)) return commandX;
            if(commandX.getAlias().contains(name)) return commandX;
        }
        return null;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        Command command = getValidCommand(event.getAuthor(), event.getMessage());
        if(command == null) return;
        if(!(event.getMember().hasPermission(command.getRequiredPermissions()))){
            event.getChannel().sendMessage(
                    MessageUtil.getNoPermissionEmbed(command.getRequiredPermissions(),
                            event.getAuthor().getName(),
                            event.getAuthor().getAvatarUrl()).build()
            ).queue();
            return;
        }
        command.execute(event);
        LOGGER.debug(command);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        Command command = getValidCommand(event.getAuthor(), event.getMessage());
        if (command != null) {
            command.execute(event);
            LOGGER.debug(command);
        }
    }

    public Command getValidCommand(User user, Message message) {
        if(user.isBot()) return null;
        String[] split = message.getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");
        return getCommand(split[0]);
    }
}
