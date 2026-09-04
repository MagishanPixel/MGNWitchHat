package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.BasicRenderDecor;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.RenderValue;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.function.Consumer;

public class DecorRenderer {
    public static final boolean ON_DEBUG = true;

    public interface RenderDecor {
        void render(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> setPose, ItemStack stack);
    }

    private static ImmutableMap<DecorType, RenderDecor> DECOR_RENDERERS;

    public static void render(Map<DecorType, ItemStack> mapTypes, BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose) {
        for (Map.Entry<DecorType, ItemStack> entry : mapTypes.entrySet()) {
            RenderDecor v = DECOR_RENDERERS.get(entry.getKey());

            v.render(blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose, entry.getValue());
        }
    }

    public static void init() {
        ImmutableMap.Builder<DecorType, RenderDecor> rendBuilder = new ImmutableMap.Builder<>();
        rendBuilder.put(DecorType.SKULL,
                decorBuilder()
                        .add(RenderValue.builder()
                                .setModel(HatBakedModels.ModelType.SKELETON_SKULL)
                                .setRenderType(RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png")))
                                .scale(0.5f)
                                .rotate(-7.5, -90, 0)
                                .translate(0.65f, -0.815f, 0)
                                .build()
                        )
                        .build()
        );
        DECOR_RENDERERS = rendBuilder.build();
    }

    private static BasicRenderDecor.Builder decorBuilder() {
        return new BasicRenderDecor.Builder();
    }


    public static void setPoseAsDebug(PoseStack poseStack) {
        poseStack.scale(0.5f,0.5f,0.5f);
        poseStack.translate(0.65f, -0.815f, 0);
        poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(-7.5), (float) Math.toRadians(-90), (float) Math.toRadians(0)));
    }



}
