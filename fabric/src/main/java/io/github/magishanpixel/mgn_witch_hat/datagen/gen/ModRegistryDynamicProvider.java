package io.github.magishanpixel.mgn_witch_hat.datagen.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDynamicProvider extends FabricDynamicRegistryProvider {
    public ModRegistryDynamicProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {

    }

    @Override
    public String getName() {
        return "mgn_dynamic_registry";
    }
}
