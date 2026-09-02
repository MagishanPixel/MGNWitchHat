package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.item.recipe.WitchHatCustomRecipe;
import net.blay09.mods.balm.world.item.crafting.BalmRecipeTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ModCustomRecipes {
    public static Holder<RecipeSerializer<WitchHatCustomRecipe>> WITCH_HAT_SERIALIZER;

    public static void init(BalmRecipeTypeRegistrar reg) {
        WITCH_HAT_SERIALIZER = reg.registerSerializer("witch_hat_serializer", location -> new SimpleCraftingRecipeSerializer<>(WitchHatCustomRecipe::new)).asHolder();
    }
}
