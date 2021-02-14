package com.devflask.roboflask;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    private final JDA bot;

    public Bot() throws LoginException {
        this.bot = init();
    }

    //TODO: implement a login system and env token.
    private JDA init() throws LoginException {

        return JDABuilder.createDefault("").build();
    }
}
