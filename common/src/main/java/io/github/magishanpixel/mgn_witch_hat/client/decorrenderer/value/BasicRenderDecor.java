package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.WitchHatRenderer;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorPlacement;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BasicRenderDecor implements WitchHatRenderer.RenderDecor {
    private final ImmutableMap<DecorPlacement, ImmutableList<RenderValue>> baseRenderers;

    @Nullable
    private final RenderValue.ModelVal defaultModel;

    public BasicRenderDecor(ImmutableMap<DecorPlacement, ImmutableList<RenderValue>> valList, @Nullable RenderValue.ModelVal defauldModel) {
        this.baseRenderers = valList;
        this.defaultModel = defauldModel;
    }

    public static class Builder {
        private final Map<DecorPlacement, ImmutableList.Builder<RenderValue>> builder = new HashMap<>();
        private RenderValue.ModelVal defaultModel;
        private boolean autoSided = false;

        private void addTo(DecorPlacement placement, RenderValue v) {
            if (!builder.containsKey(placement)) {
                builder.put(placement, new ImmutableList.Builder<>());
            }

            builder.get(placement).add(v);
        }

        public Builder add(RenderValue v) {
            addTo(DecorPlacement.REGULAR, v);

            if (autoSided) {
                addTo(DecorPlacement.RIGHT, new RenderValue(v.modelVal, v.scale, new Vec3(v.rot.x, -v.rot.y, v.rot.z), -v.pX, v.pY, v.pZ, v.onDebug));
            }

            return this;
        }

        public Builder autoSided() {
            this.autoSided = true;
            return this;
        }

        public Builder add_BACK(RenderValue v) {
            addTo(DecorPlacement.BACK, v);
            return this;
        }

        public Builder add_RIGHT(RenderValue v) {
            addTo(DecorPlacement.RIGHT, v);
            return this;
        }

        public Builder setDefaultModel(RenderValue.ModelVal model) {
            this.defaultModel = model;
            return this;
        }

        public BasicRenderDecor build() {
            ImmutableMap.Builder<DecorPlacement, ImmutableList<RenderValue>> m = new ImmutableMap.Builder<>();

            builder.forEach((placement, v) -> {
                m.put(placement, v.build());
            });

            return new BasicRenderDecor(m.build(), defaultModel);
        }
    }

    @Override
    public void render(BlockRenderDispatcher blockRenderer, ItemRenderer itemRenderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, int overlay, Consumer<PoseStack> setPose, DataDecor data) {
        ItemStack stack = data.stack();

        DecorPlacement placement = data.placement();

        ImmutableList<RenderValue> resList = baseRenderers.get(placement);

        if (resList == null) return;

        for (int i = 0; i < resList.size(); i++) {
            RenderValue v = resList.get(i);

            poseStack.pushPose();
            setPose.accept(poseStack);

            Runnable adjustedPose = () -> {
                if (!v.onDebug) {
                    poseStack.scale(v.scale, v.scale, v.scale);
                    poseStack.translate(v.pX, v.pY, v.pZ);
                    poseStack.mulPose(new Quaternionf().rotationXYZ(
                            (float) Math.toRadians(v.rot.x),
                            (float) Math.toRadians(v.rot.y),
                            (float) Math.toRadians(v.rot.z)
                    ));
                } else {
                    // For hotswapping yuh
                    WitchHatRenderer.setPoseAsDebug(poseStack);
                }
            };


            RenderValue.ModelVal myModel = v.modelVal != null ? v.modelVal : defaultModel;

            if (myModel != null) {
                if (myModel.modelType != null && myModel.renderType != null) {
                    adjustedPose.run();
                    VertexConsumer vertexConsumer = buffer.getBuffer(myModel.renderType.apply(stack));
                    Model model = HatBakedModels.getModel(myModel.modelType.apply(stack));
                    model.renderToBuffer(poseStack, vertexConsumer, packedLight, overlay);
                } else if (myModel.blockstate != null) {
                    BlockState targState = myModel.blockstate;

                    if (targState.isAir()) {
                        Item item = stack.getItem();
                        if (item instanceof BlockItem blockItem) {
                            targState = blockItem.getBlock().defaultBlockState();
                        }
                    }

                    poseStack.pushPose();
                    poseStack.scale(1f, 1f, 1f);
                    poseStack.translate(-0.5f, 0, 0.5);
                    poseStack.mulPose(new Quaternionf().rotationXYZ(
                            (float) Math.toRadians(180),
                            (float) Math.toRadians(0),
                            (float) Math.toRadians(0)));

                    poseStack.pushPose();
                    adjustedPose.run();

                    blockRenderer.renderSingleBlock(targState, poseStack, buffer, packedLight, overlay);

                    poseStack.popPose();
                    poseStack.popPose();
                }
            }


            poseStack.popPose();
        }
    }
}
