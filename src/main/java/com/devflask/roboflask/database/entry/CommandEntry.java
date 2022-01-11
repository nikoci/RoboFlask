package com.devflask.roboflask.database.entry;

import com.devflask.roboflask.Robo;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.database.DatabaseManager;
import com.devflask.roboflask.interfaces.Command;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandEntry implements Command, Entry {

    public String commandID;
    public String guildID;
    public String name;
    public String description;
    public String[] arguments;
    public String[] instructions;
    public String[] roles;
    public String[] channels;
    public String[] alias;

    public CommandEntry(String commandID, String guildID, String name, String description, String[] arguments, String[] instructions, String[] roles, String[] channels, String[] alias) {
        this.commandID = Objects.requireNonNullElseGet(commandID, () -> Robo.generateId() + "");
        this.guildID = guildID;
        this.name = name;
        this.description = description;
        this.arguments = arguments;
        this.instructions = instructions;
        this.roles = roles;
        this.channels = channels;
        this.alias = alias;

        DatabaseManager.cache.put(EntryType.DatabaseCommand, this);
    }

    //GETTERS

    public String getCommandID() {
        return commandID;
    }

    public String getGuildID() {
        return guildID;
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

    //SETTERS

    public CommandEntry setName(String name) {
        this.name = name;
        return this;
    }

    public CommandEntry setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandEntry setArguments(String[] arguments) {
        this.arguments = arguments;
        return this;
    }

    public CommandEntry setInstructions(String[] instructions) {
        this.instructions = instructions;
        return this;
    }

    public CommandEntry setRoles(String[] roles) {
        this.roles = roles;
        return this;
    }

    public CommandEntry setChannels(String[] channels) {
        this.channels = channels;
        return this;
    }

    public CommandEntry setAlias(String[] alias) {
        this.alias = alias;
        return this;
    }


    @Override
    public void execute(CommandInformation info) {
        //from arguments and instructions
    }

    public String asJson() {
        return new Gson().toJson(this);
    }
}
