package com.joaquintown.mod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

/**
 * Registers custom sound events for the Joaquintown mod.
 */
public class ModSounds {

    // Villager ambient sounds
    public static final SoundEvent VILLAGER_AMBIENT = registerSound("entity.villager.ambient");

    // Villager interaction sounds
    public static final SoundEvent VILLAGER_HURT = registerSound("entity.villager.hurt");
    public static final SoundEvent VILLAGER_DEATH = registerSound("entity.villager.death");
    public static final SoundEvent VILLAGER_TRADE = registerSound("entity.villager.trade");
    public static final SoundEvent VILLAGER_NO = registerSound("entity.villager.no");
    public static final SoundEvent VILLAGER_CELEBRATE = registerSound("entity.villager.celebrate");
    public static final SoundEvent VILLAGER_WORK = registerSound("entity.villager.work_armorer");

    // Zombie sounds
    public static final SoundEvent ZOMBIE_AMBIENT = registerSound("entity.zombie.ambient");
    public static final SoundEvent ZOMBIE_HURT = registerSound("entity.zombie.hurt");
    public static final SoundEvent ZOMBIE_DEATH = registerSound("entity.zombie.death");

    // Iron Golem (King Kong) sounds
    public static final SoundEvent IRON_GOLEM_AMBIENT = registerSound("entity.iron_golem.ambient");
    public static final SoundEvent IRON_GOLEM_HURT = registerSound("entity.iron_golem.hurt");
    public static final SoundEvent IRON_GOLEM_DEATH = registerSound("entity.iron_golem.death");

    // Ravager (Godzilla) sounds
    public static final SoundEvent GIANT_AMBIENT = registerSound("entity.ravager.ambient");
    public static final SoundEvent GIANT_HURT = registerSound("entity.ravager.hurt");
    public static final SoundEvent GIANT_DEATH = registerSound("entity.ravager.death");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of("joaquintown", id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    /**
     * Initialize and register all custom sounds.
     * Call this during mod initialization.
     */
    public static void registerSounds() {
        // Sounds are registered via static initialization
        // This method is called to ensure the class is loaded
    }
}
