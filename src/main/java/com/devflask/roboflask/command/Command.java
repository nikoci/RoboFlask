package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

public interface Command {
    @NotNull
    String getName();
    @NotNull
    default Collection<String> getAlias() {
        return new HashSet<>();
    }
    @NotNull
    default String getHelp() {
        return "No help found for this command.";
    }

    void execute(CommandInformation info);

    @Nonnull
    default Collection<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}