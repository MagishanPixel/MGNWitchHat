package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WITCH_HAT)
                .pattern(" # ")
                .pattern("#B#")
                .pattern("###")
                .define('#', Ingredient.of(ItemTags.WOOL))
                .define('B', Items.AMETHYST_SHARD)
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.WITCH_HAT), FabricRecipeProvider.has(ModItems.WITCH_HAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HAT_BAND.asItem())
                .pattern("  B")
                .pattern("BBB")
                .pattern("B  ")
                .define('B', Ingredient.of(ItemTags.WOOL))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.HAT_BAND), FabricRecipeProvider.has(ModItems.HAT_BAND))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_BUCKLE)
                .pattern("BBB")
                .pattern("B B")
                .pattern("BBB")
                .define('B', Ingredient.of(Items.GOLD_INGOT))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.GOLD_BUCKLE), FabricRecipeProvider.has(ModItems.GOLD_BUCKLE))
                .save(recipeOutput);

        hatBand(ModItems.WHITE_HAT_BAND, Items.WHITE_DYE, recipeOutput);
        hatBand(ModItems.ORANGE_HAT_BAND, Items.ORANGE_DYE, recipeOutput);
        hatBand(ModItems.MAGENTA_HAT_BAND, Items.MAGENTA_DYE, recipeOutput);
        hatBand(ModItems.LIGHT_BLUE_HAT_BAND, Items.LIGHT_BLUE_DYE, recipeOutput);
        hatBand(ModItems.YELLOW_HAT_BAND, Items.YELLOW_DYE, recipeOutput);
        hatBand(ModItems.LIME_HAT_BAND, Items.LIME_DYE, recipeOutput);
        hatBand(ModItems.PINK_HAT_BAND, Items.PINK_DYE, recipeOutput);
        hatBand(ModItems.GRAY_HAT_BAND, Items.GRAY_DYE, recipeOutput);
        hatBand(ModItems.CYAN_HAT_BAND, Items.CYAN_DYE, recipeOutput);
        hatBand(ModItems.PURPLE_HAT_BAND, Items.PURPLE_DYE, recipeOutput);
        hatBand(ModItems.BLUE_HAT_BAND, Items.BLUE_DYE, recipeOutput);
        hatBand(ModItems.BROWN_HAT_BAND, Items.BROWN_DYE, recipeOutput);
        hatBand(ModItems.GREEN_HAT_BAND, Items.GREEN_DYE, recipeOutput);
        hatBand(ModItems.RED_HAT_BAND, Items.RED_DYE, recipeOutput);
        hatBand(ModItems.BLACK_HAT_BAND, Items.BLACK_DYE, recipeOutput);

        /*
        for (DeferredItem deferredItem : List.of(
                ModItems.WHITE_RIBBON,
                ModItems.ORANGE_RIBBON,
                ModItems.MAGENTA_RIBBON,
                ModItems.LIGHT_BLUE_RIBBON,
                ModItems.YELLOW_RIBBON,
                ModItems.LIME_RIBBON,
                ModItems.PINK_RIBBON,
                ModItems.GRAY_RIBBON,
                ModItems.LIGHT_GRAY_RIBBON,
                ModItems.CYAN_RIBBON,
                ModItems.PURPLE_RIBBON,
                ModItems.BLUE_RIBBON,
                ModItems.BROWN_RIBBON,
                ModItems.GREEN_RIBBON,
                ModItems.RED_RIBBON,
                ModItems.BLACK_RIBBON
        )) {
            Item item = deferredItem.asItem();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_BUCKLE)
                    .pattern("B B")
                    .pattern(" B ")
                    .pattern("S S")
                    .define('B', Ingredient.of(Items.B))
                    .define('S', Ingredient.of(Items.STRING))
                    .unlockedBy(FabricRecipeProvider.getHasName(ModItems.GOLD_BUCKLE), FabricRecipeProvider.has(ModItems.GOLD_BUCKLE))
                    .save(recipeOutput);
        }*/
    }

    private static void hatBand(DeferredItem item, Item dyeItem, RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item)
                .requires(ModItems.HAT_BAND)
                .requires(dyeItem)
                .group("hat_band_colors")
                .unlockedBy(FabricRecipeProvider.getHasName(item), FabricRecipeProvider.has(item))
                .save(output);
    }
}

