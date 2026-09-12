package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.*;
import io.github.magishanpixel.mgn_witch_hat.client.models.decors.*;
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
    public static final ResourceLocation CREEPER_HEAD = id("creeper_head");
    public static final ResourceLocation ZOMBIE_HEAD = id("zombie_head");
    public static final ResourceLocation RIBBON = id("ribbon");
    public static final ResourceLocation CANDLES = id("candles");
    public static final ResourceLocation FEATHER = id("feather");
    public static final ResourceLocation SHORT_BRIM = id("short_brim");
    public static final ResourceLocation WIDE_BRIM = id("wide_brim");
    public static final ResourceLocation PUMPKIN = id("pumpkin");

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
        m.put(RIBBON, new RibbonModel<>(context.bakeLayer(ModModelLayers.RIBBON)));

        m.put(CANDLES, new CandlesModel<>(context.bakeLayer(ModModelLayers.CANDLES)));
        m.put(FEATHER, new FeatherModel<>(context.bakeLayer(ModModelLayers.FEATHER)));

        m.put(SHORT_BRIM, new ShortBrimModel<>(context.bakeLayer(ModModelLayers.SHORT_BRIM)));
        m.put(WIDE_BRIM, new ShortBrimModel<>(context.bakeLayer(ModModelLayers.WIDE_BRIM)));
        m.put(PUMPKIN, new PumpkinDecorModel<>(context.bakeLayer(ModModelLayers.PUMPKIN)));

        m.put(ZOMBIE_HEAD, new SkullModel(context.bakeLayer(ModelLayers.ZOMBIE_HEAD)));
        m.put(CREEPER_HEAD, new SkullModel(context.bakeLayer(ModelLayers.CREEPER_HEAD)));

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
