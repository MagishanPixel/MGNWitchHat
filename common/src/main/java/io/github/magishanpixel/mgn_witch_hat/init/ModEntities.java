package io.github.magishanpixel.mgn_witch_hat.init;

import io.github.magishanpixel.mgn_witch_hat.entity.WitchHatDisplayEntity;
import net.blay09.mods.balm.world.entity.BalmEntityTypeRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {
    public static Holder<EntityType<WitchHatDisplayEntity>> WITCH_HAT_DISPLAY;

    public static void init(BalmEntityTypeRegistrar reg) {
        WITCH_HAT_DISPLAY = reg.register("witch_hat_display", () -> EntityType.Builder.<WitchHatDisplayEntity>of(
                        WitchHatDisplayEntity::new, MobCategory.MISC)
                .sized(0.9f, 0.5f)
        ).asHolder();
    }
}
