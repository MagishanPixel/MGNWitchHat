package io.github.magishanpixel.mgn_witch_hat.datagen;

import io.github.magishanpixel.mgn_witch_hat.datagen.gen.ModItemTagsProvider;
import io.github.magishanpixel.mgn_witch_hat.datagen.gen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MGNWitchHatDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModItemTagsProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
