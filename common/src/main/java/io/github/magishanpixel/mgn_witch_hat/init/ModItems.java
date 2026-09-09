package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.item.ColoredItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchCandleItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchHatItem;
import io.github.magishanpixel.mgn_witch_hat.misc.BrimType;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.blay09.mods.balm.core.DeferredHolder;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ModItems {
    public static DeferredItem WITCH_HAT;

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

    public static DeferredItem WHITE_RIBBON;
    public static DeferredItem ORANGE_RIBBON;
    public static DeferredItem MAGENTA_RIBBON;
    public static DeferredItem LIGHT_BLUE_RIBBON;
    public static DeferredItem YELLOW_RIBBON;
    public static DeferredItem LIME_RIBBON;
    public static DeferredItem PINK_RIBBON;
    public static DeferredItem GRAY_RIBBON;
    public static DeferredItem LIGHT_GRAY_RIBBON;
    public static DeferredItem CYAN_RIBBON;
    public static DeferredItem PURPLE_RIBBON;
    public static DeferredItem BLUE_RIBBON;
    public static DeferredItem BROWN_RIBBON;
    public static DeferredItem GREEN_RIBBON;
    public static DeferredItem RED_RIBBON;
    public static DeferredItem BLACK_RIBBON;

    public static DeferredItem RAVEN_FEATHER;
    public static DeferredItem WITCH_CANDLE;
    public static DeferredItem WITCH_CRYSTAL_DUST;

    public static Holder<CreativeModeTab> MAIN_TAB;

    public static void init(BalmItemRegistrar reg) {
        WITCH_HAT = reg.register("witch_hat", p -> new WitchHatItem(p.component(ModDataComponents.HAS_BAND.value(), false).component(ModDataComponents.BRIM_TYPE.value(), BrimType.SHORT)), p -> p.stacksTo(1)).asDeferredItem();

        HAT_BAND = reg.register("hat_band", p -> new ColoredItem(p, null, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();

        WHITE_HAT_BAND = reg.register("white_hat_band", p -> new ColoredItem(p, DyeColor.WHITE, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        ORANGE_HAT_BAND = reg.register("orange_hat_band", p -> new ColoredItem(p, DyeColor.ORANGE, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        MAGENTA_HAT_BAND = reg.register("magenta_hat_band", p -> new ColoredItem(p, DyeColor.MAGENTA, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_BLUE_HAT_BAND = reg.register("light_blue_hat_band", p -> new ColoredItem(p, DyeColor.LIGHT_BLUE, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        YELLOW_HAT_BAND = reg.register("yellow_hat_band", p -> new ColoredItem(p, DyeColor.YELLOW, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        LIME_HAT_BAND = reg.register("lime_hat_band", p -> new ColoredItem(p, DyeColor.LIME, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        PINK_HAT_BAND = reg.register("pink_hat_band", p -> new ColoredItem(p, DyeColor.PINK, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        GRAY_HAT_BAND = reg.register("gray_hat_band", p -> new ColoredItem(p, DyeColor.GRAY, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_GRAY_HAT_BAND = reg.register("light_gray_hat_band", p -> new ColoredItem(p, DyeColor.LIGHT_GRAY, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        CYAN_HAT_BAND = reg.register("cyan_hat_band", p -> new ColoredItem(p, DyeColor.CYAN, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        PURPLE_HAT_BAND = reg.register("purple_hat_band", p -> new ColoredItem(p, DyeColor.PURPLE, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        BLUE_HAT_BAND = reg.register("blue_hat_band", p -> new ColoredItem(p, DyeColor.BLUE, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        BROWN_HAT_BAND = reg.register("brown_hat_band", p -> new ColoredItem(p, DyeColor.BROWN, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        GREEN_HAT_BAND = reg.register("green_hat_band", p -> new ColoredItem(p, DyeColor.GREEN, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        RED_HAT_BAND = reg.register("red_hat_band", p -> new ColoredItem(p, DyeColor.RED, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();
        BLACK_HAT_BAND = reg.register("black_hat_band", p -> new ColoredItem(p, DyeColor.BLACK, ColoredItem.ItemType.HAT_BAND), p -> p.stacksTo(1)).asDeferredItem();

        WHITE_RIBBON = reg.register("white_ribbon", p -> new ColoredItem(p, DyeColor.WHITE, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        ORANGE_RIBBON = reg.register("orange_ribbon", p -> new ColoredItem(p, DyeColor.ORANGE, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        MAGENTA_RIBBON = reg.register("magenta_ribbon", p -> new ColoredItem(p, DyeColor.MAGENTA, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_BLUE_RIBBON = reg.register("light_blue_ribbon", p -> new ColoredItem(p, DyeColor.LIGHT_BLUE, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        YELLOW_RIBBON = reg.register("yellow_ribbon", p -> new ColoredItem(p, DyeColor.YELLOW, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        LIME_RIBBON = reg.register("lime_ribbon", p -> new ColoredItem(p, DyeColor.LIME, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        PINK_RIBBON = reg.register("pink_ribbon", p -> new ColoredItem(p, DyeColor.PINK, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        GRAY_RIBBON = reg.register("gray_ribbon", p -> new ColoredItem(p, DyeColor.GRAY, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        LIGHT_GRAY_RIBBON = reg.register("light_gray_ribbon", p -> new ColoredItem(p, DyeColor.LIGHT_GRAY, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        CYAN_RIBBON = reg.register("cyan_ribbon", p -> new ColoredItem(p, DyeColor.CYAN, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        BLUE_RIBBON = reg.register("blue_ribbon", p -> new ColoredItem(p, DyeColor.BLUE, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        BROWN_RIBBON = reg.register("brown_ribbon", p -> new ColoredItem(p, DyeColor.BROWN, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        GREEN_RIBBON = reg.register("green_ribbon", p -> new ColoredItem(p, DyeColor.GREEN, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        RED_RIBBON = reg.register("red_ribbon", p -> new ColoredItem(p, DyeColor.RED, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        BLACK_RIBBON = reg.register("black_ribbon", p -> new ColoredItem(p, DyeColor.BLACK, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();
        PURPLE_RIBBON = reg.register("purple_ribbon", p -> new ColoredItem(p, DyeColor.PURPLE, ColoredItem.ItemType.RIBBON), p -> p.stacksTo(1)).asDeferredItem();

        RAVEN_FEATHER = reg.register("raven_feather", Item::new, p -> p.stacksTo(1)).asDeferredItem();
        WITCH_CANDLE = reg.register("witch_candle", p -> new WitchCandleItem(p.component(ModDataComponents.CANDLE_LIT.value(), false)), p -> p.stacksTo(1)).asDeferredItem();
        WITCH_CRYSTAL_DUST = reg.register("witch_crystal_dust", Item::new).asDeferredItem();
    }

    public static void creativeTabInit(BalmCreativeModeTabRegistrar reg) {
        MAIN_TAB = reg.register("main_tab", (location, builder) -> builder
                .title(Component.translatable("itemgroup.mgn_witch_hat"))
                .icon(WITCH_HAT::createStack)
                .displayItems((itemDisplayParameters, v) -> {
                    v.accept(WITCH_HAT);
                    ItemStack a = WITCH_HAT.createStack();
                    a.set(ModDataComponents.BRIM_TYPE.value(), BrimType.WIDE);
                    v.accept(a);

                    v.accept(WITCH_CRYSTAL_DUST);
                    v.accept(RAVEN_FEATHER);
                    v.accept(WITCH_CANDLE);

                    v.accept(HAT_BAND);

                    v.accept(WHITE_HAT_BAND);
                    v.accept(ORANGE_HAT_BAND);
                    v.accept(MAGENTA_HAT_BAND);
                    v.accept(LIGHT_BLUE_HAT_BAND);
                    v.accept(YELLOW_HAT_BAND);
                    v.accept(LIME_HAT_BAND);
                    v.accept(PINK_HAT_BAND);
                    v.accept(GRAY_HAT_BAND);
                    v.accept(LIGHT_GRAY_HAT_BAND);
                    v.accept(CYAN_HAT_BAND);
                    v.accept(PURPLE_HAT_BAND);
                    v.accept(BLUE_HAT_BAND);
                    v.accept(BROWN_HAT_BAND);
                    v.accept(GREEN_HAT_BAND);
                    v.accept(RED_HAT_BAND);
                    v.accept(BLACK_HAT_BAND);

                    v.accept(WHITE_RIBBON);
                    v.accept(ORANGE_RIBBON);
                    v.accept(MAGENTA_RIBBON);
                    v.accept(LIGHT_BLUE_RIBBON);
                    v.accept(YELLOW_RIBBON);
                    v.accept(LIME_RIBBON);
                    v.accept(PINK_RIBBON);
                    v.accept(GRAY_RIBBON);
                    v.accept(LIGHT_GRAY_RIBBON);
                    v.accept(CYAN_RIBBON);
                    v.accept(PURPLE_RIBBON);
                    v.accept(BLUE_RIBBON);
                    v.accept(BROWN_RIBBON);
                    v.accept(GREEN_RIBBON);
                    v.accept(RED_RIBBON);
                    v.accept(BLACK_RIBBON);

                })
        ).asHolder();
    }

    public static void addToCreativeTab(Consumer<ItemStack> v) {}
}
