package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

import java.util.List;
import java.util.function.Consumer;

public class DecorRenderer {
    private interface RenderDecor {
        void v(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose);
    }

    public static final ImmutableMap<DecorType, RenderDecor> DECOR_RENDERERS;

    static {
        ImmutableMap.Builder<DecorType, RenderDecor> rendBuilder = new ImmutableMap.Builder<>();
        rendBuilder.put(
                DecorType.SKULL,
                (blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose) -> {
                    Model model = HatBakedModels.getModel(HatBakedModels.ModelType.SKELETON_SKULL);

                    VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png")));

                    poseStack.pushPose();

                    resetPose.accept(poseStack);
                    poseStack.scale(0.5f,0.5f,0.5f);
                    poseStack.translate(0.65, -0.815f, 0);
                    poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(-7.5), (float) Math.toRadians(-75),0));

                    model.renderToBuffer(poseStack, vertexConsumer, packedLight, overlay);

                    poseStack.popPose();

                }
                );

        rendBuilder.put(
                DecorType.LANTERN,
                (blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose) -> {

                }
        );

        DECOR_RENDERERS = rendBuilder.build();
    }


    public static void render(List<DecorType> listTypes, BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose) {
        for (int i = 0; i < listTypes.size(); i++) {
            RenderDecor v = DECOR_RENDERERS.get(listTypes.get(i));


            // too lazy to name the function btw :3
            v.v(blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose);

        }
    }

}
