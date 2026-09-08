package io.github.magishanpixel.mgn_witch_hat.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

public interface SplittedParts {
    void renderSpecificBone(int num, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay);
}
