package io.github.magishanpixel.mgn_witch_hat.client;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.renderer.WitchHatDisplayEntityRenderer;
import io.github.magishanpixel.mgn_witch_hat.client.tooltip.WitchHatTooltip;
import io.github.magishanpixel.mgn_witch_hat.entity.WitchHatDisplayEntity;
import io.github.magishanpixel.mgn_witch_hat.init.ModEntities;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayers;
import net.blay09.mods.balm.api.client.module.BalmClientModule;
import net.blay09.mods.balm.api.client.rendering.BalmRenderers;
import net.blay09.mods.balm.client.BalmClientTooltipComponentRegistrar;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.blay09.mods.balm.client.renderer.blockentity.BalmBlockEntityRendererRegistrar;
import net.blay09.mods.balm.client.renderer.chunk.BalmBlockRenderTypeRegistrar;
import net.blay09.mods.balm.client.renderer.entity.BalmEntityRendererRegistrar;
import net.blay09.mods.balm.server.packs.resources.BalmClientResourceReloadListenerRegistrar;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class MGNWitchHatClient implements BalmClientModule {
    public static void init() {}

    @Override
    public ResourceLocation getId() {
        return MGNConstants.newId("client");
    }

    @Override
    public void registerModelLayers(BalmModelLayerRegistrar modelLayers) {
        ModModelLayers.init(modelLayers);

    }

    @Override
    public void registerClientTooltipComponents(BalmClientTooltipComponentRegistrar reg) {
        reg.register(WitchHatTooltip.DisplayStacks.class, WitchHatTooltip::new);
    }


    @Override
    public void registerEntityRenderers(BalmEntityRendererRegistrar entityRenderers) {
        entityRenderers.register(ModEntities.WITCH_HAT_DISPLAY, WitchHatDisplayEntityRenderer::new);
    }
}
