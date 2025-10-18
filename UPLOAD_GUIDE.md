# Mod Upload Guide

This guide will walk you through uploading the Joaquintown mod to CurseForge and Modrinth.

## 📦 What You'll Upload

Your mod JAR file is located at:
```
build/libs/joaquintown-1.0.0.jar
```

This file contains everything: code, textures, sounds, and configuration.

## 🎯 Platform Recommendations

### CurseForge
- **Pros**: Largest mod hosting platform, most users, built into many launchers
- **Cons**: Slower approval process, stricter content rules
- **Best for**: Maximum reach and visibility

### Modrinth
- **Pros**: Modern interface, faster approval, better developer tools, open source
- **Cons**: Smaller user base (growing rapidly)
- **Best for**: Developer-friendly experience, faster updates

**Recommendation**: Upload to BOTH platforms for maximum reach!

---

## 📤 CurseForge Upload Process

### 1. Create an Account
1. Go to https://www.curseforge.com/
2. Click "Sign Up" (top right)
3. Create an account or sign in with Google/Twitch

### 2. Create a Project
1. Go to https://authors.curseforge.com/
2. Click "Create Project"
3. Select **"Minecraft"** as the game
4. Select **"Mod"** as the project type

### 3. Fill Out Project Information

**Basic Info:**
- **Project Name**: Joaquintown
- **Summary**: Transform all villagers into unique Joaquin-styled characters with custom textures, sounds, and a flattened nose!
- **Category**: Select "World Generation" and "Miscellaneous"
- **Mod Loader**: Fabric
- **Tags**: Add tags like "villagers", "textures", "sounds", "village"

**Description:**
- Copy the contents of `CURSEFORGE_README.md`
- Add screenshots if you have them (highly recommended!)
- Use the rich text editor to format nicely

**Logo/Icon:**
- Upload a 256x256 PNG logo (optional but recommended)
- Should represent your mod visually

### 4. Upload the Mod File
1. Click on "Files" tab
2. Click "Upload File"
3. Select your JAR: `build/libs/joaquintown-1.0.0.jar`

**File Information:**
- **Display Name**: Joaquintown 1.0.0
- **Game Version**: Select Minecraft 1.21.1
- **Release Type**: Release (or Beta/Alpha if not fully tested)
- **Mod Loader**: Fabric
- **Dependencies**: Add Fabric API as a required dependency
  - Click "Add dependency"
  - Search for "Fabric API"
  - Set as "Required"

**Changelog:**
```
Initial release of Joaquintown!

✨ Features:
- Custom Joaquin villager textures for all professions
- Removed 3D nose from villager models
- Custom villager sounds (ambient, hurt, trading, working)
- Auto-spawning welcome village at world spawn
- Works with all biomes and professions

See full changelog on GitHub or project page.
```

5. Click "Upload" and wait for processing

### 5. Submit for Approval
- CurseForge requires manual approval for first releases
- Usually takes 1-3 days
- You'll get an email when approved

---

## 🚀 Modrinth Upload Process

### 1. Create an Account
1. Go to https://modrinth.com/
2. Click "Log in" (top right)
3. Sign up with email, GitHub, Discord, or other options

### 2. Create a Project
1. Click your profile icon → "Dashboard"
2. Click "Create a project"
3. Select "Mod" as project type

### 3. Fill Out Project Information

**Basic Info:**
- **Name**: Joaquintown
- **Summary**: Transform all villagers into Joaquin-styled characters with custom textures, sounds, and removed noses!
- **Categories**: Select "World Generation", "Decoration", "Audio"
- **Client/Server**: Both (or Client-side with explanation)
- **License**: CC0-1.0

**Description:**
- Copy from `CURSEFORGE_README.md`
- Modrinth uses Markdown, so formatting should work well
- Add screenshots/videos if available

**Icon:**
- Upload a square icon (recommended)

### 4. Upload Version
1. Click "Versions" tab
2. Click "Create a version"

