package io.github.magishanpixel.mgn_witch_hat.misc;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;

public enum BuckleType implements StringRepresentable {
    IRON("iron"),
    GOLD("gold"),
    DIAMOND("diamond")
    ;

    public static final StringRepresentable.EnumCodec<BuckleType> CODEC = StringRepresentable.fromEnum(BuckleType::values);

    private final String name;

    BuckleType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
