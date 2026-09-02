package io.github.magishanpixel.mgn_witch_hat.client;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;

public class MGNWitchHatClient_FABRIC implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MGNWitchHatClient.init();
        BalmClient.initializeMod(MGNConstants.MOD_ID, EmptyLoadContext.INSTANCE, new MGNWitchHatClient());

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, livingEntityRenderer, registrationHelper, context) -> {
            HatBakedModels.bakeModels(context);
        });
    }
}
