package com.devflask.roboflask.interfaces;

import com.devflask.roboflask.Bot;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.command.CommandManager;
import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

public interface Command {
    @NotNull
    String getName();

    @NotNull
    default CommandManager getManager() {
        return Bot.getCommandManager();
    }

    @NotNull
    default Collection<String> getAlias() {
        return new HashSet<>();
    }

    @NotNull
    default String getDescription() {
        return "No help found for this command.";
    }

    void execute(CommandInformation info);

    @Nonnull
    default Collection<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}