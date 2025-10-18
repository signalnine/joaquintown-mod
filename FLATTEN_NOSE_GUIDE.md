# How to Flatten/Remove the Villager Nose

The large villager nose is part of the base texture. Here's how to flatten or remove it.

## Understanding the Nose Location

On a villager texture (64x64 pixels), the nose is located on the **HEAD section** (top left area):
- **X position:** Approximately pixels 20-24 (center of the front face)
- **Y position:** Approximately pixels 10-14 (middle of face, below eyes)
- **Color:** Usually gray/brown (#786C58 or similar)

## Method 1: Using Blockbench (Easiest)

### Step 1: Open Your Texture
1. Open Blockbench
2. File → New → Entity Model → Villager
3. Click "Textures" tab (right panel)
4. Import your `joaquin_villager.png` texture

### Step 2: Find the Nose
1. Click the **Paint** tool (or press **P**)
2. Look at the **front of the villager's face** on the 3D model
3. The nose sticks out - that's what we're flattening!
4. Zoom in on the 2D texture (left panel) to see the nose pixels

### Step 3: Paint Over the Nose
1. **Pick the skin color:**
   - Use the **Color Picker** tool (eyedropper icon)
   - Click on the forehead or cheek area (skin-tone pixels)
   - This should be something like `#C59978` or `#D4A58A` (peachy/tan)

2. **Paint over the nose:**
   - Select brush size: **2-3 pixels**
   - Paint over the gray/brown nose pixels
   - Cover the center protruding part completely
   - Blend it to match the surrounding skin

3. **Check the 3D model:**
   - The nose should now appear flat!
   - Rotate the model to verify it looks good from all angles

### Step 4: Add a Small Nose (Optional)
If you want a subtle nose instead of completely flat:
1. Use a slightly darker skin tone (`#B08968`)
2. Paint just **2-3 pixels** in the center where the nose was
3. Make it vertical, not protruding
4. This gives a hint of a nose without the big bump

### Step 5: Export
1. File → Export → Export Texture
2. Save as `joaquin_villager.png`
3. Replace the existing file in your mod

## Method 2: Using Paint.NET or GIMP

### Step 1: Open the Texture
```bash
open src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png
```

### Step 2: Locate the Nose
1. **Zoom in to 800%** (very important!)
2. Look at the **top-left quarter** of the texture (the base head)
3. The nose is in the **CENTER** of the front face:
   ```
   Texture layout (64x64):
   ┌────────────────┬────────────────┐
   │   BASE HEAD    │  HEAD LAYER 2  │
   │                │  (hair/accessories)│
   │   ┌─────┐     │                │
   │   │ O O │  👈 Eyes            │
   │   │  👃 │  👈 NOSE HERE       │
   │   │  👄 │  👈 Mouth           │
   │   └─────┘     │                │
   └────────────────┴────────────────┘
        0-32px          32-64px
   ```

### Step 3: Identify Nose Pixels
The nose is typically:
- **Gray/brown color:** `#786C58`, `#6B5C4F`, or similar
- **Location:** Approximately at pixels (21-23, 11-13) from top-left
- **Shape:** Vertical rectangular shape, 2-3 pixels wide, 3-4 pixels tall

### Step 4: Replace with Skin Color
1. **Use the Color Picker tool** (eyedropper)
2. Click on the **forehead or cheek** area to get the skin color
3. **Select the Pencil tool** (size: 1 pixel)
4. **Paint over the nose pixels** with the skin color
5. Cover the entire nose area to flatten it

### Step 5: Optional - Add Subtle Nose
1. Use a color **slightly darker** than the skin
2. Paint just **2 vertical pixels** in the center
3. This gives a subtle nose suggestion

### Step 6: Save
1. Save the file (keep PNG format)
2. Make sure it's still 64x64 pixels

## Visual Guide

### Before (Big Nose):
```
Front of face:
┌──────┐
│ O  O │  ← Eyes
│  👃  │  ← Big protruding nose (gray/brown)
│   -  │  ← Mouth
└──────┘
```

### After (Flat/Small Nose):
```
Front of face:
┌──────┐
│ O  O │  ← Eyes
│  ·   │  ← Flat or small nose (skin-tone with 1-2 darker pixels)
│   -  │  ← Mouth
└──────┘
```

## Color Reference

**Typical nose colors to REPLACE:**
- `#786C58` (gray-brown)
- `#6B5C4F` (dark brown)
- `#8B7355` (medium brown)

**Skin colors to USE instead:**
- `#C59978` (light tan)
- `#D4A58A` (peach)
- `#BC8F6F` (medium tan)

**Optional subtle nose color:**
- `#B08968` (darker tan - use just 2-3 pixels)

## After Editing

### Update Your Mod:
```bash
# Copy your edited texture to both locations:
cp path/to/edited/joaquin_villager.png \
   src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png

cp path/to/edited/joaquin_villager.png \
   src/main/resources/assets/minecraft/textures/entity/villager/villager.png
```

### Rebuild and Test:
```bash
./gradlew build
./gradlew runClient
```

### In Game:
- Summon a villager: `/summon minecraft:villager ~ ~ ~`
- Check if the nose is flat/smaller!

## Tips

1. **Zoom in!** Work at 400-800% zoom to see individual pixels clearly
2. **Use the right color:** Match the surrounding skin tone exactly
3. **Check 3D view:** In Blockbench, verify it looks good on the model
4. **Save often:** Keep backup copies in case you want to revert
5. **Test in game:** The in-game appearance is what matters most

## Troubleshooting

**Problem:** Can't find the nose pixels
- **Solution:** Look at X=20-24, Y=10-14 from top-left of the HEAD section (not head layer 2)

**Problem:** Made it too flat, looks weird
- **Solution:** Add back 2-3 darker pixels vertically in the center for a subtle nose

**Problem:** Color doesn't match
- **Solution:** Use the eyedropper tool to pick the exact skin color from nearby pixels

## Quick Edit Summary

1. Open `joaquin_villager.png` in Blockbench or image editor
2. Zoom in to 800%
3. Find the nose (center of face, gray/brown pixels)
4. Color-pick the skin tone from the forehead
5. Paint over the nose with skin color
6. Optional: Add 2-3 darker pixels for subtle nose
7. Save and export
8. Copy to both texture locations
9. Rebuild: `./gradlew build`
10. Test: `./gradlew runClient`

Done! Your villagers will now have flat or smaller noses.
