package io.github.magishanpixel.mgn_witch_hat.misc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum BrimType implements StringRepresentable {
    SHORT(0, "short"),
    WIDE(1, "wide")
    ;

    private static final IntFunction<BrimType> BY_ID = ByIdMap.continuous(BrimType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<BrimType> CODEC = StringRepresentable.fromEnum(BrimType::values);
    public static final StreamCodec<ByteBuf, BrimType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, BrimType::getId);

    private final int id;
    private final String name;

    public int getId() {
        return id;
    }

    BrimType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
