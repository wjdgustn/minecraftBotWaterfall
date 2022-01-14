package com.hyonsu.minecraftBotWaterfall

import java.net.URL
import java.net.URLEncoder

fun getServerAddress(domain: String): String {
    return URL("$apiServer/api/getserveraddress?domain=${URLEncoder.encode(domain, "utf-8")}").readText()
}