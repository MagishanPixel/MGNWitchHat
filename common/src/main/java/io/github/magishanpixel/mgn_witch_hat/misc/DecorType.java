package io.github.magishanpixel.mgn_witch_hat.misc;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public enum DecorType implements StringRepresentable {
    SKULL(0,"skull", v -> v.is(MGNConstants.ItemTags.Decor.SKULLS), true),
    LANTERN(1,"lantern", v -> v.is(Items.LANTERN) || v.is(Items.SOUL_LANTERN), true),
    FLOWER(2, "flower", v -> v.is(ItemTags.SMALL_FLOWERS), true),
    CANDLE(3, "candle", v -> v.is(ItemTags.CANDLES), true),
    MOSS(4, "moss", v -> v.is(Items.MOSS_BLOCK), false),
    RIBBON(5, "ribbon", v -> v.is(MGNConstants.ItemTags.Decor.RIBBON), false);
    ;
    private final String name;
    private final int id;
    private final Predicate<ItemStack> check;

    private final boolean sided;
    
    DecorType(int id, String name, Predicate<ItemStack> check, boolean sided) {
        this.name = name;
        this.check = check;
        this.id = id;
        this.sided = sided;
    }

    public int getId() {
        return id;
    }

    public boolean isSided() {
        return sided;
    }

    public static DecorType getType(ItemStack stack) {
        for (DecorType v : values()) {
            if (v.check.test(stack)) {
                return v;
            }
        }

        return null;
    }

    private static final IntFunction<DecorType> BY_ID = ByIdMap.continuous(DecorType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<DecorType> CODEC = StringRepresentable.fromEnum(DecorType::values);
    public static final StreamCodec<ByteBuf, DecorType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, DecorType::getId);

    @Override
    public String getSerializedName() {
        return name;
    }
}