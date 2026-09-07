package io.github.magishanpixel.mgn_witch_hat.item;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ColoredItem extends Item {
    private static final Map<ItemType, Map<DyeColor, ColoredItem>> ITEM_MAP = new HashMap<>();

    public enum ItemType {
        HAT_BAND,
        RIBBON
    }

    @Nullable
    private final DyeColor dyeColor;


    public ColoredItem(Properties properties, @Nullable DyeColor col, ItemType itemType) {
        super(properties);

        dyeColor = col;
        if (col != null) {
            if (!ITEM_MAP.containsKey(itemType)) {
                ITEM_MAP.put(itemType, new HashMap<>());
            }
            ITEM_MAP.get(itemType).put(col, this);
        }
    }

    @Nullable
    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public static ColoredItem byColor(ItemType type, DyeColor col) {
        return ITEM_MAP.get(type).get(col);
    }


}
