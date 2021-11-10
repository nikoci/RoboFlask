package com.devflask.roboflask.database;

import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.interfaces.Command;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class DatabaseCommand implements Command {

    public int id;
    public String guildId;
    public String name;
    public String description;
    public String[] arguments;
    public String[] instructions;
    public String[] roles;
    public String[] channels;
    public String[] alias;


    public DatabaseCommand(int id, String guildId, String data) {
        this.id = id;
        this.guildId = guildId;

        //TODO: implement to parse data correctly
        //new Gson().fromJson(data, this.getClass());
        /*
        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.instructions = instructions;
        this.roles = roles;
        this.channels = channels;
        this.alias = alias;
        */
    }

    public int getId() {
        return id;
    }

    public String getGuildId() {
        return guildId;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull String getDescription() {
        return this.description;
    }

    public @NotNull String[] getArguments() {
        return this.arguments;
    }

    public @NotNull String[] getInstructions() {
        return this.instructions;
    }

    public @NotNull String[] getRoles() {
        return this.roles;
    }

    public @NotNull String[] getChannels() {
        return this.channels;
    }

    @Override
    public @NotNull Collection<String> getAlias() {
        return Arrays.asList(alias);
    }

    @Override
    public void execute(CommandInformation info) {
        //from arguments and instructions
    }

    public String asJson() {
        return new Gson().toJson(this);
    }
}
