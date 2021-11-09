package com.devflask.roboflask.database;

import com.devflask.roboflask.Bot;
import com.devflask.roboflask.Robo;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public record DatabaseManager(Database database) {

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


}
