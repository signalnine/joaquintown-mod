package com.joaquintown.mod.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.Identifier;

/**
 * Custom villager renderer that removes the 3D nose protrusion.
 *
 * This renderer extends VillagerEntityRenderer and modifies the model
 * to hide/flatten the nose geometry.
 *
 * Note: We don't override getTexture() to allow profession-specific textures
 * from the resource pack to be used correctly by the parent class.
 */
public class NoNoseVillagerRenderer extends VillagerEntityRenderer {

    public NoNoseVillagerRenderer(EntityRendererFactory.Context context) {
        super(context);

        // The model is already created by the parent constructor
        // We'll modify it to hide the nose in the mixin
    }
}
