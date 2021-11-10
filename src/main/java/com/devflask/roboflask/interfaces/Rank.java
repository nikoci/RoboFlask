package com.devflask.roboflask.interfaces;

import org.jetbrains.annotations.NotNull;

public interface Rank {

    @NotNull String getGuildId();

    @NotNull String getName();

    @NotNull String getDescription();

    @NotNull String getIcon();

    @NotNull int getLadderPosition();

}
