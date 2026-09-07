package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import io.github.magishanpixel.mgn_witch_hat.client.models.*;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayers;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HatBakedModels {
    private static ImmutableMap<ModelType, Model> MODEL_MAP = null;

    public enum ModelType {
        HAT,
        HAT_BAND,
        BUCKLE,
        SKULL,
        WITHER_SKULL,
        MOSS_COVERED,
        RIBBON
    }

    public static boolean isAvail() {
        return MODEL_MAP != null;
    }

    public static void bakeModels(EntityRendererProvider.Context context) {
        ImmutableMap.Builder<ModelType, Model> m = ImmutableMap.builder();

        m.put(ModelType.HAT, new WitchHatModel<>(context.bakeLayer(ModModelLayers.WITCH_HAT)));
        m.put(ModelType.HAT_BAND, new HatBandModel<>(context.bakeLayer(ModModelLayers.ROBE)));
        m.put(ModelType.BUCKLE, new BuckleModel<>(context.bakeLayer(ModModelLayers.BUCKLE)));

        m.put(ModelType.SKULL, new SkullModel(context.bakeLayer(ModelLayers.SKELETON_SKULL)));

        m.put(ModelType.MOSS_COVERED, new MossCoveredModel<>(context.bakeLayer(ModModelLayers.MOSS_COVERED)));
        m.put(ModelType.RIBBON, new RibbonModel<>(context.bakeLayer(ModModelLayers.RIBBON)));

        MODEL_MAP = m.build();
    }

    public static Model getModel(ModelType v) {
        if (MODEL_MAP != null) {
            return MODEL_MAP.get(v);
        }

        return null;
    }
}
