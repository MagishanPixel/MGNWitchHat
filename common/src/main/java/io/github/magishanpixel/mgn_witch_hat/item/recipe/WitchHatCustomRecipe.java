package io.github.magishanpixel.mgn_witch_hat.item.recipe;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModCustomRecipes;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.item.BuckleItem;
import io.github.magishanpixel.mgn_witch_hat.item.HatBandItem;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WitchHatCustomRecipe extends CustomRecipe {
    public WitchHatCustomRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack targStack = ItemStack.EMPTY;
        ItemStack buckleStack = ItemStack.EMPTY;
        ItemStack dyeStack = ItemStack.EMPTY;
        ItemStack bandStack = ItemStack.EMPTY;

        List<DecorType> decorList = new ArrayList<>();
        boolean canCraft = false;

        List<ItemStack> catchedStack = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack inputStack = input.getItem(i);
            if (!inputStack.isEmpty()) {
                if (inputStack.is(ModItems.WITCH_HAT.value())) {
                    if (!targStack.isEmpty()) {
                        return false;
                    }
                    targStack = inputStack;

                    if (targStack.has(ModDataComponents.DECOR_TYPES.value())) {
                        decorList.addAll(targStack.get(ModDataComponents.DECOR_TYPES.value()).keySet());
                    }
                } else {
                    catchedStack.add(inputStack);
                }
            }

        }

        for (ItemStack inputStack : catchedStack) {
            if (!inputStack.isEmpty()) {
                Item item = inputStack.getItem();
                if (item instanceof BuckleItem) {
                    if (!buckleStack.isEmpty()) {
                        return false;
                    }

                    buckleStack = inputStack;
                    canCraft = true;
                } else if (item instanceof DyeItem)  {
                    if (!dyeStack.isEmpty()) {
                        return false;
                    }
                    dyeStack = inputStack;
                } else if (item instanceof HatBandItem) {
                    if (!bandStack.isEmpty()) {
                        return false;
                    }
                    bandStack = inputStack;
                    canCraft = true;
                } else if (inputStack.is(MGNConstants.ItemTags.WITCH_HAT_DECOR)) {
                    DecorType deco = DecorType.getType(inputStack);

                    if (deco != null) {
                        if (decorList.contains(deco)) {
                            return false;
                        }
                        decorList.add(deco);
                        canCraft = true;
                    }
                } else {
                    return false;
                }
            }
        }

        return canCraft && !targStack.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        DyeItem dyeItem = null;
        BuckleItem buckleItem = null;
        HatBandItem bandItem = null;
        ItemStack targStack = ItemStack.EMPTY;
        Map<DecorType, ItemStack> prevDecors = new HashMap<>();
        Map<DecorType, ItemStack> decorList = new HashMap<>();
        boolean canCraft = false;

        List<ItemStack> catchedStack = new ArrayList<>();

        for (int i = 0; i < input.size(); ++i) {
            ItemStack inputStack = input.getItem(i);
            if (!inputStack.isEmpty()) {
                if (inputStack.is(ModItems.WITCH_HAT.value())) {
                    if (!targStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    targStack = inputStack.copy();

                    if (targStack.has(ModDataComponents.DECOR_TYPES.value())) {
                        prevDecors = targStack.get(ModDataComponents.DECOR_TYPES.value());
                    }
                } else {
                    catchedStack.add(inputStack);
                }
            }
        }

        for (ItemStack inputStack : catchedStack) {
            if (!inputStack.isEmpty()) {
                Item item = inputStack.getItem();
                if (item instanceof DyeItem) {
                    if (dyeItem != null) {
                        return ItemStack.EMPTY;
                    }
                    dyeItem = (DyeItem) item;
                    canCraft = true;

                } else if (item instanceof BuckleItem) {
                    if (buckleItem != null) {
                        return ItemStack.EMPTY;
                    }

                    buckleItem = (BuckleItem) item;
                    canCraft = true;
                } else if (item instanceof HatBandItem) {
                    if (bandItem != null) {
                        return ItemStack.EMPTY;
                    }

                    bandItem = (HatBandItem) item;
                    canCraft = true;
                } else if (inputStack.is(MGNConstants.ItemTags.WITCH_HAT_DECOR)) {
                    DecorType deco = DecorType.getType(inputStack);

                    if (deco != null) {
                        if (prevDecors.containsKey(deco)) {
                            return ItemStack.EMPTY;
                        }
                        decorList.put(deco, deco.canStoreValue() ? inputStack.copy() : ItemStack.EMPTY);
                        canCraft = true;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if (canCraft && !targStack.isEmpty()) {
            if (dyeItem != null) {
                targStack.set(ModDataComponents.WITCH_HAT_COLOR.value(), dyeItem.getDyeColor());
            }

            if (buckleItem != null) {
                targStack.set(ModDataComponents.BUCKLE_TYPE.value(), buckleItem.getBuckleType());
            }

            if (bandItem != null) {
                targStack.set(ModDataComponents.HAS_BAND.value(), true);

                if (bandItem.getDyeColor() != null) {
                    targStack.set(ModDataComponents.BAND_COLOR.value(), bandItem.getDyeColor());
                } else {
                    targStack.remove(ModDataComponents.BAND_COLOR.value());
                }
            }

            if (!decorList.isEmpty()) {
                decorList.putAll(prevDecors);
                targStack.set(ModDataComponents.DECOR_TYPES.value(), decorList);
            }

            MGNConstants.LOG.info(targStack.toString());

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
