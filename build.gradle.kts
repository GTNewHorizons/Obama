import net.minecraftforge.gradle.user.UserExtension
import java.io.FileInputStream
import java.util.*
import java.io.*

buildscript {
    repositories {
        mavenCentral()
        maven("http://files.minecraftforge.net/maven")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.github.GTNewHorizons:ForgeGradle:stable-2021-11-20")
    }
}

plugins {
    idea
    java
}

apply(plugin = "forge")

//Downloads Javadocs and sources by default
idea {
    module {
        this.isDownloadJavadoc = true
        this.isDownloadSources = true
    }
}

//Set Java to version 1.8
java {
    this.sourceCompatibility = JavaVersion.VERSION_1_8
    this.targetCompatibility = JavaVersion.VERSION_1_8
}

configurations.all {
    isTransitive = false
}

//Set standard encoding
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

//Add extra sources here
sourceSets.getByName("main") {
    java.srcDir("src/main/java")
    java.srcDir("AVRcore/src")
}

//Load Minecraft Version
val Project.minecraft: UserExtension
    get() = extensions.getByName<UserExtension>("minecraft")

val projectVersion: String by project

//Generates a hash for each new commit to differentiate versions
var commitHash = Runtime
        .getRuntime()
        .exec("git rev-parse --short HEAD")
        .let { process ->
            process.waitFor()
            val output = process.inputStream.use {
                it.bufferedReader().use(BufferedReader::readText)
            }
            process.destroy()
            output.trim()
        }

minecraft.version = "1.7.10-10.13.4.1614-1.7.10"
version = "$projectVersion-$commitHash"
group = "com.github.technus"

//Minecraft Block
configure<UserExtension> {
    //Replaces version inside the mod
    this.includes.addAll(
            arrayOf(
                    "GTAFMod.java"
            )
    )
    this.replacements.putAll(
            mapOf(
                    Pair("@version@", project.version)
            )
    )

    //This is sometimes called 'eclipse' instead
    this.runDir = "run"
}

repositories {
    mavenLocal()
    maven("https://gregtech.overminddl1.com/") { this.name = "GT6Maven" }
    maven("http://maven.ic2.player.to/") { this.name = "ic2" }
    maven("http://jenkins.usrv.eu:8081/nexus/content/repositories/releases/") { this.name = "UsrvDE/GTNH" }
    ivy {
        this.name = "gtnh_download_source_underscores"
        this.artifactPattern("http://downloads.gtnewhorizons.com/Mods_for_Jenkins/[module]_[revision].[ext]")
    }
    ivy {
        this.name = "gtnh_download_source"
        this.artifactPattern("http://downloads.gtnewhorizons.com/Mods_for_Jenkins/[module]-[revision].[ext]")
    }
    ivy {
        this.name = "BuildCraft"
        this.artifactPattern("http://www.mod-buildcraft.com/releases/BuildCraft/[revision]/[module]-[revision](-[classifier]).[ext]")
    }
    maven("http://maven.cil.li/") { this.name = "OpenComputers" }
    maven("http://default.mobiusstrip.eu/maven") { this.name = "Jabba" }
    maven("http://chickenbones.net/maven/") { this.name = "CodeChicken" }
    maven("http://www.ryanliptak.com/maven/") { this.name = "appleCore" }
    maven("https://jitpack.io")
}

dependencies {
    //Local Libraries
    compile(fileTree("libs") { this.include("*.jar") })

    val ic2Version: String by project
    val gt5uVersion: String by project
    val yamcoreVersion: String by project
    val opencomputersVersion: String by project
    val computercraftVersion: String by project
    val baublesVersion: String by project
    val thaumcraftVersion: String by project
    val codechickenlibVersion: String by project
    val codechickencoreVersion: String by project
    val neiVersion: String by project
    val wailaVersion: String by project
    val structlibVersion: String by project
    val bartworksVersion: String by project

    //Hard Dependencies
    compile("net.industrial-craft:industrialcraft-2:$ic2Version:dev")
    compile("com.github.GTNewHorizons:GT5-Unofficial:$gt5uVersion:dev"){
        exclude("net.industrial-craft", "industrialcraft-2")
    }
    compile("eu.usrv:YAMCore:$yamcoreVersion:deobf")
    compile("com.github.GTNewHorizons:StructureLib:$structlibVersion:deobf")
    compile("com.github.GTNewHorizons:bartworks:$bartworksVersion:dev")

    //Compile Dependencies
    compileOnly("li.cil.oc:OpenComputers:$opencomputersVersion:dev")
    compileOnly("dan200.computercraft:ComputerCraft:$computercraftVersion")
    compile("com.azanor.baubles:Baubles:$baublesVersion:deobf")
    compile("thaumcraft:Thaumcraft:$thaumcraftVersion:dev")
    compile("codechicken:CodeChickenLib:$codechickenlibVersion:dev")
    compile("codechicken:CodeChickenCore:$codechickencoreVersion:dev")
    compile("com.github.GTNewHorizons:NotEnoughItems:$neiVersion:dev")

    //Optional Libraries for Testing
    runtimeOnly("mcp.mobius.waila:Waila:$wailaVersion")

}

tasks.withType<Jar> {
    //Mark as outdated if versions change
    this.inputs.properties += "version" to project.version
    this.inputs.properties += "mcversion" to project.minecraft.version
    this.archiveBaseName.set("Obama-${project.minecraft.version}")

    //Replace versions in mcmod.info
    this.filesMatching("/mcmod.info") {
        this.expand(
                mapOf(
                        "version" to project.version,
                        "mcversion" to project.minecraft.version
                )
        )
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        this.from(sourceSets.main.get().allSource)
        this.archiveClassifier.set("sources")
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(javadoc)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    val devJar by creating(Jar::class) {
        from(sourceSets.main.get().output)
        archiveClassifier.set("dev")
    }
    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(devJar)
    }
}
//
////Fixes texture loading during 'Minecraft Client' debugging
//sourceSets {
//    this.main {
//        output.setResourcesDir(output.classesDirs.asPath)
//    }
//}