package com.joaquintown.mod;

import com.joaquintown.mod.client.renderer.GodzillaRenderer;
import com.joaquintown.mod.client.renderer.KingKongRenderer;
import com.joaquintown.mod.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client-side initialization for Joaquintown mod.
 * Handles client-only features like texture registration and entity renderers.
 */
public class JoaquintownModClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("joaquintown-client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Joaquintown client-side mod initializing...");
        LOGGER.info("Custom villager textures (profession-specific) are loaded via resource pack");
        LOGGER.info("Villager nose removed via VillagerModelMixin");

        // Register entity renderers
        EntityRendererRegistry.register(ModEntities.GODZILLA, GodzillaRenderer::new);
        LOGGER.info("Godzilla renderer registered.");

        EntityRendererRegistry.register(ModEntities.KING_KONG, KingKongRenderer::new);
        LOGGER.info("King Kong renderer registered.");
    }
}
