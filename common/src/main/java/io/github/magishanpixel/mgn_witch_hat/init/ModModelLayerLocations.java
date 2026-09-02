package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.BuckleModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.HatBandModel;
import io.github.magishanpixel.mgn_witch_hat.client.models.WitchHatModel;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayerLocations {
    public static ModelLayerLocation WITCH_HAT = create("witch_hat");
    public static ModelLayerLocation ROBE = create("hat_band");
    public static ModelLayerLocation BUCKLE = create("buckle");

    public static void init(BalmModelLayerRegistrar layer) {
        layer.register(WITCH_HAT.getModel(), WitchHatModel::createBodyLayer);
        layer.register(ROBE.getModel(), HatBandModel::createBodyLayer);
        layer.register(BUCKLE.getModel(), BuckleModel::createBodyLayer);
    }

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(MGNConstants.newId(name), "main");
    }
}
