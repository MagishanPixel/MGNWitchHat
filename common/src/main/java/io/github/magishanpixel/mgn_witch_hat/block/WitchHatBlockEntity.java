package io.github.magishanpixel.mgn_witch_hat.block;

import io.github.magishanpixel.mgn_witch_hat.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WitchHatBlockEntity extends BlockEntity {
    private ItemStack hatStack = ItemStack.EMPTY;

    public WitchHatBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.WITCH_HAT_ENTITY.value(), pos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlocks.WITCH_HAT_ENTITY.value();
    }

    public void setHatStack(ItemStack hatStack) {
        this.hatStack = hatStack;
    }

    public ItemStack getHatStack() {
        return this.hatStack;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (tag.contains("witch_hat_stack")) {
            hatStack = ItemStack.parseOptional(registries, tag.getCompound("witch_hat_stack"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("witch_hat_stack", hatStack.save(registries));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithFullMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
