# Villager Custom Sounds

Place your custom villager sound files here. All files should be in `.ogg` format (Vorbis audio).

## Required Sound Files

### Ambient Sounds (idle/random sounds)
- `ambient1.ogg` - Random idle sound 1
- `ambient2.ogg` - Random idle sound 2
- `ambient3.ogg` - Random idle sound 3

### Interaction Sounds
- `yes1.ogg` - Trading success sound 1
- `yes2.ogg` - Trading success sound 2
- `yes3.ogg` - Trading success sound 3
- `no1.ogg` - Cannot trade sound 1
- `no2.ogg` - Cannot trade sound 2
- `no3.ogg` - Cannot trade sound 3

### Action Sounds
- `celebrate.ogg` - Celebration sound (after raid victory)
- `work.ogg` - Working at job site sound

### Damage Sounds
- `hurt1.ogg` - Taking damage sound 1
- `hurt2.ogg` - Taking damage sound 2
- `death.ogg` - Death sound

## Audio Specifications

- **Format:** OGG Vorbis
- **Sample Rate:** 44100 Hz (recommended) or 22050 Hz
- **Bit Rate:** 96-128 kbps (recommended)
- **Channels:** Mono (recommended for positional audio)
- **Length:** 0.5-3 seconds for most sounds

## Converting Audio to OGG

### Using FFmpeg (command line)
```bash
ffmpeg -i input.mp3 -c:a libvorbis -q:a 4 output.ogg
```

### Using Audacity (GUI)
1. Open your audio file in Audacity
2. File → Export → Export as OGG
3. Set quality to 5-7
4. Save to this directory

## Testing Sounds

After adding sound files:
1. Rebuild the mod: `./gradlew build`
2. Run the game and find a villager
3. Listen for your custom sounds during:
   - Idle wandering (ambient)
   - Trading (yes/no)
   - Taking damage (hurt)
   - Dying (death)
   - After raid victory (celebrate)
   - Working at job site (work)

## Tips

- Keep sounds short and loopable where appropriate
- Test volume levels - villagers are heard from a distance
- Multiple ambient sounds add variety (they're played randomly)
- Match the tone of your Joaquintown theme!
