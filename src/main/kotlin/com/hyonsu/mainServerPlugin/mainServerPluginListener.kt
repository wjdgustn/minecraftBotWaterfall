package com.hyonsu.mainServerPlugin

import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.event.PostLoginEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler


class mainServerPluginListener: Listener {
    @EventHandler
    fun onPostLogin(e: PostLoginEvent) {
        for (player in ProxyServer.getInstance().players) {
            player.sendMessage(TextComponent(e.player.name.toString() + " has joined the network."))
        }
    }
}