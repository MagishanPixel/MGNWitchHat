package io.github.magishanpixel.mgn_witch_hat;

import io.github.magishanpixel.mgn_witch_hat.init.ModCustomRecipes;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.api.command.BalmCommands;
import net.blay09.mods.balm.api.module.BalmModule;
import net.blay09.mods.balm.core.component.BalmDataComponentTypeRegistrar;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.crafting.BalmRecipeTypeRegistrar;
import net.minecraft.resources.ResourceLocation;

public class MGNWitchHat implements BalmModule {
    public static void init() {
        MGNConstants.LOG.info("COMMON INIT");
    }

    @Override
    public ResourceLocation getId() {
        return MGNConstants.newId("common");
    }

    @Override
    public void registerItems(BalmItemRegistrar items) {
        ModItems.init(items);
    }

    @Override
    public void registerDataComponentTypes(BalmDataComponentTypeRegistrar dataComponentTypes) {
        ModDataComponents.init(dataComponentTypes);
    }

    @Override
    public void registerRecipeTypes(BalmRecipeTypeRegistrar recipeTypes) {
        ModCustomRecipes.init(recipeTypes);
    }

    @Override
    public void registerCreativeModeTabs(BalmCreativeModeTabRegistrar creativeModeTabs) {
        ModItems.creativeTabInit(creativeModeTabs);
    }
}