package com.joaquintown.mod.client.renderer;

import com.joaquintown.mod.client.model.KingKongEntityModel;
import com.joaquintown.mod.entity.KingKongEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

/**
 * Renderer for King Kong entity.
 * Uses the vanilla iron golem model but scaled up to make it look more imposing.
 */
public class KingKongRenderer extends MobEntityRenderer<KingKongEntity, KingKongEntityModel> {

    private static final Identifier TEXTURE = Identifier.of("joaquintown", "textures/entity/king_kong.png");
    private static final float SCALE = 1.3f; // 30% larger than normal iron golem

    public KingKongRenderer(EntityRendererFactory.Context context) {
        super(context, new KingKongEntityModel(context.getPart(EntityModelLayers.IRON_GOLEM)), 0.8f);
    }

    @Override
    public Identifier getTexture(KingKongEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(KingKongEntity entity, MatrixStack matrixStack, float tickDelta) {
        matrixStack.scale(SCALE, SCALE, SCALE);
        super.scale(entity, matrixStack, tickDelta);
    }
}
