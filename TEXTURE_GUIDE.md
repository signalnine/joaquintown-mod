# Texture Creation Guide for Joaquintown Mod

This guide will help you create custom villager textures with curly hair and accessories for the Joaquintown mod.

## Understanding Minecraft Textures

Minecraft textures are **PNG image files** that wrap around 3D models. Villager textures are typically:
- **64x64 pixels** (width x height)
- PNG format with transparency support
- Organized in a specific layout that maps to body parts

## Tools You'll Need

### Option 1: Blockbench (Recommended for Beginners)
**Best for:** Complete beginners, visual texture editing, seeing results in 3D

- **Download:** https://www.blockbench.net/
- **Free:** Yes
- **Features:**
  - Built-in Minecraft model viewer
  - Paint directly on 3D models
  - See your texture in real-time
  - Export textures directly

**Pros:** See exactly where pixels go on the model
**Cons:** Less precise for detailed pixel art

### Option 2: Paint.NET (Windows)
**Best for:** Detailed pixel art, layer-based editing

- **Download:** https://www.getpaint.net/
- **Free:** Yes
- **Features:**
  - Layers support
  - Selection tools
  - Clone stamp (great for copying vanilla textures)
  - Color picker

**Pros:** Professional tools, layers, precise editing
**Cons:** Need to understand texture layout

### Option 3: GIMP (Cross-platform)
**Best for:** Advanced users, maximum control

- **Download:** https://www.gimp.org/
- **Free:** Yes
- **Features:**
  - Full Photoshop-like capabilities
  - Advanced layer management
  - Filters and effects

**Pros:** Most powerful, professional grade
**Cons:** Steeper learning curve

### Option 4: Aseprite (Paid, but Excellent)
**Best for:** Pixel art enthusiasts

- **Website:** https://www.aseprite.org/
- **Cost:** $20 (or compile free from source)
- **Features:**
  - Designed specifically for pixel art
  - Animation support
  - Excellent for small textures

## Villager Texture Layout

Minecraft villager textures use this layout (64x64 pixels):

```
┌────────────────────────────────────────────────────┐
│                     HEAD                           │ (0,0) to (32,16)
│  Front   Right   Back    Left    Top    Bottom    │
├────────────────────────────────────────────────────┤
│                   HEAD LAYER 2                     │ (32,0) to (64,16)
│  (Overlay for hat/hair/accessories)                   │
├────────────────────────────────────────────────────┤
│                     BODY                           │ (16,16) to (40,32)
│            (Robe/profession clothing)              │
├────────────────────────────────────────────────────┤
│     RIGHT ARM        LEFT ARM                      │ (40,16) to (56,32)
│                                                     │
├────────────────────────────────────────────────────┤
│     RIGHT LEG        LEFT LEG                      │ (0,16) to (16,32)
│                                                     │
└────────────────────────────────────────────────────┘
```

**Key Areas for Joaquin Villagers:**
- **Head Layer 2 (top right):** This is where we add curly hair and accessories!
- **Head (top left):** The base face
- **Body:** Keeps profession clothing

## Step-by-Step: Creating Joaquin Villager Textures

### Method 1: Using Blockbench (Easiest)

#### Step 1: Download Blockbench
1. Go to https://www.blockbench.net/
2. Download and install for your OS
3. Open Blockbench

#### Step 2: Load Vanilla Villager
1. File → New → Minecraft Entity Model
2. Search for "villager" in the preset models
3. Load the villager model

#### Step 3: Import Vanilla Texture
1. Get vanilla texture from `.minecraft/versions/1.21.1/1.21.1.jar`
2. Extract: `assets/minecraft/textures/entity/villager/villager.png`
3. In Blockbench: Click "Texture" tab → Import texture
4. Load the vanilla villager.png

