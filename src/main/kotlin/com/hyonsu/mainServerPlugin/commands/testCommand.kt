package com.hyonsu.mainServerPlugin.commands

import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command
import java.net.InetSocketAddress

class testCommand: Command("test") {
    override fun execute(sender: CommandSender, args: Array<String>) {
        sender.sendMessage("hi")
    }
}
