package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.item.ColoredItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchCandleItem;
import io.github.magishanpixel.mgn_witch_hat.item.WitchHatItem;
import io.github.magishanpixel.mgn_witch_hat.misc.*;
import net.blay09.mods.balm.core.DeferredHolder;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

                    ItemStack premadestack1 = createWithDecorStack(List.of(
                            DecorType.JACK_O_LANTERN,
                            DecorType.RIBBON
                    ), List.of(
                            new DataDecor(Items.JACK_O_LANTERN.getDefaultInstance(), DecorPlacement.RIGHT),
                            new DataDecor(PURPLE_RIBBON.createStack(), DecorPlacement.REGULAR)
                    ));

                    premadestack1.set(ModDataComponents.HAS_BAND.value(), true);
                    premadestack1.set(ModDataComponents.BAND_COLOR.value(), DyeColor.PURPLE);
                    premadestack1.set(ModDataComponents.BUCKLE_TYPE.value(), BuckleType.GOLD);

                    ItemStack premadestack2 = createWithDecorStack(List.of(
                            DecorType.SKULL,
                            DecorType.FLOWER,
                            DecorType.LANTERN
                    ), List.of(
                            new DataDecor(Items.CREEPER_HEAD.getDefaultInstance(), DecorPlacement.RIGHT),
                            new DataDecor(Items.PINK_TULIP.getDefaultInstance(), DecorPlacement.RIGHT),
                            new DataDecor(Items.LANTERN.getDefaultInstance(), DecorPlacement.REGULAR)
                    ));

                    premadestack2.set(ModDataComponents.HAS_BAND.value(), true);
                    premadestack2.set(ModDataComponents.BAND_COLOR.value(), DyeColor.BROWN);
                    premadestack2.set(ModDataComponents.WITCH_HAT_COLOR.value(), DyeColor.GREEN);

                    ItemStack premadestack3 = createWithDecorStack(List.of(
                            DecorType.SKULL,
                            DecorType.FEATHER,
                            DecorType.CANDLE,
                            DecorType.LANTERN
                    ), List.of(
                            new DataDecor(Items.SKELETON_SKULL.getDefaultInstance(), DecorPlacement.RIGHT),
                            new DataDecor(RAVEN_FEATHER.createStack(), DecorPlacement.RIGHT),
                            new DataDecor(WITCH_CANDLE.createStack(), DecorPlacement.REGULAR),
                            new DataDecor(Items.SOUL_LANTERN.getDefaultInstance(), DecorPlacement.BACK)
                    ));

                    premadestack3.set(ModDataComponents.WITCH_HAT_COLOR.value(), DyeColor.BLACK);

                    v.accept(premadestack1);
                    v.accept(premadestack2);
                    v.accept(premadestack3);

                    for (DyeColor col : DyeColor.values()) {
                        ItemStack stack = WITCH_HAT.createStack();
                        stack.set(ModDataComponents.WITCH_HAT_COLOR.value(), col);
                        v.accept(stack);
                    }

                    AcceptAsDecor acceptAsDecor = (decorType, placement, stackList) -> {
                        for (ItemStack stack : stackList) {
                            v.accept(createWithDecorStack(decorType, placement, stack));
                        }
                    };

                    acceptAsDecor.run(DecorType.SKULL, DecorPlacement.RIGHT, List.of(
                            Items.SKELETON_SKULL.getDefaultInstance(),
                            Items.WITHER_SKELETON_SKULL.getDefaultInstance(),
                            Items.CREEPER_HEAD.getDefaultInstance(),
                            Items.ZOMBIE_HEAD.getDefaultInstance()
                    ));

                    ItemStack CANDLE_LIT_STACK = WITCH_CANDLE.createStack();
                    CANDLE_LIT_STACK.set(ModDataComponents.CANDLE_LIT.value(), true);

                    acceptAsDecor.run(DecorType.CANDLE, DecorPlacement.RIGHT, List.of(
                            WITCH_CANDLE.createStack(),
                            CANDLE_LIT_STACK
                    ));

                    acceptAsDecor.run(DecorType.FEATHER, DecorPlacement.RIGHT, List.of(
                            Items.FEATHER.getDefaultInstance(),
                            RAVEN_FEATHER.createStack()
                    ));

                    acceptAsDecor.run(DecorType.PUMPKIN, DecorPlacement.RIGHT, List.of(
                            Items.PUMPKIN.getDefaultInstance(),
                            Items.CARVED_PUMPKIN.getDefaultInstance()
                    ));

                    acceptAsDecor.run(DecorType.JACK_O_LANTERN, DecorPlacement.RIGHT, List.of(Items.JACK_O_LANTERN.getDefaultInstance()));

                    acceptAsDecor.run(DecorType.LANTERN, DecorPlacement.RIGHT, List.of(Items.LANTERN.getDefaultInstance(), Items.SOUL_LANTERN.getDefaultInstance()));
                    acceptAsDecor.run(DecorType.LANTERN, DecorPlacement.BACK, List.of(Items.LANTERN.getDefaultInstance(), Items.SOUL_LANTERN.getDefaultInstance()));

                    acceptAsDecor.run(DecorType.FLOWER, DecorPlacement.RIGHT, List.of(
                            Items.ORANGE_TULIP.getDefaultInstance(),
                            Items.PINK_TULIP.getDefaultInstance(),
                            Items.RED_TULIP.getDefaultInstance(),
                            Items.WHITE_TULIP.getDefaultInstance()
                    ));

                })
        ).asHolder();
    }

    private interface AcceptAsDecor {
        void run(DecorType decorType, DecorPlacement placement, List<ItemStack> stackList);
    }

    private static ItemStack createWithDecorStack(DecorType decorType, DecorPlacement placement, ItemStack stackDecor) {
        ItemStack stack = ModItems.WITCH_HAT.createStack();

        if (!stack.has(ModDataComponents.DECOR_TYPES.value())) {
            stack.set(ModDataComponents.DECOR_TYPES.value(), new HashMap<>());
        }
        Map<DecorType, DataDecor> m = stack.get(ModDataComponents.DECOR_TYPES.value());

        m.put(decorType, new DataDecor(stackDecor, placement));

        stack.set(ModDataComponents.DECOR_TYPES.value(), m);

        return stack;
    }

    private static ItemStack createWithDecorStack(List<DecorType> decorList, List<DataDecor> dataList) {
        ItemStack stack = ModItems.WITCH_HAT.createStack();

        if (!stack.has(ModDataComponents.DECOR_TYPES.value())) {
            stack.set(ModDataComponents.DECOR_TYPES.value(), new HashMap<>());
        }
        Map<DecorType, DataDecor> m = stack.get(ModDataComponents.DECOR_TYPES.value());

        for (int i = 0; i < decorList.size(); i++) {
            m.put(decorList.get(i), dataList.get(i));
        }

        stack.set(ModDataComponents.DECOR_TYPES.value(), m);

        return stack;
    }

    public static void addToCreativeTab(Consumer<ItemStack> v) {}
}
