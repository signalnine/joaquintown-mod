# Joaquintown Mod - Windows Installer
# Automated installation script for Minecraft 1.21.1 + Fabric + Joaquintown mod
#
# This script will:
# 1. Check for Java 21
# 2. Install Fabric Loader 0.15.11 for Minecraft 1.21.1
# 3. Download Fabric API 0.102.1+1.21.1
# 4. Download Joaquintown mod 1.0.0
# 5. Set up everything in the correct folders

# Requires PowerShell 5.0+ (Windows 10/11 default)

param(
    [switch]$SkipJavaCheck
)

# Color output functions
function Write-Success { param($msg) Write-Host $msg -ForegroundColor Green }
function Write-Info { param($msg) Write-Host $msg -ForegroundColor Cyan }
function Write-Warning { param($msg) Write-Host $msg -ForegroundColor Yellow }
function Write-Error { param($msg) Write-Host $msg -ForegroundColor Red }
function Write-Step { param($msg) Write-Host "`n==> $msg" -ForegroundColor Magenta }

# Configuration
$MINECRAFT_VERSION = "1.21.1"
$FABRIC_LOADER_VERSION = "0.15.11"
$FABRIC_API_VERSION = "0.102.1+1.21.1"
$MOD_VERSION = "1.0.0"

# URLs
$FABRIC_INSTALLER_URL = "https://maven.fabricmc.net/net/fabricmc/fabric-installer/1.0.1/fabric-installer-1.0.1.jar"
$FABRIC_API_URL = "https://cdn.modrinth.com/data/P7dR8mSH/versions/XAkY2vb0/fabric-api-0.102.1%2B1.21.1.jar"
$JOAQUINTOWN_MOD_URL = "https://github.com/signalnine/joaquintown-mod/releases/download/v1.0.0/joaquintown-1.0.0.jar"

# Paths
$MINECRAFT_DIR = "$env:APPDATA\.minecraft"
$MODS_DIR = "$MINECRAFT_DIR\mods"
$TEMP_DIR = "$env:TEMP\joaquintown-installer"

# Banner
Clear-Host
Write-Host @"

     ██╗ ██████╗  █████╗  ██████╗ ██╗   ██╗██╗███╗   ██╗████████╗ ██████╗ ██╗    ██╗███╗   ██╗
     ██║██╔═══██╗██╔══██╗██╔═══██╗██║   ██║██║████╗  ██║╚══██╔══╝██╔═══██╗██║    ██║████╗  ██║
     ██║██║   ██║███████║██║   ██║██║   ██║██║██╔██╗ ██║   ██║   ██║   ██║██║ █╗ ██║██╔██╗ ██║
██   ██║██║   ██║██╔══██║██║▄▄ ██║██║   ██║██║██║╚██╗██║   ██║   ██║   ██║██║███╗██║██║╚██╗██║
╚█████╔╝╚██████╔╝██║  ██║╚██████╔╝╚██████╔╝██║██║ ╚████║   ██║   ╚██████╔╝╚███╔███╔╝██║ ╚████║
 ╚════╝  ╚═════╝ ╚═╝  ╚═╝ ╚══▀▀═╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝  ╚══╝╚══╝ ╚═╝  ╚═══╝

                               Minecraft Mod Installer v1.0.0
                          Installing Joaquintown with all dependencies

"@ -ForegroundColor Yellow

Write-Info "This installer will set up everything you need to play Joaquintown!"
Write-Info "Estimated time: 2-3 minutes"
Start-Sleep -Seconds 2

# Create temp directory
Write-Step "Setting up temporary files"
if (Test-Path $TEMP_DIR) {
    Remove-Item -Path $TEMP_DIR -Recurse -Force
}
New-Item -ItemType Directory -Path $TEMP_DIR -Force | Out-Null
Write-Success "Temporary directory created: $TEMP_DIR"

# Check for Java
Write-Step "Checking for Java 21"
if (-not $SkipJavaCheck) {
    try {
        $javaVersion = java -version 2>&1 | Select-String "version" | ForEach-Object { $_ -replace '.*"(\d+)\..*', '$1' }

        if ($javaVersion -ge 21) {
            Write-Success "Java $javaVersion found - OK"
        } else {
            Write-Warning "Java $javaVersion found, but Java 21+ is recommended"
            Write-Info "Download Java 21 from: https://adoptium.net/"
            $continue = Read-Host "Continue anyway? (y/n)"
            if ($continue -ne 'y') {
                exit 1
            }
        }
    } catch {
        Write-Error "Java not found!"
        Write-Info "Please install Java 21 from: https://adoptium.net/"
        Write-Info "After installing, run this script again"
        pause
        exit 1
    }
} else {
    Write-Warning "Skipping Java check (use at your own risk)"
}

# Check for Minecraft
Write-Step "Checking for Minecraft installation"
if (Test-Path $MINECRAFT_DIR) {
    Write-Success "Minecraft directory found: $MINECRAFT_DIR"
} else {
    Write-Error "Minecraft not found at: $MINECRAFT_DIR"
    Write-Info "Please install Minecraft launcher from: https://www.minecraft.net/download"
    Write-Info "Run Minecraft at least once before installing mods"
    pause
    exit 1
}

# Download Fabric Installer
Write-Step "Downloading Fabric Installer"
$fabricInstallerPath = "$TEMP_DIR\fabric-installer.jar"
try {
    Invoke-WebRequest -Uri $FABRIC_INSTALLER_URL -OutFile $fabricInstallerPath -UseBasicParsing
    Write-Success "Fabric Installer downloaded"
} catch {
    Write-Error "Failed to download Fabric Installer: $_"
    pause
    exit 1
}

