package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value;

import com.google.common.collect.ImmutableList;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import io.github.magishanpixel.mgn_witch_hat.misc.BrimType;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorPlacement;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class RenderValue {
    public final float scale;
    public final Vec3 rot;
    public final float pX;
    public final float pY;
    public final float pZ;
    public final boolean onDebug;
    public final ModelVal modelVal;
    public final boolean defaultRot;
    public final int boneId;
    public final ImmutableList<PoseStage> poseStages;
    public final Predicate<ItemStack> glowPredicate;

    public enum PoseStage {
        TRANSLATE,
        MULPOSE,
        SCALE
    }

    public RenderValue(ModelVal modelVal, float scale, Vec3 rot, float pX, float pY, float pZ, boolean onDebug, boolean defaultRot, ImmutableList<PoseStage> poseStages, int boneId, Predicate<ItemStack> glowPredicate) {
        this.modelVal = modelVal;
        this.scale = scale;
        this.rot = rot;
        this.pX = pX;
        this.pY = pY;
        this.pZ = pZ;
        this.onDebug = onDebug;
        this.defaultRot = defaultRot;
        this.boneId = boneId;
        this.poseStages = poseStages;
        this.glowPredicate = glowPredicate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public record ParamVal(ItemStack stack, DecorPlacement placement, BrimType brimType) {}

    public static class ModelVal {
        public final BlockState blockstate;
        public final Function<ParamVal, ResourceLocation> modelType;
        public final Function<ParamVal, RenderType> renderType;

        public static ModelVal asBlockItem() {
            return new Builder().blockstateAsItem().build();
        }

        public ModelVal(BlockState blockstate, Function<ParamVal, ResourceLocation> modelType, Function<ParamVal, RenderType> renderType) {
            this.blockstate = blockstate;
            this.modelType = modelType;
            this.renderType = renderType;
        }

        public static class Builder {
            private BlockState blockstate;
            private Function<ParamVal, ResourceLocation> modelType;
            private Function<ParamVal, RenderType> renderType;
            private boolean isEmpty = true;

            public Builder blockstate(BlockState state) {
                this.blockstate = state;
                isEmpty = false;
                return this;
            }

            public Builder blockstateAsItem() {
                return blockstate(Blocks.AIR.defaultBlockState());
            }

            public Builder setModel(ResourceLocation name) {
                return setModel(paramVal -> name);
            }

            public Builder setModel(Function<ParamVal, ResourceLocation> model) {
                this.modelType = model;
                isEmpty = false;
                return this;
            }

            public Builder setRenderType(RenderType rend) {
                return setRenderType(paramVal -> rend);
            }

            public Builder setRenderType(Function<ParamVal, RenderType> rend) {
                this.renderType = rend;
                isEmpty = false;
                return this;
            }

            public ModelVal build() {
                return isEmpty ? null : new ModelVal(blockstate, modelType, renderType);
            }
        }


    }

    public static class Builder {
        private ModelVal.Builder modelVal = new ModelVal.Builder();
        private float scale = 1f;
        private Vec3 rot = new Vec3(0,0,0);
        private float pX = 0f;
        private float pY = 0f;
        private float pZ = 0f;
        private boolean onDebug;
        private List<PoseStage> poseStages = new ArrayList<>();
        private boolean revertRot = false;
        private int boneId = 1;
        private Predicate<ItemStack> glowPredicate;

        public Builder glow() {
            return glow(stack -> true);
        }

        public Builder glow(Predicate<ItemStack> predicate) {
            this.glowPredicate = glowPredicate;
            return this;
        }

        public Builder boneId(int num) {
            this.boneId = num;
            return this;
        }

        public Builder revertRot() {
            this.revertRot =  true;
            return this;
        }

        public Builder setStage(PoseStage stage) {
            poseStages.add(stage);
            return this;
        }

        public Builder blockstate(BlockState state) {
            this.modelVal.blockstate(state);
            return this;
        }

        public Builder blockstateAsItem() {
            this.modelVal.blockstate(Blocks.AIR.defaultBlockState());
            return this;
        }

        public Builder setModel(ResourceLocation model) {
            this.modelVal.setModel(stack -> model);
            return this;
        }

        public Builder setModel(Function<ParamVal, ResourceLocation> model) {
            this.modelVal.setModel(model);
            return this;
        }

        public Builder setRenderType(RenderType rend) {
            this.modelVal.setRenderType(stack -> rend);
            return this;
        }

        public Builder setRenderType(Function<ParamVal, RenderType> rend) {
            this.modelVal.setRenderType(rend);
            return this;
        }

        public Builder scale(float v) {
            this.scale = v;
            return this;
        }

        public Builder rotate(double x, double y, double z) {
            this.rot = new Vec3(x, y, z);
            return this;
        }

        public Builder translate(float x, float y, float z) {
            this.pX = x;
            this.pY = y;
            this.pZ = z;

            return this;
        }

        // I named the function like this so I can see it due to being dyslexic-
        public Builder debug_DEBUG_DEBUUUG() {
            this.onDebug = true;
            return this;
        }

        public RenderValue build() {
            ImmutableList<PoseStage> poseList = poseStages.isEmpty() ? ImmutableList.of(PoseStage.TRANSLATE, PoseStage.SCALE, PoseStage.MULPOSE) : ImmutableList.copyOf(poseStages);

            return new RenderValue(modelVal.build(), scale, rot, pX, pY, pZ, onDebug, revertRot, poseList, boneId, glowPredicate);
        }
    }
}
