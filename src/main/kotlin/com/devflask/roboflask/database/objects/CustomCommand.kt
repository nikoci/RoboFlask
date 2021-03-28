package com.devflask.roboflask.database.objects

import com.devflask.roboflask.command.Command

abstract class CustomCommand(): Command {
    abstract val commandID: Int
    abstract val guildID: Long
    abstract val handle: String
    abstract val description: String
    abstract val response: String

}
