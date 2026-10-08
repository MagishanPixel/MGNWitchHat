package io.github.magishanpixel.mgn_witch_hat.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.magishanpixel.mgn_witch_hat.entity.WitchHatDisplayEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.RotationSegment;

public class WitchHatDisplayEntityRenderer extends EntityRenderer<WitchHatDisplayEntity> {
    public WitchHatDisplayEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(WitchHatDisplayEntity witchHatDisplayEntity) {
        return null;
    }

    @Override
    public void render(WitchHatDisplayEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();


        poseStack.translate(0, -0.24, 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(180f));
        poseStack.mulPose(Axis.YP.rotationDegrees(180));
        poseStack.mulPose(Axis.YP.rotationDegrees(RotationSegment.convertToDegrees(entity.getRotation())));
        poseStack.translate(0, 0, -(0.175f/16f) + (1/16f));
        poseStack.mulPose(Axis.XP.rotationDegrees(7.5f));
        poseStack.scale(0.6f, 0.6f, 0.6f);


        WitchHatRenderer.renderHat(entity.getHatStack(), poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY,
                p -> p.mulPose(Axis.XN.rotation((float) (Math.toRadians(7.5)))),
                null
        );
        poseStack.popPose();
    }
}
