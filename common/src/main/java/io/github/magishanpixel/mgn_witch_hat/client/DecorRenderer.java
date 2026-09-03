package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Consumer;

public class DecorRenderer {
    private interface RenderDecor {
        void v(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay);
    }

    public static final ImmutableMap<DecorType, RenderDecor> DECOR_RENDERERS;

    static {
        ImmutableMap.Builder<DecorType, RenderDecor> rendBuilder = new ImmutableMap.Builder<>();
        rendBuilder.put(
                DecorType.SKULL,
                (blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay) -> {
                    BlockState skullState = Blocks.SKELETON_SKULL.defaultBlockState();
                    poseStack.translate(0, 0.1, 0);
                    blockRenderer.renderSingleBlock(skullState, poseStack, buffer, packedLight, overlay);
                }
                );

        DECOR_RENDERERS = rendBuilder.build();
    }


    public static void render(List<DecorType> listTypes, BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose) {
        for (int i = 0; i < listTypes.size(); i++) {
            RenderDecor v = DECOR_RENDERERS.get(listTypes.get(i));

            poseStack.pushPose();
            resetPose.accept(poseStack);

            // too lazy to name the function btw :3
            v.v(blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay);
            poseStack.popPose();
        }
    }

}
