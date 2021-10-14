package com.devflask.roboflask;

import com.devflask.roboflask.command.moderation.Ban;
import com.devflask.roboflask.command.moderation.Unban;
import com.devflask.roboflask.command.util.Help;
import com.devflask.roboflask.command.util.Info;
import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandManager;
import com.devflask.roboflask.command.util.Ping;
import com.devflask.roboflask.command.moderation.Kick;
import com.devflask.roboflask.configuration.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    private static final CommandManager commandManager = new CommandManager();;
    private ConfigManager configManager;
    private final String token;

    public Bot(String token) throws LoginException, InterruptedException {
        this.token = token;
        initJDA();
        initializeCommands();
    }

    public void initializeCommands(){
        registerCommands(
                new Help(),
                new Ping(),
                new Info(),
                new Kick(),
                new Ban(),
                new Unban()
        );
    }
    
    private void initJDA() throws LoginException, InterruptedException {
        JDA bot = setupJDA().build();
        bot.awaitReady();

        bot.getGuildById("298480981441118208").getSelfMember().modifyNickname("test").queue();
    }

    private JDABuilder setupJDA(){
        JDABuilder builder = JDABuilder.create(this.token == null ? System.getenv("RoboflaskToken") : this.token, getIntents());
        builder.setActivity(Activity.watching("running on cd"));
        builder.addEventListeners(commandManager);
        return builder;
    }

    private void setupDatabase(){

    }

    private EnumSet<GatewayIntent> getIntents(){
        return EnumSet.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS
        );
    }

    private void registerCommands(Command ... commands){
        for (Command cmd : commands) commandManager.addCommand(cmd);
    }

    public static CommandManager getCommandManager(){
        return commandManager;
    }


}
