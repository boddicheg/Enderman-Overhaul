plugins {
    id("net.fabricmc.fabric-loom") version "1.17.20"
}

val minecraftVersion: String by project
val fabricLoaderVersion: String by project
val fabricApiVersion: String by project
val resourcefulLibVersion: String by project
val resourcefulConfigVersion: String by project
val geckolibVersion: String by project
val modId: String by project

base {
    archivesName.set("$modId-fabric-$minecraftVersion")
}

repositories {
    mavenCentral()
    maven("https://maven.teamresourceful.com/repository/maven-public/")
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    implementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    implementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
    implementation("com.teamresourceful.resourcefullib:resourcefullib-fabric-$minecraftVersion:$resourcefulLibVersion")
    implementation("com.teamresourceful.resourcefulconfig:resourcefulconfig-fabric-$minecraftVersion:$resourcefulConfigVersion")
    implementation("com.geckolib:geckolib-fabric-$minecraftVersion:$geckolibVersion")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    withSourcesJar()
}

loom {
    accessWidenerPath.set(file("src/main/resources/endermanoverhaul.accesswidener"))
}

fabricApi {
    configureDataGeneration {
        client.set(true)
        outputDirectory.set(file("src/generated/resources"))
    }
}

sourceSets.main {
    resources.exclude(".cache/**")
    resources.exclude("META-INF/neoforge.mods.toml", "data/**/neoforge/**")
    java.exclude("**/compat/mekanism/**")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.processResources {
    val metadata = mapOf(
        "version" to project.version,
        "minecraftVersion" to minecraftVersion,
        "fabricLoaderVersion" to fabricLoaderVersion,
        "fabricApiVersion" to fabricApiVersion,
        "geckolibVersion" to geckolibVersion,
        "resourcefulLibVersion" to resourcefulLibVersion,
        "resourcefulConfigVersion" to resourcefulConfigVersion
    )
    inputs.properties(metadata)
    filesMatching("fabric.mod.json") {
        expand(metadata)
    }
}
