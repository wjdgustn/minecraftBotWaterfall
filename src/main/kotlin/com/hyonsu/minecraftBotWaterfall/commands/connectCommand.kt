package com.hyonsu.minecraftBotWaterfall.commands

import com.hyonsu.minecraftBotWaterfall.getServerAddress
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command
import java.net.InetSocketAddress

class connectCommand: Command("connect") {
    override fun execute(sender: CommandSender, args: Array<String>) {
        if(sender is ProxiedPlayer && !sender.hasPermission("mainserverplugin.admin.connect"))
            return sender.sendMessage("§c권한이 없습니다.")

        if(args.isEmpty()) return sender.sendMessage("§cUsage: /connect <domain> [player | \"all\"]")

        val host = args[0]
        if(sender !is ProxiedPlayer && args.size < 2) return sender.sendMessage("§cConsole need to send player name or \"all\"!")
        val player = if(args.size < 2 || args[1] == "all") sender as ProxiedPlayer else ProxyServer.getInstance().getPlayer(args[1])
        val targetServerInfo = getServerAddress(host).split(":")

        if(targetServerInfo[0] == "no") {
            sender.sendMessage("§c해당 서버를 찾을 수 없습니다!")
            return
        }

        val targetServerIP = targetServerInfo[0]
        val targetServerPort = targetServerInfo[1].toInt()

//        player.sendMessage(targetServerInfo.joinToString(":"))

        val server = ProxyServer.getInstance().constructServerInfo(host, InetSocketAddress(targetServerIP, targetServerPort), "", false)

        if(args.size > 1 && args[1] == "all") for(p in ProxyServer.getInstance().players) p.connect(server)
        else player.connect(server)
    }
}