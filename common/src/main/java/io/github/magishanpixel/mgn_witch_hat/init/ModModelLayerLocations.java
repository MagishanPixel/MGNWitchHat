package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.BuckleModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.RobeModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.WitchHatModel;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayerLocations {
    public static ModelLayerLocation WITCH_HAT = create("witch_hat");
    public static ModelLayerLocation ROBE = create("robe");
    public static ModelLayerLocation BUCKLE = create("buckle");

    public static void init(BalmModelLayerRegistrar layer) {
        layer.register(WITCH_HAT.getModel(), WitchHatModel::createBodyLayer);
        layer.register(ROBE.getModel(), RobeModel::createBodyLayer);
        layer.register(BUCKLE.getModel(), BuckleModel::createBodyLayer);
    }

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(MGNConstants.newId(name), "main");
    }
}
