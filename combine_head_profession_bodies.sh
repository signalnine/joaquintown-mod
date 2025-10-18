#!/bin/bash

# Script to combine custom Joaquin head with vanilla profession bodies
# This creates profession-specific textures with your custom head

set -e

echo "=== Combining Joaquin Head with Profession Bodies ==="
echo ""

# Paths
CUSTOM_HEAD="./src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png"
OUTPUT_DIR="./src/main/resources/assets/minecraft/textures/entity/villager/profession"

# Check if custom head exists
if [ ! -f "$CUSTOM_HEAD" ]; then
    echo "ERROR: Custom head texture not found at: $CUSTOM_HEAD"
    exit 1
fi

echo "Using custom head from: $CUSTOM_HEAD"
echo ""

# Create output directory
mkdir -p "$OUTPUT_DIR"

# List of professions to process
PROFESSIONS=(
    "farmer"
    "fisherman"
    "shepherd"
    "fletcher"
    "librarian"
    "cartographer"
    "cleric"
    "armorer"
    "weaponsmith"
    "toolsmith"
    "butcher"
    "leatherworker"
    "mason"
    "nitwit"
)

echo "This script will extract vanilla profession textures and combine them"
echo "with your custom Joaquin head (curly hair & glasses)."
echo ""
echo "Requirements:"
echo "  - ImageMagick installed (brew install imagemagick)"
echo "  - Minecraft 1.21.1 installed"
echo ""

# Check if ImageMagick is installed
if ! command -v convert &> /dev/null; then
    echo "ERROR: ImageMagick not found. Please install it:"
    echo "  macOS: brew install imagemagick"
    echo "  Linux: sudo apt-get install imagemagick"
    echo "  Windows: Download from https://imagemagick.org/"
    exit 1
fi

echo "✅ ImageMagick found"
echo ""

# Detect Minecraft directory
if [[ "$OSTYPE" == "darwin"* ]]; then
    MC_DIR="$HOME/Library/Application Support/minecraft"
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    MC_DIR="$HOME/.minecraft"
else
    MC_DIR="$APPDATA/.minecraft"
fi

VERSION_JAR="$MC_DIR/versions/1.21.1/1.21.1.jar"

if [ ! -f "$VERSION_JAR" ]; then
    echo "ERROR: Minecraft 1.21.1 not found. Please run Minecraft 1.21.1 first."
    exit 1
fi

echo "✅ Found Minecraft jar"
echo ""

# Create temp directory
TEMP_DIR="./temp_profession_textures"
mkdir -p "$TEMP_DIR"

echo "Extracting vanilla profession textures..."
unzip -q "$VERSION_JAR" "assets/minecraft/textures/entity/villager/profession/*" -d "$TEMP_DIR" 2>/dev/null || true

if [ ! -d "$TEMP_DIR/assets/minecraft/textures/entity/villager/profession" ]; then
    echo "ERROR: Could not extract profession textures"
    rm -rf "$TEMP_DIR"
    exit 1
fi

echo "✅ Extracted vanilla profession textures"
echo ""

# Process each profession
for PROFESSION in "${PROFESSIONS[@]}"; do
    VANILLA_TEXTURE="$TEMP_DIR/assets/minecraft/textures/entity/villager/profession/${PROFESSION}.png"
    OUTPUT_TEXTURE="$OUTPUT_DIR/${PROFESSION}.png"

    if [ -f "$VANILLA_TEXTURE" ]; then
        echo "Processing: $PROFESSION"

        # Step 1: Copy vanilla texture as base
        cp "$VANILLA_TEXTURE" "$OUTPUT_TEXTURE"

        # Step 2: Extract head portion from custom texture (top-left 32x16 pixels)
        convert "$CUSTOM_HEAD" -crop 32x16+0+0 "$TEMP_DIR/custom_head.png"

        # Step 3: Extract head layer 2 from custom texture (top-right 32x16 pixels)
        convert "$CUSTOM_HEAD" -crop 32x16+32+0 "$TEMP_DIR/custom_head_layer2.png"

        # Step 4: Overlay custom head onto profession texture
        convert "$OUTPUT_TEXTURE" \
                "$TEMP_DIR/custom_head.png" -geometry +0+0 -composite \
                "$TEMP_DIR/custom_head_layer2.png" -geometry +32+0 -composite \
                "$OUTPUT_TEXTURE"

        echo "  ✅ Created: $PROFESSION.png"
    else
        echo "  ⚠️  Skipped: $PROFESSION (texture not found)"
    fi
done

# Cleanup
rm -rf "$TEMP_DIR"

echo ""
echo "=== Complete! ==="
echo ""
echo "Created profession-specific textures in:"
echo "  $OUTPUT_DIR"
echo ""
echo "Each profession now has:"
echo "  ✅ Your custom Joaquin head (curly hair, glasses, flat nose)"
echo "  ✅ Vanilla profession-specific body (robes, tools, etc.)"
echo ""
echo "Next steps:"
echo "  1. Rebuild: ./gradlew build"
echo "  2. Test: ./gradlew runClient"
echo "  3. Check different villager professions in-game!"
echo ""
