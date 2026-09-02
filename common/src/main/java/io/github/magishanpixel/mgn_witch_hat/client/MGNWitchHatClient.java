package io.github.magishanpixel.mgn_witch_hat.client;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayerLocations;
import net.blay09.mods.balm.api.client.module.BalmClientModule;
import net.blay09.mods.balm.client.color.item.BalmItemColorRegistrar;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.minecraft.resources.ResourceLocation;

public class MGNWitchHatClient implements BalmClientModule {
    public static void init() {
        MGNConstants.LOG.info("COMMON CLIENT INIT");
    }

    @Override
    public ResourceLocation getId() {
        return MGNConstants.newId("client");
    }

    @Override
    public void registerModelLayers(BalmModelLayerRegistrar modelLayers) {
        ModModelLayerLocations.init(modelLayers);
    }

    @Override
    public void registerItemColors(BalmItemColorRegistrar blockColors) {
        BalmClientModule.super.registerItemColors(blockColors);
    }
}
