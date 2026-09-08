package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.*;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    public static ModelLayerLocation WITCH_HAT = create("witch_hat");
    public static ModelLayerLocation ROBE = create("hat_band");
    public static ModelLayerLocation BUCKLE = create("buckle");
    public static ModelLayerLocation MOSS_COVERED = create("moss_covered");
    public static ModelLayerLocation RIBBON = create("ribbon");
    public static ModelLayerLocation CANDLES = create("candles");
    public static ModelLayerLocation FEATHER = create("feather");

    public static void init(BalmModelLayerRegistrar layer) {
        layer.register(WITCH_HAT.getModel(), WitchHatModel::createBodyLayer);
        layer.register(ROBE.getModel(), HatBandModel::createBodyLayer);
        layer.register(BUCKLE.getModel(), BuckleModel::createBodyLayer);
        layer.register(MOSS_COVERED.getModel(), MossCoveredModel::createBodyLayer);
        layer.register(RIBBON.getModel(), RibbonModel::createBodyLayer);
        layer.register(CANDLES.getModel(), CandlesModel::createBodyLayer);
        layer.register(FEATHER.getModel(), FeatherModel::createBodyLayer);
    }

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(MGNConstants.newId(name), "main");
    }
}
