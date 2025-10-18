# Quick Fix: Remove Villager Nose (5 Minutes)

## Fastest Method - Blockbench

### 1. Open Blockbench (https://www.blockbench.net/)
   - File → New → Entity Model → Villager → Create

### 2. Load Your Texture
   - Textures tab (right side) → Import
   - Select: `src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png`

### 3. Select Paint Tool
   - Click paintbrush icon (or press **P**)

### 4. Pick Skin Color
   - Click the **eyedropper tool**
   - Click on the **forehead** or **cheek** of the villager face (on the 3D model)
   - This picks the tan/peach skin color

### 5. Paint Over the Nose
   - Look at the **front of the face** on the 3D model
   - You'll see the nose sticking out
   - **Click and drag** over the nose on the 3D model
   - Paint until the nose is covered with skin color
   - The nose should now look flat!

### 6. Export
   - File → Export → Export Texture
   - Save to Desktop as `joaquin_villager_flat.png`

### 7. Copy to Your Mod
```bash
# Copy the edited version
cp ~/Desktop/joaquin_villager_flat.png \
   src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png

# Update the override
cp ~/Desktop/joaquin_villager_flat.png \
   src/main/resources/assets/minecraft/textures/entity/villager/villager.png
```

### 8. Rebuild and Test
```bash
./gradlew build
./gradlew runClient
```

### 9. Verify In-Game
   - Type: `/summon minecraft:villager ~ ~ ~`
   - Check if nose is flat!

---

## Even Faster: Exact Pixel Locations

If you want to edit in an image editor, here are the exact pixels to change:

**Nose Location:** Base head section (top-left quarter of 64x64 texture)
- **X coordinates:** 20, 21, 22, 23 (from left)
- **Y coordinates:** 10, 11, 12, 13 (from top)

**What to do:**
1. Open texture in any image editor
2. Zoom to 800%
3. Select pixels at (20-23, 10-13)
4. Fill with skin color (use eyedropper on nearby pixels)
5. Save

**Colors:**
- Replace: `#786C58` or `#6B5C4F` (nose gray/brown)
- With: `#C59978` or `#D4A58A` (skin peach/tan)

---

## Result

Before: 👃 (big nose)
After:  👔 (flat face or subtle nose)

Your villagers will look more human-like without the prominent villager nose!
