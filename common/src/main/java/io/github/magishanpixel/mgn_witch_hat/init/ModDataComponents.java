package io.github.magishanpixel.mgn_witch_hat.init;

import com.mojang.serialization.Codec;
import io.github.magishanpixel.mgn_witch_hat.misc.BrimType;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import io.github.magishanpixel.mgn_witch_hat.misc.DataDecor;
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
    public static Holder<DataComponentType<Map<DecorType, DataDecor>>> DECOR_TYPES;
    public static Holder<DataComponentType<BrimType>> BRIM_TYPE;

    public static void init(BalmDataComponentTypeRegistrar reg) {
        WITCH_HAT_COLOR = reg.register("witch_hat_color", DyeColor.CODEC, DyeColor.STREAM_CODEC).asHolder();
        BAND_COLOR = reg.register("band_color", DyeColor.CODEC, DyeColor.STREAM_CODEC).asHolder();
        BUCKLE_TYPE = reg.register("buckle_type", BuckleType.CODEC, BuckleType.STREAM_CODEC).asHolder();
        HAS_BAND = reg.register("has_band", Codec.BOOL, ByteBufCodecs.BOOL).asHolder();
        DECOR_TYPES = reg.register("decor_types",
                Codec.unboundedMap(DecorType.CODEC, DataDecor.CODEC),
                ByteBufCodecs.map(
                        HashMap::new,
                        DecorType.STREAM_CODEC,
                        DataDecor.STREAM_CODEC
                )
        ).asHolder();
        BRIM_TYPE = reg.register("brim_type", BrimType.CODEC, BrimType.STREAM_CODEC).asHolder();

    }
}
