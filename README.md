# Joaquintown Mod

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.16.2-blue.svg)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)

A Minecraft mod featuring **Godzilla** and **King Kong** as village defenders, custom villager systems, and a banana-based economy.

## 🎮 Features

### 🦖 **Kaiju Defenders**
- **Godzilla** - 600 HP titan with custom 3D animated model
- **King Kong** - 500 HP mighty gorilla with authentic roars
- Both powerful enough to defeat Wardens
- Automatically spawn to protect villages

### 🍌 **Banana Economy**
- Villagers trade using bananas instead of emeralds
- Works with all villager professions
- Banana food item included

### 👤 **Custom Villagers**
- Modified appearance (nose removed)
- Custom profession-specific textures
- Joaquin-themed voice acting

### 🔊 **Custom Sounds**
- Authentic Godzilla roars
- Authentic King Kong roars
- Custom villager voice lines
- Modified zombie sounds

### 🏘️ **Auto Village Spawning**
- Plains village spawns at world spawn
- Godzilla and King Kong automatically defend it
- Persistent defenders that won't despawn

---

## 📚 Documentation

- **[Complete Documentation](MOD_DOCUMENTATION.md)** - Full technical details, all features, architecture
- **[Quick Reference Guide](QUICK_REFERENCE.md)** - Commands, stats, troubleshooting
- **[Agents Documentation](AGENTS.md)** - Development workflow and AI assistance

---

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Gradle** 8.10+ (included via wrapper)
- **Git** (optional, for cloning)

### Building the Mod

```bash
# Clone the repository (or download)
git clone <repository-url>
cd mc-mod

# Build the mod
./gradlew build

# The JAR will be in build/libs/joaquintown-1.0.0.jar
```

### Testing in Development

```bash
# Run Minecraft with the mod loaded
./gradlew runClient
```

### Installing for Play

1. Build the mod (see above)
2. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 1.21.1
3. Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) (version 0.102.1+)
4. Install [GeckoLib](https://www.curseforge.com/minecraft/mc-mods/geckolib) (version 4.6.1+)
5. Copy `joaquintown-1.0.0.jar` to your `.minecraft/mods` folder

**Mods Folder Locations:**
- Windows: `%appdata%/.minecraft/mods`
- macOS: `~/Library/Application Support/minecraft/mods`
- Linux: `~/.minecraft/mods`

---

## 📊 Entity Stats

| Entity | Health | Attack | Armor | Speed | Special |
|--------|--------|--------|-------|-------|---------|
| **Godzilla** | 600 HP | 40 dmg | 15 (+8) | 0.25 | Knockback immune |
| **King Kong** | 500 HP | 35 dmg | 12 (+6) | 0.30 | Knockback immune |
| Warden | 500 HP | 30 dmg | 0 | 0.30 | Sonic boom |
| Iron Golem | 100 HP | 21 dmg | 0 | 0.25 | - |

---

## 🎯 Key Features Detail

### Godzilla
- Custom GeckoLib 3D model with animations
- Authentic roar sound effects
- 4 blocks tall, 2 blocks wide
- Targets all hostile mobs (except creepers)
- Allied with iron golems and King Kong

### King Kong
- Scaled iron golem model (130% size)
- Authentic Kong roar sounds
- 3.2 blocks tall, 1.6 blocks wide
- Fast movement speed
- Allied with iron golems and Godzilla

### Banana Trading
- All emerald trades replaced with bananas
- Works automatically via mixin system
- Affects all villager professions
- Bananas are edible

### Village System
- Spawns automatically on world creation
- Located at world spawn point
- Includes both kaiju defenders
- Persistent entities (won't despawn)

---

## 🛠️ Development

### Project Structure

```
src/main/
├── java/com/joaquintown/mod/
│   ├── entity/          # Godzilla, King Kong
│   ├── client/          # Models, renderers
│   ├── mixin/           # Vanilla game modifications
│   ├── item/            # Banana, spawn eggs
│   └── world/           # Village spawning logic
└── resources/
    ├── assets/joaquintown/
    │   ├── geo/         # Godzilla model
    │   ├── animations/  # Godzilla animations
    │   ├── sounds/      # All custom sounds
    │   └── textures/    # Entity & villager textures
    └── joaquintown.mixins.json
```

### Running Tests

```bash
./gradlew test

# View test results
open build/reports/tests/test/index.html
```

### IDE Setup

**IntelliJ IDEA:**
```bash
./gradlew genSources
# File → Open → select build.gradle
```

**Eclipse:**
```bash
./gradlew eclipse
# File → Import → Existing Projects
```

**VS Code:**
```bash
./gradlew genSources
# Install Java Extension Pack
# Open folder
```

---

## 📋 Quick Commands

```bash
# Spawn Godzilla
/summon joaquintown:godzilla ~ ~ ~

# Spawn King Kong
/summon joaquintown:king_kong ~ ~ ~

# Give banana
/give @p joaquintown:banana 64

# Give spawn eggs
/give @p joaquintown:godzilla_spawn_egg
/give @p joaquintown:king_kong_spawn_egg
```

---

## ⚠️ Known Issues

1. **Iron Golem Targeting:** Vanilla iron golems may occasionally attack Godzilla/King Kong (one-sided, rare)
2. **Build Warning:** IronGolemEntityMixin warning is harmless (mixin is disabled)
3. **Sound Licensing:** Current sounds may need replacement for public distribution

See [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md#known-issues) for full details.

---

## 🔄 Version History

### 1.0.0 (October 16, 2025)
- Initial release
- Godzilla and King Kong entities
- Banana economy system
- Custom villager system
- Custom sound overrides
- Automatic village spawning

See [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md#version-history) for complete changelog.

---

## 📖 Additional Resources

- **[Fabric Wiki](https://fabricmc.net/wiki/)** - Fabric modding documentation
- **[GeckoLib Wiki](https://github.com/bernie-g/geckolib/wiki)** - Animation system docs
- **[Minecraft Wiki](https://minecraft.wiki/)** - Game mechanics reference

---

## 🤝 Contributing

This is a personal project, but suggestions and bug reports are welcome!

1. Check existing issues
2. Create detailed bug reports
3. Include logs and crash reports
4. Test with only this mod + required dependencies

---

## 📄 License

[Specify your license here]

**Important:** This mod uses third-party assets that may require proper licensing for public distribution. See [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md#credits) for asset credits.

---

## 🙏 Credits

- **Development:** [Your Name/Username]
- **AI Assistance:** Claude (Anthropic)
- **Dependencies:** Fabric API, GeckoLib
- **Godzilla Model:** Extracted from "Extra Kaiju Update" mod
- **Sounds:** Custom recordings and conversions

See [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md#credits) for complete credits.

---

## 📧 Support

- **Documentation:** See [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md)
- **Quick Help:** See [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
- **Issues:** [Create an issue on GitHub]
- **Contact:** [Your contact method]

---

**For complete technical documentation, features, and architecture details, see [MOD_DOCUMENTATION.md](MOD_DOCUMENTATION.md)**
