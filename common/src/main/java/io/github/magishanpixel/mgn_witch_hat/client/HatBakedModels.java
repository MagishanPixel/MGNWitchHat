package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.*;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayers;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HatBakedModels {
    public static final ResourceLocation HAT = id("hat");
    public static final ResourceLocation HAT_BAND = id("hat_band");
    public static final ResourceLocation BUCKLE = id("buckle");
    public static final ResourceLocation SKULL = id("skull");
    public static final ResourceLocation MOSS_COVERED = id("moss_covered");
    public static final ResourceLocation RIBBON = id("ribbon");
    public static final ResourceLocation CANDLES = id("candles");

    private static ImmutableMap<ResourceLocation, Model> MODEL_MAP = null;

    public static boolean isAvail() {
        return MODEL_MAP != null;
    }

    public static void bakeModels(EntityRendererProvider.Context context) {
        ImmutableMap.Builder<ResourceLocation, Model> m = ImmutableMap.builder();

        m.put(HAT, new WitchHatModel<>(context.bakeLayer(ModModelLayers.WITCH_HAT)));
        m.put(HAT_BAND, new HatBandModel<>(context.bakeLayer(ModModelLayers.ROBE)));
        m.put(BUCKLE, new BuckleModel<>(context.bakeLayer(ModModelLayers.BUCKLE)));

        m.put(SKULL, new SkullModel(context.bakeLayer(ModelLayers.SKELETON_SKULL)));

        m.put(MOSS_COVERED, new MossCoveredModel<>(context.bakeLayer(ModModelLayers.MOSS_COVERED)));
        m.put(RIBBON, new RibbonModel<>(context.bakeLayer(ModModelLayers.RIBBON)));

        m.put(CANDLES, new CandlesModel<>(context.bakeLayer(ModModelLayers.CANDLES)));

        MODEL_MAP = m.build();
    }

    private static ResourceLocation id(String str) {
        return MGNConstants.newId(str);
    }

    public static Model getModel(ResourceLocation v) {
        if (MODEL_MAP != null) {
            return MODEL_MAP.get(v);
        }

        return null;
    }
}
