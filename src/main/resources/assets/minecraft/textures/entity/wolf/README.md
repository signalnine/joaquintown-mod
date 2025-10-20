# Wolf Textures - Custom Bella Skin

This directory contains custom wolf textures to make wolves look like your family dog Bella.

## 📁 Required Files

Place your custom wolf texture files here:

### Main Textures:
- `wolf.png` - Wild/untamed wolf texture
- `wolf_tame.png` - Tamed wolf texture (with collar)
- `wolf_angry.png` - Angry/hostile wolf texture

### Optional Textures (if you want variants):
- `wolf_wet.png` - Wet wolf texture
- `wolf_collar.png` - Just the collar overlay (optional)

## 📐 Texture Format

**Dimensions:** 64x32 pixels (standard Minecraft wolf texture)

**File Format:** PNG with transparency support

## 🎨 How to Create Bella's Texture

### Option 1: Use a Photo Editor

1. **Find a reference image** of Bella or a similar dog
2. **Use an image editor** (Photoshop, GIMP, Paint.NET, etc.)
3. **Download vanilla wolf texture** as a template:
   - From: https://minecraft.wiki/w/Wolf#Textures
   - Or extract from Minecraft JAR
4. **Modify the texture** to match Bella's coloring:
   - Change fur colors
   - Modify patterns
   - Adjust face markings
   - Keep the same UV mapping layout

### Option 2: Use a Minecraft Skin Editor

1. **Use an online tool:**
   - Nova Skin Editor: https://minecraft.novaskin.me/
   - Planet Minecraft: https://www.planetminecraft.com/texture-pack/

2. **Import vanilla wolf texture**
3. **Paint Bella's colors** onto the template
4. **Export as PNG**

### Option 3: Use AI Tools

1. **Take a photo of Bella**
2. **Use an AI texture generator** to create Minecraft-style texture
3. **Refine manually** to fit the wolf UV map

## 🗺️ Wolf Texture UV Map

The wolf texture is laid out like this:

```
Head:        Pixels 0,0  to 23,15  (front, top, bottom, sides)
Body:        Pixels 18,14 to 44,30 (torso)
Legs:        Pixels 0,16 to 16,32  (all four legs)
Tail:        Pixels 9,18 to 16,25  (tail)
Mane:        Pixels 21,0  to 34,7  (fluffy neck/chest)
```

## 🐕 Tips for Bella's Texture

Think about Bella's features:
- **Fur Color**: What's her primary color? (brown, black, white, golden, etc.)
- **Markings**: Does she have spots, patches, or unique markings?
- **Face**: Eye color, nose color, mouth/snout details
- **Ears**: Shape and color of ears
- **Paws**: Are they a different color than the body?
- **Tail**: Solid color or patterned?

## 🔧 Quick Start Template

I can help you create a basic texture! Tell me about Bella:

1. **What breed/type is she?** (e.g., Golden Retriever, German Shepherd, mixed)
2. **Primary fur color?** (e.g., golden brown, black, white)
3. **Any distinctive markings?** (e.g., white chest, black ears, spotted)
4. **What color are her eyes?** (brown, blue, amber, etc.)

## 📝 Current Status

**Installed textures:**
- [ ] wolf.png (not yet added)
- [ ] wolf_tame.png (not yet added)
- [ ] wolf_angry.png (not yet added)

## 🎮 Testing Your Texture

After adding your texture file:

1. Rebuild the mod: `./gradlew build`
2. Run the client: `./gradlew runClient`
3. Spawn a wolf: `/summon minecraft:wolf ~ ~ ~`
4. Look at your custom Bella wolf!

## 📸 Resources

- **Vanilla Wolf Texture**: [Minecraft Wiki](https://minecraft.wiki/w/Wolf#Textures)
- **Texture Guides**: [Planet Minecraft Tutorials](https://www.planetminecraft.com/forums/communities/texturing)
- **Color Picker**: Use a color picker tool on Bella's photo to get exact colors
- **Reference Images**: Take multiple photos of Bella from different angles

---

**When you're ready to add the texture, just place your PNG file(s) in this directory!**

The mod will automatically use them to override vanilla wolf textures.
