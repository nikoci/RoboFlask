package com.devflask.roboflask.database;

import com.devflask.roboflask.Bot;
import com.devflask.roboflask.Robo;
import com.devflask.roboflask.database.entry.*;
import com.google.gson.Gson;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record DatabaseManager(Database database) {

    public static Map<EntryType, Object> cache = new HashMap<>();

    //region Profile

    public int addProfile(ProfileEntry profileEntry) {
        String sql =
                "INSERT INTO Profiles (userID, guildID, level, xp, xpToNextLevel, coins, messages, profileBanner, profileBorder, profileColor)" +
                        "VALUES ('" +
                        profileEntry.getMember().getId() + "', '" +
                        profileEntry.getMember().getGuild().getId() + "', " +
                        profileEntry.getLevel() + ", " +
                        profileEntry.getXp() + ", " +
                        profileEntry.getXpToNextLevel() + ", " +
                        profileEntry.getCoins() + ", " +
                        profileEntry.getMessages() + ", '" +
                        profileEntry.getProfileBanner() + "', '" +
                        profileEntry.getProfileBorder() + "', '" +
                        profileEntry.getProfileColor() +
                        "')";

        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int removeProfile(ProfileEntry profileEntry) {
        String sql = "DELETE FROM `profiles` WHERE `userID`='" + profileEntry.getMember().getId() + "' AND `guildID`='" + profileEntry.getMember().getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateProfile(ProfileEntry profileEntry) {
        String sql = "UPDATE `profiles` SET `level`='" + profileEntry.getLevel() + "', `xp`='" + profileEntry.getXp() + "', `xpToNextLevel`='" + profileEntry.getXpToNextLevel() + "', `coins`='" + profileEntry.getCoins() + "', `messages`='" + profileEntry.getMessages() + "', `profileBanner`='" + profileEntry.getProfileBanner() + "', `profileBorder`='" + profileEntry.getProfileBorder() + "', `profileColor`='" + profileEntry.getProfileColor() + "' WHERE `userID`='" + profileEntry.getMember().getId() + "' AND `guildID`='" + profileEntry.getMember().getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public ProfileEntry fetchProfile(Member member) {
        String sql = "SELECT * FROM `profiles` WHERE `userID`='" + member.getId() + "' AND `guildID`='" + member.getGuild().getId() + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                ProfileEntry dbp = new ProfileEntry(
                        member,
                        rs.getInt("level"),
                        rs.getInt("xp"),
                        rs.getInt("xpToNextLevel"),
                        rs.getInt("coins"),
                        rs.getInt("messages"),
                        rs.getString("profileBanner"),
                        rs.getString("profileBorder"),
                        rs.getInt("profileColor")
                );

                rs.close();
                return dbp;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //endregion Profile
    //region Guild

    public void addGuild(GuildEntry guildEntry) {
        String sql =
                "INSERT INTO `guilds` (`roboID`, `guildID`)" +
                        "VALUES ('" +
                            guildEntry.getGuildID() + "', '" +
                            guildEntry.asJson() + "'" +
                        ")";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int removeGuild(GuildEntry guildEntry) {
        String sql = "DELETE FROM `guilds` WHERE `guildID`='" + guildEntry.getGuildID() + "' AND `roboID`='" + guildEntry.getRoboID() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateGuild(GuildEntry guildEntry) {
        String sql =
                "UPDATE `guilds` SET " +
                        "`data`='" + guildEntry.asJson() + "'" +
                        "WHERE `guildID`='" + guildEntry.getGuildID() + "' AND `roboID`='" + guildEntry.getRoboID() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public GuildEntry fetchGuild(Guild guild) {
        String sql = "SELECT * FROM `guilds` WHERE `guildID`='" + guild.getId() + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                String data = rs.getString("data");
                GuildEntry guildEntry = new Gson().fromJson(data, GuildEntry.class);
                cache.put(EntryType.DatabaseGuild, guildEntry);
                rs.close();
                return guildEntry;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //endregion Guild
    //region COMMAND

    public void addCommand(CommandEntry commandEntry) {
        String sql = "INSERT INTO `commands` (`commandID`, `guildID`, `data`) " +
                "VALUES ('" +
                commandEntry.commandID + "', '" +
                commandEntry.guildID + "', '" +
                commandEntry.asJson() + "'" +
                ");";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCommand(CommandEntry commandEntry) {
        String sql = "DELETE FROM `commands` WHERE `commandID`='" + commandEntry.getCommandID() + "' AND `guildID`='" + commandEntry.guildID + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCommand(CommandEntry commandEntry) {
        String sql = "UPDATE `commands` SET `data`='" + commandEntry.asJson() + "' WHERE `commandID`='" + commandEntry.getCommandID() + "' AND `guildID`='" + commandEntry.guildID + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CommandEntry fetchCommand(String commandID, String guildID) {
        String sql = "SELECT * FROM `commands` WHERE `commandID`='" + commandID + "' AND `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                String data = rs.getString("data");
                CommandEntry dbg = new Gson().fromJson(data, CommandEntry.class);
                cache.put(EntryType.DatabaseCommand, dbg);
                rs.close();
                return dbg;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //endregion COMMAND
    //region RANK

    public void addRank(RankEntry rankEntry) {
        String sql = "INSERT INTO `ranks` (`rankID`, `guildID`, `data`) " +
                "VALUES ('" +
                rankEntry.rankID + "', '" +
                rankEntry.guildID + "', '" +
                rankEntry.asJson() + "'" +
                ");";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRank(RankEntry rankEntry) {
        String sql = "DELETE FROM `ranks` WHERE `rankID`='" + rankEntry.getRankID() + "' AND `guildID`='" + rankEntry.getGuildId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRank(RankEntry rankEntry) {
        String sql = "UPDATE `ranks` SET `data`='" + rankEntry.asJson() + "' WHERE `rankID`='" + rankEntry.getRankID() + "' AND `guildID`='" + rankEntry.getGuildId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RankEntry fetchRank(String rankID, String guildID) {
        String sql = "SELECT * FROM `ranks` WHERE `rankID`='" + rankID + "' AND `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                String data = rs.getString("data");
                RankEntry dbg = new Gson().fromJson(data, RankEntry.class);
                cache.put(EntryType.DatabaseRank, dbg);
                rs.close();
                return dbg;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //endregion RANK
    //region GSON json parsing

    public String convertToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public Object convertFromJson(String json, Class classType) {
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }

    //endregion GSON json parsing
}
