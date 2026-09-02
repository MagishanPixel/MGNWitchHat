package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.item.BuckleItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchHatItem;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModItems {
    public static DeferredItem WITCH_HAT;

    public static DeferredItem IRON_BUCKLE;
    public static DeferredItem DIAMOND_BUCKLE;
    public static DeferredItem GOLD_BUCKLE;

    public static void init(BalmItemRegistrar reg) {
        WITCH_HAT = reg.register("witch_hat", WitchHatItem::new, p -> p.stacksTo(1)).asDeferredItem();

        IRON_BUCKLE = reg.register("iron_buckle", p -> new BuckleItem(p, BuckleType.IRON), p -> p.stacksTo(1)).asDeferredItem();
        DIAMOND_BUCKLE = reg.register("diamond_buckle", p -> new BuckleItem(p, BuckleType.DIAMOND), p -> p.stacksTo(1)).asDeferredItem();
        GOLD_BUCKLE = reg.register("gold_buckle", p -> new BuckleItem(p, BuckleType.GOLD), p -> p.stacksTo(1)).asDeferredItem();
    }

    public static void addToCreativeTab(Consumer<ItemStack> v) {
        v.accept(WITCH_HAT.createStack());
        v.accept(GOLD_BUCKLE.createStack());
        v.accept(IRON_BUCKLE.createStack());
        v.accept(DIAMOND_BUCKLE.createStack());

    }
}
