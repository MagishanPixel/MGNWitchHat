package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class BuckleItem extends Item {
    private static final Map<BuckleType, BuckleItem> ITEM_MAP = new HashMap<>();
    private final BuckleType buckleType;

    public BuckleItem(Properties properties, BuckleType type) {
        super(properties);
        buckleType = type;
        ITEM_MAP.put(type, this);
    }

    public BuckleType getBuckleType() {
        return this.buckleType;
    }

    public static BuckleItem byType(BuckleType type) {
        return ITEM_MAP.get(type);
    }
}
