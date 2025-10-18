# Changelog

All notable changes to the Joaquintown mod will be documented in this file.

## [1.0.0] - 2025-10-13

### Added
- Initial release of Joaquintown mod
- Custom Joaquin villager textures for all professions (14 total)
- Custom villager type textures for all biomes (7 total)
- Removed 3D nose protrusion from villager models via mixin
- Custom villager sounds:
  - 3 ambient/idle sounds
  - 2 hurt sounds
  - 1 death sound
  - 3 trading success sounds
  - 3 trading failure sounds
  - 1 celebration sound
  - 1 work sound
- Automatic spawn village generation at world spawn
- "Welcome to Joaquintown!" join message
- Full compatibility with Minecraft 1.21.1
- Fabric API integration

### Features
- All villagers display custom Joaquin appearance with curly hair
- Each profession retains distinctive accessories (hats, aprons, etc.)
- Works across all biome types
- Custom sound effects add personality to villager interactions
- Clean, professional texture work with ImageMagick compositing
- Comprehensive testing suite included

### Technical Details
- Built with Fabric Loom 1.7.2
- Java 21 compatibility
- Mixin-based model modifications
- Resource pack texture overrides
- Sound event registration system
