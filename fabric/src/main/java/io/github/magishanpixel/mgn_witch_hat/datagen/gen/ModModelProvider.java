package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        gen.generateFlatItem(ModItems.RAVEN_FEATHER.asItem(), ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(ModItems.HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.WHITE_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.ORANGE_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.MAGENTA_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIGHT_BLUE_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.YELLOW_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIME_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.PINK_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.GRAY_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIGHT_GRAY_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.CYAN_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.PURPLE_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BLUE_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BROWN_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.GREEN_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.RED_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BLACK_HAT_BAND.asItem(), ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(ModItems.WHITE_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.ORANGE_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.MAGENTA_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIGHT_BLUE_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.YELLOW_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIME_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.PINK_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.GRAY_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.LIGHT_GRAY_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.CYAN_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.PURPLE_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BLUE_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BROWN_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.GREEN_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.RED_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.BLACK_RIBBON.asItem(), ModelTemplates.FLAT_ITEM);
    }
}
