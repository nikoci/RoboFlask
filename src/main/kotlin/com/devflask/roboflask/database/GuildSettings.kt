package com.devflask.roboflask.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object GuildSettings : LongIdTable() {

    val guildID = reference("id", Guilds)
    val prefix = varchar("prefix", 32).default("!")
    val logEnabled = bool("log_enabled").default(false)
    val logID = varchar("log_id", 32)
}

class GuildSetting(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GuildSetting>(GuildSettings)

    var prefix by GuildSettings.prefix
    var guildID by Guild referencedOn GuildSettings.guildID
    var logEnabled by GuildSettings.logEnabled
    var logID by GuildSettings.logID
}