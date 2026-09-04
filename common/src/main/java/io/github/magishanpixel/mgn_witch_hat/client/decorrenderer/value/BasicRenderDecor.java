package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.DecorRenderer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.function.Consumer;

public class BasicRenderDecor implements DecorRenderer.RenderDecor {
    private final ImmutableList<RenderValue> rendList;

    @Nullable
    private final RenderValue.ModelVal defaultModel;


    public BasicRenderDecor(ImmutableList<RenderValue> valList, @Nullable RenderValue.ModelVal defauldModel) {
        this.rendList = valList;
        this.defaultModel = defauldModel;
    }

    public static class Builder {
        private final ImmutableList.Builder<RenderValue> builder = new ImmutableList.Builder<>();
        private RenderValue.ModelVal defaultModel;

        public Builder add(RenderValue v) {
            builder.add(v);
            return this;
        }

        public Builder setDefaultModel(RenderValue.ModelVal model) {
            this.defaultModel = model;
            return this;
        }

        public BasicRenderDecor build() {
            return new BasicRenderDecor(builder.build(), defaultModel);
        }
    }

    @Override
    public void render(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> setPose, ItemStack stack) {
        for (int i = 0; i < rendList.size(); i++) {
            RenderValue v = rendList.get(i);

            poseStack.pushPose();
            setPose.accept(poseStack);
            if (!v.onDebug) {
                poseStack.scale(v.scale, v.scale, v.scale);
                poseStack.translate(-v.pX, v.pY, v.pZ);
                poseStack.mulPose(new Quaternionf().rotationXYZ(
                        (float) Math.toRadians(v.rot.x),
                        (float) Math.toRadians(-v.rot.y),
                        (float) Math.toRadians(v.rot.z)
                ));
            } else {
                // For hotswapping yuh
                DecorRenderer.setPoseAsDebug(poseStack);
            }

            RenderValue.ModelVal myModel = v.modelVal != null ? v.modelVal : defaultModel;

            if (myModel != null) {
                if (myModel.modelType != null && myModel.renderType != null) {
                    VertexConsumer vertexConsumer = buffer.getBuffer(myModel.renderType.apply(stack));
                    Model model = HatBakedModels.getModel(myModel.modelType.apply(stack));
                    model.renderToBuffer(poseStack, vertexConsumer, packedLight, overlay);
                } else if (myModel.blockstate != null) {
                    blockRenderer.renderSingleBlock(myModel.blockstate, poseStack, buffer, packedLight, overlay);
                }
            }

            poseStack.popPose();
        }
    }
}
