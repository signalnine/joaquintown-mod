# Bella Wolf Texture Guide

Create a custom wolf texture that looks like your dog Bella!

## 🐕 Bella's Features

**Colors:**
- **Body:** Tan/Sandy color
- **Legs:** White (all four legs)
- **Collar:** White all the way around the neck
- **Tail tip:** White
- **Tail:** Extra floofy!

## 🎨 Color Codes

Use these hex codes for accurate colors:

```
Tan Fur:        #C4A570  (sandy tan)
White Fur:      #FFFFFF  (pure white)
Dark Tan:       #A0855B  (for shading/depth)
Light Tan:      #D4B896  (for highlights)
Black Nose:     #000000
Brown Eyes:     #8B4513
Pink Tongue:    #FFA0A0
```

## 📐 Step-by-Step Painting Guide

### Materials Needed:
- Image editor (Paint, GIMP, Photoshop, Paint.NET, etc.)
- Vanilla wolf texture as template (download below)
- Patience and steady hand!

### Step 1: Get the Template

**Download vanilla wolf texture:**
1. Right-click and save: [Wolf Texture](https://minecraft.wiki/images/Wolf_JE4_BE3.png)
2. Save as `wolf_template.png`
3. This is your 64x32 pixel template

### Step 2: Open in Editor

1. Open `wolf_template.png` in your image editor
2. **IMPORTANT:** Make sure you're editing at 100% zoom or higher
3. Turn on pixel grid if available (helps with precision)

### Step 3: Color the Body (Tan)

**Change all gray fur to tan:**

1. **Select all gray fur areas** (avoid legs, collar, tail tip)
2. **Use bucket fill or color replace:**
   - From: Gray (#B3B3B3 or similar)
   - To: Tan (#C4A570)

**Areas to paint TAN:**
- Top and sides of head
- Back of neck (above collar)
- Entire back and sides of body
- Upper tail (except tip)
- Tops of ears

### Step 4: Color the Legs (White)

**All four legs should be pure white:**

**Front Left Leg** (pixels approximately 0,16 to 4,32):
- Fill entire leg with white (#FFFFFF)

**Front Right Leg** (pixels approximately 0,24 to 4,32):
- Fill entire leg with white (#FFFFFF)

**Back Left Leg** (pixels approximately 8,16 to 12,32):
- Fill entire leg with white (#FFFFFF)

**Back Right Leg** (pixels approximately 8,24 to 12,32):
- Fill entire leg with white (#FFFFFF)

### Step 5: Color the Collar (White)

**White collar all the way around the neck:**

**The mane/collar area** (pixels approximately 21,0 to 34,7):
- This is the fluffy chest/neck fur
- Paint the ENTIRE mane white (#FFFFFF)
- This will create the white collar effect

**Also paint white:**
- Bottom of neck on body texture
- All neck fur should be white to create full collar

### Step 6: Color the Tail Tip (White)

**Make the end of the tail white:**

**Tail texture** (pixels approximately 9,18 to 16,25):
- Paint TAN for most of tail (base and middle)
- Paint WHITE for the last 2-3 pixels of tail tip
- Make tail look extra fluffy by adding some pixels around edges

**To make it floofer:**
- Add a few extra white pixels at the very end
- Maybe extend the tail area slightly for extra floof

### Step 7: Face Details

**Eyes:**
- Keep or paint brown (#8B4513)

**Nose:**
- Black (#000000)

**Muzzle/Snout:**
- Could be lighter tan or slightly white
- Bella might have white on her snout?

**Ears:**
- Tan on outside
- Lighter tan or white on inside

### Step 8: Add Depth (Optional)

Make it look more realistic:

**Shading (Dark Tan #A0855B):**
- Under the body
- Around leg joints
- Behind ears
- Base of tail

**Highlights (Light Tan #D4B896):**
- Top of head
- Bridge of nose
- Top of back
- Tops of legs

### Step 9: Save Your Texture

1. **Save as PNG** (not JPG - needs transparency)
2. **Keep 64x32 dimensions** (don't resize!)
3. **Name it:** `wolf.png`
4. **For tamed version:** Save another copy as `wolf_tame.png`

## 📍 Placement

Copy your finished texture to:
```
/Users/gabe/Documents/joaquin_mc/mc-mod/src/main/resources/assets/minecraft/textures/entity/wolf/wolf.png
```

## 🎨 Visual Reference Guide

Here's a text-based layout showing TAN (T) vs WHITE (W):

```
HEAD (top view):
  TTTTTT
  TTTTTT
  TTTTTT

NECK/COLLAR (side view):
  WWWWWW  ← All white collar!
  WWWWWW
  WWWWWW

BODY (side view):
  TTTTTT  ← Tan body
  TTTTTT
  TTTTTT

LEGS (all four):
  WWWW    ← All white legs
  WWWW
  WWWW
  WWWW

TAIL:
  TTTTT   ← Tan base
  TTTTT   ← Tan middle
  TTTWW   ← White tip!
```

## 🖼️ Alternative: Use Online Tool

**Nova Skin Editor** (easiest for beginners):
1. Go to: https://minecraft.novaskin.me/resourcepacks
2. Click "New Resource Pack"
3. Find "Wolf" in entity list
4. Use paint tools to color:
   - Body: Tan
   - Legs: White (all 4)
   - Collar/Mane: White (all around)
   - Tail tip: White
5. Download as resource pack
6. Extract `wolf.png` from the pack

## 🧪 Testing

After creating your texture:

```bash
# Build the mod
./gradlew build

# Run the game
./gradlew runClient

# In-game, spawn a wolf
/summon minecraft:wolf ~ ~ ~

# Or tame one
/give @p bone 64
```

## 🎯 Tips for Best Results

1. **Work at high zoom** - 800% or higher in your editor
2. **Use pixel-perfect tools** - Pencil tool, not brush
3. **Check vanilla texture** - Use it as a guide for shading
4. **Save often!** - Make backup copies
5. **Test in-game** - Colors look different in Minecraft lighting

## 📸 Need Help?

If you get stuck:
- Take a screenshot of your texture
- Describe what doesn't look right
- I can help troubleshoot!

## 🎨 Quick Color Reference

**If using MS Paint color picker:**
- Tan: Red=196, Green=165, Blue=112
- White: Red=255, Green=255, Blue=255
- Dark Tan: Red=160, Green=133, Blue=91

---

**Once you're done, place `wolf.png` in the wolf directory and rebuild the mod!**

All wolves (and especially tamed ones with their collar!) will look like Bella! 🐕
