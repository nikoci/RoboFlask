package com.devflask.roboflask.database

class MySQLDatabase : DatabaseManager {
    override fun getPrefix(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun setPrefix(id: Long, prefix: String) {
        TODO("Not yet implemented")
    }

    override fun getCommands(id: Long): Collection<CustomCommand> {
        TODO("Not yet implemented")
    }

    override fun addCommand(id: Long, command: CustomCommand) {
        TODO("Not yet implemented")
    }

    override fun isLogEnabled(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun setLogEnabled(id: Long, value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getLogChannelID(id: Long): Long {
        TODO("Not yet implemented")
    }

    override fun setLogChannelID(id: Long, channelID: Long) {
        TODO("Not yet implemented")
    }
}