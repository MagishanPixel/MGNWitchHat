package io.github.magishanpixel.mgn_witch_hat;

import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class MGNWitchHat_FABRIC implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MGNWitchHat.init();
        Balm.initializeMod(MGNConstants.MOD_ID, EmptyLoadContext.INSTANCE, new MGNWitchHat());

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            ModItems.addToCreativeTab(output::accept);
        });

    }
}
