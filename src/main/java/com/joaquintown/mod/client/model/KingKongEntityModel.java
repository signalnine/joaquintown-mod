package com.joaquintown.mod.client.model;

import com.joaquintown.mod.entity.KingKongEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

/**
 * Model for King Kong entity.
 * Based on the iron golem model structure but adapted for KingKongEntity.
 */
public class KingKongEntityModel extends EntityModel<KingKongEntity> implements ModelWithArms, ModelWithHead {
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public KingKongEntityModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    @Override
    public void setAngles(KingKongEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;

        this.rightLeg.pitch = -1.5F * MathHelper.wrap(limbAngle, 13.0F) * limbDistance;
        this.leftLeg.pitch = 1.5F * MathHelper.wrap(limbAngle, 13.0F) * limbDistance;
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        // Iron golem doesn't use this method for arm positioning
        // Arms are positioned in setAngles method
    }

    @Override
    public void render(net.minecraft.client.util.math.MatrixStack matrices, net.minecraft.client.render.VertexConsumer vertices, int light, int overlay, int color) {
        this.head.render(matrices, vertices, light, overlay, color);
        this.body.render(matrices, vertices, light, overlay, color);
        this.rightArm.render(matrices, vertices, light, overlay, color);
        this.leftArm.render(matrices, vertices, light, overlay, color);
        this.rightLeg.render(matrices, vertices, light, overlay, color);
        this.leftLeg.render(matrices, vertices, light, overlay, color);
    }
}
