package com.devflask.roboflask.database;

import com.devflask.roboflask.Bot;
import com.devflask.roboflask.Robo;
import com.google.gson.Gson;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public record DatabaseManager(Database database) {

    //region Profile

    public int addProfile(DatabaseProfile databaseProfile) {
        String sql =
                "INSERT INTO Profiles (userID, guildID, level, xp, xpToNextLevel, coins, messages, profileBanner, profileBorder, profileColor)" +
                        "VALUES ('" +
                        databaseProfile.getMember().getId() + "', '" +
                        databaseProfile.getMember().getGuild().getId() + "', " +
                        databaseProfile.getLevel() + ", " +
                        databaseProfile.getXp() + ", " +
                        databaseProfile.getXpToNextLevel() + ", " +
                        databaseProfile.getCoins() + ", " +
                        databaseProfile.getMessages() + ", '" +
                        databaseProfile.getProfileBanner() + "', '" +
                        databaseProfile.getProfileBorder() + "', '" +
                        databaseProfile.getProfileColor() +
                        "')";

        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int removeProfile(DatabaseProfile databaseProfile) {
        String sql = "DELETE FROM `profiles` WHERE `userID`='" + databaseProfile.getMember().getId() + "' AND `guildID`='" + databaseProfile.getMember().getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateProfile(DatabaseProfile databaseProfile) {
        String sql = "UPDATE `profiles` SET `level`='" + databaseProfile.getLevel() + "', `xp`='" + databaseProfile.getXp() + "', `xpToNextLevel`='" + databaseProfile.getXpToNextLevel() + "', `coins`='" + databaseProfile.getCoins() + "', `messages`='" + databaseProfile.getMessages() + "', `profileBanner`='" + databaseProfile.getProfileBanner() + "', `profileBorder`='" + databaseProfile.getProfileBorder() + "', `profileColor`='" + databaseProfile.getProfileColor() + "' WHERE `userID`='" + databaseProfile.getMember().getId() + "' AND `guildID`='" + databaseProfile.getMember().getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public DatabaseProfile fetchProfile(Member member) {
        String sql = "SELECT * FROM `profiles` WHERE `userID`='" + member.getId() + "' AND `guildID`='" + member.getGuild().getId() + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                DatabaseProfile dbp = new DatabaseProfile(
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

    /**
     * @deprecated This method is no longer recommended due to the high chance of returning null.
     */
    @Deprecated
    public DatabaseProfile fetchProfile(String userID, String guildID) {
        String sql = "SELECT * FROM `profiles` WHERE `userID`='" + userID + "' AND `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {

                Member member = null;

                for (Bot bot : Robo.bots) {
                    if (bot.getJDA().getGuildById(guildID) != null) {
                        member = Objects.requireNonNull(bot.getJDA().getGuildById(guildID)).getMemberById(userID);
                    }
                }

                if (member == null) return null;

                DatabaseProfile dbp = new DatabaseProfile(
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

    public void addGuild(DatabaseGuild databaseGuild) {
        String sql =
                "INSERT INTO `guilds` (guildID, prefix, customCommands, customRanks, totalCommands, totalMessages)" +
                        "VALUES ('" +
                        databaseGuild.getGuild().getId() + "', '" +
                        databaseGuild.getPrefix() + "', " +
                        convertToJson(databaseGuild.getCustomCommands()) + ", " +
                        convertToJson(databaseGuild.getCustomRanks()) + ", " +
                        databaseGuild.getTotalCommands() + ", " +
                        databaseGuild.getTotalMessages() + ", " +
                        "')";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int removeGuild(DatabaseGuild databaseGuild) {
        String sql = "DELETE FROM `guilds` WHERE `guildID`='" + databaseGuild.getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateGuild(DatabaseGuild databaseGuild) {
        String sql =
                "UPDATE `guilds` SET " +
                        "`prefix`='" + databaseGuild.getPrefix() + "', " +
                        "`customCommands`=" + convertToJson(databaseGuild.getCustomCommands()) + ", " +
                        "`customRanks`=" + convertToJson(databaseGuild.getCustomRanks()) + ", " +
                        "`totalCommands`=" + databaseGuild.getTotalCommands() + ", " +
                        "`totalMessages`=" + databaseGuild.getTotalMessages() + " " +
                        "WHERE `guildID`='" + databaseGuild.getGuild().getId() + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public DatabaseGuild fetchGuild(Guild guild) {
        String sql = "SELECT * FROM `guilds` WHERE `guildID`='" + guild.getId() + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                DatabaseGuild dbg = new DatabaseGuild(
                        guild,
                        rs.getString("prefix"),
                        (DatabaseCommand[]) convertFromJson(rs.getString("customCommands"), DatabaseCommand[].class),
                        (DatabaseRank[]) convertFromJson(rs.getString("customRanks"), DatabaseRank[].class),
                        rs.getInt("totalCommands"),
                        rs.getInt("totalMessages")
                );
                rs.close();
                return dbg;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @deprecated This method is no longer recommended due to the high chance of returning null.
     */
    @Deprecated
    public DatabaseGuild fetchGuild(String guildID) {
        String sql = "SELECT * FROM `guilds` WHERE `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {

                Guild guild = null;

                for (Bot bot : Robo.bots) {
                    if (bot.getJDA().getGuildById(guildID) != null) {
                        guild = bot.getJDA().getGuildById(guildID);
                    }
                }

                if (guild == null) return null;

                DatabaseGuild dbg = new DatabaseGuild(
                        guild,
                        rs.getString("prefix"),
                        (DatabaseCommand[]) convertFromJson(rs.getString("customCommands"), DatabaseCommand[].class),
                        (DatabaseRank[]) convertFromJson(rs.getString("customRanks"), DatabaseRank[].class),
                        rs.getInt("totalCommands"),
                        rs.getInt("totalMessages")
                );
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

    //endregion Guild
    //region COMMAND

    public void addCommand(DatabaseCommand databaseCommand) {
        String sql = "INSERT INTO `commands` (`guildID`, `data`) " +
                "VALUES ('" +
                databaseCommand.guildId + "', '" +
                databaseCommand.asJson() + "', '" +
                ");";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCommand(DatabaseCommand databaseCommand) {
        String sql = "DELETE FROM `commands` WHERE `commandID`='" + databaseCommand.getId() + "' AND `guildID`='" + databaseCommand.guildId + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCommand(DatabaseCommand databaseCommand) {
        String sql = "UPDATE `commands` SET `data`='" + databaseCommand.asJson() + "' WHERE `commandID`='" + databaseCommand.getId() + "' AND `guildID`='" + databaseCommand.guildId + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseCommand fetchCommand(String commandID, String guildID) {
        String sql = "SELECT * FROM `commands` WHERE `commandID`='" + commandID + "' AND `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                DatabaseCommand dbg = new DatabaseCommand(
                        rs.getInt("commandID"),
                        rs.getString("guildID"),
                        rs.getString("data")
                );
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

    public void addRank(DatabaseRank databaseRank) {
        String sql = "INSERT INTO `ranks` (`guildID`, `data`) " +
                "VALUES ('" +
                databaseRank.guildId + "', '" +
                databaseRank.asJson() + "', '" +
                ");";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRank(DatabaseRank databaseRank) {
        String sql = "DELETE FROM `ranks` WHERE `rankID`='" + databaseRank.getId() + "' AND `guildID`='" + databaseRank.guildId + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRank(DatabaseRank databaseRank) {
        String sql = "UPDATE `ranks` SET `data`='" + databaseRank.asJson() + "' WHERE `rankID`='" + databaseRank.getId() + "' AND `guildID`='" + databaseRank.guildId + "';";
        try {
            database.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseRank fetchRank(String rankID, String guildID) {
        String sql = "SELECT * FROM `ranks` WHERE `rankID`='" + rankID + "' AND `guildID`='" + guildID + "';";
        try {
            ResultSet rs = database.executeQuery(sql);
            if (rs.next()) {
                DatabaseRank dbg = new DatabaseRank(
                        rs.getInt("rankID"),
                        rs.getString("guildID"),
                        rs.getString("data")
                );
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
