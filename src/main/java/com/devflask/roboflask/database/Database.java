package com.devflask.roboflask.database;

import com.devflask.roboflask.Robo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public int id;

    private final String host;
    private final String username;
    private final String password;

    public Connection connection;

    public Database(String host, String username, String password) {
        this.id = Robo.generateId();
        Robo.addDatabase(this);

        this.host = host;
        this.username = username;
        this.password = password;
    }

    public Database connect() {
        try {
            connection = DriverManager.getConnection(
                    host,
                    username,
                    password
            );

            this.executeUpdate("CREATE TABLE IF NOT EXISTS `profiles` (" +
                    " userID VARCHAR(255) not NULL, " +
                    " guildID VARCHAR(255) not NULL, " +
                    " level INTEGER, " +
                    " xp INTEGER, " +
                    " xpToNextLevel INTEGER, " +
                    " coins INTEGER, " +
                    " messages INTEGER, " +
                    " profileBanner TEXT, " +
                    " profileBorder TEXT, " +
                    " profileColor INTEGER, " +
                    " PRIMARY KEY (userID, guildID)" +
                    ");");

            this.executeUpdate("CREATE TABLE IF NOT EXISTS `guilds` (\n" +
                    "  `guildID` VARCHAR(255) NOT NULL,\n" +
                    "  `prefix` VARCHAR(255),\n" +
                    "  `customCommands` JSON,\n" +
                    "  `customRanks` JSON,\n" +
                    "  `totalCommands` INTEGER,\n" +
                    "  `totalMessages` INTEGER,\n" +
                    "  PRIMARY KEY (`guildID`)\n" +
                    ");");

            this.executeUpdate("CREATE TABLE IF NOT EXISTS `commands` (\n" +
                    "  `commandID` INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "  `guildID` VARCHAR(255) NOT NULL,\n" +
                    "  `data` JSON,\n" +
                    "  PRIMARY KEY (`commandID`, `guildID`)\n" +
                    ");");

            this.executeUpdate("CREATE TABLE IF NOT EXISTS `ranks` (\n" +
                    "  `rankID` INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "  `guildID` VARCHAR(255) NOT NULL,\n" +
                    "  `data` JSON,\n" +
                    "  PRIMARY KEY (`rankID`, `guildID`)\n" +
                    ");");


            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Database disconnect() throws SQLException {
        connection.close();
        return this;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        if (connection.isClosed()) return null;
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public void executeUpdate(String query) throws SQLException {
        if (connection.isClosed()) return;
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    //Static Methods

    public static Database getDatabase(int id) {
        for (Database database : Robo.databases) {
            if (database.id == id) {
                return database;
            }
        }
        return null;
    }

    public static Database[] getDatabases(String host, String username, String password) {
        List<Database> databaseList = new ArrayList<>();
        for (Database database : Robo.databases) {
            if (database.host.equalsIgnoreCase(host)
                    && database.username.equalsIgnoreCase(username)
                    && database.password.equalsIgnoreCase(password)) {
                databaseList.add(database);
            }
        }
        return databaseList.size() == 0 ? null : databaseList.toArray(new Database[0]);
    }

    public int getId() {
        return id;
    }
}