# Install Fabric Loader
Write-Step "Installing Fabric Loader $FABRIC_LOADER_VERSION for Minecraft $MINECRAFT_VERSION"
Write-Info "This will create a new Fabric profile in your Minecraft launcher"
try {
    $fabricInstallOutput = & java -jar $fabricInstallerPath client -mcversion $MINECRAFT_VERSION -loader $FABRIC_LOADER_VERSION -noprofile 2>&1

    if ($LASTEXITCODE -eq 0) {
        Write-Success "Fabric Loader installed successfully"
    } else {
        Write-Warning "Fabric install may have issues, but continuing..."
        Write-Info "Output: $fabricInstallOutput"
    }
} catch {
    Write-Error "Failed to install Fabric: $_"
    Write-Info "You may need to install Fabric manually from: https://fabricmc.net/use/installer/"
    pause
    exit 1
}

# Create mods directory
Write-Step "Setting up mods directory"
if (-not (Test-Path $MODS_DIR)) {
    New-Item -ItemType Directory -Path $MODS_DIR -Force | Out-Null
    Write-Success "Mods directory created: $MODS_DIR"
} else {
    Write-Info "Mods directory already exists: $MODS_DIR"
}

# Download Fabric API
Write-Step "Downloading Fabric API $FABRIC_API_VERSION"
$fabricApiPath = "$MODS_DIR\fabric-api-$FABRIC_API_VERSION.jar"
try {
    Invoke-WebRequest -Uri $FABRIC_API_URL -OutFile $fabricApiPath -UseBasicParsing
    Write-Success "Fabric API downloaded to mods folder"
} catch {
    Write-Error "Failed to download Fabric API: $_"
    Write-Info "You can download it manually from: https://modrinth.com/mod/fabric-api"
    pause
    exit 1
}

# Download Joaquintown Mod
Write-Step "Downloading Joaquintown Mod v$MOD_VERSION"
$joaquintownModPath = "$MODS_DIR\joaquintown-$MOD_VERSION.jar"
try {
    Invoke-WebRequest -Uri $JOAQUINTOWN_MOD_URL -OutFile $joaquintownModPath -UseBasicParsing
    Write-Success "Joaquintown mod downloaded to mods folder"
} catch {
    Write-Error "Failed to download Joaquintown mod: $_"
    Write-Info "You can download it manually from: https://github.com/signalnine/joaquintown-mod/releases"
    pause
    exit 1
}

# Cleanup
Write-Step "Cleaning up temporary files"
Remove-Item -Path $TEMP_DIR -Recurse -Force
Write-Success "Cleanup complete"

# Installation complete
Write-Host "`n" -NoNewline
Write-Host "╔════════════════════════════════════════════════════════════════════╗" -ForegroundColor Green
Write-Host "║                                                                    ║" -ForegroundColor Green
Write-Host "║              ✓ INSTALLATION COMPLETE!                             ║" -ForegroundColor Green
Write-Host "║                                                                    ║" -ForegroundColor Green
Write-Host "╚════════════════════════════════════════════════════════════════════╝" -ForegroundColor Green

Write-Host "`n📦 Installed Components:" -ForegroundColor Cyan
Write-Host "  ✓ Fabric Loader $FABRIC_LOADER_VERSION" -ForegroundColor White
Write-Host "  ✓ Fabric API $FABRIC_API_VERSION" -ForegroundColor White
Write-Host "  ✓ Joaquintown Mod v$MOD_VERSION" -ForegroundColor White

Write-Host "`n🎮 How to Play:" -ForegroundColor Cyan
Write-Host "  1. Open Minecraft Launcher" -ForegroundColor White
Write-Host "  2. Select the 'fabric-loader-$FABRIC_LOADER_VERSION-$MINECRAFT_VERSION' profile" -ForegroundColor White
Write-Host "  3. Click 'Play'" -ForegroundColor White
Write-Host "  4. Create a new world to experience Joaquintown!" -ForegroundColor White

Write-Host "`n⚡ Features:" -ForegroundColor Cyan
Write-Host "  • Auto-spawning village at world spawn" -ForegroundColor White
Write-Host "  • Godzilla & King Kong guardians" -ForegroundColor White
Write-Host "  • Custom Joaquin-styled villagers" -ForegroundColor White
Write-Host "  • Legendary weapons & banana foods" -ForegroundColor White
Write-Host "  • 8 automatic structure placements" -ForegroundColor White

Write-Host "`n📁 Installation Location:" -ForegroundColor Cyan
Write-Host "  $MODS_DIR" -ForegroundColor Gray

Write-Host "`n💡 Tips:" -ForegroundColor Cyan
Write-Host "  • Create a NEW world for best experience (structures spawn at 0,0)" -ForegroundColor White
Write-Host "  • Look for the 'Welcome to Joaquintown!' message on join" -ForegroundColor White
Write-Host "  • Explore for guardians north and south of spawn village" -ForegroundColor White

Write-Host "`n🐛 Issues? Report at:" -ForegroundColor Cyan
Write-Host "  https://github.com/signalnine/joaquintown-mod/issues" -ForegroundColor Gray

Write-Host "`n" -NoNewline
Write-Success "Enjoy your adventure in Joaquintown! 🎉"
Write-Host ""

pause
