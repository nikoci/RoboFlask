package com.devflask.roboflask.database

import com.devflask.roboflask.database.objects.CustomCommand
import com.devflask.roboflask.database.objects.Guild
import com.devflask.roboflask.database.objects.GuildSetting
import com.devflask.roboflask.database.objects.User

interface Datasource {

    fun getGuild(id: Long): Guild?
    fun insertGuild(guild: Guild)
    fun removeGuild(id: Long)
    fun removeGuild(guild: Guild)

    fun getUser(userID: Long, guildID: Long): User?
    fun getUsersFromID(userID: Long): Collection<User>
    fun getUsersFromGuild(guildID: Long): Collection<User>
    fun removeUser(userID: Long, guildID: Long)

    fun getPrefix(guildID: Long): String?
    fun setPrefix(guildID: Long, prefix: String)

    fun setLogID(guildID: Long, channelID: Long)
    fun getLogID(guildID: Long): Long?

    fun getXP(user: User): Long?
    fun setXP(user: User, xp: Long)
    fun getXP(userID: Long, guildID: Long): Long?
    fun setXP(userID: Long, guildID: Long, xp: Long)

    fun getLevel(userID: Long, guildID: Long): Int?
    fun getLevel(user: User): Int?

    fun getCustomCommands(guildID: Long): Collection<CustomCommand>
    fun insertCustomCommand(guildID: Long, command: CustomCommand)
    fun removeCustomCommand(guildID: Long, commandID: Int)

    fun getGuildSetting(id: Long): GuildSetting

}
