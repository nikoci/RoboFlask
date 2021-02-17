package com.devflask.roboflask;

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

    public Bot() throws LoginException, InterruptedException {
        initJDA();
        registerCommands(new Ping());
    }

    //TODO: implement a login system and env token.
    private void initJDA() throws LoginException, InterruptedException {
        bot = setupJDA().build();
        bot.awaitReady();
    }

    private JDABuilder setupJDA(){
        //builder = JDABuilder.createDefault(System.getenv("ROBO_TOKEN"));
        builder = JDABuilder.createDefault("");
        Collection<GatewayIntent> intentsDisallowed = new HashSet<GatewayIntent>();
        //builder.setDisabledIntents(intentsDisallowed);
        builder.setActivity(Activity.watching("my creation"));
        builder.addEventListeners(commandManager);
        return builder;
    }

    private void setupDatabase(){

    }

    private void registerCommands(Command command){
        commandManager.addCommand(new Ping());
    }


}
