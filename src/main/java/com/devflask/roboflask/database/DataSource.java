package com.devflask.roboflask.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc_url");
        config.setUsername("database_username");
        config.setPassword("database_password");

    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
