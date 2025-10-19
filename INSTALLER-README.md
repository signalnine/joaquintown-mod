# Joaquintown Windows Installer

Automated one-click installer for Joaquintown mod and all dependencies on Windows.

## 📥 What This Installer Does

The installer automatically:
1. ✅ Checks for Java 21
2. ✅ Installs Fabric Loader 0.15.11 for Minecraft 1.21.1
3. ✅ Downloads Fabric API 0.102.1+1.21.1
4. ✅ Downloads Joaquintown mod 1.0.0
5. ✅ Places everything in the correct folders
6. ✅ Sets up Minecraft launcher profile

**Total time:** 2-3 minutes (depending on download speed)

## 🚀 Quick Start

### Option 1: Double-Click Installation (Easiest)

1. Download both files:
   - `INSTALL-JOAQUINTOWN.bat`
   - `install-joaquintown.ps1`

2. Place both files in the same folder

3. **Double-click `INSTALL-JOAQUINTOWN.bat`**

4. Follow the on-screen instructions

5. Launch Minecraft and select the Fabric profile

### Option 2: PowerShell Direct

1. Right-click `install-joaquintown.ps1`
2. Select "Run with PowerShell"
3. If you see a security warning, type `Y` and press Enter

## 📋 Prerequisites

**Required BEFORE running the installer:**

1. **Minecraft Java Edition**
   - Purchased and installed
   - Must have run vanilla Minecraft at least once
   - Download: https://www.minecraft.net/download

2. **Java 21 or higher**
   - Download: https://adoptium.net/
   - Choose "JDK 21" for your operating system
   - The installer will check for this

**Windows Version:**
- Windows 10 or Windows 11 (PowerShell 5.0+)
- Older Windows versions may need manual installation

## 🎮 After Installation

1. **Open Minecraft Launcher**

2. **Select Profile:**
   - Look for `fabric-loader-0.15.11-1.21.1` in the profile dropdown
   - If you don't see it, click "Installations" and enable it

3. **Click "Play"**

4. **Create a New World:**
   - For best experience, create a BRAND NEW world
   - Structures and guardians spawn at world origin (0, 0)
   - Look for "Welcome to Joaquintown!" message

5. **Enjoy!**
   - Village spawns at 0, 0 within 2 seconds
   - Godzilla spawns north of village
   - King Kong spawns south of village

## 📁 Installation Locations

The installer places files here:

```
C:\Users\<YourName>\AppData\Roaming\.minecraft\
├── mods\
│   ├── fabric-api-0.102.1+1.21.1.jar
│   └── joaquintown-1.0.0.jar
└── versions\
    └── fabric-loader-0.15.11-1.21.1\
        └── (Fabric files)
```

## 🔧 Troubleshooting

### "Java not found!"

**Problem:** Java is not installed or not in PATH

**Solution:**
1. Download Java 21: https://adoptium.net/
2. Install Java 21
3. Restart your computer
4. Run the installer again

**Alternative:** Skip Java check (risky):
```powershell
powershell -ExecutionPolicy Bypass -File install-joaquintown.ps1 -SkipJavaCheck
```

### "Minecraft not found!"

**Problem:** Minecraft hasn't been run yet

**Solution:**
1. Install Minecraft launcher: https://www.minecraft.net/download
2. Run Minecraft at least once
3. Close Minecraft
4. Run the installer again

### "Script is not digitally signed"

**Problem:** Windows PowerShell execution policy blocking

**Solution 1 - Use the batch file:**
- Double-click `INSTALL-JOAQUINTOWN.bat` instead
- It automatically bypasses the policy

**Solution 2 - Run manually:**
```powershell
powershell -ExecutionPolicy Bypass -File install-joaquintown.ps1
```

