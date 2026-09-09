package io.github.magishanpixel.mgn_witch_hat.client;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.decorrenderer.WitchHatRenderer;
import io.github.magishanpixel.mgn_witch_hat.client.renderer.WitchHatItemRenderer;
import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.init.ModItems;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = MGNConstants.MOD_ID, dist = Dist.CLIENT)
public class MGNWitchHatClient_NEOFORGE {
    public MGNWitchHatClient_NEOFORGE(IEventBus modEventBus) {
        MGNWitchHatClient.init();
        final var loadContext = new NeoForgeLoadContext(modEventBus);
        BalmClient.initializeMod(MGNConstants.MOD_ID,loadContext,new MGNWitchHatClient());

        modEventBus.addListener(this::onAddLayers);
        modEventBus.addListener(this::clientInit);
        modEventBus.addListener(this::registerItemRenderer);
        //NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public void onAddLayers(EntityRenderersEvent.AddLayers event) {
        EntityRendererProvider.Context context = event.getContext();
        HatBakedModels.bakeModels(context);
    }

    public void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(WitchHatRenderer::init);
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.WITCH_CANDLE.asItem(), MGNConstants.newId("candle_lit"), (itemStack, clientLevel, livingEntity, i) -> itemStack.get(ModDataComponents.CANDLE_LIT.value()) ? 1f : 0f);
        });
    }

    public void registerItemRenderer(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new WitchHatItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        }, ModItems.WITCH_HAT);
    }



    public void registerCommands(RegisterClientCommandsEvent event) {
        LiteralArgumentBuilder<CommandSourceStack> command = LiteralArgumentBuilder.literal("reloadwitchhat");
        command.executes(context -> {
            WitchHatRenderer.init();
            return 1;
        });

        event.getDispatcher().register(command);
    }
}
