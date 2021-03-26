package com.devflask.roboflask.database

interface DatabaseManager {

    fun getPrefix(id: Long): String
    fun setPrefix(id: Long, prefix: String)

    fun getCommands(id: Long): Collection<CustomCommand>
    fun addCommand(id: Long, command: CustomCommand)

    fun isLogEnabled(id: Long): Boolean
    fun setLogEnabled(id: Long, value: Boolean)

    fun getLogChannelID(id: Long): Long
    fun setLogChannelID(id: Long, channelID: Long)

    //TODO: Finish interface
}
