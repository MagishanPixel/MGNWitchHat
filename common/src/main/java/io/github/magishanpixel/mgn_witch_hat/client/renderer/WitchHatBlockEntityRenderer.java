package io.github.magishanpixel.mgn_witch_hat.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.block.WitchHatBlockEntity;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class WitchHatBlockEntityRenderer implements BlockEntityRenderer<WitchHatBlockEntity> {
    private static final ImmutableMap<Direction, DirVal> DIR_MAP;

    static {
        ImmutableMap.Builder<Direction, DirVal> builder = ImmutableMap.builder();
        builder.put(Direction.NORTH, new DirVal(0, 0, 0));
        builder.put(Direction.EAST, new DirVal(270, 0, -1));
        builder.put(Direction.WEST, new DirVal(90, -1, 0));
        builder.put(Direction.SOUTH, new DirVal(180, -1, -1));

        DIR_MAP = builder.build();
    }

    private record DirVal(float rot, int x, int z) {}

    @Override
    public boolean shouldRender(WitchHatBlockEntity blockEntity, Vec3 cameraPos) {
        return BlockEntityRenderer.super.shouldRender(blockEntity, cameraPos);
    }

    @Override
    public void render(WitchHatBlockEntity block, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStack stack = block.getHatStack();
        Direction direction = block.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        if (stack.isEmpty()) {
            stack = ModItems.WITCH_HAT.createStack();
        }

        poseStack.pushPose();
        poseStack.translate(0, 0.03, 0);

        DirVal dirVal = DIR_MAP.get(direction);

        poseStack.mulPose(Axis.YP.rotationDegrees(dirVal.rot));

        poseStack.translate(dirVal.x, 0, dirVal.z);
        poseStack.mulPose(Axis.XN.rotationDegrees(7.5f));
        WitchHatRenderer.renderAsItem(stack, poseStack, bufferSource, packedLight, packedOverlay);


        poseStack.popPose();
    }
}
