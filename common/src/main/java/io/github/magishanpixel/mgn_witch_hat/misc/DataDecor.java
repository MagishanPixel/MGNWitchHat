package io.github.magishanpixel.mgn_witch_hat.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

public record DataDecor(ItemStack stack, DecorPlacement placement) {
    public static final Codec<DataDecor> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.OPTIONAL_CODEC.fieldOf("stack").forGetter(DataDecor::stack),
            DecorPlacement.CODEC.fieldOf("placement").forGetter(DataDecor::placement)
    ).apply(inst, DataDecor::new));
}
