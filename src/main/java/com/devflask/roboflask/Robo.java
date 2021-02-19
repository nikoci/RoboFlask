package com.devflask.roboflask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;


public class Robo  {

    private static final Logger LOGGER = LogManager.getLogger(Robo.class);
    private final Bot bot;

    //Way into the program
    public static void main(String[] args) {
        try {
            // TODO: BotInstance bot = new Robo();
            if(args.length == 0){
                Robo robo = new Robo(System.getenv("TOKEN"));
            }else {
                Robo robo = new Robo(args[0]); //Token is set by args for now
            }

        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Robo(String arg) throws LoginException, InterruptedException {
        bot = new Bot(arg);
    }

}
