package io.github.magishanpixel.mgn_witch_hat.misc;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum DecorPlacement implements StringRepresentable {
    REGULAR(0,"regular"),
    BACK(1,"back"),
    LEFT(2,"left"),
    RIGHT(3,"right");

    private final String name;
    private final int id;

    DecorPlacement(int id, String name) {
        this.name = name;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private static final IntFunction<DecorPlacement> BY_ID = ByIdMap.continuous(DecorPlacement::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StringRepresentable.EnumCodec<DecorPlacement> CODEC = StringRepresentable.fromEnum(DecorPlacement::values);
    public static final StreamCodec<ByteBuf, DecorPlacement> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, DecorPlacement::getId);

}
