package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.minecraft.world.item.Item;

public class BuckleItem extends Item {
    private final BuckleType buckleType;

    public BuckleItem(Properties properties, BuckleType type) {
        super(properties);
        buckleType = type;
    }

    public BuckleType getBuckleType() {
        return this.buckleType;
    }
}
