package io.github.magishanpixel.mgn_witch_hat.client;

import com.google.common.collect.ImmutableMap;
import io.github.magishanpixel.mgn_witch_hat.client.models.BuckleModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.RobeModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.WitchHatModel;
import io.github.magishanpixel.mgn_witch_hat.init.ModModelLayerLocations;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HatBakedModels {
    private static ImmutableMap<ModelType, Model> HAT_MAP = null;

    public enum ModelType {
        HAT,
        ROBE,
        BUCKLE
    }

    public static boolean isAvail() {
        return HAT_MAP != null;
    }

    public static void bakeModels(EntityRendererProvider.Context context) {
        ImmutableMap.Builder<ModelType, Model> m = ImmutableMap.builder();

        m.put(ModelType.HAT, new WitchHatModel<>(context.bakeLayer(ModModelLayerLocations.WITCH_HAT)));
        m.put(ModelType.ROBE, new RobeModel<>(context.bakeLayer(ModModelLayerLocations.ROBE)));
        m.put(ModelType.BUCKLE, new BuckleModel<>(context.bakeLayer(ModModelLayerLocations.BUCKLE)));

        HAT_MAP = m.build();
    }

    public static Model getModel(ModelType v) {
        if (HAT_MAP != null) {
            return HAT_MAP.get(v);
        }

        return null;
    }
}
