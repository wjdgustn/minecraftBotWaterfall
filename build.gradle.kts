plugins {
    kotlin("jvm") version "1.4.32"
}

group = "com.hyonsu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.github.waterfallmc:waterfall-api:1.18-R0.1-SNAPSHOT")
}

tasks {
    jar {
        from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

        doLast {
            copy {
                from(File("build/libs/${rootProject.name}-${version}.jar"))
                val plugins = File("D:/Programming/discord/minecraft-bot/waterfall/plugins")
                into(plugins)
            }
        }
    }
}