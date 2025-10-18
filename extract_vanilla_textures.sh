#!/bin/bash

# Script to extract vanilla villager textures from Minecraft
# This creates a reference directory with vanilla textures to modify

set -e

echo "=== Extracting Vanilla Minecraft Villager Textures ==="
echo ""

# Detect OS
if [[ "$OSTYPE" == "darwin"* ]]; then
    # macOS
    MC_DIR="$HOME/Library/Application Support/minecraft"
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Linux
    MC_DIR="$HOME/.minecraft"
else
    # Windows (Git Bash or WSL)
    MC_DIR="$APPDATA/.minecraft"
fi

# Check if Minecraft directory exists
if [ ! -d "$MC_DIR" ]; then
    echo "ERROR: Minecraft directory not found at: $MC_DIR"
    echo "Please install Minecraft first, or adjust MC_DIR in this script."
    exit 1
fi

echo "Minecraft directory: $MC_DIR"

# Find the version jar
VERSION_JAR="$MC_DIR/versions/1.21.1/1.21.1.jar"

if [ ! -f "$VERSION_JAR" ]; then
    echo "ERROR: Minecraft 1.21.1 jar not found at: $VERSION_JAR"
    echo "Please run Minecraft 1.21.1 at least once to download the files."
    exit 1
fi

echo "Found version jar: $VERSION_JAR"
echo ""

# Create reference directory
REFERENCE_DIR="./texture_references"
mkdir -p "$REFERENCE_DIR/villager"

echo "Extracting textures to: $REFERENCE_DIR"
echo ""

# Extract using unzip (should work on macOS/Linux, on Windows use 7zip or similar)
if command -v unzip &> /dev/null; then
    # Extract all villager textures
    unzip -q "$VERSION_JAR" "assets/minecraft/textures/entity/villager/*" -d "$REFERENCE_DIR/temp" 2>/dev/null || true

    # Move to cleaner directory structure
    if [ -d "$REFERENCE_DIR/temp/assets/minecraft/textures/entity/villager" ]; then
        cp -r "$REFERENCE_DIR/temp/assets/minecraft/textures/entity/villager/"* "$REFERENCE_DIR/villager/"
        rm -rf "$REFERENCE_DIR/temp"
        echo "✅ Successfully extracted villager textures!"
        echo ""
        echo "Extracted files:"
        ls -la "$REFERENCE_DIR/villager/"
        echo ""
        echo "📁 Reference textures are in: $REFERENCE_DIR/villager/"
        echo ""
        echo "Next steps:"
        echo "1. Open these PNG files in your image editor (Blockbench, Paint.NET, GIMP)"
        echo "2. Modify them to add curly hair and glasses"
        echo "3. Save your custom version as: src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png"
        echo ""
        echo "See TEXTURE_GUIDE.md for detailed instructions!"
    else
        echo "ERROR: Could not find villager textures in jar file."
        exit 1
    fi
else
    echo "ERROR: 'unzip' command not found."
    echo "Please install unzip or manually extract textures from:"
    echo "$VERSION_JAR"
    echo ""
    echo "Look for: assets/minecraft/textures/entity/villager/*.png"
    exit 1
fi

# Create a quick reference guide
cat > "$REFERENCE_DIR/REFERENCE.md" << 'EOF'
# Vanilla Texture References

These are the original Minecraft villager textures extracted from version 1.21.1.

## Files Extracted

- `villager.png` - Base villager texture (use this as your starting point!)
- `professions/*.png` - Profession overlays
- `type/*.png` - Biome variants

## How to Use These

1. **Option A - Start from scratch:**
   - Open `villager.png` in Blockbench
   - Use it as a reference for the layout
   - Create your own Joaquin version

2. **Option B - Modify existing:**
   - Copy `villager.png` to your mod's texture directory
   - Open it in your image editor
   - Add curly hair to the "head layer 2" area (top right)
   - Add glasses to the face area
   - Save as `joaquin_villager.png`

## Texture Layout (64x64 pixels)

```
┌─────────────────┬─────────────────┐
│   Base Head     │  Head Layer 2   │  <- Add hair & glasses here!
│   (0,0-32,16)   │  (32,0-64,16)   │
├─────────────────┴─────────────────┤
│        Body & Arms & Legs          │
│         (rest of texture)          │
└────────────────────────────────────┘
```

The **Head Layer 2** (top right quarter) is rendered ON TOP of the base head.
This is perfect for adding hair and glasses without changing the face!

## Key Areas

- **Head Layer 2 Top (32,0 to 64,8):** Hair on top of head
- **Head Layer 2 Front (40,8 to 48,16):** Glasses on face
- **Head Layer 2 Sides:** Hair on sides

## Colors to Consider

**Joaquin's curly hair:**
- Dark brown: #2B1810
- Medium brown: #4A3020

**Glasses:**
- Frames: #333333 (dark gray)
- Lenses: #B8D8E8 (light blue, make semi-transparent)

Good luck with your textures!
EOF

echo "📄 Created reference guide: $REFERENCE_DIR/REFERENCE.md"
echo ""
echo "✨ All done! Happy texturing!"
