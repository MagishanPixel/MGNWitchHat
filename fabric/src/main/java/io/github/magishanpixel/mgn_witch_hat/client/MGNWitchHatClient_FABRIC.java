package io.github.magishanpixel.mgn_witch_hat.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.WitchHatRenderer;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.mixin.object.builder.client.ModelPredicateProviderRegistryAccessor;
import net.fabricmc.fabric.mixin.object.builder.client.ModelPredicateProviderRegistrySpecificAccessor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MGNWitchHatClient_FABRIC implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MGNWitchHatClient.init();
        BalmClient.initializeMod(MGNConstants.MOD_ID, EmptyLoadContext.INSTANCE, new MGNWitchHatClient());

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            HatBakedModels.bakeModels(context);
        });


        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.WITCH_HAT, new BuiltinItemRendererRegistry.DynamicItemRenderer() {
            @Override
            public void render(ItemStack stack, ItemDisplayContext mode, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
                WitchHatRenderer.renderAsItem(stack, poseStack, buffer, light, overlay);
            }
        });

        ModelPredicateProviderRegistrySpecificAccessor.callRegister(ModItems.WITCH_CANDLE.asItem(), MGNConstants.newId("candle_lit"), (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponents.CANDLE_LIT.value()) ? 1f : 0f);

        WitchHatRenderer.init();
    }
}
