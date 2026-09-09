package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider<Item> {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(MGNConstants.ItemTags.BUCKLE)
                .add(Items.IRON_INGOT)
                .add(Items.DIAMOND)
                .add(Items.GOLD_INGOT)
        ;

        getOrCreateTagBuilder(MGNConstants.ItemTags.WITCH_HAT_DECOR)
                .addTag(MGNConstants.ItemTags.Decor.SKULLS)
                .addTag(MGNConstants.ItemTags.Decor.RIBBON)
                .addOptionalTag(ItemTags.SMALL_FLOWERS)
                .add(Items.LANTERN)
                .add(Items.MOSS_BLOCK)
                .addOptionalTag(ItemTags.CANDLES)
                .addTag(MGNConstants.ItemTags.Decor.FEATHER)
                .add(Items.PUMPKIN, Items.CARVED_PUMPKIN, Items.JACK_O_LANTERN)
        ;

        getOrCreateTagBuilder(MGNConstants.ItemTags.Decor.FEATHER)
                .add(Items.FEATHER)
                .add(ModItems.RAVEN_FEATHER.asItem())
        ;

        getOrCreateTagBuilder(MGNConstants.ItemTags.Decor.SKULLS)
                .add(Items.SKELETON_SKULL)
                .add(Items.WITHER_SKELETON_SKULL)
                .add(Items.CREEPER_HEAD)
                .add(Items.ZOMBIE_HEAD)
        ;
        
        getOrCreateTagBuilder(MGNConstants.ItemTags.HAT_BAND)
                .add(ModItems.HAT_BAND.asItem())
                .add(ModItems.WHITE_HAT_BAND.asItem())
                .add(ModItems.ORANGE_HAT_BAND.asItem())
                .add(ModItems.MAGENTA_HAT_BAND.asItem())
                .add(ModItems.LIGHT_BLUE_HAT_BAND.asItem())
                .add(ModItems.YELLOW_HAT_BAND.asItem())
                .add(ModItems.LIME_HAT_BAND.asItem())
                .add(ModItems.PINK_HAT_BAND.asItem())
                .add(ModItems.GRAY_HAT_BAND.asItem())
                .add(ModItems.LIGHT_GRAY_HAT_BAND.asItem())
                .add(ModItems.CYAN_HAT_BAND.asItem())
                .add(ModItems.PURPLE_HAT_BAND.asItem())
                .add(ModItems.BLUE_HAT_BAND.asItem())
                .add(ModItems.BROWN_HAT_BAND.asItem())
                .add(ModItems.GREEN_HAT_BAND.asItem())
                .add(ModItems.RED_HAT_BAND.asItem())
                .add(ModItems.BLACK_HAT_BAND.asItem())
        ;

        getOrCreateTagBuilder(MGNConstants.ItemTags.Decor.RIBBON)
                .add(ModItems.WHITE_RIBBON.asItem())
                .add(ModItems.ORANGE_RIBBON.asItem())
                .add(ModItems.MAGENTA_RIBBON.asItem())
                .add(ModItems.LIGHT_BLUE_RIBBON.asItem())
                .add(ModItems.YELLOW_RIBBON.asItem())
                .add(ModItems.LIME_RIBBON.asItem())
                .add(ModItems.PINK_RIBBON.asItem())
                .add(ModItems.GRAY_RIBBON.asItem())
                .add(ModItems.LIGHT_GRAY_RIBBON.asItem())
                .add(ModItems.CYAN_RIBBON.asItem())
                .add(ModItems.PURPLE_RIBBON.asItem())
                .add(ModItems.BLUE_RIBBON.asItem())
                .add(ModItems.BROWN_RIBBON.asItem())
                .add(ModItems.GREEN_RIBBON.asItem())
                .add(ModItems.RED_RIBBON.asItem())
                .add(ModItems.BLACK_RIBBON.asItem())
        ;

    }
}
