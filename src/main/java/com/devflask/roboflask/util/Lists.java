package com.devflask.roboflask.util;

import com.devflask.roboflask.command.Command;

import java.util.*;

public class Lists {

    public static final HashMap<String, List> hooks = new HashMap<>();
    public static String[] names;

    public static List<Command> commands = new ArrayList<>();

    public static void hook(){
        Lists.hooks.put("commands", Lists.commands);

        Lists.names = Lists.hooks.keySet().toArray(new String[0]);
    }

    public static List getListByString(String listName){
        for (Map.Entry<String, List> e : hooks.entrySet()){
            if (listName.equalsIgnoreCase(e.getKey().toString())){
                return e.getValue();
            }
        }
        return null;
    }
}
