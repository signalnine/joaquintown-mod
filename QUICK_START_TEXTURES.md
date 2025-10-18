# Quick Start: Creating Your First Joaquin Villager Texture

**Time needed:** 30-40 minutes for your first texture

## The Absolute Easiest Way (Recommended)

### Step 1: Get Blockbench (5 minutes)
1. Go to https://www.blockbench.net/
2. Click "Download" and install for your OS (macOS, Windows, Linux)
3. Open Blockbench

### Step 2: Load a Villager Model (2 minutes)
1. In Blockbench, click: **File → New → Entity Model**
2. In the search box, type: **villager**
3. Select **"Villager"** from the list
4. Click **Create**

You now have a 3D villager model loaded!

### Step 3: Get a Vanilla Texture (3 minutes)

**Option A - Download from online:**
- Go to: https://minecraft.wiki/w/Villager#Textures
- Right-click any villager texture image
- Save as `vanilla_villager.png`

**Option B - Extract from Minecraft (if you have it installed):**
```bash
./extract_vanilla_textures.sh
```

**Option C - Use Blockbench's built-in textures:**
- Blockbench often includes default textures
- Check the "Textures" panel on the right

### Step 4: Import the Texture (1 minute)
1. In Blockbench, click the **"Textures"** tab (right side panel)
2. Click the **+** button or **"Import Texture"**
3. Select your `vanilla_villager.png` file
4. The model should now be textured!

### Step 5: Paint Curly Hair (15 minutes)

1. **Select the Paint tool:**
   - Click the **paintbrush icon** in the toolbar
   - Or press **P** on your keyboard

2. **Find the head overlay layer:**
   - Look at the 3D model
   - The head has TWO layers - we want the outer transparent layer
   - This is the "Head Layer 2" or "hat layer"

3. **Choose your hair color:**
   - Click the color picker
   - Enter hex code: **#2B1810** (dark brown)
   - Or use the color wheel to pick a dark brown

4. **Paint the hair:**
   - **Zoom in** on the head (scroll wheel or View menu)
   - Paint on TOP of the head first:
     ```
     Make small curved strokes like this:
     ╭─╮ ╭─╮ ╭─╮
     ╰─╯ ╰─╯ ╰─╯
     (These are curls!)
     ```
   - Paint the SIDES of the head (around the ears)
   - Paint the BACK of the head

5. **Add curl details:**
   - Use a slightly lighter brown: **#4A3020**
   - Add highlights on the curls
   - Make wavy "S" or "C" shapes

**Tips:**
- Use brush size 1-3 pixels
- Rotate the model to see all angles (right-click and drag)
- Use Ctrl+Z (or Cmd+Z) to undo mistakes
- Paint loosely at first, refine later

### Step 6: Paint Accessories (5 minutes)

1. **Choose frame color:**
   - Pick dark gray: **#333333**
   - Or black: **#000000**

2. **Paint the frames:**
   - Look at the FRONT of the face
   - Paint two rectangles or circles where eyes would be:
     ```
     ┌──┐ ┌──┐  <- Draw these shapes on the face
     │  │ │  │
     └──┘ └──┘
     ```
   - Add a small bridge connecting them in the middle

3. **Add lenses:**
   - Choose light blue: **#B8D8E8**
   - Or very light gray: **#E0E0E0**
   - Fill inside the frames
   - Make it slightly transparent if possible (80% opacity)

4. **Add shine (optional):**
   - Use white: **#FFFFFF**
   - Add one tiny pixel in corner of each lens for reflection

### Step 7: Export Your Texture (1 minute)

1. Click: **File → Export → Export Texture**
2. Choose location: Desktop or Downloads
3. Name it: **joaquin_villager.png**
4. Click **Save**

### Step 8: Add to Your Mod (1 minute)

```bash
# Move the file to your mod's texture directory
cp ~/Desktop/joaquin_villager.png src/main/resources/assets/joaquintown/textures/entity/villager/
```

Or just manually copy the file to:
```
src/main/resources/assets/joaquintown/textures/entity/villager/joaquin_villager.png
```

### Step 9: Test It! (2 minutes)

You'll need to write code to load this texture (we'll do that next), but for now you can view it:

1. Open the PNG file in any image viewer
2. Zoom in to see the details
3. Check if hair and accessories look good

## Visual Guide: Where to Paint

```
Villager Texture Layout (64x64 pixels):
┌────────────────────────────────┬────────────────────────────────┐
│                                │ ← HEAD LAYER 2                 │
│     BASE HEAD                  │   (PAINT HERE!)                │
│     (leave this alone)         │                                │
│                                │   Paint hair on top/sides      │
│     Has the face               │   Paint accessories on front       │
│                                │                                │
├────────────────────────────────┴────────────────────────────────┤
│                                                                  │
│              BODY, ARMS, LEGS                                   │
│              (leave this alone for now)                         │
│                                                                  │
└──────────────────────────────────────────────────────────────────┘
       Left half (0-32)                Right half (32-64)
```

## Example: Super Simple Version (10 minutes)

Want to make a SUPER basic version just to test?

1. Open Blockbench
2. Load villager model
3. Use Paint tool
4. Paint the ENTIRE top right section (#2B1810 brown) - just solid color
5. Paint two small dark gray squares on the face (#333333)
6. Export as `joaquin_villager.png`

This gives you a villager with brown "hair" and basic "accessories" to test the system!

## Common Issues & Solutions

**Problem:** "I can't see what I'm painting!"
- **Solution:** Make sure you're painting on the HEAD LAYER 2 (outer layer)
- Try changing the brush size to be bigger (3-5 pixels)

**Problem:** "The texture looks pixelated/blurry"
- **Solution:** That's normal! Minecraft is pixelated. Make sure you're working at 100% or 200% zoom

**Problem:** "I painted on the wrong area"
- **Solution:** Press Ctrl+Z (Cmd+Z on Mac) to undo
- Or use the eraser tool to remove paint

**Problem:** "I don't have artistic skills"
- **Solution:** Start VERY simple - just solid colors at first
- Make a brown square for hair, two gray squares for accessories
- You can always refine it later!

**Problem:** "The colors don't match"
- **Solution:** Use these exact hex codes:
  - Hair: #2B1810
  - Accessories: #333333
  - Lenses: #B8D8E8

## Next Steps

Once you have `joaquin_villager.png` created:

1. ✅ Place it in `src/main/resources/assets/joaquintown/textures/entity/villager/`
2. ⏭️ Write code to load and apply this texture (I'll help with this)
3. 🧪 Test in game with `./gradlew runClient`
4. 🎨 Iterate and improve based on how it looks

## Resources

- **Blockbench:** https://www.blockbench.net/
- **Minecraft Wiki (Villager):** https://minecraft.wiki/w/Villager
- **Color Picker:** https://www.google.com/search?q=color+picker
- **Pixel Art Basics:** Search YouTube for "pixel art tutorial for beginners"

## Questions?

If you get stuck, just ask! Common questions:
- "How do I make it more curly?" → Use more C-shapes and S-shapes
- "How do I make better accessories?" → Look at reference images of accessories
- "Can I use different colors?" → Yes! But stay in the brown/black range for hair

---

**Remember:** Your first texture will probably look rough - that's totally normal! The key is to START, test it, and then improve it. Don't aim for perfection on version 1!
