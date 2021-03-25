package com.devflask.roboflask.command;

import net.dv8tion.jda.api.Permission;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

public interface Command {
    @Nonnull
    String getName();
    @Nonnull
    default Collection<String> getAlias() {
        return new HashSet<>();
    }
    @Nonnull
    default String getHelp() {
        return "No help found for this command.";
    }

    void execute(CommandInformation info);

    @Nonnull
    default Collection<Permission> getRequiredPermissions() {
        return new HashSet<>();
    }
}