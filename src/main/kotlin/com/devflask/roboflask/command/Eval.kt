package com.devflask.roboflask.command

import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent

class Eval:Command {
    override fun getName(): String {
        return "evaljs"
    }

    fun execute(event: PrivateMessageReceivedEvent?) {
        TODO("Eval JS in a private message.")
    }

    fun execute(event: GuildMessageReceivedEvent?) {
        TODO("Eval JS in a normal guild message")
    }

    override fun execute(info: CommandInformation?) {
        if(info!!.isGuild){
            execute(info.guildEvent)
        }else execute(info.privateEvent)
    }


}