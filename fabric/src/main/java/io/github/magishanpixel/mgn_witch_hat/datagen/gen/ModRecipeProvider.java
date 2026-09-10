package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WITCH_HAT)
                .pattern("S#S")
                .pattern("#B#")
                .pattern("###")
                .define('#', Ingredient.of(ItemTags.WOOL))
                .define('B', Items.AMETHYST_SHARD)
                .define('S', ModItems.WITCH_CRYSTAL_DUST)
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.WITCH_HAT), FabricRecipeProvider.has(ModItems.WITCH_HAT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HAT_BAND.asItem())
                .pattern("  B")
                .pattern("BBB")
                .pattern("B  ")
                .define('B', Ingredient.of(ItemTags.WOOL))
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.HAT_BAND), FabricRecipeProvider.has(ModItems.HAT_BAND))
                .save(recipeOutput);

        AutoRecipe hatBand = (deferredItem, dyeItem, v) -> {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, deferredItem)
                    .requires(ModItems.HAT_BAND)
                    .requires(dyeItem)
                    .group("hat_band_colors")
                    .unlockedBy(FabricRecipeProvider.getHasName(deferredItem), FabricRecipeProvider.has(deferredItem))
                    .save(v);
        };

        hatBand.create(ModItems.WHITE_HAT_BAND, Items.WHITE_DYE, recipeOutput);
        hatBand.create(ModItems.ORANGE_HAT_BAND, Items.ORANGE_DYE, recipeOutput);
        hatBand.create(ModItems.MAGENTA_HAT_BAND, Items.MAGENTA_DYE, recipeOutput);
        hatBand.create(ModItems.LIGHT_BLUE_HAT_BAND, Items.LIGHT_BLUE_DYE, recipeOutput);
        hatBand.create(ModItems.YELLOW_HAT_BAND, Items.YELLOW_DYE, recipeOutput);
        hatBand.create(ModItems.LIME_HAT_BAND, Items.LIME_DYE, recipeOutput);
        hatBand.create(ModItems.PINK_HAT_BAND, Items.PINK_DYE, recipeOutput);
        hatBand.create(ModItems.GRAY_HAT_BAND, Items.GRAY_DYE, recipeOutput);
        hatBand.create(ModItems.CYAN_HAT_BAND, Items.CYAN_DYE, recipeOutput);
        hatBand.create(ModItems.PURPLE_HAT_BAND, Items.PURPLE_DYE, recipeOutput);
        hatBand.create(ModItems.BLUE_HAT_BAND, Items.BLUE_DYE, recipeOutput);
        hatBand.create(ModItems.BROWN_HAT_BAND, Items.BROWN_DYE, recipeOutput);
        hatBand.create(ModItems.GREEN_HAT_BAND, Items.GREEN_DYE, recipeOutput);
        hatBand.create(ModItems.RED_HAT_BAND, Items.RED_DYE, recipeOutput);
        hatBand.create(ModItems.BLACK_HAT_BAND, Items.BLACK_DYE, recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WITCH_CRYSTAL_DUST)
                .requires(Items.GUNPOWDER)
                .requires(Items.STRING)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.WITCH_CRYSTAL_DUST), FabricRecipeProvider.has(ModItems.WITCH_CRYSTAL_DUST))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAVEN_FEATHER)
                .requires(Items.FEATHER)
                .requires(ModItems.WITCH_CRYSTAL_DUST)
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.RAVEN_FEATHER), FabricRecipeProvider.has(ModItems.RAVEN_FEATHER))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WITCH_CANDLE)
                .requires(ItemTags.CANDLES)
                .requires(ModItems.WITCH_CRYSTAL_DUST)
                .unlockedBy(FabricRecipeProvider.getHasName(ModItems.WITCH_CANDLE), FabricRecipeProvider.has(ModItems.WITCH_CANDLE))
                .save(recipeOutput);

        AutoRecipe ribbonRecipe = (deferredItem, wool, v) -> {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, deferredItem)
                    .pattern(" W ")
                    .pattern("WDW")
                    .pattern("S S")
                    .define('W', wool)
                    .define('D', ModItems.WITCH_CRYSTAL_DUST.value())
                    .define('S', Items.STRING)
                    .group("witch_ribbon_colors")
                    .unlockedBy(FabricRecipeProvider.getHasName(deferredItem), FabricRecipeProvider.has(deferredItem))
                    .save(recipeOutput);
        };

        ribbonRecipe.create(ModItems.WHITE_RIBBON, Items.WHITE_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.ORANGE_RIBBON, Items.ORANGE_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.MAGENTA_RIBBON, Items.MAGENTA_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.LIGHT_BLUE_RIBBON, Items.LIGHT_BLUE_DYE, recipeOutput);
        ribbonRecipe.create(ModItems.YELLOW_RIBBON, Items.YELLOW_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.LIME_RIBBON, Items.LIME_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.PINK_RIBBON, Items.PINK_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.GRAY_RIBBON, Items.GRAY_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.LIGHT_GRAY_RIBBON, Items.LIGHT_GRAY_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.CYAN_RIBBON, Items.CYAN_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.PURPLE_RIBBON, Items.PURPLE_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.BLUE_RIBBON, Items.BLUE_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.BROWN_RIBBON, Items.BROWN_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.GREEN_RIBBON, Items.GREEN_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.RED_RIBBON, Items.RED_WOOL, recipeOutput);
        ribbonRecipe.create(ModItems.BLACK_RIBBON, Items.BLACK_WOOL, recipeOutput);
    }

    private interface AutoRecipe {
        void create(DeferredItem deferredItem, Item targItem, RecipeOutput output);
    }

}

