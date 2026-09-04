package io.github.magishanpixel.mgn_witch_hat.init;

import com.mojang.serialization.Codec;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModDataComponents {

    public static Holder<DataComponentType<DyeColor>> WITCH_HAT_COLOR;
    public static Holder<DataComponentType<DyeColor>> BAND_COLOR;
    public static Holder<DataComponentType<BuckleType>> BUCKLE_TYPE;
    public static Holder<DataComponentType<Boolean>> HAS_BAND;
    public static Holder<DataComponentType<Map<DecorType, ItemStack>>> DECOR_TYPES;

    public static void init(BalmDataComponentTypeRegistrar reg) {
        WITCH_HAT_COLOR = reg.register("witch_hat_color", DyeColor.CODEC).asHolder();
        BAND_COLOR = reg.register("band_color", DyeColor.CODEC).asHolder();
        BUCKLE_TYPE = reg.register("buckle_type", BuckleType.CODEC).asHolder();
        HAS_BAND = reg.register("has_band", Codec.BOOL).asHolder();
        DECOR_TYPES = reg.register("decor_types",
                Codec.unboundedMap(DecorType.CODEC, ItemStack.OPTIONAL_CODEC)
        ).asHolder();

    }
}
