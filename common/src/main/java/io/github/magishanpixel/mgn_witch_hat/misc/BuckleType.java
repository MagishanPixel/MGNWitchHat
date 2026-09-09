package io.github.magishanpixel.mgn_witch_hat.misc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public enum BuckleType implements StringRepresentable {
    IRON(0, "iron", v -> v.is(Items.IRON_INGOT)),
    GOLD(1, "gold", v -> v.is(Items.GOLD_INGOT)),
    DIAMOND(2, "diamond", v -> v.is(Items.DIAMOND))
    ;

    private static final IntFunction<BuckleType> BY_ID = ByIdMap.continuous(BuckleType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<BuckleType> CODEC = StringRepresentable.fromEnum(BuckleType::values);
    public static final StreamCodec<ByteBuf, BuckleType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, BuckleType::getId);

    private final String name;
    private final int id;
    private final Predicate<ItemStack> check;

    public static BuckleType getType(ItemStack stack) {
        for (BuckleType v : values()) {
            if (v.check.test(stack)) {
                return v;
            }
        }

        return null;
    }

    BuckleType(int id, String name, Predicate<ItemStack> check) {
        this.name = name;
        this.id = id;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
