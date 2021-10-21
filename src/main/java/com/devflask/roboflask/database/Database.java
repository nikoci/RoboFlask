package com.devflask.roboflask.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Database {

    public static Map<Database, Integer> databaseMap = new HashMap<>();
    public int id;

    boolean connected;

    String host;
    String username;
    private final String password;

    Connection connection;

    public Database(String host, String username, String password) {
        this.id = generateId();
        databaseMap.put(this, this.id);

        connected = false;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public Database connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                host,
                username,
                password
        );
        return this;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(query);
    }

    public Database disconnect() throws SQLException {
        connection.close();
        return this;
    }

    private int generateId(){
        //generate
        int i = new Random().nextInt(10000000, 99999999);

        //check availability
        for (Database database : databaseMap.keySet()){
            while (database.id == i){
                i = new Random().nextInt(10000000, 99999999);
            }
        }

        return i;
    }

}
