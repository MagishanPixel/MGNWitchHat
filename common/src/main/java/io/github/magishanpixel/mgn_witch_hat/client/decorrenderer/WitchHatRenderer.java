package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.BasicRenderDecor;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.RenderValue;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SkullBlock;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.function.Consumer;

public class WitchHatRenderer {
    public interface RenderDecor {
        void render(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> setPose, DataDecor data);
    }

    private static ImmutableMap<DecorType, RenderDecor> DECOR_RENDERERS;

    public static void renderDecors(Map<DecorType, DataDecor> mapTypes, BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose) {
        for (Map.Entry<DecorType, DataDecor> entry : mapTypes.entrySet()) {
            RenderDecor v = DECOR_RENDERERS.get(entry.getKey());

            v.render(blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose, entry.getValue());
        }
    }

    public static void init() {
        ImmutableMap.Builder<DecorType, RenderDecor> rendBuilder = new ImmutableMap.Builder<>();

        // SKULL
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
                        .autoSided()
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
                        .add_BACK(RenderValue.builder()
                                .scale(0.5f)
                                .translate(0.2f, -0.805f, 0.6f)
                                .rotate(0, -145, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .scale(0.35f)
                                .translate(-0.4f, -1.15f, 0.9f)
                                .rotate(0, -220, 0)
                                .build()
                        )
                        .build()
        );

        // FLOWER
        rendBuilder.put(DecorType.FLOWER,
                decorBuilder()
                        .setDefaultModel(new RenderValue.ModelVal.Builder()
                                .blockstateAsItem()
                                .build()
                        )
                        .add(RenderValue.builder()
                                .scale(0.7f)
                                .translate(0.75f, 0.5f, -0.2f)
                                .rotate(-10, 0, 0)
                                .build()
                        )
                        .add_RIGHT(RenderValue.builder()
                                .scale(0.7f)
                                .translate(-0.325f, 0.5f, -0.2f)
                                .rotate(-10, 0, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .scale(0.7f)
                                .translate(0.2f, 0.45f, -0.25f)
                                .rotate(-15, 0, 0)
                                .build()
                        )
                        .build()
        );

        DECOR_RENDERERS = rendBuilder.build();
    }

    public static void qRot(PoseStack poseStack, double x, double y, double z) {
        poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z)));
    }

    private static BasicRenderDecor.Builder decorBuilder() {
        return new BasicRenderDecor.Builder();
    }

    public static void setPoseAsDebug(PoseStack poseStack) {
        poseStack.scale(0.7f,0.7f,0.7f);
        //0.75
        poseStack.translate(0.2f, 0.45, -0.25);
        poseStack.mulPose(new Quaternionf().rotationXYZ(
                (float) Math.toRadians(-15),
                (float) Math.toRadians(0),
                (float) Math.toRadians(0)));


    }

    public static void renderHat(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int overlay, Consumer<PoseStack> resetPose, @Nullable Consumer<PoseStack> startPose) {
        Model hatModel = HatBakedModels.getModel(HatBakedModels.ModelType.HAT);

        poseStack.pushPose();
        if (startPose != null) {
            startPose.accept(poseStack);
        }

        poseStack.scale(1.1f, 1.1f, 1.1f);
        poseStack.translate(0, -1.9f,0);

        ResourceLocation tex_hat = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? MGNConstants.getTexture("witch_hat/" + stack.get(ModDataComponents.WITCH_HAT_COLOR.value())) : MGNConstants.getTexture("witch_hat/base");
        hatModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_hat)), packedLight, overlay, -1);

        if (stack.get(ModDataComponents.HAS_BAND.value())) {
            Model bandModel = HatBakedModels.getModel(HatBakedModels.ModelType.HAT_BAND);
            ResourceLocation tex_band;

            if (stack.has(ModDataComponents.BAND_COLOR.value())) {
                tex_band = MGNConstants.getTexture("hat_band/" + stack.get(ModDataComponents.BAND_COLOR.value()).getSerializedName());
            } else {
                tex_band = MGNConstants.getTexture("hat_band/base");
            }

            bandModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_band)), packedLight, overlay, -1);
        }

        if (stack.has(ModDataComponents.BUCKLE_TYPE.value())) {
            Model buckleModel = HatBakedModels.getModel(HatBakedModels.ModelType.BUCKLE);
            ResourceLocation tex_buckle = MGNConstants.getTexture("buckle/" + stack.get(ModDataComponents.BUCKLE_TYPE.value()).getSerializedName());
            buckleModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_buckle)), packedLight, overlay, -1);
        }

        poseStack.popPose();

        if (stack.has(ModDataComponents.DECOR_TYPES.value())) {
            Minecraft inst = Minecraft.getInstance();

            renderDecors(
                    stack.get(ModDataComponents.DECOR_TYPES.value()),
                    inst.getBlockRenderer(),
                    inst.getItemRenderer(),
                    buffer,
                    poseStack,
                    packedLight,
                    overlay,
                    resetPose
            );



        }
    }

    public static void renderAsItem(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        WitchHatRenderer.qRot(poseStack, 180, 180, 0);
        poseStack.translate(-0.5f, 0.5f, 0.5f);

        WitchHatRenderer.renderHat(stack, poseStack, buffer, packedLight, packedOverlay,
                p -> p.mulPose(Axis.XN.rotation((float) (Math.toRadians(7.5)))),
                null
        );

        poseStack.popPose();
    }




}
