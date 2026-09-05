package io.github.magishanpixel.mgn_witch_hat.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record DataDecor(ItemStack stack, DecorPlacement placement) {
    public static final Codec<DataDecor> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.CODEC.fieldOf("stack").forGetter(DataDecor::stack),
            DecorPlacement.CODEC.fieldOf("placement").forGetter(DataDecor::placement)
    ).apply(inst, DataDecor::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DataDecor> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, DataDecor::stack,
            DecorPlacement.STREAM_CODEC, DataDecor::placement,
            DataDecor::new
    );
}
