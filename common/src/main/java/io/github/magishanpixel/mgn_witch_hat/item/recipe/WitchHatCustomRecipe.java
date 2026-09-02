package io.github.magishanpixel.mgn_witch_hat.item.recipe;

import io.github.magishanpixel.mgn_witch_hat.init.ModCustomRecipes;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.item.BuckleItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

// Copied from ArmorDyeRecipe.class btwww
public class WitchHatCustomRecipe extends CustomRecipe {
    public WitchHatCustomRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack targStack = ItemStack.EMPTY;
        ItemStack buckleStack = ItemStack.EMPTY;
        ItemStack dyeStack = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack inputStack = input.getItem(i);
            if (!inputStack.isEmpty()) {

                Item item = inputStack.getItem();

                if (inputStack.is(ModItems.WITCH_HAT.value())) {
                    if (!targStack.isEmpty()) {
                        return false;
                    }
                    targStack = inputStack;
                } else if (item instanceof BuckleItem) {
                    if (!buckleStack.isEmpty()) {
                        return false;
                    }

                    buckleStack = inputStack;
                } else if (item instanceof DyeItem)  {
                    if (!dyeStack.isEmpty()) {
                        return false;
                    }
                    dyeStack = inputStack;
                } else {
                    return false;
                }
            }

            if ((!dyeStack.isEmpty() || !buckleStack.isEmpty()) && !targStack.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        DyeItem dyeItem = null;
        BuckleItem buckleItem = null;
        ItemStack targStack = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack inputStack = input.getItem(i);
            if (!inputStack.isEmpty()) {
                Item item = inputStack.getItem();
                if (inputStack.is(ModItems.WITCH_HAT.value())) {
                    if (!targStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    targStack = inputStack.copy();
                } else if (item instanceof DyeItem) {
                    if (dyeItem != null) {
                        return ItemStack.EMPTY;
                    }
                    dyeItem = (DyeItem) item;

                } else if (item instanceof BuckleItem) {
                    if (buckleItem != null) {
                        return ItemStack.EMPTY;
                    }

                    buckleItem = (BuckleItem) item;
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if ((dyeItem != null || buckleItem != null) && !targStack.isEmpty()) {
            if (dyeItem != null) {
                targStack.set(ModDataComponents.WITCH_HAT_COLOR.value(), dyeItem.getDyeColor());
            }

            if (buckleItem != null) {
                targStack.set(ModDataComponents.BUCKLE_TYPE.value(), buckleItem.getBuckleType());
            }

            return targStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModCustomRecipes.WITCH_HAT_SERIALIZER.value();
    }
}
