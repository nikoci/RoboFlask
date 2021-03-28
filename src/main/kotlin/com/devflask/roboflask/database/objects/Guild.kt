package com.devflask.roboflask.database.objects

import java.util.*

data class Guild(
    val id: Long, val ownerID: Long, val name: String, val joinedAt: Date, val setting: GuildSetting, val users: Collection<User>, val commands: Collection<CustomCommand>){
}
