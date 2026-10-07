package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.MGNConstants;
import io.github.magishanpixel.mgn_witch_hat.client.models.*;
import io.github.magishanpixel.mgn_witch_hat.client.models.decors.*;
import net.blay09.mods.balm.client.model.geom.BalmModelLayerRegistrar;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModModelLayers {
    public static ModelLayerLocation WITCH_HAT = create("witch_hat");
    public static ModelLayerLocation ROBE = create("hat_band");
    public static ModelLayerLocation BUCKLE = create("buckle");
    public static ModelLayerLocation RIBBON = create("ribbon");
    public static ModelLayerLocation CANDLES = create("candles");
    public static ModelLayerLocation FEATHER = create("feather");

    public static ModelLayerLocation SHORT_BRIM = create("short_brim");
    public static ModelLayerLocation WIDE_BRIM = create("wide_brim");

    public static ModelLayerLocation PUMPKIN = create("pumpkin");

    public static ModelLayerLocation ANTLER = create("antler");
    public static ModelLayerLocation HORN = create("horn");

    public static void init(BalmModelLayerRegistrar layer) {
        layer.register(WITCH_HAT.getModel(), WitchHatModel::createBodyLayer);
        layer.register(ROBE.getModel(), HatBandModel::createBodyLayer);
        layer.register(BUCKLE.getModel(), BuckleModel::createBodyLayer);
        layer.register(RIBBON.getModel(), RibbonModel::createBodyLayer);
        layer.register(CANDLES.getModel(), CandlesModel::createBodyLayer);
        layer.register(FEATHER.getModel(), FeatherModel::createBodyLayer);
        layer.register(SHORT_BRIM.getModel(), ShortBrimModel::createBodyLayer);
        layer.register(WIDE_BRIM.getModel(), WideBrimModel::createBodyLayer);
        layer.register(PUMPKIN.getModel(), PumpkinDecorModel::createBodyLayer);
        layer.register(ANTLER.getModel(), AntlerModel::createBodyLayer);
        layer.register(HORN.getModel(), HornModel::createBodyLayer);
    }

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(MGNConstants.newId(name), "main");
    }
}
