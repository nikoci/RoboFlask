package com.devflask.roboflask;

import com.devflask.roboflask.command.CommandManager;
import com.devflask.roboflask.command.moderation.Ban;
import com.devflask.roboflask.command.moderation.Get;
import com.devflask.roboflask.command.moderation.Kick;
import com.devflask.roboflask.command.moderation.Unban;
import com.devflask.roboflask.command.util.Help;
import com.devflask.roboflask.command.util.Info;
import com.devflask.roboflask.command.util.Ping;
import com.devflask.roboflask.database.Database;
import com.devflask.roboflask.interfaces.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Bot {

    public int id;

    private static final CommandManager commandManager = new CommandManager();

    private final String token;
    private Database database;
    private JDA jda;

    public Bot(String token) {
        this.token = token;
        setup();
    }

    public Bot(String token, Database database) {
        this.token = token;
        this.database = database.connect();
        setup();
    }

    private void setup() {
        this.id = Robo.generateId();
        Robo.bots.add(this);

        try {
            initJDA();
            initCommands();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initJDA() throws LoginException, InterruptedException {
        jda = setupJDA().build();
        jda.awaitReady();
    }

    public void initCommands() {
        registerCommands(
                new Help(),
                new Ping(),
                new Info(),
                new Kick(),
                new Ban(),
                new Unban(),
                new Get()
        );
    }

    private JDABuilder setupJDA() {
        JDABuilder builder = JDABuilder.create(this.token == null ? System.getenv("RoboFlaskToken") : this.token, getIntents());
        builder.setActivity(Activity.watching("running on cd"));
        builder.addEventListeners(commandManager);
        return builder;
    }

    private EnumSet<GatewayIntent> getIntents() {
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

    private void registerCommands(Command... commands) {
        for (Command cmd : commands) commandManager.addCommand(cmd);
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    //getters
    public Database getDatabase() {
        return this.database;
    }

    public JDA getJDA() {
        return this.jda;
    }

    public int getId() {
        return this.id;
    }

    //setters
    public void setDatabase(Database database) {
        this.database = database;
    }
}
