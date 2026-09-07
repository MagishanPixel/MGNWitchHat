package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value;

import com.google.common.collect.ImmutableList;
import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RenderValue {
    public final float scale;
    public final Vec3 rot;
    public final float pX;
    public final float pY;
    public final float pZ;
    public final boolean onDebug;
    public final ModelVal modelVal;
    public final boolean defaultRot;
    public final ImmutableList<PoseStage> poseStages;

    public enum PoseStage {
        TRANSLATE,
        MULPOSE,
        SCALE
    }

    public RenderValue(ModelVal modelVal, float scale, Vec3 rot, float pX, float pY, float pZ, boolean onDebug, boolean defaultRot, ImmutableList<PoseStage> poseStages) {
        this.modelVal = modelVal;
        this.scale = scale;
        this.rot = rot;
        this.pX = pX;
        this.pY = pY;
        this.pZ = pZ;
        this.onDebug = onDebug;
        this.defaultRot = defaultRot;
        this.poseStages = poseStages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class ModelVal {
        public final BlockState blockstate;
        public final Function<ItemStack, HatBakedModels.ModelType> modelType;
        public final Function<ItemStack, RenderType> renderType;

        public static ModelVal asBlockItem() {
            return new Builder().blockstateAsItem().build();
        }

        public ModelVal(BlockState blockstate, Function<ItemStack, HatBakedModels.ModelType> modelType, Function<ItemStack, RenderType> renderType) {
            this.blockstate = blockstate;
            this.modelType = modelType;
            this.renderType = renderType;
        }

        public static class Builder {
            private BlockState blockstate;
            private Function<ItemStack, HatBakedModels.ModelType> modelType;
            private Function<ItemStack, RenderType> renderType;
            private boolean isEmpty = true;

            public Builder blockstate(BlockState state) {
                this.blockstate = state;
                isEmpty = false;
                return this;
            }

            public Builder blockstateAsItem() {
                this.blockstate = Blocks.AIR.defaultBlockState();
                isEmpty = false;
                return this;
            }

            public Builder setModel(HatBakedModels.ModelType model) {
                this.modelType = stack -> model;
                isEmpty = false;
                return this;
            }

            public Builder setModel(Function<ItemStack, HatBakedModels.ModelType> model) {
                this.modelType = model;
                return this;
            }

            public Builder setRenderType(RenderType rend) {
                this.renderType = stack -> rend;
                isEmpty = false;
                return this;
            }

            public Builder setRenderType(Function<ItemStack, RenderType> rend) {
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
        private float spX = 0f;
        private float spY = 0f;
        private float spZ = 0f;
        private boolean onDebug;
        private List<PoseStage> poseStages = new ArrayList<>();
        private boolean revertRot = false;

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

        public Builder setModel(HatBakedModels.ModelType model) {
            this.modelVal.setModel(stack -> model);
            return this;
        }

        public Builder setModel(Function<ItemStack, HatBakedModels.ModelType> model) {
            this.modelVal.setModel(model);
            return this;
        }

        public Builder setRenderType(RenderType rend) {
            this.modelVal.setRenderType(stack -> rend);
            return this;
        }

        public Builder setRenderType(Function<ItemStack, RenderType> rend) {
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

        public Builder center(float x, float y, float z) {
            this.spX = x;
            this.spY = y;
            this.spZ = z;

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

            return new RenderValue(modelVal.build(), scale, rot, pX, pY, pZ, onDebug, revertRot, poseList);
        }
    }
}
