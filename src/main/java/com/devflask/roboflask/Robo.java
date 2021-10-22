package com.devflask.roboflask;

import com.devflask.roboflask.database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.*;


public class Robo  {

    public static Map<Robo, Integer> roboMap = new HashMap<>();
    public int id;

    private static final Logger LOGGER = LogManager.getLogger(Robo.class);

    public Bot bot = null;
    public Database database = null;
    private  static String[] arguments;

    //Way into the program
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try {
            arguments = args;
            Robo robo = new Robo().create();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Robo(){
        this.id = generateId();
        roboMap.put(this, this.id);
        LOGGER.info("Created Robo with id: "+this.id);
    }

    //Setters
    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public static void setArguments(String[] arguments) {
        Robo.arguments = arguments;
    }

    //Getters
    public Bot getBot() {
        return bot;
    }

    public Database getDatabase() {
        return database;
    }

    public int getId() {
        return id;
    }

    public Robo create() throws LoginException, InterruptedException, SQLException, ClassNotFoundException {

        //Check bot
        if (bot == null){
            if (arguments.length < 1){
                LOGGER.error("Robo:"+this.id+" has no bot bound to it!");
                System.exit(1);
            }else {
                bot = new Bot(arguments[0]);
            }
        }

        //Check database
        if (database == null){
            if (arguments.length < 3){
                LOGGER.info("Database remains unset for Robo:"+this.id);
                return this;
            }else {
                String pass = "";
                if (arguments.length > 3){
                    pass = arguments[3];
                }
                database = new Database(arguments[1], arguments[2], pass).connect();
            }
        }

        return this;
    }

    private int generateId(){
        //generate
        int i = new Random().nextInt(10000000, 99999999);

        //check availability
        for (Robo robo : roboMap.keySet()){
            while (robo.id == i){
                i = new Random().nextInt(10000000, 99999999);
            }
        }

        return i;
    }

}


