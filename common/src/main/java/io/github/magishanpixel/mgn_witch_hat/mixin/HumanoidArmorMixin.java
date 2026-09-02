package io.github.magishanpixel.mgn_witch_hat.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.item.WitchHatItem;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public class HumanoidArmorMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void rend(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);

        if (stack.getItem() instanceof WitchHatItem hatItem) {
            ci.cancel();

            if (!HatBakedModels.isAvail()) return;

            RenderLayer<T, M> self = (RenderLayer<T, M>) (Object) this;
            int i = LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F);


            ResourceLocation tex_robe = MGNConstants.getTexture("robe");

            Model hatModel = HatBakedModels.getModel(HatBakedModels.ModelType.HAT);
            Model robeModel = HatBakedModels.getModel(HatBakedModels.ModelType.ROBE);
            Model buckleModel = HatBakedModels.getModel(HatBakedModels.ModelType.BUCKLE);

            poseStack.pushPose();
            self.getParentModel().getHead().translateAndRotate(poseStack);

            poseStack.scale(1.1f, 1.1f, 1.1f);
            poseStack.translate(0, -1.9f,0);

            ResourceLocation tex_hat = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? MGNConstants.getTexture("witch_hat/" + stack.get(ModDataComponents.WITCH_HAT_COLOR.value())) : MGNConstants.getTexture("witch_hat/base");
            hatModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_hat)), packedLight, i, -1);

            robeModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_robe)), packedLight, i, -1);

            if (stack.has(ModDataComponents.BUCKLE_TYPE.value())) {
                ResourceLocation tex_buckle = MGNConstants.getTexture("buckle/" + stack.get(ModDataComponents.BUCKLE_TYPE.value()).getSerializedName());
                buckleModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_buckle)), packedLight, i, -1);
            }

            poseStack.popPose();


        }
    }

}
