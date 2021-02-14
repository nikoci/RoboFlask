package com.devflask.roboflask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Robo  {

    private static final Logger LOGGER = LogManager.getLogger(Robo.class);

    //Way into the program
    public static void main(String[] args) {
        LOGGER.info("hello world");
        LOGGER.debug("hello world - the debug");
        LOGGER.warn("hello world- the warning?");
        LOGGER.error("hello w0rld - the err0r");
    }

}
