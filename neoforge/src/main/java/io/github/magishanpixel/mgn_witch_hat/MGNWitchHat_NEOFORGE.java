package io.github.magishanpixel.mgn_witch_hat;


import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import io.github.magishanpixel.mgn_witch_hat.misc.DecorType;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(MGNConstants.MOD_ID)
public class MGNWitchHat_NEOFORGE {

    public MGNWitchHat_NEOFORGE(IEventBus eventBus) {
        MGNWitchHat.init();
        final var loadContext = new NeoForgeLoadContext(eventBus);
        Balm.initializeMod(MGNConstants.MOD_ID,loadContext,new MGNWitchHat());

        eventBus.addListener(this::addToCreativeTab);
        eventBus.addListener(this::commonInit);
    }

    public void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().location() == CreativeModeTabs.TOOLS_AND_UTILITIES.location()) {
            ModItems.addToCreativeTab(event::accept);
        }
    }

    public void commonInit(FMLCommonSetupEvent event) {
        event.enqueueWork(DecorType::mapInit);
    }

}