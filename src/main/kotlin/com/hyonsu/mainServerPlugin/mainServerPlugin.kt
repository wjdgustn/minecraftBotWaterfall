package com.hyonsu.mainServerPlugin

import com.hyonsu.mainServerPlugin.commands.testCommand
import net.md_5.bungee.api.plugin.Plugin

class mainServerPlugin : Plugin() {
    override fun onEnable() {
        proxy.pluginManager.registerListener(this, mainServerPluginListener())

        proxy.pluginManager.registerCommand(this, testCommand())

        println("wa pypirus!!!!!!!")
    }
}