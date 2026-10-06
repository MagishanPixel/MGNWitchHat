package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.block.WitchHatBlock;
import io.github.magishanpixel.mgn_witch_hat.block.WitchHatBlockEntity;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.blay09.mods.balm.world.level.block.entity.BalmBlockEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlocks {
    public static DeferredBlock WITCH_HAT_BLOCK;
    public static Holder<BlockEntityType<WitchHatBlockEntity>> WITCH_HAT_ENTITY;

    public static void initBlockEntity(BalmBlockEntityTypeRegistrar reg) {
        WITCH_HAT_ENTITY = reg.register("witch_hat", WitchHatBlockEntity::new, WITCH_HAT_BLOCK).asHolder();
    }

    public static void initBlock(BalmBlockRegistrar reg) {
        WITCH_HAT_BLOCK = reg.register("witch_hat", WitchHatBlock::new, p -> p.instabreak().sound(SoundType.WOOL).noLootTable()).asDeferredBlock();
    }
}
