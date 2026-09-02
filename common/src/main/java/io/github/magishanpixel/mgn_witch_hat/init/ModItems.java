package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.item.BuckleItem;
import io.github.magishanpixel.mgn_witch_hat.item.HatBandItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchHatItem;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ModItems {
    public static DeferredItem WITCH_HAT;

    public static DeferredItem IRON_BUCKLE;
    public static DeferredItem DIAMOND_BUCKLE;
    public static DeferredItem GOLD_BUCKLE;

    public static DeferredItem HAT_BAND;
    public static DeferredItem WHITE_HAT_BAND;
    public static DeferredItem ORANGE_HAT_BAND;
    public static DeferredItem MAGENTA_HAT_BAND;
    public static DeferredItem LIGHT_BLUE_HAT_BAND;
    public static DeferredItem YELLOW_HAT_BAND;
    public static DeferredItem LIME_HAT_BAND;
    public static DeferredItem PINK_HAT_BAND;
    public static DeferredItem GRAY_HAT_BAND;
    public static DeferredItem LIGHT_GRAY_HAT_BAND;
    public static DeferredItem CYAN_HAT_BAND;
    public static DeferredItem PURPLE_HAT_BAND;
    public static DeferredItem BLUE_HAT_BAND;
    public static DeferredItem BROWN_HAT_BAND;
    public static DeferredItem GREEN_HAT_BAND;
    public static DeferredItem RED_HAT_BAND;
    public static DeferredItem BLACK_HAT_BAND;

    public static void init(BalmItemRegistrar reg) {
        WITCH_HAT = reg.register("witch_hat", p -> new WitchHatItem(p.component(ModDataComponents.HAS_BAND.value(), false)), p -> p.stacksTo(1)).asDeferredItem();

        IRON_BUCKLE = reg.register("iron_buckle", p -> new BuckleItem(p, BuckleType.IRON), p -> p.stacksTo(1)).asDeferredItem();
        DIAMOND_BUCKLE = reg.register("diamond_buckle", p -> new BuckleItem(p, BuckleType.DIAMOND), p -> p.stacksTo(1)).asDeferredItem();
        GOLD_BUCKLE = reg.register("gold_buckle", p -> new BuckleItem(p, BuckleType.GOLD), p -> p.stacksTo(1)).asDeferredItem();

        HAT_BAND = reg.register("hat_band", p -> new HatBandItem(p, null), p -> p.stacksTo(1)).asDeferredItem();

        WHITE_HAT_BAND = reg.register("white_hat_band", p -> new HatBandItem(p, DyeColor.WHITE), p -> p.stacksTo(1)).asDeferredItem();
        ORANGE_HAT_BAND = reg.register("orange_hat_band", p -> new HatBandItem(p, DyeColor.ORANGE), p -> p.stacksTo(1)).asDeferredItem();
        MAGENTA_HAT_BAND = reg.register("magenta_hat_band", p -> new HatBandItem(p, DyeColor.MAGENTA), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_BLUE_HAT_BAND = reg.register("light_blue_hat_band", p -> new HatBandItem(p, DyeColor.LIGHT_BLUE), p -> p.stacksTo(1)).asDeferredItem();
        YELLOW_HAT_BAND = reg.register("yellow_hat_band", p -> new HatBandItem(p, DyeColor.YELLOW), p -> p.stacksTo(1)).asDeferredItem();
        LIME_HAT_BAND = reg.register("lime_hat_band", p -> new HatBandItem(p, DyeColor.LIME), p -> p.stacksTo(1)).asDeferredItem();
        PINK_HAT_BAND = reg.register("pink_hat_band", p -> new HatBandItem(p, DyeColor.PINK), p -> p.stacksTo(1)).asDeferredItem();
        GRAY_HAT_BAND = reg.register("gray_hat_band", p -> new HatBandItem(p, DyeColor.GRAY), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_GRAY_HAT_BAND = reg.register("light_gray_hat_band", p -> new HatBandItem(p, DyeColor.LIGHT_GRAY), p -> p.stacksTo(1)).asDeferredItem();
        CYAN_HAT_BAND = reg.register("cyan_hat_band", p -> new HatBandItem(p, DyeColor.CYAN), p -> p.stacksTo(1)).asDeferredItem();
        PURPLE_HAT_BAND = reg.register("purple_hat_band", p -> new HatBandItem(p, DyeColor.PURPLE), p -> p.stacksTo(1)).asDeferredItem();
        BLUE_HAT_BAND = reg.register("blue_hat_band", p -> new HatBandItem(p, DyeColor.BLUE), p -> p.stacksTo(1)).asDeferredItem();
        BROWN_HAT_BAND = reg.register("brown_hat_band", p -> new HatBandItem(p, DyeColor.BROWN), p -> p.stacksTo(1)).asDeferredItem();
        GREEN_HAT_BAND = reg.register("green_hat_band", p -> new HatBandItem(p, DyeColor.GREEN), p -> p.stacksTo(1)).asDeferredItem();
        RED_HAT_BAND = reg.register("red_hat_band", p -> new HatBandItem(p, DyeColor.RED), p -> p.stacksTo(1)).asDeferredItem();
        BLACK_HAT_BAND = reg.register("black_hat_band", p -> new HatBandItem(p, DyeColor.BLACK), p -> p.stacksTo(1)).asDeferredItem();
    }

    public static void addToCreativeTab(Consumer<ItemStack> v) {
        v.accept(WITCH_HAT.createStack());
        v.accept(GOLD_BUCKLE.createStack());
        v.accept(IRON_BUCKLE.createStack());
        v.accept(DIAMOND_BUCKLE.createStack());
        v.accept(HAT_BAND.createStack());
        v.accept(WHITE_HAT_BAND.createStack());
        v.accept(ORANGE_HAT_BAND.createStack());
        v.accept(MAGENTA_HAT_BAND.createStack());
        v.accept(LIGHT_BLUE_HAT_BAND.createStack());
        v.accept(YELLOW_HAT_BAND.createStack());
        v.accept(LIME_HAT_BAND.createStack());
        v.accept(PINK_HAT_BAND.createStack());
        v.accept(GRAY_HAT_BAND.createStack());
        v.accept(LIGHT_GRAY_HAT_BAND.createStack());
        v.accept(CYAN_HAT_BAND.createStack());
        v.accept(PURPLE_HAT_BAND.createStack());
        v.accept(BLUE_HAT_BAND.createStack());
        v.accept(BROWN_HAT_BAND.createStack());
        v.accept(GREEN_HAT_BAND.createStack());
        v.accept(RED_HAT_BAND.createStack());
        v.accept(BLACK_HAT_BAND.createStack());

    }
}
