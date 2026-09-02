package io.github.magishanpixel.mgn_witch_hat.init;

import com.mojang.serialization.Codec;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.blay09.mods.balm.core.DeferredHolder;
import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.DyeColor;

public class ModDataComponents {

    public static Holder<DataComponentType<DyeColor>> WITCH_HAT_COLOR;
    public static Holder<DataComponentType<DyeColor>> ROBE_COLOR;
    public static Holder<DataComponentType<BuckleType>> BUCKLE_TYPE;
    public static Holder<DataComponentType<Boolean>> HAS_ROBE;

    public static void init(BalmDataComponentTypeRegistrar reg) {
        WITCH_HAT_COLOR = reg.register("witch_hat_color", DyeColor.CODEC).asHolder();
        ROBE_COLOR = reg.register("robe_color", DyeColor.CODEC).asHolder();
        BUCKLE_TYPE = reg.register("buckle_type", BuckleType.CODEC).asHolder();
        HAS_ROBE = reg.register("has_robe", Codec.BOOL).asHolder();
    }
}
