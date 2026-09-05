package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
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
        //getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(ModItems.STRAW_HAT.asItem());
        getOrCreateTagBuilder(MGNConstants.ItemTags.WITCH_HAT_DECOR)
                .addTag(MGNConstants.ItemTags.Decor.SKULLS)
                .addOptionalTag(ItemTags.SMALL_FLOWERS)
                .add(Items.LANTERN)
        ;

        getOrCreateTagBuilder(MGNConstants.ItemTags.Decor.SKULLS)
                .add(Items.SKELETON_SKULL)
                .add(Items.WITHER_SKELETON_SKULL)
                .add(Items.CREEPER_HEAD)
                .add(Items.ZOMBIE_HEAD)
        ;

    }
}
