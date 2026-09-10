package io.github.magishanpixel.mgn_witch_hat.misc;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
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
import java.util.function.Supplier;

public enum DecorType implements StringRepresentable {
    // I would add more but ran out of ideas :<
    // I'm planning to add more in the future when I got the ideas anyway...
    SKULL(0,"skull", v -> v.is(MGNConstants.ItemTags.Decor.SKULLS), true, true),
    LANTERN(1,"lantern", v -> v.is(Items.LANTERN) || v.is(Items.SOUL_LANTERN), true, true),
    FLOWER(2, "flower", v -> v.is(ItemTags.SMALL_FLOWERS), true, true),
    CANDLE(3, "candle", v -> v.is(ModItems.WITCH_CANDLE.asItem()), true),
    RIBBON(4, "ribbon", v -> v.is(MGNConstants.ItemTags.Decor.RIBBON)),
    FEATHER(5, "feather", v -> v.is(MGNConstants.ItemTags.Decor.FEATHER), true),
    PUMPKIN(6, "pumpkin", v -> v.is(Items.PUMPKIN) || v.is(Items.CARVED_PUMPKIN), true, true),
    JACK_O_LANTERN(7, "jack_o_lantern", v -> v.is(Items.JACK_O_LANTERN), true, true)
    ;
    private final String name;
    private final int id;
    private final Predicate<ItemStack> check;

    private final boolean sided;
    private final boolean allowBack;

    DecorType(int id, String name, Predicate<ItemStack> check) {
        this(id, name, check, false, false);
    }

    DecorType(int id, String name, Predicate<ItemStack> check, boolean sided) {
        this(id, name, check, sided, false);
    }

    DecorType(int id, String name, Predicate<ItemStack> check, boolean sided, boolean allowBack) {
        this.name = name;
        this.check = check;
        this.id = id;
        this.sided = sided;
        this.allowBack = allowBack;
    }

    public int getId() {
        return id;
    }

    public boolean isSided() {
        return sided;
    }

    public boolean allowBack() {
        return allowBack;
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