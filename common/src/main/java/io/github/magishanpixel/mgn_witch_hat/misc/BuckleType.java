package io.github.magishanpixel.mgn_witch_hat.misc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;

import java.util.function.IntFunction;

public enum BuckleType implements StringRepresentable {
    IRON(0, "iron", 0xfffff),
    GOLD(1, "gold", 0xe9b115),
    DIAMOND(2, "diamond", 0x4aedd9)
    ;

    private static final IntFunction<BuckleType> BY_ID = ByIdMap.continuous(BuckleType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<BuckleType> CODEC = StringRepresentable.fromEnum(BuckleType::values);
    public static final StreamCodec<ByteBuf, BuckleType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, BuckleType::getId);

    private final String name;
    private final int color;
    private final int id;

    BuckleType(int id, String name, int color) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCol() {
        return color;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
