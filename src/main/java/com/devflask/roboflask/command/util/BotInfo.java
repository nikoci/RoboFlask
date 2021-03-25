package com.devflask.roboflask.command.util;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.ThemeColor;
import com.sun.management.OperatingSystemMXBean;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.lang.management.ManagementFactory;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BotInfo implements Command {

    private final Logger LOGGER = LogManager.getLogger(BotInfo.class);

    @Override
    public @NotNull String getName() {
        return "botinfo";
    }

    @Override
    public @NotNull Collection<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("binfo");
        alias.add("bot-info");
        alias.add("info-bot");
        alias.add("infob");
        return alias;
    }

    @Override
    public @NotNull String getHelp() {
        return "Retrieves information about the bot.";
    }

    public void execute(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage(getInfo(event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl(), event.getJDA().getSelfUser()).build()).queue();
    }

    public void execute(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(getInfo(event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl(), event.getJDA().getSelfUser()).build()).queue();
    }

    @Override
    public void execute(CommandInformation info) {
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }


    private EmbedBuilder getInfo(String executor, String pfp, SelfUser botUser){
        DecimalFormat df = new DecimalFormat("####.##");
        df.setRoundingMode(RoundingMode.DOWN);
        OperatingSystemMXBean mxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        EmbedBuilder embed = MessageUtil.getDefaultEmbed(ThemeColor.GREEN, executor,pfp);
        embed.setDescription("I am a bot.");
        embed.setThumbnail(botUser.getAvatarUrl());
        embed.addField("Discord Stats",
                "Shard: 1" +
                "\nGuilds: " + botUser.getJDA().getGuilds().size()
                        +" \nUsers: " + (botUser.getJDA().getUsers().size() + 1), true);
        embed.addField("Server Stats", "CPU: " + df.format(mxBean.getSystemCpuLoad() * 100)  + "%\nMemory used: " + df.format(mxBean.getFreePhysicalMemorySize() / 1e+6) + " MB", true).
                setAuthor("My Stats", "https://github.com/devflask/roboflask/");
        return embed;
    }
}
