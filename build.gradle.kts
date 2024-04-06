/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
    //`net.mamoe.mirai-console`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    api(libs.org.jetbrains.kotlin.kotlin.stdlib)
    api(libs.com.mysql.mysql.connector.j)
    api(libs.org.yaml.snakeyaml)
    api(libs.redis.clients.jedis)
    api(libs.org.xerial.sqlite.jdbc)
    api(libs.org.jetbrains.kotlin.kotlin.test)
    api(libs.org.apache.logging.log4j.log4j.api)
    api(libs.org.apache.logging.log4j.log4j.core)
    testImplementation(libs.org.jetbrains.kotlin.kotlin.test.junit5)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
    compileOnly(libs.org.spigotmc.spigot.api)
    api("net.mamoe:mirai-core-api:2.15.0")
    runtimeOnly("net.mamoe:mirai-core:2.15.0")
}

group = "am9.openLightBit"
version = "1.0-SNAPSHOT"
description = "OpenLightBit"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
