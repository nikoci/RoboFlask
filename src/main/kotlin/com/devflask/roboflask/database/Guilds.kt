package com.devflask.roboflask.database

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime


object Guilds :  LongIdTable() {

    val ownerID: Column<String> = varchar("owner_id", 32)
    val name: Column<String> = varchar("name", 32)
    val joinedAt = datetime("joined_at")

}

class Guild(id: EntityID<Long>) : LongEntity(id){
    companion object : LongEntityClass<Guild>(Guilds)

    var ownerID by Guilds.ownerID
    var name by Guilds.name
    var joinedAt by Guilds.joinedAt
}