#### Step 4: Paint Your Custom Features
1. Click "Paint" tool in toolbar
2. Select the head layer 2 area (you'll see it on the 3D model)
3. Draw curly hair:
   - Use dark brown (#3D2817 or #2B1810)
   - Add wavy patterns on top and sides
   - Make it look curly with rounded shapes
4. Draw accessories:
   - Use dark gray (#333333) for frames
   - Use light blue/white (#E0F0FF) for lenses (semi-transparent)
   - Draw rectangular or round frames on the face

#### Step 5: Export Texture
1. File → Export → Export Texture
2. Save as `joaquin_villager.png`
3. Move to your mod's texture folder

### Method 2: Using Paint.NET or GIMP

#### Step 1: Extract Vanilla Texture
You need the base villager texture to work from:

**On macOS/Linux:**
```bash
# Navigate to Minecraft directory
cd ~/Library/Application\ Support/minecraft/versions/1.21.1/

# Copy the version jar
cp 1.21.1.jar 1.21.1.zip

# Extract it
unzip 1.21.1.zip -d extracted/

# Find the villager texture
find extracted/ -name "villager.png" -path "*/entity/villager/*"
```

**On Windows:**
```
1. Go to %appdata%\.minecraft\versions\1.21.1\
2. Copy 1.21.1.jar and rename to 1.21.1.zip
3. Extract with WinRAR or 7-Zip
4. Navigate to assets/minecraft/textures/entity/villager/
5. Copy villager.png
```

#### Step 2: Open in Paint.NET/GIMP
1. Open the vanilla villager.png
2. Create a new layer (Layer → Add New Layer)
3. Name it "Curly Hair" or "Accessories"

#### Step 3: Draw Curly Hair
1. Zoom in to 800% (View → Zoom)
2. Select the head layer 2 area (top right section)
3. Use pencil tool (size: 1-2 pixels)
4. Draw curly hair pattern:
   ```
   Color palette:
   - Dark brown: #2B1810 (hair base)
   - Medium brown: #4A3020 (highlights)
   - Light brown: #6B4A32 (shine)
   ```
5. Add wavy/curly patterns:
   - Draw small C-shapes and S-shapes
   - Layer them to create volume
   - Cover the top and sides of the head

#### Step 4: Draw Accessories
1. Create another layer for accessories
2. Position over the face area
3. Draw frames:
   ```
   Accessories style options:

   Option A - Round accessories:
   ○ ○  (Use dark gray #333333)

   Option B - Square accessories:
   ▢ ▢  (Use dark gray #333333)
   ```
4. Add lens color:
   - Use very light blue (#E0F0FF)
   - Set layer opacity to 30-50% for transparency
5. Add bridge connecting the two lenses

#### Step 5: Save Your Texture
1. Flatten all layers (Layer → Flatten)
2. Save As → PNG
3. Name it `joaquin_villager.png`
4. Make sure it's still 64x64 pixels

## Texture Templates

Here's a simple curly hair pattern you can follow:

```
Top view of head (pixel art):
┌────────────────┐
│  ╭╮╭╮╭╮╭╮╭╮  │  ← Curly hair top
│ ╭╯╰╯╰╯╰╯╰╮   │
│ │  FACE  │   │  ← Leave face visible
│ ╰╮      ╭╯   │
│  ╰──────╯    │  ← Hair sides
└────────────────┘
```

Front view accessories:
```
┌────────────────┐
│   ┌──┐ ┌──┐   │  ← Accessories frames
│   │  │ │  │   │  ← Lenses (light blue)
│   └──┘ └──┘   │
│      ──       │  ← Bridge
└────────────────┘
```

## Color Palette Suggestions

### Joaquin's Features
**Hair Colors:**
- Very dark brown: `#1A0F0A`
- Dark brown: `#2B1810`
- Medium brown: `#4A3020`
- Highlight: `#6B4A32`

**Accessories:**
- Frame: `#1A1A1A` (black) or `#333333` (dark gray)
- Lens: `#B8D8E8` (light blue, semi-transparent)
- Reflection: `#FFFFFF` (white, small highlight)

**Skin Tone (if customizing):**
- Light: `#F2D3BC`
- Medium: `#D4A58A`
- Tan: `#BC8F6F`

## Testing Your Texture

### In Blockbench:
1. Just load it on the model and you can see it in 3D!

### In Game:
1. Place your texture file in the correct directory (see below)
2. Run `./gradlew runClient`
3. Create a world
4. Use `/summon minecraft:villager` to spawn a villager
5. Check if your texture appears

## File Structure for Your Mod

Place your finished textures here:
```
src/main/resources/assets/joaquintown/textures/entity/villager/
├── joaquin_villager.png          # Default villager
├── professions/
│   ├── farmer.png                # Optional: custom per profession
│   ├── librarian.png
│   ├── cleric.png
│   └── ...
└── type/
    ├── plains.png                # Optional: custom per biome
    ├── desert.png
    └── ...
```

**Note:** If you only create `joaquin_villager.png`, all villagers will use it. If you want profession-specific variations, you'll need to create texture override code.

## Quick Start Recipe

**If you just want to get started quickly:**

1. **Download Blockbench** (5 minutes)
2. **Extract vanilla villager texture** (5 minutes)
3. **Open villager model in Blockbench** (2 minutes)
4. **Paint curly hair on head layer** - use brown, make wavy C-shapes (15 minutes)
5. **Paint accessories on face** - two dark rectangles with light blue fill (5 minutes)
6. **Export texture** (1 minute)
7. **Place in mod directory** (1 minute)
8. **Test in game** with `./gradlew runClient` (2 minutes)

**Total time:** About 30-40 minutes for your first texture!

## Resources & Tutorials

### Video Tutorials:
- **Blockbench Basics:** Search YouTube for "Blockbench texture painting tutorial"
- **Minecraft Texturing:** "How to make Minecraft mob textures"

### Reference Images:
Look at existing Minecraft textures for inspiration:
- Iron Golem texture (for inspiration on overlay features)
- Player skin format (similar to villager)
- Mod examples: Search "Minecraft villager retexture" on Planet Minecraft

### Texture Packs to Study:
Download popular texture packs and examine their villager textures:
- Faithful
- Mizuno's 16 Craft
- Jolicraft

## Common Mistakes to Avoid

1. **Wrong size:** Must be 64x64 pixels exactly
2. **Wrong format:** Must be PNG (not JPG)
3. **No transparency:** Make sure transparent areas stay transparent
4. **Painting on wrong area:** Use head layer 2 for hair/accessories, not base head
5. **Colors too bright:** Minecraft uses muted colors, avoid pure #FF0000 red, etc.
6. **Not zoomed in:** Always zoom to 400-800% when editing pixels
7. **Forgetting to flatten layers:** Flatten before saving final PNG

## Next Steps After Creating Textures

Once you have your `joaquin_villager.png` file:

1. **Place it in the mod:**
   ```
   src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png
   ```

2. **Update code to use it** (I can help with this)

3. **Test in game:**
   ```bash
   ./gradlew runClient
   ```

4. **Iterate:** Keep improving the texture based on how it looks in game!

## Getting Help

If you get stuck:
1. **Blockbench has built-in help:** Click the "?" icon
2. **r/Blockbench on Reddit:** Community support
3. **Planet Minecraft forums:** Texture creation help
4. **Ask me:** I can help troubleshoot or explain any step

## Example: Step-by-Step First Texture (10 minutes)

Let's make a super simple version:

1. Open Blockbench
2. File → New → Minecraft Entity Model → Villager
3. Click "Paint" tool
4. Select dark brown color (#2B1810)
5. Paint the TOP of the head layer 2 area (top right of texture)
   - Just paint it solid brown for now (curly details later)
6. Select dark gray (#333333)
7. Paint two small rectangles on the face area (accessories frames)
8. File → Export → Export Texture
9. Save as joaquin_villager.png
10. Done! You have a basic texture!

Now test it in game and see what needs improvement.

---

**Remember:** Your first texture doesn't need to be perfect! Make a simple version, test it in game, then iterate and improve it. Texture creation is an iterative process!
