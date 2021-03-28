package com.devflask.roboflask.database

import com.devflask.roboflask.database.objects.CustomCommand
import com.devflask.roboflask.database.objects.Guild
import com.devflask.roboflask.database.objects.GuildSetting
import com.devflask.roboflask.database.objects.User

class DevflaskAPIDatabase : Datasource {
    override fun getGuild(id: Long): Guild? {
        TODO("Not yet implemented")
    }

    override fun insertGuild(guild: Guild) {
        TODO("Not yet implemented")
    }

    override fun removeGuild(id: Long) {
        TODO("Not yet implemented")
    }

    override fun removeGuild(guild: Guild) {
        TODO("Not yet implemented")
    }

    override fun getUser(userID: Long, guildID: Long): User? {
        TODO("Not yet implemented")
    }

    override fun getUsersFromID(userID: Long): Collection<User> {
        TODO("Not yet implemented")
    }

    override fun getUsersFromGuild(guildID: Long): Collection<User> {
        TODO("Not yet implemented")
    }

    override fun removeUser(userID: Long, guildID: Long) {
        TODO("Not yet implemented")
    }

    override fun getPrefix(guildID: Long): String? {
        TODO("Not yet implemented")
    }

    override fun setPrefix(guildID: Long, prefix: String) {
        TODO("Not yet implemented")
    }

    override fun setLogID(guildID: Long, channelID: Long) {
        TODO("Not yet implemented")
    }

    override fun getLogID(guildID: Long): Long? {
        TODO("Not yet implemented")
    }

    override fun getXP(user: User): Long? {
        TODO("Not yet implemented")
    }

    override fun getXP(userID: Long, guildID: Long): Long? {
        TODO("Not yet implemented")
    }

    override fun setXP(user: User, xp: Long) {
        TODO("Not yet implemented")
    }

    override fun setXP(userID: Long, guildID: Long, xp: Long) {
        TODO("Not yet implemented")
    }

    override fun getLevel(userID: Long, guildID: Long): Int? {
        TODO("Not yet implemented")
    }

    override fun getLevel(user: User): Int? {
        TODO("Not yet implemented")
    }

    override fun getCustomCommands(guildID: Long): Collection<CustomCommand> {
        TODO("Not yet implemented")
    }

    override fun insertCustomCommand(guildID: Long, command: CustomCommand) {
        TODO("Not yet implemented")
    }

    override fun removeCustomCommand(guildID: Long, commandID: Int) {
        TODO("Not yet implemented")
    }

    override fun getGuildSetting(id: Long): GuildSetting {
        TODO("Not yet implemented")
    }

}