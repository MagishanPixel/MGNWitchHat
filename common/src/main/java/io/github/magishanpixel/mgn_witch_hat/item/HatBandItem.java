package io.github.magishanpixel.mgn_witch_hat.item;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class HatBandItem extends Item {
    @Nullable
    private final DyeColor dyeColor;

    public HatBandItem(Properties properties, @Nullable DyeColor col) {
        super(properties);

        dyeColor = col;
    }

    @Nullable
    public DyeColor getDyeColor() {
        return dyeColor;
    }


}
