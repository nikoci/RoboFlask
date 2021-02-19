package com.devflask.roboflask.command;

import com.devflask.roboflask.util.MessageUtil;
import com.devflask.roboflask.util.ThemeColour;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BotInfo implements Command {

    @Override
    public String getName() {
        return "botinfo";
    }

    @Override
    public Set<String> getAlias() {
        Set<String> alias = new HashSet<>();
        alias.add("binfo");
        alias.add("bot-info");
        alias.add("info-bot");
        alias.add("infob");
        return alias;
    }

    @Override
    public String getHelp() {
        return "Retrieves information about the bot.";
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = MessageUtil.getDefaultEmbed(ThemeColour.BLUE, event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = MessageUtil.getDefaultEmbed(ThemeColour.BLUE, event.getAuthor().getName(), event.getAuthor().getAvatarUrl());
    }

    @Override
    public Set<Permission> getRequiredPermissions() {
        return null;
    }

    private EmbedBuilder getInfo(String executor, String pfp, SelfUser botUser){
        EmbedBuilder embed = MessageUtil.getDefaultEmbed(ThemeColour.GREEN, executor,pfp);
        embed.setDescription("I am a bot.");
        embed.setThumbnail(botUser.getAvatarUrl());
        embed.addField("Discord Stats", " Shard: " + Objects.requireNonNull(botUser.getJDA().getShardManager()).getShardsTotal() + "\n Guilds: 1\n Users: 400000", true);
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setDescription("I am a bot")
                .setColor(new Color(1655238))
                .setTimestamp(OffsetDateTime.parse("2021-02-19T19:10:12.839Z"))
                .setFooter("$executor", "https://cdn.discordapp.com/embed/avatars/0.png")
                .setThumbnail("https://cdn.discordapp.com/embed/avatars/0.png")
                .setAuthor("My Stats", "https://discordapp.com", null)
                .addField("Discord Stats", " Shard: 0\n Guilds: 1\n Users: 400000", true)
                .addField("Server Specs", "CPU: i7-7700k (96%)\nMemory (Sys): 344/400\nMemory (JVM): 200/300", true);
        return
    }
}
