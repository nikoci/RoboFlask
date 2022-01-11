package com.devflask.roboflask.command.moderation;

import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.interfaces.Command;
import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

public class Get implements Command {

    @Override
    public @NotNull String getName() {
        return "Get";
    }

    @Override
    public Collection<Permission> getRequiredPermissions(){
        HashSet<Permission> set = new HashSet<>();
        set.add(Permission.ADMINISTRATOR);
        return set;
    }

    @Override
    public void execute(CommandInformation info) {
    }
}
