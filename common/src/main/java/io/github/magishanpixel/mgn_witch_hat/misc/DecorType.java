package io.github.magishanpixel.mgn_witch_hat.misc;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Predicate;

public enum DecorType implements StringRepresentable {
    SKULL("skull", v -> v.is(Items.SKELETON_SKULL))
    ;

    public static final StringRepresentable.EnumCodec<DecorType> CODEC = StringRepresentable.fromEnum(DecorType::values);

    private final String name;
    private final Predicate<ItemStack> check;

    DecorType(String name, Predicate<ItemStack> check) {
        this.name = name;
        this.check = check;
    }

    public static DecorType getType(ItemStack stack) {
        for (DecorType v : values()) {
            if (v.check.test(stack)) {
                return v;
            }
        }

        return null;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}