**Version Information:**
- **Version Number**: 1.0.0
- **Version Title**: Initial Release
- **Channel**: Release (or Beta/Alpha)
- **Loaders**: Select Fabric
- **Game Versions**: Select 1.21.1
- **Dependencies**: Add Fabric API
  - Click "Add dependency"
  - Search for "Fabric API"
  - Set as "Required"

**Upload File:**
- Drag and drop or select: `build/libs/joaquintown-1.0.0.jar`
- Primary file: Yes

**Changelog:**
```markdown
# Joaquintown 1.0.0 - Initial Release

## ✨ New Features
- Custom Joaquin villager textures for all 14 professions
- Custom type textures for all 7 biomes
- Removed 3D nose protrusion from villager models
- Custom villager sounds:
  - 3 ambient sounds
  - 2 hurt sounds
  - Death sound
  - Trading sounds (yes/no)
  - Celebration and work sounds
- Auto-spawning village at world spawn
- "Welcome to Joaquintown!" join message

## 📋 Requirements
- Minecraft 1.21.1
- Fabric Loader 0.15.0+
- Fabric API

Enjoy your time in Joaquintown! 🎉
```

3. Click "Create version"

### 5. Publish
- Modrinth has instant publishing (no approval needed)
- Your mod is live immediately!

---

## 📸 Recommended Screenshots

Before uploading, take these in-game screenshots:
1. **Hero shot**: Group of villagers showing different professions
2. **Close-up**: Single villager showing texture detail
3. **Village spawn**: The auto-generated spawn village
4. **Profession variety**: Different villager professions side-by-side

Place screenshots in the project description for better presentation!

---

## 🏷️ Version Naming Convention

For future updates, use this format:
- **Major release**: 2.0.0 (big changes, new features)
- **Minor release**: 1.1.0 (new features, improvements)
- **Patch release**: 1.0.1 (bug fixes)

---

## ✅ Pre-Upload Checklist

Before uploading, make sure:
- [ ] Mod builds successfully: `./gradlew build`
- [ ] JAR file exists: `build/libs/joaquintown-1.0.0.jar`
- [ ] Tested in actual Minecraft 1.21.1
- [ ] All features work (textures, sounds, village spawn)
- [ ] No console errors in game
- [ ] Screenshots taken (optional but recommended)
- [ ] `CURSEFORGE_README.md` is accurate
- [ ] `CHANGELOG.md` is up to date

---

## 🔄 Updating Your Mod

When you release updates:
1. Update version number in `gradle.properties`
2. Update `CHANGELOG.md` with changes
3. Rebuild: `./gradlew clean build`
4. Upload new JAR to both platforms
5. Mark compatibility with game version(s)
6. Add changelog for the update

---

## 💡 Tips for Success

1. **Add Screenshots**: Mods with screenshots get 5-10x more downloads
2. **Write Clear Descriptions**: Explain what your mod does simply
3. **Respond to Comments**: Build a community around your mod
4. **Update Regularly**: Even small bug fixes show the mod is maintained
5. **Cross-Platform**: Upload to both CurseForge and Modrinth for maximum reach
6. **Social Media**: Share on r/feedthebeast, Minecraft forums, Discord servers

---

## 🆘 Troubleshooting

**"My mod was rejected on CurseForge"**
- Check the rejection reason in your email
- Common issues: inappropriate content, broken JAR, missing info
- Fix issues and resubmit

**"Users report the mod doesn't work"**
- Verify they have Fabric Loader and Fabric API installed
- Check Minecraft version matches (1.21.1)
- Ask for crash logs or latest.log file

**"Want to update mod description"**
- Both platforms allow editing after upload
- Go to project settings → edit description

---

## 📞 Need Help?

- **CurseForge Support**: https://support.curseforge.com/
- **Modrinth Discord**: https://discord.modrinth.com/
- **Fabric Discord**: https://discord.gg/v6v4pMv

---

Good luck with your mod release! 🚀
