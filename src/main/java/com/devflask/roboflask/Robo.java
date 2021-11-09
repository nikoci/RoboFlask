package com.devflask.roboflask;

import com.devflask.roboflask.database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Robo {

    private static final Logger LOGGER = LogManager.getLogger(Robo.class);

    public static final List<Database> databases = new ArrayList<>();
    public static final List<Bot> bots = new ArrayList<>();
    public static List<Integer> ids = new ArrayList<>();
    public static Robo instance;

    //Way into the program
    public static void main(String[] args) {
        try {
            instance = new Robo().setup(args);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Robo setup(String[] arguments) throws LoginException, InterruptedException {
        //Check initial bot
        if (arguments.length < 1) {
            LOGGER.error("RoboFlask has no initial bot!");
            LOGGER.info("Exiting...");
            System.exit(1);
        } else if (arguments.length == 1) {
            addBot(new Bot(arguments[0]));
        } else if (arguments.length > 2) {
            if (arguments.length > 4) {
                String host, username, password;
                host = arguments[1];
                username = arguments[2];
                password = arguments[3];
                addBot(new Bot(arguments[0], new Database(host, username, password)));
            } else {
                LOGGER.warn("RoboFlask has no database! Check the credentials and try again");
                LOGGER.info("Defaulting to no database...");
                addBot(new Bot(arguments[0]));
            }
        }
        return this;
    }

    public static int generateId() {
        //generate
        int i = new Random().nextInt(Integer.MAX_VALUE);

        //check availability
        for (int id : ids) {
            while (id == i) {
                i = new Random().nextInt(Integer.MAX_VALUE);
            }
        }

        ids.add(i);
        return i;
    }

    public static void addBot(Bot bot) {
        bots.add(bot);
    }

    public static void removeBot(Bot bot) {
        bot.getJDA().shutdown();
        bots.remove(bot);
    }

    public static void removeBot(int id) {
        for (Bot bot : bots) {
            if (bot.getId() == id) {
                removeBot(bot);
                break;
            }
        }
    }

    public static Bot getBot(int id) {
        for (Bot bot : bots) {
            if (bot.getId() == id) {
                return bot;
            }
        }
        return null;
    }

    public static void addDatabase(Database database) {
        databases.add(database);
    }

    public static void removeDatabase(Database database) throws SQLException {
        database.disconnect();
        databases.remove(database);
    }

    public static void removeDatabase(int id) throws SQLException {
        for (Database database : databases) {
            if (database.getId() == id) {
                removeDatabase(database);
                break;
            }
        }
    }

    public static Database getDatabase(int id) {
        for (Database database : databases) {
            if (database.getId() == id) {
                return database;
            }
        }
        return null;
    }
}


