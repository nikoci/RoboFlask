package com.devflask.roboflask;

import com.devflask.roboflask.command.BotInfo;
import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandManager;
import com.devflask.roboflask.command.Ping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Collection;
import java.util.HashSet;

public class Bot {

    private JDA bot;
    private JDABuilder builder;
    private CommandManager commandManager = new CommandManager();
    private String token;

    public Bot(String token) throws LoginException, InterruptedException {
        //TODO: find a better solution than passing arg to bot constructor as token.
        this.token = token;

        initJDA();
        registerCommands(new Ping());
        registerCommands(new BotInfo());
    }

    //TODO: implement a login system and env token.
    private void initJDA() throws LoginException, InterruptedException {
        bot = setupJDA().build();
        bot.awaitReady();
    }

    private JDABuilder setupJDA(){
        builder = JDABuilder.createDefault(token);
        Collection<GatewayIntent> intentsDisallowed = new HashSet<>();
        //builder.setDisabledIntents(intentsDisallowed);
        builder.setActivity(Activity.watching("my creation"));
        builder.addEventListeners(commandManager);
        return builder;
    }

    private void setupDatabase(){

    }

    private void registerCommands(Command command){
        commandManager.addCommand(command);
    }


}