package io.github.magishanpixel.mgn_witch_hat.misc;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;

public enum BuckleType implements StringRepresentable {
    IRON("iron", 0xfffff),
    GOLD("gold", 0xe9b115),
    DIAMOND("diamond", 0x4aedd9)
    ;

    public static final StringRepresentable.EnumCodec<BuckleType> CODEC = StringRepresentable.fromEnum(BuckleType::values);

    private final String name;
    private final int color;

    BuckleType(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public int getCol() {
        return color;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
