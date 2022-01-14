package com.hyonsu.minecraftBotWaterfall

import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.event.*
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler
import java.net.InetSocketAddress


class minecraftBotWaterfallPluginListener: Listener {
    @EventHandler
    fun onPostLogin(e: ServerConnectEvent) {
        if(e.reason != ServerConnectEvent.Reason.JOIN_PROXY) return

        val host = e.player.pendingConnection.virtualHost.hostString
        val targetServerInfo = getServerAddress(host).split(":")

        if(targetServerInfo[0] == "no") {
            e.player.disconnect("§c해당 서버를 찾을 수 없습니다!")
            return
        }

        val targetServerIP = targetServerInfo[0]
        val targetServerPort = targetServerInfo[1].toInt()

//        e.player.sendMessage(targetServerInfo.joinToString(":"))

        e.target = ProxyServer.getInstance().constructServerInfo(host, InetSocketAddress(targetServerIP, targetServerPort), "", false)
    }
}