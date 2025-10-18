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
   - Add accessories to the face area
   - Save as `joaquin_villager.png`

## Texture Layout (64x64 pixels)

```
┌─────────────────┬─────────────────┐
│   Base Head     │  Head Layer 2   │  <- Add hair & accessories here!
│   (0,0-32,16)   │  (32,0-64,16)   │
├─────────────────┴─────────────────┤
│        Body & Arms & Legs          │
│         (rest of texture)          │
└────────────────────────────────────┘
```

The **Head Layer 2** (top right quarter) is rendered ON TOP of the base head.
This is perfect for adding hair and accessories without changing the face!

## Key Areas

- **Head Layer 2 Top (32,0 to 64,8):** Hair on top of head
- **Head Layer 2 Front (40,8 to 48,16):** Accessories on face
- **Head Layer 2 Sides:** Hair on sides

## Colors to Consider

**Joaquin's curly hair:**
- Dark brown: #2B1810
- Medium brown: #4A3020

**Accessories:**
- Frames: #333333 (dark gray)
- Lenses: #B8D8E8 (light blue, make semi-transparent)

Good luck with your textures!
