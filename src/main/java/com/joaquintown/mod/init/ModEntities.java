package com.joaquintown.mod.init;

import com.joaquintown.mod.entity.GodzillaEntity;
import com.joaquintown.mod.entity.KingKongEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registers custom entities for the Joaquintown mod.
 */
public class ModEntities {

    /**
     * Godzilla entity type.
     * A massive kaiju boss that roams the world.
     */
    public static final EntityType<GodzillaEntity> GODZILLA = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("joaquintown", "godzilla"),
            EntityType.Builder.create(GodzillaEntity::new, SpawnGroup.MONSTER)
                    .dimensions(2.0f, 4.0f) // width: 2 blocks, height: 4 blocks
                    .maxTrackingRange(80)
                    .build()
    );

    /**
     * King Kong entity type.
     * A mighty gorilla that protects villages.
     */
    public static final EntityType<KingKongEntity> KING_KONG = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("joaquintown", "king_kong"),
            EntityType.Builder.create(KingKongEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1.6f, 3.2f) // Slightly larger than iron golem (1.4 x 2.7)
                    .maxTrackingRange(64)
                    .build()
    );

    /**
     * Initialize and register all custom entities.
     * Call this during mod initialization.
     */
    public static void registerEntities() {
        // Register Godzilla's attributes
        FabricDefaultAttributeRegistry.register(GODZILLA, GodzillaEntity.createGodzillaAttributes());

        // Register King Kong's attributes
        FabricDefaultAttributeRegistry.register(KING_KONG, KingKongEntity.createKingKongAttributes());
    }
}
