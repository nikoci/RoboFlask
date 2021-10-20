package com.devflask.roboflask.util;

import com.devflask.roboflask.Bot;
import com.devflask.roboflask.Robo;
import com.devflask.roboflask.command.Command;
import com.devflask.roboflask.database.Database;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    public static final HashMap<String, Map> hooks = new HashMap<>();
    public static String[] names;

    //Attributes
    public static Map<Robo, Integer> roboMap = new HashMap<>();
    public static Map<Bot, Integer> botMap = new HashMap<>();
    public static Map<Database, Integer> databaseMap = new HashMap<>();

    public static void hook(){
        Maps.hooks.put("roboMap", Maps.roboMap);
        Maps.hooks.put("botMap", Maps.botMap);
        Maps.hooks.put("databaseMap", Maps.databaseMap);

        Maps.names = Maps.hooks.keySet().toArray(new String[0]);
    }

    public static Map getMapByString(String mapName){
        for (Map.Entry<String, Map> e : hooks.entrySet()){
            if (mapName.equalsIgnoreCase(e.getKey().toString())){
                return e.getValue();
            }
        }
        return null;
    }

}
