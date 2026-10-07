package io.github.magishanpixel.mgn_witch_hat.entity;

import io.github.magishanpixel.mgn_witch_hat.init.ModEntities;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.decoration.BlockAttachedEntity;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class WitchHatDisplayEntity extends Entity {
    private static final EntityDataAccessor<ItemStack> HAT_STACK = SynchedEntityData.defineId(WitchHatDisplayEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> ROTATION = SynchedEntityData.defineId(WitchHatDisplayEntity.class, EntityDataSerializers.INT);
    private BlockPos pos = new BlockPos(0,0,0);
    private int interval = 0;

    public WitchHatDisplayEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public WitchHatDisplayEntity(Level level, BlockPos pos, ItemStack stack, int rot) {
        super(ModEntities.WITCH_HAT_DISPLAY.value(), level);
        this.entityData.set(HAT_STACK, stack);
        this.pos = pos;
        this.entityData.set(ROTATION, rot);
        this.moveTo(pos.getBottomCenter());
    }

    public int getRotation() {
        return entityData.get(ROTATION);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            if (!this.isRemoved() && !this.level().isClientSide) {
                this.kill();
                this.markHurt();
                level().playSound(null,getX(), getY(), getZ(), SoundEvents.WOOL_BREAK, SoundSource.BLOCKS);

                Entity entity = source.getEntity();

                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.hasInfiniteMaterials()) {
                        return true;
                    }
                }

                this.dropItem(source.getEntity());
            }

            return true;
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide()) {
            interval++;

            if (interval >= 100) {
                if (!survives()) {
                    this.dropItem(null);
                    this.discard();
                }
            }
        }
    }

    public boolean survives() {
        if (!this.level().noCollision(this)) {
            return false;
        } else {
            BlockState blockstate = this.level().getBlockState(pos.below());
            boolean flag = blockstate.isSolid() || DiodeBlock.isDiode(blockstate);
            return flag && this.level().getEntities(this, this.getBoundingBox(), (v) -> v instanceof BlockAttachedEntity).isEmpty();
        }
    }

    public ItemStack getHatStack() {
        return this.entityData.get(HAT_STACK);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HAT_STACK, ModItems.WITCH_HAT.createStack());
        builder.define(ROTATION, 0);
    }


    public void dropItem(@Nullable Entity entity) {
        ItemStack resStack = getHatStack();

        Containers.dropItemStack(level(),
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                resStack
                );
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("hat_stack")) {
            ItemStack stack = ItemStack.parseOptional(this.registryAccess(), compound.getCompound("hat_stack"));

            if (stack.isEmpty()) {
                stack = ModItems.WITCH_HAT.createStack();
            }

            this.entityData.set(HAT_STACK, stack);
        }

        if (compound.contains("hat_pos_x")) {
            this.pos = new BlockPos(
                    compound.getInt("hat_pos_x"),
                    compound.getInt("hat_pos_y"),
                    compound.getInt("hat_pos_z")

            );

            this.setPos(pos.getBottomCenter());
        }

        if (compound.contains("hat_rotation")) {
            this.entityData.set(ROTATION, compound.getInt("hat_rotation"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.put("hat_stack", getHatStack().save(this.registryAccess()));
        compound.putInt("hat_pos_x", pos.getX());
        compound.putInt("hat_pos_y", pos.getY());
        compound.putInt("hat_pos_z", pos.getZ());
        compound.putInt("hat_rotation", getRotation());
    }

    @Override
    public void move(MoverType type, Vec3 pos) {
        if (!this.level().isClientSide && !this.isRemoved() && pos.lengthSqr() > (double)0.0F) {
            this.kill();
            this.dropItem((Entity)null);
        }

    }

    @Override
    public void push(double x, double y, double z) {
        if (!this.level().isClientSide && !this.isRemoved() && x * x + y * y + z * z > (double)0.0F) {
            this.kill();
            this.dropItem((Entity)null);
        }

    }
}
