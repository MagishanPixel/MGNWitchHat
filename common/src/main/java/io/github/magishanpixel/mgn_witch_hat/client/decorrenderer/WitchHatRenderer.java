package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.BasicRenderDecor;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value.RenderValue;
import io.github.magishanpixel.mgn_witch_hat.client.models.SplittedParts;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayers;
import io.github.magishanpixel.mgn_witch_hat.item.ColoredItem;
import io.github.magishanpixel.mgn_witch_hat.misc.BrimType;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class WitchHatRenderer {
    public interface RenderDecor {
        void render(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> setPose, DataDecor datam, ItemStack hatStack);
    }

    private static ImmutableMap<DecorType, RenderDecor> DECOR_RENDERERS;

    public static void renderDecors(Map<DecorType, DataDecor> mapTypes, ItemStack hatStack, BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> resetPose) {
        if (DECOR_RENDERERS == null) return;

        for (Map.Entry<DecorType, DataDecor> entry : mapTypes.entrySet()) {
            DataDecor data = entry.getValue();

            if (!data.stack().isEmpty()) {
                RenderDecor v = DECOR_RENDERERS.get(entry.getKey());

                v.render(blockRenderer, itemRenderer, buffer, poseStack, packedLight, overlay, resetPose, data, hatStack);
            }
        }
    }

    private static ResourceLocation getDecorsTex(String name) {
        return MGNConstants.newId("textures/entity/hat/decors/" + name + ".png");
    }

    public static void setPoseAsDebug(PoseStack poseStack) {
        poseStack.translate(0, -1.362f, 0.565f);
        poseStack.scale(0.55f,0.55f,0.55f);
        qRot(poseStack, 0, 0, 0);
    }

    public static void qRot(PoseStack poseStack, double x, double y, double z) {
        poseStack.mulPose(new Quaternionf().rotationXYZ((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z)));
    }

    private static BasicRenderDecor.Builder decorBuilder() {
        return new BasicRenderDecor.Builder();
    }

    public static void renderHat(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int overlay, Consumer<PoseStack> resetPose, @Nullable Consumer<PoseStack> startPose) {
        Model hatModel = HatBakedModels.getModel(HatBakedModels.HAT);
        BrimType brimType = stack.get(ModDataComponents.BRIM_TYPE.value());
        Model brimModel = HatBakedModels.getModel(switch (brimType) {
            case SHORT -> HatBakedModels.SHORT_BRIM;
            case WIDE -> HatBakedModels.WIDE_BRIM;
        });

        String hatCol = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? stack.get(ModDataComponents.WITCH_HAT_COLOR.value()).getSerializedName() : "base";

        poseStack.pushPose();
        if (startPose != null) {
            startPose.accept(poseStack);
        }

        poseStack.scale(1.1f, 1.1f, 1.1f);
        poseStack.translate(0, -1.9f,0);

        ResourceLocation tex_hat = MGNConstants.getTexture("witch_hat/" + hatCol);
        ResourceLocation tex_brim = MGNConstants.getTexture("brim/" + brimType.getSerializedName() + "/" + hatCol);
        hatModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_hat)), packedLight, overlay, -1);
        brimModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_brim)), packedLight, overlay, -1);

        if (stack.get(ModDataComponents.HAS_BAND.value())) {
            Model bandModel = HatBakedModels.getModel(HatBakedModels.HAT_BAND);
            ResourceLocation tex_band;

            if (stack.has(ModDataComponents.BAND_COLOR.value())) {
                tex_band = MGNConstants.getTexture("hat_band/" + stack.get(ModDataComponents.BAND_COLOR.value()).getSerializedName());
            } else {
                tex_band = MGNConstants.getTexture("hat_band/base");
            }

            bandModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_band)), packedLight, overlay, -1);
        }

        if (stack.has(ModDataComponents.BUCKLE_TYPE.value())) {
            Model buckleModel = HatBakedModels.getModel(HatBakedModels.BUCKLE);
            ResourceLocation tex_buckle = MGNConstants.getTexture("buckle/" + stack.get(ModDataComponents.BUCKLE_TYPE.value()).getSerializedName());
            buckleModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(tex_buckle)), packedLight, overlay, -1);
        }

        poseStack.popPose();

        if (stack.has(ModDataComponents.DECOR_TYPES.value())) {
            Minecraft inst = Minecraft.getInstance();

            renderDecors(
                    stack.get(ModDataComponents.DECOR_TYPES.value()),
                    stack,
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

    // INITIALIZE THE DECORS OFC
    public static void init() {
        // for being reloadable
        DECOR_RENDERERS = null;

        ImmutableMap.Builder<DecorType, RenderDecor> rendBuilder = new ImmutableMap.Builder<>();

        // I'm having fun making the builder one btw :D
        // I know you didnt ask but whatever-

        // PUMPKIN
        rendBuilder.put(DecorType.PUMPKIN,
            decorBuilder()
                    .autoSided()
                    .setDefaultModel(new RenderValue.ModelVal.Builder()
                            .setModel(HatBakedModels.PUMPKIN)
                            .setRenderType(paramVal -> {
                                String str = "pumpkin" + (paramVal.stack().is(Items.CARVED_PUMPKIN) ? "_carved" : "");

                                return RenderType.entityCutoutNoCull(getDecorsTex(str));
                            })
                            .build()
                    )
                    .add(RenderValue.builder()
                            .translate(0.4f, -0.74f, -0.15f)
                            .scale(0.225f)
                            .rotate(0, -65, 0)
                            .build()
                    )
                    .add(RenderValue.builder()
                            .translate(0.4f, -0.665f, 0.05f)
                            .scale(0.175f)
                            .rotate(0, -105, 0)
                            .build()
                    )
                    .add_BACK(RenderValue.builder()
                            .translate(0.08f, -0.74f, 0.3f)
                            .scale(0.225f)
                            .rotate(0, -145, 0)
                            .build()
                    )
                    .add_BACK(RenderValue.builder()
                            .translate(-0.125f, -0.665f, 0.325f)
                            .scale(0.175f)
                            .rotate(0, -205, 0)
                            .build()
                    )
                    .build()
        );

        // JACK O LANTERN
        rendBuilder.put(DecorType.JACK_O_LANTERN,
                decorBuilder()
                        .autoSided()
                        .setDefaultModel(new RenderValue.ModelVal.Builder()
                                .setModel(HatBakedModels.PUMPKIN)
                                .setRenderType(RenderType.entityCutoutNoCull(getDecorsTex("jack_o_lantern_empty")))
                                .build()
                        )
                        .add(RenderValue.builder()
                                .translate(0.4f, -0.74f, -0.15f)
                                .scale(0.225f)
                                .rotate(0, -65, 0)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .translate(0.4f, -0.665f, 0.05f)
                                .scale(0.175f)
                                .rotate(0, -105, 0)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .translate(0.4f, -0.74f, -0.15f)
                                .scale(0.225f)
                                .rotate(0, -65, 0)
                                .setModel(HatBakedModels.PUMPKIN)
                                .setRenderType(RenderType.entityCutoutNoCull(getDecorsTex("jack_o_lantern_lit")))
                                .glow()
                                .build()
                        )
                        .add(RenderValue.builder()
                                .translate(0.4f, -0.665f, 0.05f)
                                .scale(0.175f)
                                .rotate(0, -105, 0)
                                .setModel(HatBakedModels.PUMPKIN)
                                .setRenderType(RenderType.entityCutoutNoCull(getDecorsTex("jack_o_lantern_lit")))
                                .glow()
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .translate(0.08f, -0.74f, 0.3f)
                                .scale(0.225f)
                                .rotate(0, -145, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .translate(-0.125f, -0.665f, 0.325f)
                                .scale(0.175f)
                                .rotate(0, -205, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .translate(0.08f, -0.74f, 0.3f)
                                .scale(0.225f)
                                .rotate(0, -145, 0)
                                .setModel(HatBakedModels.PUMPKIN)
                                .setRenderType(RenderType.entityCutoutNoCull(getDecorsTex("jack_o_lantern_lit")))
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .translate(-0.125f, -0.665f, 0.325f)
                                .scale(0.175f)
                                .rotate(0, -205, 0)
                                .setModel(HatBakedModels.PUMPKIN)
                                .setRenderType(RenderType.entityCutoutNoCull(getDecorsTex("jack_o_lantern_lit")))
                                .build()
                        )
                        .build()
        );


        // FEATHER
        rendBuilder.put(DecorType.FEATHER,
                decorBuilder()
                        .autoSided()
                        .add(RenderValue.builder()
                                .setModel(HatBakedModels.FEATHER)
                                .setRenderType(paramVal -> {
                                    ItemStack stack = paramVal.stack();
                                    String str = "feather";

                                    if (stack.is(ModItems.RAVEN_FEATHER.asItem())) {
                                        str = "raven_feather";
                                    }

                                    return RenderType.entityCutout(getDecorsTex(str));
                                })
                                .translate(0.35f, -1.5f, -0.1f)
                                .scale(0.65f)
                                .rotate(0, 10, 0)
                                .build()
                        )
                        .build()
        );

        // First decor ever made btw :3
        // SKULL
        rendBuilder.put(DecorType.SKULL,
                decorBuilder()
                        .setDefaultModel(new RenderValue.ModelVal.Builder()
                                .setModel(HatBakedModels.SKULL)
                                .setRenderType(paramVal -> {
                                    BlockItem blockItem = (BlockItem) paramVal.stack().getItem();

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
                                            return RenderType.entityCutoutNoCull(tex);
                                        }
                                    }

                                    return RenderType.entityCutoutNoCull(ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png"));
                                })
                                .build()
                        )
                        .autoSided()
                        .add(RenderValue.builder()
                                .setStage(RenderValue.PoseStage.SCALE)
                                .setStage(RenderValue.PoseStage.TRANSLATE)
                                .setStage(RenderValue.PoseStage.MULPOSE)
                                .scale(0.5f)
                                .translate(0.65f, -0.806f, -0.15f)
                                .rotate(0, -70, 0)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .setStage(RenderValue.PoseStage.SCALE)
                                .setStage(RenderValue.PoseStage.TRANSLATE)
                                .setStage(RenderValue.PoseStage.MULPOSE)
                                .scale(0.35f)
                                .translate(1.1f, -1.151f, 0.4f)
                                .rotate(0, -135, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .setStage(RenderValue.PoseStage.SCALE)
                                .setStage(RenderValue.PoseStage.TRANSLATE)
                                .setStage(RenderValue.PoseStage.MULPOSE)
                                .scale(0.5f)
                                .translate(0.2f, -0.806f, 0.6f)
                                .rotate(0, -145, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .setStage(RenderValue.PoseStage.SCALE)
                                .setStage(RenderValue.PoseStage.TRANSLATE)
                                .setStage(RenderValue.PoseStage.MULPOSE)
                                .scale(0.35f)
                                .translate(-0.4f, -1.151f, 0.9f)
                                .rotate(0, -220, 0)
                                .build()
                        )
                        .build()
        );

        // LANTERN
        rendBuilder.put(DecorType.LANTERN,
                decorBuilder()
                        .setDefaultModel(RenderValue.ModelVal.asBlockItem())
                        .autoSided()
                        .add(RenderValue.builder()
                                .glow()
                                .translate(0.4f, 0.653f, 0.35f)
                                .scale(0.5f)
                                .rotate(0, 25, 0)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .glow()
                                .translate(0, 1f, -0.565f)
                                .scale(0.5f)
                                .build()
                        )
                        .build()

        );

        // FLOWER
        rendBuilder.put(DecorType.FLOWER,
                decorBuilder()
                        .setDefaultModel(RenderValue.ModelVal.asBlockItem())
                        .autoSided()
                        .add(RenderValue.builder()
                                .translate(0.45f, 0.75f, 0)
                                .scale(0.6f)
                                .rotate(-15, 0, -15)
                                .build()
                        )
                        .add_BACK(RenderValue.builder()
                                .translate(0, 0.7f, -0.4f)
                                .scale(0.6f)
                                .rotate(-15, 0, 0)
                                .build()
                        )
                        .build()
        );

        // RIBBON
        rendBuilder.put(DecorType.RIBBON, decorBuilder()
                .add(RenderValue.builder()
                        .setStage(RenderValue.PoseStage.SCALE)
                        .setStage(RenderValue.PoseStage.TRANSLATE)
                        .setStage(RenderValue.PoseStage.MULPOSE)
                        .setModel(HatBakedModels.RIBBON)
                        .setRenderType(paramVal -> {
                            ColoredItem coloredItem = (ColoredItem) paramVal.stack().getItem();
                            return RenderType.entityCutoutNoCull(getDecorsTex("ribbon/" + coloredItem.getDyeColor().getSerializedName()));
                        })
                        .scale(1.1f)
                        .translate(0, -1.9f, 0)
                        .revertRot()
                        .build()
                )
                .build()
        );

        final Function<RenderValue.ParamVal, RenderType> CANDLE_FIRE = paramVal -> {
            if (!paramVal.stack().get(ModDataComponents.CANDLE_LIT.value())) return RenderType.entityCutout(getDecorsTex("candle"));

            Level level = Minecraft.getInstance().level;
            int currClock = (int) level.getGameTime();
            int v = ((currClock / 4) % 3) + 1;
            return RenderType.entityCutout(getDecorsTex("candle_fire" + v));
        };

        // CANDLES
        rendBuilder.put(DecorType.CANDLE,
                decorBuilder()
                        .setDefaultModel(new RenderValue.ModelVal.Builder()
                                .setModel(HatBakedModels.CANDLES)
                                .setRenderType(RenderType.entityCutout(getDecorsTex("candle")))
                                .build()
                        )
                        .autoSided()
                        // BODY
                        .add(RenderValue.builder()
                                .scale(0.7f)
                                .translate(0.4f, -1.455f, -0.45f)
                                .rotate(0, 45, 0)
                                .boneId(1)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .scale(0.7f)
                                .translate(0.4f, -1.455f, -0.25f)
                                .boneId(2)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .scale(0.7f)
                                .translate(0.4f, -1.455f, -0.1f)
                                .rotate(0, -15, 0)
                                .boneId(3)
                                .build()
                        )
                        // TIP
                        .add(RenderValue.builder()
                                .glow(stack -> stack.get(ModDataComponents.CANDLE_LIT.value()))
                                .setModel(HatBakedModels.CANDLES)
                                .setRenderType(CANDLE_FIRE)
                                .scale(0.7f)
                                .translate(0.4f, -1.76f, -0.45f)
                                .rotate(0, 45, 0)
                                .boneId(4)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .glow(stack -> stack.get(ModDataComponents.CANDLE_LIT.value()))
                                .setModel(HatBakedModels.CANDLES)
                                .setRenderType(CANDLE_FIRE)
                                .scale(0.5f)
                                .translate(0.4f, -1.33f, -0.25f)
                                .rotate(0, 0, 0)
                                .boneId(4)
                                .build()
                        )
                        .add(RenderValue.builder()
                                .glow(stack -> stack.get(ModDataComponents.CANDLE_LIT.value()))
                                .setModel(HatBakedModels.CANDLES)
                                .setRenderType(CANDLE_FIRE)
                                .scale(0.5f)
                                .translate(0.4f, -1.37f, -0.1f)
                                .rotate(0, -15, 0)
                                .boneId(4)
                                .build()
                        )
                        .build()
        );

        DECOR_RENDERERS = rendBuilder.build();
    }

}
