package com.devflask.roboflask.command;

import com.devflask.roboflask.configuration.Config;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.Messages;
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

    public static List<Command> commands = new ArrayList<>();
    //implement this properly somehow. private String prefix = new ConfigManager().getPrefix();
    private final String prefix = Config.DEFAULT_PREFIX;
    //private final String commandRegex = "^" + prefix + "(\\w+)\\s(.*)";
    private static final Logger LOGGER = LogManager.getLogger(CommandManager.class);
    private final Set<Long> adminOverrides = new HashSet<>();

    private static void accept(Command command) {

    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void removeCommand(String commandName){
        commands.forEach((command) -> {
            if(command.getName().equalsIgnoreCase(commandName)){
                commands.remove(command);
            }
        });
    }

    public HashSet<Command> getCommands(){
        return new HashSet<>(commands);
    }

    private Command getCommand(String name){
        name = name.toLowerCase();
        for (Command commandX : commands) {
            LOGGER.debug(commandX.getName() + " " + name);
            if(commandX.getName().equalsIgnoreCase(name)) return commandX;
            if(commandX.getAlias().contains(name)) return commandX;
        }
        return null;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){

        Command command = getValidCommand(event.getAuthor(), event.getMessage());

        if(command == null) return;

        event.getMessage().delete().queue();

        if(adminOverrides.contains(event.getAuthor().getIdLong())){
            command.execute(new CommandInformation(event, event.getMessage().getContentRaw()));
            LOGGER.debug(event.getAuthor().getAsTag() + " executed admin command: " + command.getName());
            return;
        }

        if(!(Objects.requireNonNull(event.getMember()).hasPermission(command.getRequiredPermissions()))){
            event.getChannel().sendMessage(
                    MessageUtil.getPermissionError(Messages.PERMISSION_ERROR_USER, command.getRequiredPermissions(),
                            event.getAuthor().getName(),
                            event.getAuthor().getAvatarUrl()).build()
            ).queue();
            return;
        }

        if(!(Objects.requireNonNull(event.getGuild().getSelfMember()).hasPermission(command.getRequiredPermissions()))){
            event.getChannel().sendMessage(
                    MessageUtil.getPermissionError(Messages.PERMISSION_ERROR_BOT, command.getRequiredPermissions(),
                            event.getAuthor().getName(),
                            event.getAuthor().getAvatarUrl()).build()
            ).queue();
            return;
        }
        command.execute(new CommandInformation(event, event.getMessage().getContentRaw()));
        LOGGER.debug(command);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        Command command = getValidCommand(event.getAuthor(), event.getMessage());
        if(command == null) return;
        if(adminOverrides.contains(event.getAuthor().getIdLong())){
            command.execute(new CommandInformation(event, event.getMessage().getContentRaw()));
            LOGGER.debug(event.getAuthor().getAsTag() + " executed admin command: " + command.getName());
            return;
        }
        command.execute(new CommandInformation(event, event.getMessage().getContentRaw()));
    }

    public Command getValidCommand(User user, Message message) {
        if(user.isBot()) return null;
        if(!message.getContentRaw().startsWith(prefix)){
            return null;
        }
        String[] split = message.getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");
        return getCommand(split[0]);
    }

    public void addAdminOverride(long id){
        adminOverrides.add(id);
    }

    public void removeAdminOverride(long id){
        adminOverrides.remove(id);
    }
}
