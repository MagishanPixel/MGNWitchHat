package io.github.magishanpixel.mgn_witch_hat;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;

public class MGNWitchHat_FABRIC implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MGNWitchHat.init();
        Balm.initializeMod(MGNConstants.MOD_ID, EmptyLoadContext.INSTANCE, new MGNWitchHat());
    }
}
