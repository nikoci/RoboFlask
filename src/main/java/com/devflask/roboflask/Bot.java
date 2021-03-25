package com.devflask.roboflask;

import com.devflask.roboflask.command.moderation.Ban;
import com.devflask.roboflask.command.util.BotInfo;
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

    private JDA bot;
    private JDABuilder builder;
    private CommandManager commandManager = new CommandManager();;
    private ConfigManager configManager;
    private String token;

    public Bot() throws LoginException, InterruptedException {
        initJDA();
        initializeCommands();
    }

    public Bot(String token) throws LoginException, InterruptedException {
        this.token = token;
        initJDA();
        initializeCommands();
    }

    public void initializeCommands(){
        registerCommands(new Ping());
        registerCommands(new BotInfo());
        registerCommands(new Kick());
        registerCommands(new Ban());
    }
    
    private void initJDA() throws LoginException, InterruptedException {
        bot = setupJDA().build();
        bot.awaitReady();
    }

    private JDABuilder setupJDA(){
        System.out.println("TOKEN IS: "+System.getenv("RoboflaskToken"));
        builder = JDABuilder.create(this.token == null ? System.getenv("RoboflaskToken") : this.token, getIntents());
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

    private void registerCommands(Command command){
        commandManager.addCommand(command);
    }


}
