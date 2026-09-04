package io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.value;

import io.github.magishanpixel.mgn_witch_hat.client.HatBakedModels;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RenderValue {
    public final BlockState blockstate;
    public final HatBakedModels.ModelType modelType;
    public final float scale;
    public final Vec3 rot;
    public final float pX;
    public final float pY;
    public final float pZ;
    public final RenderType renderType;
    public final boolean onDebug;

    public RenderValue(BlockState blockstate, HatBakedModels.ModelType modelType, float scale, Vec3 rot, float pX, float pY, float pZ, RenderType renderType, boolean onDebug) {
        this.blockstate = blockstate;
        this.modelType = modelType;
        this.scale = scale;
        this.rot = rot;
        this.pX = pX;
        this.pY = pY;
        this.pZ = pZ;
        this.renderType = renderType;
        this.onDebug = onDebug;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BlockState blockState;
        private HatBakedModels.ModelType modelType;
        private RenderType renderType;
        private float scale = 1f;
        private Vec3 rot = new Vec3(0,0,0);
        private float pX = 0f;
        private float pY = 0f;
        private float pZ = 0f;
        private boolean onDebug;

        public Builder blockstate(BlockState state) {
            this.blockState = state;
            return this;
        }

        public Builder setModel(HatBakedModels.ModelType model) {
            this.modelType = model;
            return this;
        }

        public Builder setRenderType(RenderType rend) {
            this.renderType = rend;
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

        public Builder debug() {
            this.onDebug = true;
            return this;
        }

        public RenderValue build() {
            return new RenderValue(blockState, modelType, scale, rot, pX, pY, pZ, renderType, onDebug);
        }
    }
}
