<img src="https://i.imgur.com/6hlMdx5.png" width="100%" alt="Enderman Overhaul wordmark">

# Enderman Overhaul for Fabric

[![Minecraft Java Edition 26.1](https://img.shields.io/badge/Minecraft_Java-26.1-3C8527)](#usage)
[![Fabric Loader](https://img.shields.io/badge/Loader-Fabric-DBD0B4)](#usage)
[![Java 25](https://img.shields.io/badge/Java-25-E76F00)](#usage)
[![Build from source](https://img.shields.io/badge/Build-from_source-6F42C1)](#build-from-source)

Enderman Overhaul adds more than 20 Enderman variants with their own models,
animations, and sounds. Find them across the Overworld, Nether, and End. Collect
eight specialty pearls that can teleport targets, freeze nearby mobs, or summon
an Enderman companion.

This fork brings Enderman Overhaul 2.0.3 to **Minecraft Java Edition 26.1**
on Fabric.

<p align="center">
  <a href="https://modrinth.com/mod/enderman-overhaul/gallery"><img src="https://cdn.modrinth.com/data/Lq6ojcWv/images/4c645dfc1cf331d8f3498b680653e66dff01a2ac_350.webp" width="32%" alt="Dark Oak Enderman"></a>
  <a href="https://modrinth.com/mod/enderman-overhaul/gallery"><img src="https://cdn.modrinth.com/data/Lq6ojcWv/images/7416ba143a7f2e0b25c05a4b2d329cf17e6e01bb_350.webp" width="32%" alt="End Islands Enderman"></a>
  <a href="https://modrinth.com/mod/enderman-overhaul/gallery"><img src="https://cdn.modrinth.com/data/Lq6ojcWv/images/c4edd4bf39dc375361abe720e01136eee7b43260_350.webp" width="32%" alt="Enderman companions"></a>
</p>

## Usage

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) 0.20.1 or newer
   for Minecraft 26.1 and run the game with Java 25.
2. [Build the mod JAR](#build-from-source) and copy it into your instance's
   `mods` folder along with the Fabric dependencies below.
3. For multiplayer, install the same mod and dependencies on the dedicated server.

| Dependency | Tested version |
| --- | --- |
| [Fabric API](https://modrinth.com/mod/fabric-api/version/3dM0X6ou) | 0.155.3+26.1.2 |
| [GeckoLib](https://modrinth.com/mod/geckolib/version/yUpAp23B) | 5.5 for Fabric 26.1 |
| [Resourceful Lib](https://modrinth.com/mod/resourceful-lib/version/G0rgQsmz) | 4.0.2 |
| [Resourceful Config](https://modrinth.com/mod/resourceful-config/version/GMW14IUd) | 4.0.1 |

The first launch creates `config/endermanoverhaul.jsonc` for spawn and behavior
settings. The client also creates `config/endermanoverhaul-client.jsonc` for
visual options, including the vanilla Enderman model replacement.

The optional Mekanism Additions baby Enderman renderer is unavailable in this port.

## Build from source

Set `JAVA_HOME` to a JDK 25 installation and run the Gradle wrapper from the
repository root.

**Windows**

```powershell
.\gradlew.bat clean build
```

**macOS and Linux**

```sh
./gradlew clean build
```

The playable JAR is `build/libs/endermanoverhaul-fabric-26.1-2.0.3.jar`.

To regenerate item models, English translations, loot tables, and tags, run the
`runDatagen` task in a separate Gradle invocation before building.

## Credits and license

The [original Enderman Overhaul project](https://github.com/bonsaistudi0s/Enderman-Overhaul)
credits Josh (Joosh) for art, Alex Nijjar for programming, and Scratchy for sound
effects. The wordmark and screenshots are from the original project's
[Modrinth page](https://modrinth.com/mod/enderman-overhaul).

The original project is licensed **All Rights Reserved**.