**Solution 3 - Change policy (admin):**
```powershell
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### "Failed to download Fabric/Joaquintown"

**Problem:** Network/firewall blocking downloads

**Solution:**
1. Check your internet connection
2. Temporarily disable firewall/antivirus
3. Try again
4. **OR** Download manually:
   - Fabric Installer: https://fabricmc.net/use/installer/
   - Fabric API: https://modrinth.com/mod/fabric-api
   - Joaquintown: https://github.com/signalnine/joaquintown-mod/releases

### "Mod doesn't appear in Minecraft"

**Checklist:**
- [ ] Did you select the Fabric profile in the launcher?
- [ ] Are both JAR files in the mods folder?
- [ ] Did you restart Minecraft after installation?
- [ ] Is Minecraft version 1.21.1?

**Verify installation:**
1. Press Windows key + R
2. Type: `%appdata%\.minecraft\mods`
3. Press Enter
4. You should see both JAR files

### Structures don't spawn in existing world

**This is normal!**
- Structures only spawn in NEW worlds
- Existing worlds won't get the spawn village
- Solution: Create a fresh world

## 🗑️ Uninstalling

To remove the mod:

1. Press Windows key + R
2. Type: `%appdata%\.minecraft\mods`
3. Press Enter
4. Delete these files:
   - `joaquintown-1.0.0.jar`
   - (Optional) `fabric-api-0.102.1+1.21.1.jar`

To completely remove Fabric:
- In Minecraft launcher, go to Installations
- Delete the `fabric-loader-0.15.11-1.21.1` profile
- Delete the folder: `%appdata%\.minecraft\versions\fabric-loader-0.15.11-1.21.1`

## 🔄 Updating

When a new version is released:

1. Download the new installer files
2. Run `INSTALL-JOAQUINTOWN.bat` again
3. It will replace old files with new ones
4. Restart Minecraft

## 🛡️ Security & Privacy

**What the installer accesses:**
- Downloads files from:
  - `maven.fabricmc.net` (Fabric Loader)
  - `cdn.modrinth.com` (Fabric API)
  - `github.com` (Joaquintown mod)
- Writes to:
  - `.minecraft\mods` folder
  - `.minecraft\versions` folder
  - Temporary files in `%TEMP%`

**No data collection:**
- The installer does not send any data
- No telemetry or analytics
- No account/login required
- Open source (you can read the code!)

## 📝 Manual Installation Alternative

If the installer doesn't work, install manually:

1. **Install Java 21**
   - Download: https://adoptium.net/

2. **Install Fabric Loader**
   - Download: https://fabricmc.net/use/installer/
   - Run the installer
   - Select Minecraft 1.21.1
   - Click "Install"

3. **Download mods**
   - Fabric API: https://modrinth.com/mod/fabric-api
   - Joaquintown: https://github.com/signalnine/joaquintown-mod/releases

4. **Place JARs in mods folder**
   - Press Windows + R
   - Type: `%appdata%\.minecraft\mods`
   - Copy both JAR files there

5. **Launch Minecraft**
   - Select Fabric profile
   - Play!

## 💡 Tips

- **Create a backup profile:** Before modding, create a backup of your saves
- **Allocate more RAM:** In launcher, edit Fabric profile → JVM arguments → `-Xmx4G`
- **Compatibility:** Works with OptiFine, Sodium, Iris (may have conflicts)
- **Multiplayer:** Server must also have the mod installed
- **Screenshots:** F2 to capture your Joaquintown adventures!

## 🐛 Still Having Issues?

1. **Check the log:**
   - `%appdata%\.minecraft\logs\latest.log`
   - Look for errors mentioning "joaquintown"

2. **Report a bug:**
   - GitHub Issues: https://github.com/signalnine/joaquintown-mod/issues
   - Include:
     - Error message
     - Installer output
     - latest.log file
     - Windows version
     - Java version

## 📜 License

This installer script is released under CC0-1.0 (Public Domain).
Feel free to modify and redistribute!

---

**Installer Version:** 1.0.0
**Last Updated:** 2025-10-18
**Minecraft Version:** 1.21.1
**Mod Version:** 1.0.0
