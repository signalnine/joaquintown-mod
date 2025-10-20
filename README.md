# Joaquintown Mod

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.15.11-blue.svg)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://adoptium.net/)
[![Release](https://img.shields.io/github/v/release/signalnine/joaquintown-mod)](https://github.com/signalnine/joaquintown-mod/releases)
[![License](https://img.shields.io/badge/License-CC0--1.0-lightgrey.svg)](LICENSE)

**An epic Minecraft adventure mod featuring legendary guardians, custom villagers, powerful weapons, and automatic world generation!**

> 🎮 Transform your spawn into Joaquintown - complete with Godzilla & King Kong defenders, custom Joaquin-styled villagers, legendary weapons, banana foods, and 8 automatic structure placements!

---

## 📥 Installation

### 🪟 Windows - One-Click Installer (Recommended!)

**The easiest way to install on Windows:**

1. **Download the installer:**
   - [INSTALL-JOAQUINTOWN.bat](https://github.com/signalnine/joaquintown-mod/raw/main/INSTALL-JOAQUINTOWN.bat) (Right-click → Save As)
   - [install-joaquintown.ps1](https://github.com/signalnine/joaquintown-mod/raw/main/install-joaquintown.ps1) (Right-click → Save As)

2. **Place both files in the same folder**

3. **Double-click `INSTALL-JOAQUINTOWN.bat`**

4. **Follow the on-screen instructions** (2-3 minutes)

5. **Launch Minecraft** and select the Fabric profile!

✨ The installer automatically downloads and installs:
- Fabric Loader 0.15.11
- Fabric API 0.102.1+1.21.1
- Joaquintown mod 1.0.2

📖 **Detailed Instructions:** See [INSTALLER-README.md](INSTALLER-README.md)

---

### 🐧 Linux / 🍎 macOS / Manual Installation

**Prerequisites:**
- Java 21+ ([Download](https://adoptium.net/))
- Minecraft Java Edition 1.21.1

**Steps:**

1. **Install Fabric Loader**
   - Download: [Fabric Installer](https://fabricmc.net/use/installer/)
   - Run installer, select Minecraft 1.21.1
   - Click "Install"

2. **Download Required Mods**
   - [Fabric API 0.102.1+1.21.1](https://modrinth.com/mod/fabric-api/version/0.102.1+1.21.1)
   - [Joaquintown 1.0.2](https://github.com/signalnine/joaquintown-mod/releases/download/v1.0.2/joaquintown-1.0.2.jar)

3. **Install Mods**
   - Place both JAR files in `.minecraft/mods` folder:
     - **Windows:** `%appdata%\.minecraft\mods`
     - **macOS:** `~/Library/Application Support/minecraft/mods`
     - **Linux:** `~/.minecraft/mods`

4. **Launch Minecraft**
   - Select `fabric-loader-0.15.11-1.21.1` profile
   - Create a **new world** for best experience
   - Enjoy Joaquintown! 🎉

---

## ✨ Features

### 🏘️ Automatic World Generation

**Spawn Village System:**
- Plains village spawns at world origin (0, 0) within 2 seconds
- "Welcome to Joaquintown!" message on player join
- 100% structure placement success rate (8/8 structures)

**Guardian Defenders:**
- 🦖 **Godzilla** - Spawns north of village (6-8 blocks tall, 200 HP)
- 🦍 **King Kong** - Spawns south of village (4-5 blocks tall, ground pound ability)
- Both guardians protect villagers and are persistent (won't despawn)

**Structure Placements:**
- **Sky Outpost:** Pillager outpost at Y=150 (30 blocks east)
- **Loot Plaza:** 4 structures in cardinal directions (80 blocks from spawn)
  - Desert Pyramid (North)
  - Shipwreck (South)
  - Swamp Hut (East)
  - Igloo (West)
- **Vertical Stack:** Epic 3-level tower (120 blocks northwest)
  - Fortress (Y=80)
  - Woodland Mansion (Y=120)
  - End City (Y=200)

### 👥 Custom Joaquin Villagers

**Visual Customization:**
- Unique Joaquin-styled textures with curly hair
- 14 profession variants (all with custom accessories)
- 7 biome type variants (plains, desert, savanna, taiga, snow, swamp, jungle)
- Flattened nose model (3D protrusion removed via mixin)

**Custom Sounds:**
- 15 custom sound events
- Ambient, trading, hurt, death, work, celebration sounds
- Unique voice personality for each villager

### ⚔️ Legendary Weapons

**Godzilla's Blade** (Rare drop from Godzilla):
- Netherite sword-tier damage
- Unbreakable with Mending
- Sharpness V
- Looting III

**Kong's Axe** (Rare drop from King Kong):
- High damage axe
- Unbreakable with Mending
- Efficiency V
- Fortune III

### 🍌 Banana Food System

**5 Craftable Items:**
- 🍌 Raw Banana (hunger: 2)
- 🍌 Fried Banana (hunger: 6, saturation: 8.0)
- 🍞 Banana Bread (hunger: 5, saturation: 6.0)
- 🍫 Chocolate Banana (hunger: 4, saturation: 7.0)
- 🥤 Banana Smoothie (hunger: 3, saturation: 5.0, drinkable)

**Full crafting system** with recipes and smelting

### 🔊 Custom Sounds

**Modified Entities:**
- **Villagers:** Complete sound overhaul (15 sounds)
- **Iron Golems:** Roar sounds (3 variants) + hurt sound
- **Zombies:** Custom zombie sounds (4 variants)
- **Ravagers:** Joaquin-styled retexture
- **Godzilla:** Custom ambient, hurt, death sounds

### 🎮 Spawn Eggs

Creative mode spawn eggs for:
- Godzilla
- King Kong

---

## 📊 Entity Stats

| Entity | Health | Attack | Armor | Special Abilities |
|--------|--------|--------|-------|-------------------|
| **Godzilla** | 200 HP | High | Heavy | Atomic breath, tail swipe |
| **King Kong** | 150 HP | High | Medium | Ground pound, chest-beat |
| Iron Golem | 100 HP | 21 dmg | 0 | Knockback |
| Warden | 500 HP | 30 dmg | 0 | Sonic boom |

---

## 🎯 Quick Start Guide

### After Installation:

1. **Launch Minecraft** with the Fabric profile
2. **Create a NEW world** (structures only spawn in new worlds)
3. **Look for the welcome message** when you join
4. **Visit spawn (0, 0)** to see the village and guardians
5. **Explore the structures** in all directions from spawn

### Key Commands:

```bash
# Spawn entities
/summon joaquintown:godzilla ~ ~ ~
/summon joaquintown:king_kong ~ ~ ~

# Get items
/give @p joaquintown:banana 64
/give @p joaquintown:godzillas_blade
/give @p joaquintown:kongs_axe

# Get spawn eggs
/give @p joaquintown:godzilla_spawn_egg
/give @p joaquintown:king_kong_spawn_egg
```

### Finding Structures:

- **Village & Guardians:** 0, 0 (spawn point)
- **Sky Outpost:** 30, 150, 0 (east, in the sky)
- **Desert Pyramid:** 0, ?, 80 (north)
- **Shipwreck:** 0, ?, -80 (south)
- **Swamp Hut:** 80, ?, 0 (east)
- **Igloo:** -80, ?, 0 (west)
- **Vertical Stack:** -120, varies, -120 (northwest)

---

## 📋 Requirements

- **Minecraft:** 1.21.1
- **Mod Loader:** Fabric Loader 0.15.11+
- **Dependencies:** Fabric API 0.102.1+1.21.1
- **Java:** 21 or higher
- **Platform:** Windows, macOS, Linux

---

## 🛠️ Building from Source

### Prerequisites:
- Java 21+ ([Download](https://adoptium.net/))
- Git (optional)

### Build Steps:

```bash
# Clone the repository
git clone https://github.com/signalnine/joaquintown-mod.git
cd joaquintown-mod

# Set JAVA_HOME (macOS example)
export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home

# Build the mod
./gradlew build

# JAR output
build/libs/joaquintown-1.0.2.jar
```

### Development:

```bash
# Run test client
./gradlew runClient

# Run test server
./gradlew runServer

# Run tests
./gradlew test

# Generate sources for IDE
./gradlew genSources
```

---

## 📚 Documentation

- **[Complete Mod Documentation](MOD_DOCUMENTATION.md)** - Full technical details, architecture, all features
- **[Quick Reference Guide](QUICK_REFERENCE.md)** - Commands, stats, troubleshooting
- **[Windows Installer Guide](INSTALLER-README.md)** - Installation troubleshooting
- **[Upload Guide](UPLOAD_GUIDE.md)** - How to publish to CurseForge/Modrinth
- **[CurseForge Description](CURSEFORGE_README.md)** - Ready-to-use mod description

---

## 📦 Downloads

- **[Latest Release (v1.0.2)](https://github.com/signalnine/joaquintown-mod/releases/tag/v1.0.2)**
  - Direct JAR download
  - Windows installer
  - Full changelog

- **[All Releases](https://github.com/signalnine/joaquintown-mod/releases)**

---

## ⚙️ Technical Details

**Mod Information:**
- Version: 1.0.2
- Minecraft: 1.21.1
- Yarn Mappings: 1.21.1+build.3
- Fabric Loader: 0.15.11
- Fabric API: 0.102.1+1.21.1
- Fabric Loom: 1.7.2

**Code Stats:**
- 170 files
- 8,656+ lines of code
- 23/23 unit tests passing ✓

**Implementation:**
- Mixins for model/sound modifications
- Resource pack overrides for textures
- NBT persistent state tracking
- Event-driven structure placement
- Custom entity models and renderers

---

## ⚠️ Known Issues

1. **Best with New Worlds:** Structures and guardians only spawn in new worlds
2. **Existing Worlds:** Villager textures work, but spawn structures won't generate
3. **Sound Conflicts:** Mods that override villager sounds may conflict
4. **Iron Golem Targeting:** Custom targeting may not work if overridden by other mods

---

## 🐛 Bug Reports & Support

**Found a bug?**
- [Create an Issue](https://github.com/signalnine/joaquintown-mod/issues)
- Include: Error message, logs, Minecraft version, mod list

**Need help?**
- Check [QUICK_REFERENCE.md](QUICK_REFERENCE.md) for troubleshooting
- Check [INSTALLER-README.md](INSTALLER-README.md) for installation issues
- Review [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md) for technical details

---

## 🔄 Version History

### 1.0.2 (2025-10-20)

**Banana Trees & Recipe Fixes**

🍌 **New Features:**
- Complete banana tree system (sapling, leaves, growing bananas)
- Seaweed wraps food item
- Fixed all crafting recipes for Minecraft 1.21.1
- Baby kaiju rendering and sounds

### 1.0.1 (2025-10-18)

**The Bella Update**

🐕 **Wolf Customizations:**
- Corgi-style short legs for all wolves
- Bella texture for tamed wolves

### 1.0.0 (2025-10-18)

**Initial Release - "Joaquintown Rises"**

🏘️ **World Generation:**
- Auto-spawning village at world origin
- Godzilla & King Kong guardian defenders
- 8 automatic structure placements (100% success rate)
- Sky outpost, loot plaza, vertical stack

👥 **Villagers:**
- Custom Joaquin-styled textures (14 professions, 7 biomes)
- Flattened nose model
- 15 custom sounds

⚔️ **Combat & Items:**
- Legendary weapons (Godzilla's Blade, Kong's Axe)
- 5 banana food items with crafting
- Spawn eggs for guardians

🔊 **Audio:**
- Custom sounds for villagers, iron golems, zombies, ravagers, Godzilla

🪟 **Extras:**
- Windows one-click installer
- Complete documentation suite
- GitHub releases with direct downloads

See [full changelog](MOD_DOCUMENTATION.md#version-history)

---

## 🤝 Contributing

This is a personal project, but contributions are welcome!

**How to contribute:**
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

**Before contributing:**
- Check existing issues
- Follow existing code style
- Include tests for new features
- Update documentation

---

## 📄 License

**CC0-1.0 (Public Domain)**

This mod is released into the public domain. You are free to:
- ✅ Use commercially
- ✅ Modify and redistribute
- ✅ Use in modpacks
- ✅ Create derivative works

No attribution required, but appreciated!

---

## 🙏 Credits

**Development:**
- Primary Developer: signalnine
- AI Assistance: Claude (Anthropic)

**Dependencies:**
- Fabric API (FabricMC)
- Fabric Loader (FabricMC)
- Yarn Mappings (FabricMC)

**Assets:**
- Custom villager textures
- Custom sound recordings
- Custom entity models

**Built with:**
- [Claude Code](https://claude.com/claude-code) - AI-powered development
- Java 21
- IntelliJ IDEA / VS Code

---

## 🌟 Star This Repo!

If you enjoy Joaquintown, please consider starring the repository! ⭐

It helps others discover the mod and motivates continued development.

---

## 📧 Contact

- **GitHub:** [@signalnine](https://github.com/signalnine)
- **Issues:** [Report a bug](https://github.com/signalnine/joaquintown-mod/issues)
- **Releases:** [Download latest](https://github.com/signalnine/joaquintown-mod/releases)

---

**Enjoy your adventure in Joaquintown! 🎉**

*Built with [Claude Code](https://claude.com/claude-code)*
