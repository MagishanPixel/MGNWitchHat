package io.github.magishanpixel.mgn_witch_hat.item;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class HatBandItem extends Item {
    private static final Map<DyeColor, HatBandItem> ITEM_MAP = new HashMap<>();

    @Nullable
    private final DyeColor dyeColor;


    public HatBandItem(Properties properties, @Nullable DyeColor col) {
        super(properties);

        dyeColor = col;
        if (col != null) {
            ITEM_MAP.put(col, this);
        }
    }

    @Nullable
    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public static HatBandItem byColor(DyeColor col) {
        return ITEM_MAP.get(col);
    }


}
