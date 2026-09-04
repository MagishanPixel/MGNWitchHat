package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.BasicRenderDecor;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.RenderValue;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
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
                        .setDefaultModel(new RenderValue.ModelVal.Builder()
                                .setModel(HatBakedModels.ModelType.SKULL)
                                .setRenderType(stack -> {
                                    BlockItem blockItem = (BlockItem) stack.getItem();

                                    if (blockItem.getBlock() instanceof SkullBlock skullBlock) {
                                        SkullBlock.Types type = (SkullBlock.Types) skullBlock.getType();

                                        ResourceLocation tex = switch (type) {
                                            case SkullBlock.Types.SKELETON -> ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");
                                            case SkullBlock.Types.WITHER_SKELETON -> ResourceLocation.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png");
                                            case SkullBlock.Types.ZOMBIE -> ResourceLocation.withDefaultNamespace("textures/entity/zombie/zombie.png");
                                            case SkullBlock.Types.CREEPER -> ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper.png");
                                            default -> null;
                                        };

                                        if (tex != null) {
                                            return RenderType.entityCutoutNoCullZOffset(tex);
                                        }
                                    }

                                    return RenderType.entityCutoutNoCullZOffset(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png"));
                                })
                                .build()
                        )
                        .add(RenderValue.builder()
                                .scale(0.5f)
                                .translate(0.65f, -0.805f, -0.15f)
                                .rotate(0, -70, 0)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .scale(0.35f)
                                .translate(1.1f, -1.15f, 0.4f)
                                .rotate(0, -135, 0)
                                .build()
                        )
                        .build()
        );

        /*
        rendBuilder.put(DecorType.SKULL, (blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, setPose, stack) -> {
            poseStack.pushPose();
            setPose.accept(poseStack);

            poseStack.pushPose();
            poseStack.scale(0.5f, 0.5f, 0.5f);
            poseStack.translate(0.65f, -0.805f, -0.15f);
            qRot(poseStack, 0, -70, 0);


            itemRenderer.render(stack, ItemDisplayContext.NONE, false, poseStack, buffer, packedLight, overlay, itemRenderer.getModel(stack, null, null, 0));
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.scale(0.35f, 0.35f, 0.35f);
            poseStack.translate(1.1f, -1.15f, 0.4f);
            qRot(poseStack, 0, -135, 0);
            itemRenderer.renderStatic(stack, ItemDisplayContext.NONE, packedLight, overlay, poseStack, buffer, null, 0);
            poseStack.popPose();

            poseStack.popPose();
        });*/

        DECOR_RENDERERS = rendBuilder.build();
    }

    private static void qRot(PoseStack poseStack, double x, double y, double z) {
        poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z)));
    }

    private static BasicRenderDecor.Builder decorBuilder() {
        return new BasicRenderDecor.Builder();
    }


    public static void setPoseAsDebug(PoseStack poseStack) {
        poseStack.scale(0.5f,0.5f,0.5f);
        poseStack.translate(0.65f, -0.805f, -0.15);
        poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(0), (float) Math.toRadians(-70), (float) Math.toRadians(0)));
    }



}
