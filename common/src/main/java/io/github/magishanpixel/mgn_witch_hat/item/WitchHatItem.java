package io.github.magishanpixel.mgn_witch_hat.item;

import io.github.magishanpixel.mgn_witch_hat.init.ModDataComponents;
import io.github.magishanpixel.mgn_witch_hat.misc.BuckleType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class WitchHatItem extends Item implements Equipable {
    public WitchHatItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> c, TooltipFlag tooltipFlag) {
        DyeColor dyeHat = stack.has(ModDataComponents.WITCH_HAT_COLOR.value()) ? stack.get(ModDataComponents.WITCH_HAT_COLOR.value()) : null;
        BuckleType buckleType = stack.has(ModDataComponents.BUCKLE_TYPE.value()) ? stack.get(ModDataComponents.BUCKLE_TYPE.value()) : null;

        if (dyeHat != null) {
            Component col = Component.literal(dyeHat.getSerializedName());
            c.add(col);
        }

        if (buckleType != null) {
            Component col = Component.literal(buckleType.getSerializedName());

            c.add(col);
        }

    }


    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }
}
