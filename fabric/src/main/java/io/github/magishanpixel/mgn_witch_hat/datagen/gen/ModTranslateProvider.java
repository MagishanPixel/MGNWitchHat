package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModTranslateProvider extends FabricLanguageProvider {
    public ModTranslateProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder t) {
        t.add(ModItems.WITCH_HAT.asItem(), "Witch Hat");
        t.add(ModItems.DIAMOND_BUCKLE.asItem(), "Diamond Buckle");
        t.add(ModItems.GOLD_BUCKLE.asItem(), "Gold Buckle");
        t.add(ModItems.IRON_BUCKLE.asItem(), "Iron Buckle");

        t.add(ModItems.HAT_BAND.asItem(), "Hat Band");
        t.add(ModItems.WHITE_HAT_BAND.asItem(), "White Hat Band");
        t.add(ModItems.ORANGE_HAT_BAND.asItem(), "Orange Hat Band");
        t.add(ModItems.MAGENTA_HAT_BAND.asItem(), "Magenta Hat Band");
        t.add(ModItems.LIGHT_BLUE_HAT_BAND.asItem(), "Light Blue Hat Band");
        t.add(ModItems.YELLOW_HAT_BAND.asItem(), "Yellow Hat Band");
        t.add(ModItems.LIME_HAT_BAND.asItem(), "Lime Hat Band");
        t.add(ModItems.PINK_HAT_BAND.asItem(), "Pink Hat Band");
        t.add(ModItems.GRAY_HAT_BAND.asItem(), "Gray Hat Band");
        t.add(ModItems.LIGHT_GRAY_HAT_BAND.asItem(), "Light Gray Hat Band");
        t.add(ModItems.CYAN_HAT_BAND.asItem(), "Cyan Hat Band");
        t.add(ModItems.PURPLE_HAT_BAND.asItem(), "Purple Hat Band");
        t.add(ModItems.BLUE_HAT_BAND.asItem(), "Blue Hat Band");
        t.add(ModItems.BROWN_HAT_BAND.asItem(), "Brown Hat Band");
        t.add(ModItems.GREEN_HAT_BAND.asItem(), "Green Hat Band");
        t.add(ModItems.RED_HAT_BAND.asItem(), "Red Hat Band");
        t.add(ModItems.BLACK_HAT_BAND.asItem(), "Black Hat Band");

        t.add(ModItems.WHITE_RIBBON.asItem(), "White Ribbon");
        t.add(ModItems.ORANGE_RIBBON.asItem(), "Orange Ribbon");
        t.add(ModItems.MAGENTA_RIBBON.asItem(), "Magenta Ribbon");
        t.add(ModItems.LIGHT_BLUE_RIBBON.asItem(), "Light Blue Ribbon");
        t.add(ModItems.YELLOW_RIBBON.asItem(), "Yellow Ribbon");
        t.add(ModItems.LIME_RIBBON.asItem(), "Lime Ribbon");
        t.add(ModItems.PINK_RIBBON.asItem(), "Pink Ribbon");
        t.add(ModItems.GRAY_RIBBON.asItem(), "Gray Ribbon");
        t.add(ModItems.LIGHT_GRAY_RIBBON.asItem(), "Light Gray Ribbon");
        t.add(ModItems.CYAN_RIBBON.asItem(), "Cyan Ribbon");
        t.add(ModItems.PURPLE_RIBBON.asItem(), "Purple Ribbon");
        t.add(ModItems.BLUE_RIBBON.asItem(), "Blue Ribbon");
        t.add(ModItems.BROWN_RIBBON.asItem(), "Brown Ribbon");
        t.add(ModItems.GREEN_RIBBON.asItem(), "Green Ribbon");
        t.add(ModItems.RED_RIBBON.asItem(), "Red Ribbon");
        t.add(ModItems.BLACK_RIBBON.asItem(), "Black Ribbon");

        t.add(ModItems.RAVEN_FEATHER.asItem(), "Raven Feather");

    }

}
