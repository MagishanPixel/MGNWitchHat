package io.github.magishanpixel.mgn_witch_hat.item.recipe;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModCustomRecipes;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.item.ColoredItem;
import io.github.magishanpixel.mgn_witch_hat.misc.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
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
        boolean hasWool = false;

        List<DecorType> decorList = new ArrayList<>();
        boolean canCraft = false;

        Map<Integer,ItemStack> catchedStack = new HashMap<>();
        int centerSlot = -1;

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

                    centerSlot = i;
                } else {
                    catchedStack.put(i, inputStack);
                }
            }

        }

        for (Map.Entry<Integer, ItemStack> entry : catchedStack.entrySet()) {
            ItemStack inputStack = entry.getValue();
            int slot = entry.getKey();
            if (!inputStack.isEmpty()) {
                Item item = inputStack.getItem();
                if (inputStack.is(MGNConstants.ItemTags.BUCKLE)) {
                    if (!buckleStack.isEmpty()) {
                        return false;
                    }

                    buckleStack = inputStack;
                    canCraft = true;
                } else if (item instanceof DyeItem) {
                    if (!dyeStack.isEmpty()) {
                        return false;
                    }

                    dyeStack = inputStack;
                    canCraft = true;
                } else if (inputStack.is(MGNConstants.ItemTags.HAT_BAND)) {
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

                        boolean canDeco = !deco.isSided();

                        if (deco.isSided()) {
                            canDeco = (slot == centerSlot - 1) || (slot == centerSlot + 1) || (deco.allowBack() && (slot == centerSlot + input.width()));
                        }

                        if (!canDeco) {
                            return false;
                        }

                        decorList.add(deco);
                        canCraft = true;
                    }
                } else if (inputStack.is(ItemTags.WOOL)) {
                    if (hasWool || targStack.get(ModDataComponents.BRIM_TYPE.value()) == BrimType.WIDE) return false;
                    hasWool = true;
                    canCraft = true;
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
        BuckleType buckleType = null;
        ColoredItem bandItem = null;
        ItemStack targStack = ItemStack.EMPTY;
        Map<DecorType, DataDecor> prevDecors = new HashMap<>();
        Map<DecorType, DataDecor> decorList = new HashMap<>();
        boolean hasWool = false;
        boolean canCraft = false;

        Map<Integer,ItemStack> catchedStack = new HashMap<>();

        int centerSlot = -1;

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

                    centerSlot = i;
                } else {
                    catchedStack.put(i,inputStack);
                }
            }
        }

        for (Map.Entry<Integer, ItemStack> entry : catchedStack.entrySet()) {
            ItemStack inputStack = entry.getValue();
            int slot = entry.getKey();
            if (!inputStack.isEmpty()) {
                Item item = inputStack.getItem();
                if (item instanceof DyeItem) {
                    if (dyeItem != null) {
                        return ItemStack.EMPTY;
                    }
                    dyeItem = (DyeItem) item;
                    canCraft = true;

                } else if (inputStack.is(MGNConstants.ItemTags.BUCKLE)) {
                    if (buckleType != null) {
                        return ItemStack.EMPTY;
                    }

                    buckleType = BuckleType.getType(inputStack);
                    canCraft = true;
                } else if (inputStack.is(MGNConstants.ItemTags.HAT_BAND)) {

                    if (bandItem != null) {
                        return ItemStack.EMPTY;
                    }

                    bandItem = (ColoredItem) item;
                    canCraft = true;
                } else if (inputStack.is(MGNConstants.ItemTags.WITCH_HAT_DECOR)) {
                    DecorType deco = DecorType.getType(inputStack);

                    if (deco != null) {
                        if (prevDecors.containsKey(deco)) {
                            return ItemStack.EMPTY;
                        }

                        DecorPlacement placement = DecorPlacement.REGULAR;

                        if (deco.isSided()) {
                            if (slot == centerSlot + input.width()) {
                                if (!deco.allowBack()) {
                                    return ItemStack.EMPTY;
                                }
                                placement = DecorPlacement.BACK;
                            } else if (slot == centerSlot + 1) {
                                placement = DecorPlacement.RIGHT;
                            } else if (slot != centerSlot - 1) {
                                return ItemStack.EMPTY;
                            }
                        }

                        decorList.put(deco, new DataDecor(inputStack.copy(), placement));
                        canCraft = true;
                    }
                } else if (inputStack.is(ItemTags.WOOL)) {
                    if (hasWool || targStack.get(ModDataComponents.BRIM_TYPE.value()) == BrimType.WIDE) return ItemStack.EMPTY;
                    hasWool = true;
                    canCraft = true;
                }else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if (canCraft && !targStack.isEmpty()) {
            if (dyeItem != null) {
                targStack.set(ModDataComponents.WITCH_HAT_COLOR.value(), dyeItem.getDyeColor());
            }

            if (buckleType != null) {
                targStack.set(ModDataComponents.BUCKLE_TYPE.value(), buckleType);
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

            if (hasWool) {
                targStack.set(ModDataComponents.BRIM_TYPE.value(), BrimType.WIDE);
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
