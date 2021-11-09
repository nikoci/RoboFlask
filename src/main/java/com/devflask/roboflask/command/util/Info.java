package com.devflask.roboflask.command.util;

import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.command.CommandInformation;
import com.devflask.roboflask.util.MessageUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.*;

public class Info implements Command {

    private final Logger LOGGER = LogManager.getLogger(Info.class);

    @Override
    public @NotNull String getName() {
        return "info";
    }

    @Override
    public @NotNull Collection<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("botinfo");
        alias.add("i");
        return alias;
    }

    @Override
    public @NotNull String getHelp() {
        return "Retrieves information about the bot.";
    }

    public void execute(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage(messageEmbed(event.getJDA())).queue();
    }

    public void execute(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(messageEmbed(event.getJDA())).queue();
    }

    private MessageEmbed messageEmbed(JDA jda){
        String discordStatistics = new StringBuilder()
                .append("<:messages:895488887164633109>")
                .append(" Messages » `")
                .append("57K")
                .append("`\n")
                .append("<:users:895488895444205568>")
                .append(" Users » `")
                .append(jda.getUsers().size())
                .append("`\n")
                .append("<:ping:895488903673438228>")
                .append(" Ping » `")
                .append(jda.getRestPing().complete().toString())
                .append("`")
                .toString();

        String serverStatistics = new StringBuilder()
                .append("<:ram:895481501758672948>")
                .append(" Memory » `")
                .append(String.format("%.2f", getUsedMem()))
                .append("%`  \n")
                .append("<:cpu:895483886795120700>")
                .append(" CPU » `")
                .append(String.format("%.4f", getProcessCpuLoad()))
                .append("%`\n")
                .append("<:os:895483915815518241>")
                .append(" OS » `")
                .append(getOsName())
                .append("`")
                .toString();


        HashMap<String, String> fields = new HashMap<>();
        fields.put("Discord Statistics", discordStatistics);
        fields.put("Server Statistics", serverStatistics);

        return MessageUtil.getInformative(
                "Invite RoboFlask today at dehys.com/roboflask",
                jda.getSelfUser().getAvatarUrl(),
                "https://raw.githubusercontent.com/devflask/resources/main/roboflask/banner-1.png",
                "",
                "Information",
                fields,
                """

                        __RoboFlask__ bot software, a very powerful discord bot. Lots of features from fun games to useful utilities like __doc parsing__ and __code management__. RoboFlask is mainly focused towards the __programming community__ of discord which is why it provides a lot of __tools__ for code management
                         
                        **Getting Started**
                        Start by using the `help` command and see what the bot has to offer. This can vary from guild to guild as they can easily disable features. If you want to see all features the bot provides, check https://github.com/devflask/RoboFlask.
                        ** **"""
        ).build();
    }

    @Override
    public void execute(CommandInformation info) {
        if (info.isGuild()) execute(info.getGuildEvent());
        else execute(info.getPrivateEvent());
    }


    public static Double getProcessCpuLoad() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

            return Optional.ofNullable(list)
                    .map(l -> l.isEmpty() ? null : l)
                    .map(List::iterator)
                    .map(Iterator::next)
                    .map(Attribute.class::cast)
                    .map(Attribute::getValue)
                    .map(Double.class::cast)
                    .orElse(null);

        } catch (Exception ex) {
            return null;
        }
    }

    public String getOsName() {
        return System.getProperty("os.name");
    }

    public double getUsedMem() {
        double usedMem = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory();
        return (((Runtime.getRuntime().maxMemory() - usedMem)/(Runtime.getRuntime().maxMemory() + usedMem))*100);
    }
}
