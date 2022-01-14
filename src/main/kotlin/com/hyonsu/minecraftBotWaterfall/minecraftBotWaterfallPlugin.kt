package com.hyonsu.minecraftBotWaterfall

import com.google.common.reflect.ClassPath
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File

lateinit var plugin: minecraftBotWaterfallPlugin
lateinit var config: Configuration

lateinit var apiServer: String

class minecraftBotWaterfallPlugin : Plugin() {
    override fun onEnable() {
        plugin = this
        config = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(
            File(
                dataFolder, "config.yml"
            )
        )

        apiServer = config.getString("api-server")

        proxy.pluginManager.registerListener(this, minecraftBotWaterfallPluginListener())

        val cp: ClassPath = ClassPath.from(javaClass.classLoader)
        cp.getTopLevelClassesRecursive("com.hyonsu.minecraftBotWaterfall.commands").forEach { classInfo ->
            val c = Class.forName(classInfo.getName())
            val obj = c.newInstance()
            if (obj is Command) {
                val cmd: Command = obj
                proxy.pluginManager.registerCommand(this, cmd)
            }
        }

        println("plugin enabled")
    }

    override fun onDisable() {
        ConfigurationProvider.getProvider(YamlConfiguration::class.java)
            .save(config, File(dataFolder, "config.yml"))
    }
}