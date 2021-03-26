package com.devflask.roboflask.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Commands : IntIdTable() {

    val guildID = reference("guild_id", Guilds)
    val command = varchar("command", 32)
    val description = varchar("description", 255)
    val response = varchar("response", 255)
}

class Command(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Command>(Commands)

    var guildID by Guild referencedOn GuildSettings.guildID
    var command by Commands.command
    var description by Commands.description
    var response by Commands.response
}
